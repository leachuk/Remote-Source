package au.com.entitysolutions.taskflows.changepassword.view;

import au.com.entitysolutions.taskflows.login.view.LoginFlowBean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class ChangePasswordBean extends LoginFlowBean {
  public ChangePasswordBean() {   
  }
  
  public String changePassword(){
    FacesContext context=FacesContext.getCurrentInstance();    
    String oldPassword = (String)getChgPwdCurrentPasswordField().getValue();
    String newPassword1 = (String)getChgPwdNewPasswordField().getValue();
    String newConPassword1 = (String)getChgPwdConfNewPasswordField().getValue();
   
    
    if(oldPassword==null){
      FacesMessage msg =
          new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           "Password Error",
                           "Please enter value for current password.");
      context.addMessage(getChgPwdCurrentPasswordField().getClientId(context), msg);
      return null;
    }
    
    if(newPassword1==null){
      FacesMessage msg =
          new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           "Password Error",
                           "Please enter value for new password.");
      context.addMessage(getChgPwdNewPasswordField().getClientId(context), msg);
      return null;
    }
    
    if(newConPassword1==null){
      FacesMessage msg =
          new FacesMessage(FacesMessage.SEVERITY_ERROR,
                           "Password Error",
                           "Please confirm your new password.");
      context.addMessage(getChgPwdConfNewPasswordField().getClientId(context), msg);
      return null;
    }   
    String outcome=null;
    if (!newPassword1.equals(newConPassword1)) {      
        FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                             "Passwords do not match",
                             "The two passwords that you entered do not match");
        getChgPwdConfNewPasswordField().setValue(null);
        context.addMessage(getChgPwdConfNewPasswordField().getClientId(context), msg);
        return null;
    } else if (oldPassword.equals(newPassword1)) {
        FacesMessage msg =
            new FacesMessage(FacesMessage.SEVERITY_ERROR,
                             "New Password Error",
                             "The new password should not be the same as your current password");
        getChgPwdNewPasswordField().setValue(null);
        context.addMessage(getChgPwdNewPasswordField().getClientId(context), msg);
        return null;
    }
    
    BindingContainer bindings = getBindings();
    OperationBinding validateOperationBinding =
        bindings.getOperationBinding("validatePasswordRules");
    Boolean result = (Boolean)validateOperationBinding.execute();
    
    if (result) {
        OperationBinding operationBinding =
            bindings.getOperationBinding("changePassword");
        result = (Boolean)operationBinding.execute();
        
      if (result) {         
          outcome = CONFIRMATION;
      } else {          
          FacesMessage msg =
              new FacesMessage(FacesMessage.SEVERITY_ERROR,
                               "Error updating system",
                               "There was a system error when updating the new passwords. Please contact the administrator.");
          context.addMessage(null, msg);
          outcome = null;
      }        
    }
    else {
     FacesMessage msg =
         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                          "Password Error",
                          "Error changing the password.\r\n \r\nPlease ensure the current password is correct and the new password complies with the password rules and restrictions.");
       context.addMessage(null, msg);
       outcome = null;
   }
    
    //Password change successfully
    if(CONFIRMATION.equals(outcome)){
      getChgPwdCurrentPasswordField().resetValue();
      getChgPwdCurrentPasswordField().setValue(null);
      getChgPwdNewPasswordField().resetValue();
      getChgPwdNewPasswordField().setValue(null);
      getChgPwdConfNewPasswordField().resetValue();
      getChgPwdConfNewPasswordField().setValue(null);
      context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Change Password","Password has been changed succesfully"));
    }
    else{    
      return null;
    }
    
    return "SUCCESS";
    
  }
  
  
  
  
  
}
