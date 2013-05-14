package au.com.entitysolutions.taskflows.train.signup.common.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.IOException;

import java.io.InputStream;

import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class ResidencyDetailsStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        ResidencyDetailsStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    private RichInputFile proof1FileInput = null;
    private UploadedFile proof1File = null;
    private String proof1Type = null;
    private RichInputFile proof2FileInput = null;
    private UploadedFile proof2File = null;
    private String proof2Type = null;
    
    private InputStream proof1FileStream;
    private InputStream proof2FileStream;

    public ResidencyDetailsStopBean() {
        super();
    }

    public boolean validate() {
        boolean result = true;
        //TODO: Check at least one file is uploaded if the confirm true copy option is chosen
        //And vice-versa.
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean confirmTrueCopy =
            (Boolean)FacesUtil.resolveExpression("#{bindings.ConfirmTrueCopy.inputValue}");
        Boolean alreadySubmitted =
            (Boolean)FacesUtil.resolveExpression("#{bindings.AlreadySubmitted.inputValue}");
        LOGGER.finest("Confirm True Copy:" + confirmTrueCopy);
        LOGGER.finest("Already Submitted:" + alreadySubmitted);

        Long rowCount = (Long)FacesUtil.resolveExpression("#{bindings.PortalUserTFStepUploadedDocsView.estimatedRowCount}");

        LOGGER.finest("Uploaded files Row Count:" + rowCount);
        boolean uploadedDocs = rowCount > 0;
        boolean atleastOneFile = this.proof1File != null || this.proof2File != null;
        LOGGER.finest("atleastOneFile:" + atleastOneFile);

        if (!(confirmTrueCopy || alreadySubmitted)) {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tick a check box",
                                (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['RESIDENCY_DTLS_ATLEAST_ONE_CHECKBOX']}"));
            context.addMessage(null, msg);
            result = false;
        }
        else if (confirmTrueCopy && alreadySubmitted) {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tick only one check box",
                                (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['RESIDENCY_DTLS_ONLY_ONE_CHECKBOX']}"));
            context.addMessage(null, msg);
            result = false;
        }
        else if (confirmTrueCopy && !(atleastOneFile || uploadedDocs)){
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Atleast one file",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['RESIDENCY_DTLS_ATLEAST_ONE_FILE_ERROR']}"));
            context.addMessage(null, msg);
            result = false;
        }
        else if(atleastOneFile && !confirmTrueCopy ) {
            FacesMessage msg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tick the confirm true copy",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['RESIDENCY_DTLS_CONF_TRUE_COPY_ERROR']}"));
            context.addMessage(null, msg);
            result = false;
        }
        else
        {
            LOGGER.finest("Validate the files");            
            if (this.proof1File != null) {                
                String proof1T = (String)FacesUtil.resolveExpression("#{bindings.Proof1DocName.attributeValue}");
                LOGGER.info("-----" + proof1T);
                if(proof1T == null || proof1T.length() == 0) {
                    result = false;
                    FacesMessage msg =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select document type",
                                         (String)FacesUtil.resolveExpression("Please select a document type when uploading the file."));
                    context.addMessage(null, msg);
                }
                else
                {
                //String proof2T = #{bindings.Proof2DocName.inputValue}
                    boolean resultFile1 = FileUtils.validateUploadFile(context, this.proof1File);
                    if(!resultFile1) {
                        this.resetDocProof1();
                    }
                    result=result && resultFile1;
                } 
            }
            if (result && this.proof2File != null) {                
                String proof2T = (String)FacesUtil.resolveExpression("#{bindings.Proof2DocName.attributeValue}");
                LOGGER.info("-----" + proof2T);
                if(proof2T == null || proof2T.length() == 0) {
                    result = false;
                    FacesMessage msg =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select document type",
                                         (String)FacesUtil.resolveExpression("Please select a document type when uploading the file."));
                    context.addMessage(null, msg);
                }
                else
                {
                    boolean resultFile2 = FileUtils.validateUploadFile(context, this.proof2File);
                    if(!resultFile2) {
                       this.resetDocProof2();
                    }
                    result=result && resultFile2;
                }
            }
        }

      
        return result;
    }

    /**
     * Process documents on the residency page
     */
    public boolean processData() {
        LOGGER.entering(SOURCE_CLASS, "processData");
        LOGGER.finest("***************** Proof 1 type:" + proof1Type);
        boolean isSuccess = true;
        FacesContext context = FacesContext.getCurrentInstance();
        DCBindingContainer bindings = getBindings();
        Boolean confirmTrueCopy =
            (Boolean)FacesUtil.resolveExpression("#{bindings.ConfirmTrueCopy.inputValue}");
        Boolean alreadySubmitted =
            (Boolean)FacesUtil.resolveExpression("#{bindings.AlreadySubmitted.inputValue}");
        oracle.jbo.domain.Number docContentId1 = null;
        oracle.jbo.domain.Number docContentId2 = null;

        try {
            DCIteratorBinding iterator = bindings.findIteratorBinding("PortalSignupResidencyViewIterator");
            Row currentRow = iterator.getCurrentRow();
            LOGGER.finest("Current row:" + currentRow);

            if(confirmTrueCopy) {
                //Upload documents
                //Proof 1 if present
                if(proof1File != null) {
                    LOGGER.finest("Upload first document");
                    BlobDomain prooflFileBlob =
                        FileUtils.createBlobDomain(this.proof1File,this.proof1FileStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 prooflFileBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 proof1File.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 proof1File.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 proof1File.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{bindings.Proof1DocName.attributeValue}"));

                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");

                    docContentId1 = (oracle.jbo.domain.Number)operationBinding.execute();
                    LOGGER.finest("DocContentId1:" + docContentId1);
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        isSuccess = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }

                    if(isSuccess && currentRow != null) {
                        LOGGER.finest("Current row exists");
                        LOGGER.finest("Set DocContentId1 to new ID");
                        currentRow.setAttribute("DocContentId1", docContentId1);
                    }
                }

                //Upload second proof
                if(isSuccess && proof2File != null) {
                    BlobDomain proof2FileBlob =
                        FileUtils.createBlobDomain(this.proof2File,this.proof2FileStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 proof2FileBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 proof2File.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 proof2File.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 proof2File.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{bindings.Proof2DocName.attributeValue}"));

                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");

                    docContentId2 = (oracle.jbo.domain.Number)operationBinding.execute();
                    LOGGER.finest("DocContentId2:" + docContentId2);
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        isSuccess = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }

                    if(isSuccess && currentRow != null) {
                        LOGGER.finest("Set DocContentId2 to new ID");
                        currentRow.setAttribute("DocContentId2", docContentId2);
                    }
                }
            }
            else {
                //remove doc?
                //Should ideally remove all the docs?
                //FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                //                             FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTRACT_DTLS_SIGNED_DOC_NAME']}"));
                OperationBinding operationBinding =
                    bindings.getOperationBinding("removeDoc");
                Object obj = operationBinding.execute();
                if (operationBinding.getErrors() != null &&
                    !operationBinding.getErrors().isEmpty()) {
                    isSuccess = false;
                    LOGGER.severe("Errors returned from removeDoc AM call");
                }
                if(isSuccess && currentRow != null) {
                    LOGGER.finest("Set DocContentId to NULL");
                    currentRow.setAttribute("DocContentId1", null);
                    currentRow.setAttribute("DocContentId2", null);
                }
            }
        }
        catch(IOException ioe) {
            isSuccess = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload Error", "An error while uploading the file. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Exception occurred", ioe);
        }
        catch(Exception sqle) {
            isSuccess = false;
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error while saving the data. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Error uploading document", sqle);
        }
        finally {

        }
        if(isSuccess) {
            LOGGER.finest("Set the doc content ids into the residency table");
            this.resetComponents();
        }
        
        LOGGER.exiting(SOURCE_CLASS, "processData");
        return isSuccess;
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['RESIDENCY_DTLS_STEP_NAME']}");
    }

    public void setProof1FileInput(RichInputFile proof1FileInput) {
        this.proof1FileInput = proof1FileInput;
    }

    public RichInputFile getProof1FileInput() {
        return proof1FileInput;
    }

    public void setProof1File(UploadedFile proof1File) {
        this.proof1File = proof1File;     
        
       if(this.proof1File!=null){
        try {         
          this.proof1FileStream =proof1File.getInputStream();
        } catch (IOException e) {
        }
       }
    }

    public UploadedFile getProof1File() {
        return proof1File;
    }

    public void setProof1Type(String proof1Type) {
        this.proof1Type = proof1Type;
    }

    public String getProof1Type() {
        return proof1Type;
    }

    public void setProof2FileInput(RichInputFile proof2FileInput) {
        this.proof2FileInput = proof2FileInput;
    }

    public RichInputFile getProof2FileInput() {
        return proof2FileInput;
    }

    public void setProof2File(UploadedFile proof2File) {
        this.proof2File = proof2File;
        
      if(this.proof2File!=null){
       try {
         this.proof2FileStream = this.proof2File.getInputStream();
       } catch (IOException e) {
       }
      }  
    }

    public UploadedFile getProof2File() {
        return proof2File;
    }

    public void setProof2Type(String proof2Type) {
        this.proof2Type = proof2Type;
    }

    public String getProof2Type() {
        return proof2Type;
    }

    public void resetComponents() {
        proof1File = null;
        proof1FileInput.setValue(null);
        proof1FileInput.resetValue();
        proof2File = null;
        proof2FileInput.setValue(null);
        proof2FileInput.resetValue();
    }
    
    public void resetDocProof1(){
      proof1File = null;
      proof1FileInput.setValue(null);
      proof1FileInput.resetValue();
    }
    
    public void resetDocProof2(){
      proof2File = null;
      proof2FileInput.setValue(null);
      proof2FileInput.resetValue();
    }
}
