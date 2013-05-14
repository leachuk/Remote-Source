package au.com.entitysolutions.taskflows.login.view;


import au.com.entitysolutions.taskflows.common.view.FacesUtil;

import au.com.entitysolutions.taskflows.common.view.UserBean;
import oracle.jbo.domain.Number;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;

import oracle.jbo.Row;

import weblogic.security.URLCallbackHandler;
import weblogic.security.services.Authentication;

import weblogic.servlet.security.ServletAuthentication;


public class LoginFlowBean {


    private static String SOURCE_CLASS =
        LoginFlowBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    private static String CHANGE_PASSWORD_OUTCOME = "changePassword";
    private static String ACCEPT_T_AND_C_OUTCOME = "acceptTAndC";
    private static String LOGIN_SUCCESS = "navigate_parent";
    protected static String CONFIRMATION = "confirmation";
    private static String FORGOT_USERNAME = "forgotUserName";
    private static String FORGOT_PASSWORD = "forgotPassword";

    private RichInputText userName;
    private RichInputText passwordField;

    private boolean changePasswordFlagged = false;
    private boolean termsAndConditionsFlagged = false;
    private RichInputText chgPwdCurrentPasswordField;
    private RichInputText chgPwdNewPasswordField;
    private RichInputText chgPwdConfNewPasswordField;
    private RichInputText fgtPwdEmailAddressField;
    private RichInputText fgtUserIdEmailAddressField;

    private String tempPwdStore = null;
    private RichInputText primaryEmailField;
    private RichInputText confPrimaryEmailField;


