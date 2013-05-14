package au.com.entitysolutions.taskflows.train.signup.ipro.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.util.logging.Logger;

import java.util.regex.Pattern;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneRadio;

import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.component.UIXEditableValue;

public class SuperDetailsStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        SuperDetailsStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);
    
    private RichSelectOneRadio superFundType;
    
    private RichInputText superFundName;
    private RichInputText superFundSpin;
    private RichInputText superFundAbn;
    private RichInputText superMemberName;
    private RichInputText superMemberNumber;
    
    private RichSelectOneRadio superPaymentType;
    private  RichInputText eftAccountName;
    private  RichInputText eftAccountNo;
    private  RichInputText eftBSB;
    private  RichInputText eftEmail;
    
    private RichInputText bpayBillerCode;
    private RichInputText bpayBillerRef;
    private RichInputText salBpayBillerCode;
    private RichInputText salBpayBillerRef;
    
    private RichInputText chqPayTo;
    private RichInputText chqAddr;
    private RichInputText chqSuburb;
    private RichInputText chqPostcode;    
    private RichSelectOneChoice chqState;

    public SuperDetailsStopBean() {
        super();
    }

    public boolean validate() {
        boolean isValid = true;
        FacesContext context = FacesContext.getCurrentInstance();
        
        Boolean confirmSuper = (Boolean)FacesUtil.resolveExpression("#{bindings.ConfirmSuperCompliance.inputValue}");
        String fundChoice = (String)FacesUtil.resolveExpression("#{bindings.SuperChoice.attributeValue}");
        if("OWN_CHOICE".equals(fundChoice) && !confirmSuper) {
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Confirm Compliance",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['SUPER_DTLS_ACCEPT_ERROR']}"));
            context.addMessage(null, message);            
        }
        return isValid;
    }

    public boolean processData() {
        boolean isSuccess = true;
        DCBindingContainer binding = getBindings();
        OperationBinding oper = binding.getOperationBinding("resetSuperDetails");
        oper.execute();
        if(oper.getErrors() != null && !oper.getErrors().isEmpty()) {
            isSuccess = false;
            LOGGER.severe("Errors returned from uploadDoc AM call");
        }
        return isSuccess;
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['SUPER_DTLS_STEP_NAME']}");
    }
    
    public void superChoiceValueChange(ValueChangeEvent valueChangeEvent) {
        LOGGER.entering(SOURCE_CLASS,"superChoiceValueChange");               
        resetValue(superFundType);        
        //Chain further down
        this.superFundTypeValueChange(valueChangeEvent);
        LOGGER.exiting(SOURCE_CLASS,"superChoiceValueChange");
    }
    
    public void superFundTypeValueChange(ValueChangeEvent valueChangeEvent) {
        LOGGER.entering(SOURCE_CLASS,"superSelectionValueChangeListener");       
        resetValue(superFundName);
        resetValue(superFundSpin);
        resetValue(superFundAbn);
        resetValue(superMemberName);
        resetValue(superMemberNumber);
        
        resetValue(superPaymentType);
        
        //Chain further down
        this.superPaymentTypeValueChange(valueChangeEvent);
        LOGGER.exiting(SOURCE_CLASS,"superSelectionValueChangeListener");
    }
    
    public void superPaymentTypeValueChange(ValueChangeEvent valueChangeEvent) {
        LOGGER.entering(SOURCE_CLASS,"superSelectionValueChangeListener");       
        resetValue(eftAccountName);
//        resetValue(eftAccountNo);
        resetValue(eftBSB);
        resetValue(eftEmail);
        
        resetValue(bpayBillerCode);
        resetValue(bpayBillerRef);
        resetValue(salBpayBillerCode);
        resetValue(salBpayBillerRef);        
        
        resetValue(chqPayTo);
        resetValue(chqAddr);
        resetValue(chqSuburb);
        resetValue(chqPostcode);    
        resetValue(chqState);       

              
        LOGGER.exiting(SOURCE_CLASS,"superSelectionValueChangeListener");
    }
    
    
    private void resetValue(UIXEditableValue component ) {
        component.resetValue();
        component.setValue(null);
    }
    

    public void setSuperFundName(RichInputText superFundName) {
        this.superFundName = superFundName;
    }

    public RichInputText getSuperFundName() {
        return superFundName;
    }

    public void setSuperFundSpin(RichInputText superFundSpin) {
        this.superFundSpin = superFundSpin;
    }

    public RichInputText getSuperFundSpin() {
        return superFundSpin;
    }

    public void setSuperFundAbn(RichInputText superFundAbn) {
        this.superFundAbn = superFundAbn;
    }

    public RichInputText getSuperFundAbn() {
        return superFundAbn;
    }

    public void setSuperMemberName(RichInputText superMemberName) {
        this.superMemberName = superMemberName;
    }

    public RichInputText getSuperMemberName() {
        return superMemberName;
    }

    public void setSuperMemberNumber(RichInputText superMemberNumber) {
        this.superMemberNumber = superMemberNumber;
    }

    public RichInputText getSuperMemberNumber() {
        return superMemberNumber;
    }

    public void setSuperFundType(RichSelectOneRadio superFundType) {
        this.superFundType = superFundType;
    }

    public RichSelectOneRadio getSuperFundType() {
        return superFundType;
    }

    public void setSuperPaymentType(RichSelectOneRadio superPaymentType) {
        this.superPaymentType = superPaymentType;
    }

    public RichSelectOneRadio getSuperPaymentType() {
        return superPaymentType;
    }

    public void setEftAccountName(RichInputText eftAccountName) {
        this.eftAccountName = eftAccountName;
    }

    public RichInputText getEftAccountName() {
        return eftAccountName;
    }

    public void setEftAccountNo(RichInputText eftAccountNo) {
        this.eftAccountNo = eftAccountNo;
    }

    public RichInputText getEftAccountNo() {
        return eftAccountNo;
    }

    public void setEftBSB(RichInputText eftBSB) {
        this.eftBSB = eftBSB;
    }

    public RichInputText getEftBSB() {
        return eftBSB;
    }

    public void setEftEmail(RichInputText eftEmail) {
        this.eftEmail = eftEmail;
    }

    public RichInputText getEftEmail() {
        return eftEmail;
    }

    public void setBpayBillerCode(RichInputText bpayBillerCode) {
        this.bpayBillerCode = bpayBillerCode;
    }

    public RichInputText getBpayBillerCode() {
        return bpayBillerCode;
    }

    public void setBpayBillerRef(RichInputText bpayBillerRef) {
        this.bpayBillerRef = bpayBillerRef;
    }

    public RichInputText getBpayBillerRef() {
        return bpayBillerRef;
    }

    public void setSalBpayBillerCode(RichInputText salBpayBillerCode) {
        this.salBpayBillerCode = salBpayBillerCode;
    }

    public RichInputText getSalBpayBillerCode() {
        return salBpayBillerCode;
    }

    public void setSalBpayBillerRef(RichInputText salBpayBillerRef) {
        this.salBpayBillerRef = salBpayBillerRef;
    }

    public RichInputText getSalBpayBillerRef() {
        return salBpayBillerRef;
    }

    public void setChqPayTo(RichInputText chqPayTo) {
        this.chqPayTo = chqPayTo;
    }

    public RichInputText getChqPayTo() {
        return chqPayTo;
    }

    public void setChqAddr(RichInputText chqAddr) {
        this.chqAddr = chqAddr;
    }

    public RichInputText getChqAddr() {
        return chqAddr;
    }

    public void setChqSuburb(RichInputText chqSuburb) {
        this.chqSuburb = chqSuburb;
    }

    public RichInputText getChqSuburb() {
        return chqSuburb;
    }

    public void setChqPostcode(RichInputText chqPostcode) {
        this.chqPostcode = chqPostcode;
    }

    public RichInputText getChqPostcode() {
        return chqPostcode;
    }

    public void setChqState(RichSelectOneChoice chqState) {
        this.chqState = chqState;
    }

    public RichSelectOneChoice getChqState() {
        return chqState;
    }
    
    public static void main(String[] args) {
        String amount = "029933333333";
        Pattern patt = Pattern.compile("^\\d{4,8}");
        
        System.out.println("Patt:" + patt.matcher(amount).matches());
        
    }
}
