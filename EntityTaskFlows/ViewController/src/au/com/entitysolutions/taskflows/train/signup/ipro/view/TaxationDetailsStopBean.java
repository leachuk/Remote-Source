package au.com.entitysolutions.taskflows.train.signup.ipro.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.common.view.ViewConstants;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class TaxationDetailsStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        TaxationDetailsStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);
    private UploadedFile signedTFNFile;
    private RichInputFile signedTFNFileInput;
    private InputStream tfnStream;
    

    public TaxationDetailsStopBean() {
        super();
    }

    /**
     * Perform validations required for Tax Details Stop
     * @return
     */
    public boolean validate() {
        LOGGER.entering(SOURCE_CLASS, "validate");
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isValid = true;
        Boolean offLine =
            (Boolean)FacesUtil.resolveExpression("#{bindings.TnfDocPostedOffline.inputValue}");
        Long rowCount =
            (Long)FacesUtil.resolveExpression("#{bindings.PortalUserTFStepUploadedDocsView.estimatedRowCount}");
        String austResident =
            (String)FacesUtil.resolveExpression("#{bindings.AustResident.attributeValue}");
        LOGGER.finest("Aust Resident:" + austResident);
        String taxFreeThreshold =
            (String)FacesUtil.resolveExpression("#{bindings.TaxfreeThresholdClaimed.attributeValue}");
        LOGGER.finest("Taxfree:" + taxFreeThreshold);
        
        String hecsDebt = (String)FacesUtil.resolveExpression("#{bindings.HecsDebt.attributeValue}");
        
        if (!("Y".equals(austResident) ||
              ("N".equals(austResident) && "N".equals(taxFreeThreshold)))) {
            LOGGER.finest("Validation error: Only Australian Residents may claim the taxfree threshold");
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File must be uploaded",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_AUST_RES_TAXFREE_ERROR']}"));
            context.addMessage(null, message);
        }
        
        if (!("Y".equals(austResident) ||
              ("N".equals(austResident) && "N".equals(hecsDebt)))) {
            LOGGER.finest("Validation error: Only Australian Residents may claim the taxfree threshold");
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File must be uploaded",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_AUST_RES_HECS_ERROR']}"));
            context.addMessage(null, message);
        }
        
        if (!offLine && rowCount < 1 && this.signedTFNFile == null) {
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File must be uploaded",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_EITHER_CHECKBOX_FILE_ERROR']}"));
            context.addMessage(null, message);
        } else if (this.signedTFNFile != null && offLine) {
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File must be uploaded",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_BOTH_CHECKBOX_FILE_ERROR']}"));
            this.resetComponents();
            context.addMessage(null, message);
        } else if (signedTFNFile != null) {
            //Validate file size and mime type
            LOGGER.finest("Validate the signed TFN Form");
            boolean fileValid = FileUtils.validateUploadFile(context, signedTFNFile);
            
            if (!fileValid) {
                //Reset the upload files
                this.resetComponents();
            }
            isValid=isValid && fileValid;
        }
        
        LOGGER.exiting(SOURCE_CLASS, "validate");
        return isValid;
    }

    /**
     * Process Data
     * @return
     */
    public boolean processData() {
        LOGGER.entering(SOURCE_CLASS, "processData");
        FacesContext context = FacesContext.getCurrentInstance();
        boolean isSuccess = true;
        Boolean offLine =
            (Boolean)FacesUtil.resolveExpression("#{bindings.TnfDocPostedOffline.inputValue}");
        DCBindingContainer bindings = getBindings();
        oracle.jbo.domain.Number docContentId = null;
        try {
            DCIteratorBinding iterator =
                bindings.findIteratorBinding("PortalSignupTaxDetailsViewIterator");
            Row currentRow = iterator.getCurrentRow();
            LOGGER.finest("Current row:" + currentRow);
            if (!offLine) {
                //File must be uploaded
                if (signedTFNFile != null) {
                    BlobDomain contractBlob =
                        FileUtils.createBlobDomain(this.signedTFNFile,this.tfnStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 contractBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 signedTFNFile.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 signedTFNFile.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 signedTFNFile.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_SIGNED_TFN_FORM_DOC']}"));
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");
                    docContentId =
                            (oracle.jbo.domain.Number)operationBinding.execute();
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        isSuccess = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }
                    LOGGER.finest("Returned docContentId:" + docContentId);
                    if (isSuccess && currentRow != null) {
                        LOGGER.finest("Set the DocumentContentId to new ID");
                        currentRow.setAttribute("DocumentContentId",
                                                docContentId);
                    }
                }
            } else if(offLine){
                //remove previously uploaded files
                LOGGER.finest("Offline chosen");
                FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                             FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_SIGNED_TFN_FORM_DOC']}"));
                OperationBinding operationBinding =
                    bindings.getOperationBinding("removeDoc");
                Object obj = operationBinding.execute();
                if (operationBinding.getErrors() != null &&
                    !operationBinding.getErrors().isEmpty()) {
                    isSuccess = false;
                    LOGGER.severe("Errors returned from removeDoc AM call");
                }
                if (isSuccess && currentRow != null) {
                    LOGGER.finest("Reset the DocContentId");
                    currentRow.setAttribute("DocumentContentId", null);
                }
            }
        } catch (IOException ioe) {
            isSuccess = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File Upload Error",
                                 "An error while uploading the file. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Exception occurred", ioe);
        } catch (Exception exc) {
            isSuccess = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                 "An error while saving the data. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Exception occurred", exc);
        }

        //Reset the files
        if(isSuccess){
          this.resetComponents();
        }       
        //Process documents here
        LOGGER.exiting(SOURCE_CLASS, "processData");
        return isSuccess;
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TAX_DTLS_STEP_NAME']}");
    }

    /**
     * Download Listener for the TFN document
     * @param facesContext
     * @param outputStream
     */
    public void downloadTFNDocument(FacesContext facesContext,
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

    public void setSignedTFNFile(UploadedFile signedTFNFile) {
        this.signedTFNFile = signedTFNFile;
        
        if(this.signedTFNFile!=null){
          try {
            this.tfnStream = this.signedTFNFile.getInputStream();
          } catch (IOException e) {
          }
        }        
    }

    public UploadedFile getSignedTFNFile() {
        return signedTFNFile;
    }

    public void setSignedTFNFileInput(RichInputFile signedTFNFileInput) {
        this.signedTFNFileInput = signedTFNFileInput;
    }

    public RichInputFile getSignedTFNFileInput() {
        return signedTFNFileInput;
    }

    public void signedTFNValueChange(ValueChangeEvent valueChangeEvent) {
        LOGGER.severe("********** ValueChangeEvent ");
    }

    public void resetComponents() {
        this.signedTFNFile = null;
        this.signedTFNFileInput.resetValue();
        this.signedTFNFileInput.setValue(null);
    }
}