  /****** HELPER METHODS ********/
    public DCBindingContainer getBindings() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }

    public LoginFlowBean() {
        super();
    }

    /**
     * Action method for changePassword action
     * @return
     */
    public String changePassword() {
        LOGGER.entering(SOURCE_CLASS, "changePassword");

        String outcome = null;
        //Perform change password operations
        //if successful
        String oldPassword = (String)chgPwdCurrentPasswordField.getValue();
        String newPassword1 = (String)chgPwdNewPasswordField.getValue();
        String newPassword2 = (String)chgPwdConfNewPasswordField.getValue();
        String primaryEmail=(String)primaryEmailField.getValue();
        String confPrimaryEmail=(String)confPrimaryEmailField.getValue();
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (oldPassword == null || newPassword1 == null ||
            newPassword2 == null) {
            outcome = null;
        } else {
            if (!newPassword1.equals(newPassword2)) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Passwords do not match",
                                     "The two passwords that you entered do not match");
                context.addMessage(null, msg);
                outcome = null;
            } else if (oldPassword.equals(newPassword1)) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "New Password Error",
                                     "The new password should not be the same as your current password");
                context.addMessage(null, msg);
                outcome = null;
            }else if(!primaryEmail.equals(confPrimaryEmail)){
              confPrimaryEmailField.setValue(null);
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Primary Email Error",
                                     "The emails you have entered are not identical.");
                context.addMessage(confPrimaryEmailField.getClientId(context), msg);                
                return null;
            } else {
                BindingContainer bindings = getBindings();
                OperationBinding validateOperationBinding =
                    bindings.getOperationBinding("validatePasswordRules");
                Boolean result = (Boolean)validateOperationBinding.execute();
                if (result) {
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("changePassword");
                    result = (Boolean)operationBinding.execute();
                    
                    OperationBinding updateEmailBinding =
                        bindings.getOperationBinding("updateSysPrimaryEmail");
                    updateEmailBinding.getParamsMap().put("email",primaryEmail);
                    Boolean resultEmailUpdate = (Boolean)updateEmailBinding.execute();
                    
                    if (result && resultEmailUpdate) {
                        LOGGER.finest("Password Change Success");
                        LOGGER.finest("Email update success");
                        //Overwrite temporary password with new one
                        tempPwdStore = newPassword1;
                        outcome = CONFIRMATION;
                    } else {
                        LOGGER.finest("System Error");
                        FacesMessage msg =
                            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                             "Error updating system",
                                             "There was a system error when updating the new passwords. Please contact the administrator.");
                        context.addMessage(null, msg);
                        outcome = null;
                    }
                } else {
                    LOGGER.finest("Password Validation Error");
                    FacesMessage msg =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                         "Password Error",
                                         "Error changing the password.\r\n \r\nPlease ensure the current password is correct and the new password complies with the password rules and restrictions.");
                    context.addMessage(null, msg);
                    outcome = null;
                }
            }
        }
        LOGGER.exiting(SOURCE_CLASS, "changePassword");
        if (outcome == null) {
            //Reset the password fields
            this.chgPwdCurrentPasswordField.resetValue();
            this.chgPwdCurrentPasswordField.setValue(null);
            this.chgPwdNewPasswordField.resetValue();
            this.chgPwdNewPasswordField.setValue(null);
            this.chgPwdConfNewPasswordField.resetValue();
            this.chgPwdConfNewPasswordField.setValue(null);
        }
        return outcome;
    }

    /**
     * Managed bean method that authenticates the user.
     * @return
     */
    public String doLogin() {
        LOGGER.entering(SOURCE_CLASS, "doLogin");
        String user = (String)userName.getValue();
        tempPwdStore = (String)passwordField.getValue();
        HttpServletRequest req =
            (HttpServletRequest)ADFContext.getCurrent().getEnvironment().getRequest();
        HttpServletResponse res =
            (HttpServletResponse)ADFContext.getCurrent().getEnvironment().getResponse();

        String outcomeRule = null;
        int loginOutcome = loginInternal(user, tempPwdStore, req, res);

        if (loginOutcome == 0) {
            //Check for Password Reset Flag and redirect accordingly
            if (isChangePasswordFlagged()) {
                LOGGER.finest("Change Password Flagged");
                ServletAuthentication.logout(req);
                //User should not have access to the portal until password
                //change is effected
                outcomeRule = CHANGE_PASSWORD_OUTCOME;
            } else if (isTermsAndConditionsFlagged()) {
                LOGGER.finest("Terms and Conditions Flagged");
                //User should not have access to the portal until Terms and Conditions are accepted
                ServletAuthentication.logout(req);
                outcomeRule = ACCEPT_T_AND_C_OUTCOME;
            } else {
                System.out.println("TEST------Login successful");
                this.startUserSession(req);
                
                //Checking user roles
                for ( String role : ADFContext.getCurrent().getSecurityContext().getUserRoles()) {
                  System.out.println("User Role: " + role);
                }
                //end of user roles
                outcomeRule = LOGIN_SUCCESS;
            }
        } else if (loginOutcome == 1) {
            FacesMessage fMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Error",
                                 "Incorrect User ID or Password specified. Please try again.");
            FacesContext fCtx = FacesContext.getCurrentInstance();
            fCtx.addMessage(null, fMsg);
            outcomeRule = null;
        }
        LOGGER.exiting(SOURCE_CLASS, "doLogin");
        if (outcomeRule == null) {
            //Reset the password field
            this.passwordField.resetValue();
            this.passwordField.setValue(null);
        }
        return outcomeRule;
    }

    /**
     * Internal method that handles login
     * @param user
     * @param pwd
     * @param req
     * @param response
     * @return
     */
    private int loginInternal(String user, String pwd, HttpServletRequest req,
                              HttpServletResponse response) {
        LOGGER.entering(SOURCE_CLASS,"loginInternal");
        int loginOutcome = 1;
        try {
            //loginOutcome =
            //        ServletAuthentication.login(user, pwd, req, response);
            //ServletAuthentication.runAs(arg0, arg1);
            Subject subject =
                Authentication.login(new URLCallbackHandler(user, (pwd !=
                                                                   null) ?
                                                                  pwd.getBytes() :
                                                                  "".getBytes()));
            if (subject != null) {
                LOGGER.finest("Subject:" + subject);
                ServletAuthentication.runAs(subject, req);
                //String loginUrl = "/adfAuthentication?success_url=/faces/homepage";
                //RequestDispatcher dispatcher =  req.getRequestDispatcher(loginUrl);
                //dispatcher.forward(req, response);
                loginOutcome = 0;
            }
            UserBean userBean =
                (UserBean)FacesUtil.resolveExpression("#{userSessionBean}");
            userBean.setUserName(user);
            if(!getUserDetailsFromDatabase(userBean) && !"weblogic".equals(user))            
            {
                //No database details found, hence invalid user.
                //Except weblogic
                LOGGER.finest("No User Details found");
                loginOutcome = 1;
                ServletAuthentication.logout(req);
            }
        } catch (LoginException loginExc) {
            LOGGER.log(Level.WARNING, "Login Exception", loginExc);
            loginOutcome = 1;
        } catch (Exception exc) {
            LOGGER.log(Level.WARNING, "Exception", exc);
            loginOutcome = 1;
        }
        LOGGER.finest("loginOutcome:" + loginOutcome);
        LOGGER.entering(SOURCE_CLASS,"loginInternal");
        return loginOutcome;
    }

    /**
     * Get user details from database and set into the session bean
     * @param userBean
     * @throws Exception
     */
    private boolean getUserDetailsFromDatabase(UserBean userBean) throws Exception {
        LOGGER.entering(SOURCE_CLASS, "getUserDetailsFromDatabase");
        boolean isUserFound = false;
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("UserDetailsExecuteWithParams");
        Object obj = operationBinding.execute();
        DCIteratorBinding headerIteratorBinding =
            getBindings().findIteratorBinding("UserDetailsViewIterator");
        NavigatableRowIterator headerRowIterator =
            headerIteratorBinding.getNavigatableRowIterator();
        Row hdrRow = headerRowIterator.getCurrentRow();

        //TODO: what to do where users don't have record in database. weblogic or other admin users?
        if (hdrRow != null) {
            this.setTermsAndConditionsFlagged("Y".equals(hdrRow.getAttribute("ResetTermCondition")));
            this.setChangePasswordFlagged("Y".equals(hdrRow.getAttribute("ResetPassword")));

            userBean.setDisplayName((String)hdrRow.getAttribute("FirstName"));
            userBean.setSkin((String)hdrRow.getAttribute("PortalSkin"));
            userBean.setActive("Y".equals(hdrRow.getAttribute("IsActive")));
            userBean.setOrgId((Number)hdrRow.getAttribute("OrgId"));
            userBean.setPersonId((Number)hdrRow.getAttribute("PersonId"));
            userBean.setOrganisationName((String)hdrRow.getAttribute("BusinessGroupName"));
            isUserFound = true;
        } else {
            LOGGER.severe("User Details not found in database:" +
                          userBean.getUserName());
        }
        LOGGER.exiting(SOURCE_CLASS, "getUserDetailsFromDatabase");
        return isUserFound;
    }

    /**
     * Start User Session and setup userSessionBean
     */
    private void startUserSession(HttpServletRequest req) {
        ServletAuthentication.generateNewSessionID(req);
        boolean b = req.isUserInRole("TemporaryIProPortalUser");
        System.out.println("----------------------------" + b);
        /*
        PortalPreferences preferences = (PortalPreferences)ADFContext.getCurrent().getApplicationScope().get("preferenceBean");
        */
        //TODO: Set skin possibly from user details in the table.
    }

    /**
     * Check for the user flags from DB
     */
    private void checkUserFlags() {
        //Get details from DB
        setChangePasswordFlagged(false);
        setTermsAndConditionsFlagged(false);
    }

    public void setUserName(RichInputText userName) {
        this.userName = userName;
    }

    public RichInputText getUserName() {
        return userName;
    }

    public void setPasswordField(RichInputText password) {
        this.passwordField = password;
    }

    public RichInputText getPasswordField() {
        return passwordField;
    }

    public void setChangePasswordFlagged(boolean changePasswordFlagged) {
        this.changePasswordFlagged = changePasswordFlagged;
    }

    public boolean isChangePasswordFlagged() {
        return changePasswordFlagged;
    }

    public void setTermsAndConditionsFlagged(boolean termsAndConditionsFlagged) {
        this.termsAndConditionsFlagged = termsAndConditionsFlagged;
    }

    public boolean isTermsAndConditionsFlagged() {
        return termsAndConditionsFlagged;
    }

    public void setChgPwdCurrentPasswordField(RichInputText chgPwdCurrentPasswordField) {
        this.chgPwdCurrentPasswordField = chgPwdCurrentPasswordField;
    }

    public RichInputText getChgPwdCurrentPasswordField() {
        return chgPwdCurrentPasswordField;
    }

    public void setChgPwdNewPasswordField(RichInputText chgPwdNewPasswordField) {
        this.chgPwdNewPasswordField = chgPwdNewPasswordField;
    }

    public RichInputText getChgPwdNewPasswordField() {
        return chgPwdNewPasswordField;
    }

    public void setChgPwdConfNewPasswordField(RichInputText chgPwdConfNewPasswordField) {
        this.chgPwdConfNewPasswordField = chgPwdConfNewPasswordField;
    }

    public RichInputText getChgPwdConfNewPasswordField() {
        return chgPwdConfNewPasswordField;
    }


    /**
     * Convenience method used by forgot username and forgot password
     * @param emailAddress
     * @return
     */
    private boolean isValidEmailAddress(String emailAddress) {
        LOGGER.entering(SOURCE_CLASS, "isValidEmailAddress");
        boolean result = false;
        FacesContext context = FacesContext.getCurrentInstance();
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("UserDetailsExecuteWithEmailParam");
        Object obj = operationBinding.execute();
        DCIteratorBinding rowIteratorBinding =
            getBindings().findIteratorBinding("UserDetailsViewIterator");
        NavigatableRowIterator rowIterator =
            rowIteratorBinding.getNavigatableRowIterator();
        Row row = rowIterator.getCurrentRow();          
        if (row != null &&
            emailAddress.equalsIgnoreCase((String)row.getAttribute("Email"))) {
            result = true;
        } else {
            result = false;
            if (row != null) {
                LOGGER.finest("Email Address found:" +
                              (String)row.getAttribute("Email"));
            }
            LOGGER.finest("No User row found");
//            OperationBinding getGlobalVariableOperationBinding =
//                bindings.getOperationBinding("getGlobalVariable");
//
//            ADFContext.getCurrent().getPageFlowScope().put("globalVariableKey",
//                                                           "PORTAL_FGT_LOGIN_INCORRECT_EMAIL_ERROR");
//            String errorMessage =
//                (String)getGlobalVariableOperationBinding.execute();
//            FacesMessage facesMessage =
//                new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                                 "OZWALDO OZWALDO OZWALDO, The email address was not found",
//                                 errorMessage);
//            context.addMessage("qryEmail", facesMessage);
        }
        LOGGER.exiting(SOURCE_CLASS, "isValidEmailAddress");
        return result;
    }

    /**
     * Action method for submit button on Forgot User ID page
     * @return action outcome
     */
    public String forgotUserName() {
        LOGGER.entering(SOURCE_CLASS, "forgotUserName");
        String emailAddress = (String)fgtUserIdEmailAddressField.getValue();
        FacesContext context = FacesContext.getCurrentInstance();
        String outcome = null;
        if (isValidEmailAddress(emailAddress)) {
            //Send the user details off through email.
            LOGGER.finest("Validated Email Address. Send User Id to user - " +
                          emailAddress);
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("sendUserId");
            Boolean obj = (Boolean)operationBinding.execute();
            List errors = operationBinding.getErrors();
            boolean hasErrors = errors != null && errors.size() > 0;
            if (obj == Boolean.FALSE || hasErrors) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Error resetting password",
                                     "There was a system error when resetting the password. Please contact the administrator.");
                context.addMessage(null, msg);
                outcome = null;
                if (hasErrors) {
                    LOGGER.severe("Errors sending user id");
                    for (int ctr = 0; ctr < errors.size(); ctr++) {
                        LOGGER.severe(ctr + ") " + errors.get(ctr));
                    }
                }
            }
        }
        
        // We want this msg to appear regardless of valid or invalid email address for secureity reasons.
        //TODO: Confirmation Page or confirmation message
        FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_INFO, "User ID Emailed",
                             "Your User ID has been sent to your email address");
        context.addMessage(null, msg);
        fgtUserIdEmailAddressField.resetValue();
        fgtUserIdEmailAddressField.setValue(null);
        outcome = "cancel";
        
        LOGGER.exiting(SOURCE_CLASS, "forgotUserName");
        return outcome;
    }

    /**
     * Action method for the forgot password page
     * @return action outcome - back to login if successful, else null
     */
    public String forgotPassword() {
        String emailAddress = (String)fgtPwdEmailAddressField.getValue();
        FacesContext context = FacesContext.getCurrentInstance();
        if (isValidEmailAddress(emailAddress)) {
            //Send the user details off through email.
            //Generate password
            BindingContainer bindings = getBindings();
            OperationBinding operationBinding =
                bindings.getOperationBinding("resetPassword");
            Boolean obj = (Boolean)operationBinding.execute();
            if (obj != Boolean.TRUE) {
                FacesMessage msg =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Error resetting password",
                                     "There was a system error when resetting the password. Please contact the administrator.");
                context.addMessage(null, msg);
                return null;
            }
        }

        // We want this msg to appear regardless of valid or invalid email address for secureity reasons.
        FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_INFO, "Password Emailed",
                                "Your password has been reset and a temporary password has been sent to your email address.");
        context.addMessage(null, msg);
        fgtPwdEmailAddressField.resetValue();
        fgtPwdEmailAddressField.setValue(null);
        return "cancel";
    }


    public void setFgtPwdEmailAddressField(RichInputText fgtPwdEmailAddressField) {
        this.fgtPwdEmailAddressField = fgtPwdEmailAddressField;
    }

    public RichInputText getFgtPwdEmailAddressField() {
        return fgtPwdEmailAddressField;
    }

    /**
     * Action method for Continue button on Accept T&C
     * @return action outcome - "login_success" if successful, null if failed
     */
    public String acceptTAndC() {
        // Add event code here...
        LOGGER.entering(SOURCE_CLASS, "acceptTAndC");

        String outcome = null;
        FacesContext context = FacesContext.getCurrentInstance();
        BindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("acceptTAndC");
        Boolean result = (Boolean)operationBinding.execute();
        if (result == true) {
            //Relogin before redirecting to portal
            UserBean userBean =
                (UserBean)FacesUtil.resolveExpression("#{userSessionBean}");
            String pwd = (String)passwordField.getValue();
            HttpServletRequest req =
                (HttpServletRequest)ADFContext.getCurrent().getEnvironment().getRequest();
            HttpServletResponse res =
                (HttpServletResponse)ADFContext.getCurrent().getEnvironment().getResponse();
            int loginOutcome =
                loginInternal(userBean.getUserName(), tempPwdStore, req, res);
            this.startUserSession(req);
            outcome = LOGIN_SUCCESS;
        } else {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error updating system",
                                 "There was a system error when updating the terms and conditions acceptance. Please contact the administrator.");
            context.addMessage(null, msg);
            outcome = null;
        }
        LOGGER.finest("Outome: " + outcome);
        LOGGER.exiting(SOURCE_CLASS, "acceptTAndC");
        return outcome;
    }

    /**
     * Action method for the OK button on the password change confirmation screen
     * @return
     */
    public String confirmChangePassword() {
        // Add event code here...
        if (isTermsAndConditionsFlagged()) {
            return ACCEPT_T_AND_C_OUTCOME;
        } else {
            //Relogin before redirecting to portal
            UserBean userBean =
                (UserBean)FacesUtil.resolveExpression("#{userSessionBean}");
            String pwd = (String)passwordField.getValue();
            HttpServletRequest req =
                (HttpServletRequest)ADFContext.getCurrent().getEnvironment().getRequest();
            HttpServletResponse res =
                (HttpServletResponse)ADFContext.getCurrent().getEnvironment().getResponse();
            int loginOutcome =
                loginInternal(userBean.getUserName(), tempPwdStore, req, res);
            this.startUserSession(req);
            return LOGIN_SUCCESS;
        }
    }

    public void setFgtUserIdEmailAddressField(RichInputText fgtUserIdEmailAddressField) {
        this.fgtUserIdEmailAddressField = fgtUserIdEmailAddressField;
    }

    public RichInputText getFgtUserIdEmailAddressField() {
        return fgtUserIdEmailAddressField;
    }

    public String cancelFgtUserIdAction() {
        // Add event code here...
        if(fgtUserIdEmailAddressField != null)
        {
            fgtUserIdEmailAddressField.resetValue();
            fgtUserIdEmailAddressField.setValue(null);
        }
        return "cancel";
    }

    public String cancelFgtPwdAction() {
        // Add event code here...
        if(fgtPwdEmailAddressField != null)
        {
            fgtPwdEmailAddressField.resetValue();
            fgtPwdEmailAddressField.setValue(null);
        }
        return "cancel";
    }

  public void setPrimaryEmailField(RichInputText primaryEmailField) {
    this.primaryEmailField = primaryEmailField;
  }

  public RichInputText getPrimaryEmailField() {
    return primaryEmailField;
  }

  public void setConfPrimaryEmailField(RichInputText confPrimaryEmailField) {
    this.confPrimaryEmailField = confPrimaryEmailField;
  }

  public RichInputText getConfPrimaryEmailField() {
    return confPrimaryEmailField;
  }
}
