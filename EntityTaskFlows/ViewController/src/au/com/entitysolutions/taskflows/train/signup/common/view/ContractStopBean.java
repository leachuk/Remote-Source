package au.com.entitysolutions.taskflows.train.signup.common.view;


import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import com.bea.common.security.xacml.IOException;

import java.io.OutputStream;

import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.adfdt.model.objects.IteratorBinding;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class ContractStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        ContractStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    private RichInputFile contractFile = null;
    private String contractViewIteratorName="PortalSignupContractViewIterator";//default SignupContractView,but overwritten by Extension

    //public static final String STEP_NAME = "Contract";

    public ContractStopBean() {
        super();
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_STEP_NAME']}");
    }

    private UploadedFile signedContract;

    /**
     * Perform validations for the contract page
     * @return
     */
    public boolean validate() {
        LOGGER.entering(SOURCE_CLASS, "validate");
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;
        String sendType =
            (String)FacesUtil.resolveExpression("#{bindings.SendType.attributeValue}");
        LOGGER.finest("SendType:" + sendType);
        //If the ONLINE option is selected, there must either be an existing 
        //uploaded contract or a new one uploaded.
        Long rowCount = (Long)FacesUtil.resolveExpression("#{bindings.PortalUserTFStepUploadedDocsView.estimatedRowCount}");
        
      
        if ("ONLINE".equals(sendType) && signedContract == null && rowCount < 1) {
            LOGGER.finest("Validation error: File Required");    
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_ONLINE_FILE_ERROR']}"));
            FacesContext.getCurrentInstance().addMessage(this.getContractFile().getClientId(context),
                                                         message);
            isValid = false;
        }
        else if("OFFLINE".equals(sendType) && signedContract != null) {
                LOGGER.finest("Validation error: File Should not be uploaded");    
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload",
                                     (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_OFFLINE_FILE_ERROR']}"));
                FacesContext.getCurrentInstance().addMessage(this.getContractFile().getClientId(context),
                                                             message);
                isValid = false;                
        }
        else if(signedContract != null) {
            isValid = FileUtils.validateUploadFile(context, this.signedContract);                        
        }
        LOGGER.exiting(SOURCE_CLASS, "validate");
        if(!isValid) {
            this.resetComponents();
        }
        return isValid;
    }

    /**
     * process data for the contract stop
     * @return
     */
    public boolean processData() {

        LOGGER.entering(SOURCE_CLASS, "processData");
        FacesContext context = FacesContext.getCurrentInstance();
        DCBindingContainer bindings = getBindings();
        boolean result = true;
        oracle.jbo.domain.Number docContentId = null;
        try {
            //DCIteratorBinding iterator = bindings.findIteratorBinding("PortalSignupContractViewIterator");
            DCIteratorBinding iterator = bindings.findIteratorBinding(this.contractViewIteratorName);
            Row currentRow = iterator.getCurrentRow();
          
            LOGGER.finest("Current row:" + currentRow);
            String sendType =
                (String)FacesUtil.resolveExpression("#{bindings.SendType.attributeValue}");
           
          
            if ("ONLINE".equals(sendType) && signedContract != null) {
                //Execute the portal step view and fetch the portal step doc record
                /*this.processDocumentUpload("FetchContractDocForUpdate",
                                           "PortalUserTFStepDocViewIterator",
                                           "PortalDocContentViewIterator",
                                           signedContract);*/
                BlobDomain contractBlob =
                    FileUtils.createBlobDomain(signedContract);
                LOGGER.finest("Blob Created");
                FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                             contractBlob);
                FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                             signedContract.getLength());
                FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                             signedContract.getContentType());
                FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                             signedContract.getFilename());                
                FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                             FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_SIGNED_DOC_NAME']}"));
                System.out.println("************************ " + FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_SIGNED_DOC_NAME']}"));
                OperationBinding operationBinding =
                    bindings.getOperationBinding("uploadDoc");
                docContentId = (oracle.jbo.domain.Number)operationBinding.execute();
               
                if (operationBinding.getErrors() != null &&
                    !operationBinding.getErrors().isEmpty()) {
                    result = false;
                    LOGGER.severe("Errors returned from uploadDoc AM call");
                }
                
                if(result && currentRow != null) {
                    LOGGER.finest("Set DocumentContentId to the newly created value");
                    currentRow.setAttribute("DocumentContentId", docContentId);
                }
                LOGGER.finest("Returned docContentId:" + docContentId);
            } 
            else if ("OFFLINE".equals(sendType)){
                LOGGER.finest("Offline chosen");
                FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                             FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_SIGNED_DOC_NAME']}"));
                OperationBinding operationBinding =
                    bindings.getOperationBinding("removeDoc");
                Object obj = operationBinding.execute();
                if (operationBinding.getErrors() != null &&
                    !operationBinding.getErrors().isEmpty()) {
                    result = false;
                    LOGGER.severe("Errors returned from removeDoc AM call");
                }
                if(result && currentRow != null) {
                    LOGGER.finest("Set DocumentContentId to NULL");
                    currentRow.setAttribute("DocumentContentId", null);
                }                
            }
        } 
        catch(java.io.IOException ioe) {
            result = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload Error", "An error while uploading the file. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Exception occurred", ioe);
        }
        catch (Exception exc) {
            result = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error while saving the data. Please try again");
            context.addMessage(null, message);            
            LOGGER.log(Level.SEVERE, "Exception occurred", exc);
        }

        if(result) {
             LOGGER.finest("Insert docContentId into PORTAL_SIGNUP_CONTRACT");
            
           
                
        }
        //Reset to null (even if there is error)
        this.resetComponents();        
        LOGGER.exiting(SOURCE_CLASS, "processData");
        return result;
    }

    public void setSignedContract(UploadedFile signedContract) {       
        this.signedContract = signedContract;
    }

    public UploadedFile getSignedContract() {
        return signedContract;
    }

    /**
     * Value change listener for the send type radio button
     * @param valueChangeEvent
     */
    public void sendTypeValueChange(ValueChangeEvent valueChangeEvent) {
        LOGGER.entering(SOURCE_CLASS, "sendTypeValueChange");
     
        this.signedContract = null;
        this.getContractFile().resetValue();
        this.getContractFile().setValue(null);
        LOGGER.exiting(SOURCE_CLASS, "sendTypeValueChange");
    }
    
    /**
     * Download listener for contract file
     * @param facesContext
     * @param outputStream
     */
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
        if(rows == null || rows.length < 1) {
            LOGGER.severe("No records found in download iterator");
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Download Error",
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
            } catch (java.io.IOException ioe) {
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

    public void setContractFile(RichInputFile contractFile) {
    
        this.contractFile = contractFile;
    }

    public RichInputFile getContractFile() {
        return contractFile;
    }
    
    /**
     * Reset the file input component
     */
    public void resetComponents() {
        this.signedContract = null;
        this.getContractFile().resetValue();
        this.getContractFile().setValue(null);
    }


  public void setContractViewIteratorName(String contractViewIteratorName) {
    this.contractViewIteratorName = contractViewIteratorName;
  }

  public String getContractViewIteratorName() {
    return contractViewIteratorName;
  }
}

