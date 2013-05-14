package au.com.entitysolutions.taskflows.train.signup.ipro.view;


import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.train.signup.common.view.BankDetailsStopBean;
import au.com.entitysolutions.taskflows.train.signup.common.view.ContractStopBean;
import au.com.entitysolutions.taskflows.train.signup.common.view.PoliciesStopBean;
import au.com.entitysolutions.taskflows.train.signup.common.view.ResidencyDetailsStopBean;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainBean;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.IOException;
import java.io.OutputStream;

import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;


public class IProNewSignUpBean extends TaskflowTrainBean {
    private String resourceBundle;
    private ResourceBundle bundle;

    private static String SOURCE_CLASS =
        IProNewSignUpBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    public IProNewSignUpBean() {
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
        
        TaxationDetailsStopBean taxBean = new TaxationDetailsStopBean();
        trainStopsMap.put(taxBean.getStepName(), taxBean);
        
        PoliciesStopBean policyBean = new PoliciesStopBean();
        trainStopsMap.put(policyBean.getStepName(), policyBean);
        
        SuperDetailsStopBean superDtlsBean = new SuperDetailsStopBean();
        trainStopsMap.put(superDtlsBean.getStepName(), superDtlsBean);
        
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

    public void downloadContract(FacesContext facesContext,
                                 OutputStream outputStream) {
        // Add event code here...
        LOGGER.entering(SOURCE_CLASS, "downloadContract");
        BindingContainer bindings = getBindings();
        DCIteratorBinding headerIteratorBinding =
            getBindings().findIteratorBinding("PortalUserTFStepDownloadDocsViewIterator");
        NavigatableRowIterator headerRowIterator =
            headerIteratorBinding.getNavigatableRowIterator();
        Row[] rows = headerRowIterator.getAllRowsInRange();
        if (rows == null || rows.length < 1) {
            LOGGER.severe("No records found in download iterator");
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Download Error",
                                 "A system error occurred when downloading the file.");
            facesContext.addMessage(null, message);
        }
        for (int ctr = 0; ctr < rows.length; ctr++) {
            LOGGER.finest("Found row");
            LOGGER.finest("row.DocName:" + rows[ctr].getAttribute("DocName"));
            LOGGER.finest("row.DocSubType:" +
                          rows[ctr].getAttribute("DocSubType"));
            LOGGER.finest("row.UserTfStepId:" +
                          rows[ctr].getAttribute("UserTfStepId"));
            BlobDomain blob = (BlobDomain)rows[ctr].getAttribute("DocContent");
            try {
                FileUtils.writeBlobToStream(blob, outputStream);
            } catch (IOException ioe) {
                LOGGER.log(Level.SEVERE, "Error downloading contract", ioe);
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Download Error",
                                     "A system error occurred when downloading the file.");
                facesContext.addMessage(null, message);
            }
        }
        LOGGER.exiting(SOURCE_CLASS, "downloadContract");
    }

    public static void main(String[] s) {

    }
}

