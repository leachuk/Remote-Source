package au.com.entitysolutions.taskflows.train.signup.common.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.util.logging.Logger;

import javax.faces.FacesException;

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.binding.OperationBinding;

public class BankDetailsStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        BankDetailsStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    public BankDetailsStopBean() {
        super();
    }

    public boolean validate() {
        //No validations to be performed
        return true;
    }

    /**
     * Process data on the bank details page.
     * @return
     */
    public boolean processData() {
        LOGGER.entering(SOURCE_CLASS, "processData");
        FacesContext context = FacesContext.getCurrentInstance();
        DCBindingContainer bindings = getBindings();
        OperationBinding operationBinding =
            bindings.getOperationBinding("resetPrimaryAccountPriority");
        Object result = operationBinding.execute();
        if(!(operationBinding.getErrors() == null || operationBinding.getErrors().isEmpty())) {
            LOGGER.severe("Error invoking resetPrimaryAccountPriority method");
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error while saving the data. Please try again");
            context.addMessage(null, message);            
            throw new FacesException("Error saving record");
        }
        LOGGER.exiting(SOURCE_CLASS, "processData");
        return true;
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['BANK_DTLS_STEP_NAME']}");
    }

    public void resetComponents() {
    }
}
