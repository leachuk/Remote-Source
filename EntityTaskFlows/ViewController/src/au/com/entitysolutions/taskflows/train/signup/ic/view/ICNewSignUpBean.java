package au.com.entitysolutions.taskflows.train.signup.ic.view;


import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.train.signup.common.view.BankDetailsStopBean;
import au.com.entitysolutions.taskflows.train.signup.common.view.ContractStopBean;
import au.com.entitysolutions.taskflows.train.signup.common.view.PoliciesStopBean;
import au.com.entitysolutions.taskflows.train.signup.common.view.ResidencyDetailsStopBean;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainBean;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class ICNewSignUpBean extends TaskflowTrainBean {
    private String resourceBundle;
    private ResourceBundle bundle;

    private static String SOURCE_CLASS =
        ICNewSignUpBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    public ICNewSignUpBean() {
        super();
    }

    /**
     * Init the stop beans as required
     */
    public void initStopBeans() {
        HashMap<String, TaskflowTrainStopBean> trainStopsMap =
            new HashMap<String, TaskflowTrainStopBean>();

        ContractStopBean contractBean = new ContractStopBean();
        trainStopsMap.put(contractBean.getStepName(), contractBean);

        BankDetailsStopBean bankBean = new BankDetailsStopBean();
        trainStopsMap.put(bankBean.getStepName(), bankBean);

        ResidencyDetailsStopBean resiBean = new ResidencyDetailsStopBean();
        trainStopsMap.put(resiBean.getStepName(), resiBean);
        
        CertificatesStopBean certBean = new CertificatesStopBean();
        trainStopsMap.put(certBean.getStepName(), certBean);

        PoliciesStopBean policyBean = new PoliciesStopBean();
        trainStopsMap.put(policyBean.getStepName(), policyBean);

        this.setTrainStops(trainStopsMap);
    }

    public String completeTrain() {
        return null;
    }

    public void setResourceBundle(String resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String getResourceBundle() {
        return resourceBundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(resourceBundle);
        }
        return bundle;
    }

    public String getString(String messageKey) {
        return getBundle().getString(messageKey);
    }


    /**
     * Rollback all the changes
     * @param actionEvent
     */
    public void rollbackAndRequery(ActionEvent actionEvent) {
        LOGGER.entering(SOURCE_CLASS, "rollbackAndRequery");
        BindingContainer binding = this.getBindings();
        OperationBinding operation = binding.getOperationBinding("Rollback");
        Object result = operation.execute();
        LOGGER.finest("Result of rollback binding call:" + result);
        LOGGER.exiting(SOURCE_CLASS, "rollbackAndRequery");
    }

    /**
     * Submit taskflow
     * @param actionEvent
     */
    public void submitTaskflow(ActionEvent actionEvent) {
        LOGGER.entering(SOURCE_CLASS, "submitTaskflow");
        boolean isSuccess = true;
        BindingContainer binding = this.getBindings();
        OperationBinding operation =
            binding.getOperationBinding("completeTaskFlow");
        Object result = operation.execute();
        isSuccess =
                operation.getErrors() == null || operation.getErrors().isEmpty();
        if (!isSuccess) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errors", "There were errors when saving the data. Please try again.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            throw new FacesException("An error occurred while saving the data");
        }
        LOGGER.entering(SOURCE_CLASS, "submitTaskflow");
    }

    /**
     * Commit to database
     * @param actionEvent
     */
    public void validateAndCommit(ActionEvent actionEvent) {
        LOGGER.entering(SOURCE_CLASS, "validateAndCommit");
        String expression =
            (String)FacesUtil.resolveExpression("#{requestScope.stopName}");
        boolean isSuccess = true;
        LOGGER.finest("Expression: " + expression);
        if (expression != null) {
            TaskflowTrainStopBean stopBean = getTrainStops().get(expression);
            if (stopBean != null) {
                LOGGER.finest("Invoke stop bean methods");
                //Execute validation and post changes on the stop bean
                isSuccess = stopBean.validate();
                if (isSuccess) {
                    isSuccess = stopBean.processData();
                }
            }
            if (isSuccess) {
                LOGGER.finest("Exectute Step Completion routine");
                BindingContainer binding = this.getBindings();
                OperationBinding operation =
                    binding.getOperationBinding("completeStep");
                Object result = operation.execute();
                isSuccess =
                        operation.getErrors() == null || operation.getErrors().isEmpty();
            }
            LOGGER.exiting(SOURCE_CLASS, "validateAndCommit");
        } else {
            isSuccess = false;
        }
        if (!isSuccess) {
            //FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Errors", "There were errors when saving the data. Please try again.");
            //FacesContext.getCurrentInstance().addMessage(null, message);
            throw new FacesException("An error occurred while saving the data");
        }
    }

    public String exitTrain() {
        return "returnFromTaskflow";
    }

    public static void main(String[] s) {

    }
}

