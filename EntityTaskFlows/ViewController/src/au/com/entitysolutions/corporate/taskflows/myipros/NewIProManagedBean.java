package au.com.entitysolutions.corporate.taskflows.myipros;

import au.com.entitysolutions.corporate.taskflows.common.CommonFunctionsBean;
import au.com.entitysolutions.taskflows.common.view.FacesUtil;

import au.com.entitysolutions.taskflows.common.view.PopupUtils;

import java.io.FileWriter;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.view.rich.component.rich.RichPopup;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class NewIProManagedBean {
    private String firstname;
    private String surname;
    private Date dateOfBirth;
    private String street;
    private String suburb;
    private String state;
    private String postcode;
    private String mobile;
    private String landline;
    private String email;
    private Date contractStart;
    private Date contractEnd;
    private String terminationNoticePeriod;
    private String terminationNoticeUnit;
    private String positionTitle;
    private String startTimeHrs;
    private String startTimeMins;
    private String endTimeHrs;
    private String endTimeMins;
    private String iproRate;
    private String mgmtFee;
    private String payrollTax;
    private String totalBillable;
    private String award;
    private String classification;
    private ArrayList<SelectItem> states;
    private ArrayList<SelectItem> hours;
    private ArrayList<SelectItem> mins;
    private ArrayList<SelectItem> rateUnits;
    private String iproRateUnit;

    private String clientName;
    private String managerName;
    private String managerEmail;
    private String managerPhone;
    private String secondaryManagerName;
    private String secondaryManagerEmail;
    private String secondaryManagerPhone;
    private String invoiceInfo;

private String endUserName;
    private String endUserStreet;
    private String endUserSuburb;
    private String endUserState;
    private String endUserPostcode;

    private String wicCode;
    private String wicRate;
    private ArrayList<String> errorMessages = new ArrayList<String>();

    private String emailFilename;
    private RichPopup successPopup;

    private boolean emailSent;
    private boolean goAgain;

    private String currentDate;
    private String currentTime;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getLandline() {
        return landline;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setContractStart(Date contractStart) {
        this.contractStart = contractStart;
    }

    public Date getContractStart() {
        return contractStart;
    }

    public void setContractEnd(Date contractEnd) {
        this.contractEnd = contractEnd;
    }

    public Date getContractEnd() {
        return contractEnd;
    }

    public void setTerminationNoticePeriod(String terminationNoticePeriod) {
        this.terminationNoticePeriod = terminationNoticePeriod;
    }

    public String getTerminationNoticePeriod() {
        return terminationNoticePeriod;
    }

    public void setPositionTitle(String positionTitle) {
        this.positionTitle = positionTitle;
    }

    public String getPositionTitle() {
        return positionTitle;
    }

    public void setStartTimeHrs(String startTimeHrs) {
        this.startTimeHrs = startTimeHrs;
    }

    public String getStartTimeHrs() {
        return startTimeHrs;
    }

    public void setStartTimeMins(String startTimeMins) {
        this.startTimeMins = startTimeMins;
    }

    public String getStartTimeMins() {
        return startTimeMins;
    }

    public void setEndTimeHrs(String endTimeHrs) {
        this.endTimeHrs = endTimeHrs;
    }

    public String getEndTimeHrs() {
        return endTimeHrs;
    }

    public void setEndTimeMins(String endTimeMins) {
        this.endTimeMins = endTimeMins;
    }

    public String getEndTimeMins() {
        return endTimeMins;
    }

    public void setIproRate(String iproRate) {
        this.iproRate = iproRate;
    }

    public String getIproRate() {
        return iproRate;
    }

    public void setMgmtFee(String mgmtFee) {
        this.mgmtFee = mgmtFee;
    }

    public String getMgmtFee() {
        return mgmtFee;
    }

    public void setPayrollTax(String payrollTax) {
        this.payrollTax = payrollTax;
    }

    public String getPayrollTax() {
        return payrollTax;
    }

    public void setTotalBillable(String totalBillable) {
        this.totalBillable = totalBillable;
    }

    public String getTotalBillable() {
        return totalBillable;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getAward() {
        return award;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    public ArrayList<SelectItem> getStates() {
        states = new ArrayList<SelectItem>();
        states.add(new SelectItem("New South Wales"));
        states.add(new SelectItem("Victoria"));
        states.add(new SelectItem("Queensland"));
        states.add(new SelectItem("Western Australia"));
        states.add(new SelectItem("South Australia"));
        states.add(new SelectItem("Tasmania"));
        states.add(new SelectItem("Australian Capital Territory"));
        states.add(new SelectItem("Northern Territory"));
        states.add(new SelectItem("Overseas"));
        return states;
    }

    public ArrayList<SelectItem> getHours() {
        hours = new ArrayList<SelectItem>();
        for (int i = 0; i <= 24; i++) {
            hours.add(new SelectItem(String.format("%d", i)));
        }
        return hours;
    }

    public ArrayList<SelectItem> getMins() {
        mins = new ArrayList<SelectItem>();
        for (int i = 0; i <= 55; i += 5) {
            mins.add(new SelectItem(String.format("%02d", i)));
        }
        return mins;
    }

    public ArrayList<SelectItem> getRateUnits() {
        rateUnits = new ArrayList<SelectItem>();
        rateUnits.add(new SelectItem("Hourly"));
        rateUnits.add(new SelectItem("Daily"));
        rateUnits.add(new SelectItem("Weekly"));
        rateUnits.add(new SelectItem("Fortnightly"));
        rateUnits.add(new SelectItem("Monthly"));
        return rateUnits;
    }

    public void setIproRateUnit(String iproRateUnit) {
        this.iproRateUnit = iproRateUnit;
    }

    public String getIproRateUnit() {
        return iproRateUnit;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        if(clientName == null){
            clientName = (String)FacesUtil.resolveExpression("#{userSessionBean.organisationName}");
        }
        return clientName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setSecondaryManagerName(String secondaryManagerName) {
        this.secondaryManagerName = secondaryManagerName;
    }

    public String getSecondaryManagerName() {
        return secondaryManagerName;
    }

    public void setSecondaryManagerEmail(String secondaryManagerEmail) {
        this.secondaryManagerEmail = secondaryManagerEmail;
    }

    public String getSecondaryManagerEmail() {
        return secondaryManagerEmail;
    }

    public void setSecondaryManagerPhone(String secondaryManagerPhone) {
        this.secondaryManagerPhone = secondaryManagerPhone;
    }

    public String getSecondaryManagerPhone() {
        return secondaryManagerPhone;
    }

    public void setInvoiceInfo(String invoiceInfo) {
        this.invoiceInfo = invoiceInfo;
    }

    public String getInvoiceInfo() {
        return invoiceInfo;
    }

    public void setEndUserStreet(String endUserStreet) {
        this.endUserStreet = endUserStreet;
    }

    public String getEndUserStreet() {
        return endUserStreet;
    }

    public void setEndUserSuburb(String endUserSuburb) {
        this.endUserSuburb = endUserSuburb;
    }

    public String getEndUserSuburb() {
        return endUserSuburb;
    }

    public void setEndUserState(String endUserState) {
        this.endUserState = endUserState;
    }

    public String getEndUserState() {
        return endUserState;
    }

    public void setEndUserPostcode(String endUserPostcode) {
        this.endUserPostcode = endUserPostcode;
    }

    public String getEndUserPostcode() {
        return endUserPostcode;
    }

    public void setWicCode(String wicCode) {
        this.wicCode = wicCode;
    }

    public String getWicCode() {
        return wicCode;
    }

    public void setWicRate(String wicRate) {
        this.wicRate = wicRate;
    }

    public String getWicRate() {
        return wicRate;
    }

    public void setTerminationNoticeUnit(String terminationNoticeUnit) {
        this.terminationNoticeUnit = terminationNoticeUnit;
    }

    public String getTerminationNoticeUnit() {
        return terminationNoticeUnit;
    }

    public boolean isValidContactInfo() {
        if ((email == null || email.trim().equals("")) &&
            (landline == null || landline.trim().equals("")) &&
            (mobile == null || mobile.trim().equals(""))) {
            errorMessages.add("Either one phone number, or an email address must be provided");
            return false;
        }
        return true;
    }

    public int getMaxTerminationValue() {
        if (terminationNoticeUnit == null) {
            return 0;
        } else if (terminationNoticeUnit.equals(FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['DAYS_LBL']}"))) {
            return 365;
        } else if (terminationNoticeUnit.equals(FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['WEEKS_LBL']}"))) {
            return 52;
        } else if (terminationNoticeUnit.equals(FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['MONTHS_LBL']}"))) {
            return 12;
        }
        return 0;
    }
    
    public boolean isValidStartTime(){
        if((startTimeHrs == null && startTimeMins == null) || (startTimeHrs != null && startTimeMins != null)){
            return true;
        }else {
            errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['INVALID_START_TIME']}"));
            return false;
        }
    }
    
    public boolean isValidEndTime(){
        if((endTimeHrs == null && endTimeMins == null) || (endTimeHrs != null && endTimeMins != null)){
            return true;
        }else {
            errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['INVALID_END_TIME']}"));
            return false;
        }
    }
    
    public boolean isValidIProRate(){
        if((iproRate == null || iproRate.trim().equals("")) && iproRateUnit != null){
            errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['NO_RATE_ENTERED_ERROR']}"));
            return false;
        }else if((iproRate != null && !iproRate.trim().equals("")) && iproRateUnit == null){
            errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['NO_RATE_UNIT_SELECTED_ERROR']}"));
            return false;
        }
        return true;
    }

    public boolean isValidTerminationValue() {
        if (terminationNoticePeriod != null) {
            if (terminationNoticeUnit == null) {
                errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TERMINATION_NO_UNIT_ERROR']}"));
                return false;
            } else if (Double.parseDouble(terminationNoticePeriod) >
                       getMaxTerminationValue()) {
                if (terminationNoticeUnit.equals(FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['DAYS_LBL']}"))) {
                    errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TERMINATION_DAYS_ERROR']}"));
                } else if (terminationNoticeUnit.equals(FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['WEEKS_LBL']}"))) {
                    errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TERMINATION_WEEKS_ERROR']}"));
                } else if (terminationNoticeUnit.equals(FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['MONTHS_LBL']}"))) {
                    errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TERMINATION_MONTHS_ERROR']}"));
                }
                return false;
            }
        } else if ((terminationNoticePeriod == null ||
                    Double.parseDouble(terminationNoticePeriod) < 0) &&
                   terminationNoticeUnit != null) {
            errorMessages.add((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TERMINATION_NULL_OR_ZERO_ERROR']}"));
            return false;
        }
        return true;
    }

    public boolean isValidInfo() {
        errorMessages.clear();
        boolean validTermination = isValidTerminationValue();
        boolean validContactInfo = isValidContactInfo();
        boolean validStartTime = isValidStartTime();
        boolean validEndTime = isValidEndTime();
        boolean validIProRate = isValidIProRate();
        
        if (validTermination 
            && validContactInfo 
            && validStartTime
            && validEndTime
            && validIProRate) {
            return true;
        }
        return false;
    }

    public ArrayList<String> getErrorMessages() {
        return errorMessages;
    }


    public void resetForm() {
        firstname = null;
        surname = null;
        dateOfBirth = null;
        street = null;
        suburb = null;
        state = null;
        postcode = null;
        mobile = null;
        landline = null;
        email = null;
        contractStart = null;
        contractEnd = null;
        terminationNoticePeriod = null;
        terminationNoticeUnit = null;
        positionTitle = null;
        startTimeHrs = null;
        startTimeMins = null;
        endTimeHrs = null;
        endTimeMins = null;
        iproRate = null;
        mgmtFee = null;
        payrollTax = null;
        totalBillable = null;
        award = null;
        classification = null;
        iproRateUnit = null;

        clientName = null;
        managerName = null;
        managerEmail = null;
        managerPhone = null;
        secondaryManagerName = null;
        secondaryManagerEmail = null;
        secondaryManagerPhone = null;
        invoiceInfo = null;

        endUserStreet = null;
        endUserSuburb = null;
        endUserState = null;
        endUserPostcode = null;
        endUserName = null;

        wicCode = null;
        wicRate = null;
    }

    public String getCSVFileName() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
        return FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CSV_FILENAME']}") +
            fmt.format(dt) + ".csv";
    }

    public void setEmailFilename(String emailFilename) {
        this.emailFilename = emailFilename;
    }

    public String getEmailFilename() {
        return emailFilename;
    }

    private void generateCsvFile(String sFileName) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            FileWriter writer = new FileWriter(sFileName);

            //headers
            writer.append("Surname,");
            writer.append("First_Name,");
            writer.append("Address,");
            writer.append("Suburb,");
            writer.append("State,");
            writer.append("Postcode,");
            writer.append("Phone_Mob,");
            writer.append("Phone_Fix,");
            writer.append("Email,");
            writer.append("Contract_Start_Date,");
            writer.append("Contract_End_Date,");
            writer.append("Position_Title,");
            writer.append("Word_Start,");
            writer.append("Work_End,");
            writer.append("Termination_Notice,");
            writer.append("Rate_Inc_Super,");
            writer.append("Mgt_Fee,");
            writer.append("Payroll_Tax,");
            writer.append("Total_Billable,");
            writer.append("Rate_Unit,");
            writer.append("DOB,");
            writer.append("Award_Applicable,");
            writer.append("Award,");
            writer.append("Award_Class,");
            writer.append("Client_Name,");
            writer.append("Rep_Mgr_Name,");
            writer.append("Rep_Email,");
            writer.append("Rep_Mgr_Phone,");
            writer.append("Sec_Mgr_Name,");
            writer.append("Sec_Email,");
            writer.append("Sec_Mgr_Phone,");
            writer.append("Inv_Spec_Inf,");
            writer.append("End_Customer_Name,");
            writer.append("Work_St_Address,");
            writer.append("Work_Suburb,");
            writer.append("Work_State,");
            writer.append("Work_Postcode,");
            writer.append("WIC_Code,");
            writer.append("WIC_Ind_Rate\n");

            //data
            writer.append((surname == null ? "" : surname) + ",");
            writer.append((firstname == null ? "" : firstname) + ",");
            writer.append((street == null ? "" : street) + ",");
            writer.append((suburb == null ? "" : suburb) + ",");
            writer.append((state == null ? "" : state) + ",");
            writer.append((postcode == null ? "" : postcode) + ",");
            writer.append((mobile == null ? "" : mobile) + ",");
            writer.append((landline == null ? "" : landline) + ",");
            writer.append((email == null ? "" : email) + ",");
            writer.append((contractStart == null ? "" :
                           fmt.format(contractStart)) + ",");
            writer.append((contractEnd == null ? "" :
                           fmt.format(contractEnd)) + ",");
            writer.append((positionTitle == null ? "" : positionTitle) + ",");
            writer.append(((startTimeHrs == null || startTimeMins == null) ?
                           "" : (startTimeHrs + ":" + startTimeMins)) + ",");
            writer.append(((endTimeHrs == null || endTimeMins == null) ? "" :
                           (endTimeHrs + ":" + endTimeMins)) + ",");
            writer.append(((terminationNoticePeriod == null ||
                            terminationNoticeUnit == null) ? "" :
                           (terminationNoticePeriod + " " +
                            terminationNoticeUnit)) + ",");
            writer.append((iproRate == null ? "" : iproRate) + ",");
            writer.append((mgmtFee == null ? "" : mgmtFee) + ",");
            writer.append((payrollTax == null ? "" : payrollTax) + ",");
            writer.append((totalBillable == null ? "" : totalBillable) + ",");
            writer.append((iproRateUnit == null ? "" : iproRateUnit) + ",");
            writer.append((dateOfBirth == null ? "" :
                           fmt.format(dateOfBirth)) + ",");
            writer.append((award == null ? "" : award) + ",");
            writer.append((award == null ? "" : award) + ",");
            writer.append((classification == null ? "" : classification) +
                          ",");
            writer.append((clientName == null ? "" : clientName) + ",");
            writer.append((managerName == null ? "" : managerName) + ",");
            writer.append((managerEmail == null ? "" : managerEmail) + ",");
            writer.append((managerPhone == null ? "" : managerPhone) + ",");
            writer.append((secondaryManagerName == null ? "" :
                           secondaryManagerName) + ",");
            writer.append((secondaryManagerEmail == null ? "" :
                           secondaryManagerEmail) + ",");
            writer.append((secondaryManagerPhone == null ? "" :
                           secondaryManagerPhone) + ",");
            writer.append((invoiceInfo == null ? "" : invoiceInfo) + ",");
            writer.append((endUserName == null ? "" : endUserName) + ",");
            writer.append((endUserStreet == null ? "" : endUserStreet) + ",");
            writer.append((endUserSuburb == null ? "" : endUserSuburb) + ",");
            writer.append((endUserState == null ? "" : endUserState) + ",");
            writer.append((endUserPostcode == null ? "" : endUserPostcode) +
                          ",");
            writer.append((wicCode == null ? "" : wicCode) + ",");
            writer.append((wicRate == null ? "" : wicRate) + "\n");

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSuccessPopup(RichPopup successPopup) {
        this.successPopup = successPopup;
    }

    public RichPopup getSuccessPopup() {
        return successPopup;
    }

    public void successDialogHandler(DialogEvent dialogEvent) {
        setGoAgain(dialogEvent.getOutcome().equals(DialogEvent.Outcome.yes));
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public boolean isEmailSent() {
        return emailSent;
    }

    public void setGoAgain(boolean goAgain) {
        this.goAgain = goAgain;
    }

    public boolean isGoAgain() {
        return goAgain;
    }

    public void showSuccessPopUp(ActionEvent actionEvent) {
        // Add event code here...
        System.out.println("Get Filename.");
        setEmailFilename(getCSVFileName());

        generateCsvFile(emailFilename);

        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        OperationBinding operationBinding =
            bindings.getOperationBinding("sendEmailWithAttachments");

        String result = (String)operationBinding.execute();
        emailSent = (result != null);

        if (emailSent) {
            successPopup.show(new RichPopup.PopupHints());
        } else {
            new CommonFunctionsBean().showErrorMessage((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['REGISTRATION_ERROR_MSG']}"));
        }
    }

    public String getCurrentDate() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = fmt.format(dt);
        return currentDate;
    }

    public String getCurrentTime() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
        currentTime = fmt.format(dt);
        return currentTime;
    }

    public void setEndUserName(String endUserName) {
        this.endUserName = endUserName;
    }

    public String getEndUserName() {
        return endUserName;
    }
}
