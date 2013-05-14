package au.com.entitysolutions.taskflows.train.signup.ic.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;

import java.util.logging.Level;

import oracle.jbo.Row;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.binding.OperationBinding;

import oracle.javatools.parser.plsql.SqlKeywords;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.RowSetIterator;
import oracle.jbo.ViewObject;
import oracle.jbo.domain.BlobDomain;

import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.UploadedFile;


public class CertificatesStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        CertificatesStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);
    boolean incorpCertRqd = false;
    boolean workCoverCertRqd = false;
    boolean profIndemCertRqd = false;
    boolean publicLiabRqd = false;

    private UploadedFile incorpCert;
    private UploadedFile workCoverCert;
    private UploadedFile profIndemCert;
    private UploadedFile publicLiab;
    
    private InputStream incorpCertStream;
    private InputStream workCoverCertStream;
    private InputStream profIndemCertStream;
    private InputStream publicLiabStream;

    private Object workCoverExpiry;
    private Object profIndemExpiry;
    private Object publicLiabExpiry;

    private RichInputFile incorpCertIn;
    private RichInputFile workCoverCertIn;
    private RichInputFile profIndemCertIn;
    private RichInputFile publicLiabIn;

    public CertificatesStopBean() {
        super();
    }

    /**
     * Perform all the validations required by the Certificates Stop Bean
     */
    public boolean validate() {
        boolean isValid = true;
        FacesContext context = FacesContext.getCurrentInstance();

        Boolean isOffline =
            (Boolean)FacesUtil.resolveExpression("#{bindings.SendOffline.inputValue}");
        //Check if all the mandatory files are present
        //System.out.println("Work Cover"+ workCoverExpiry.getClass());
        if (isOffline &&
            (this.incorpCert != null || this.workCoverCert != null ||
             this.profIndemCert != null || this.publicLiab != null)) {
            isValid = false;
            this.resetComponents();
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Document Upload",
                                 "Please do not upload documents when the offline option is selected.");
            context.addMessage(null, message);
        } else if (!isOffline) {
            if (incorpCertRqd) {
                //Certificate of incorporation
                Number incorpDocId =
                    (Number)FacesUtil.resolveExpression("#{bindings.IncorpCertDocCntntId.inputValue}");
                if (incorpDocId == null && this.incorpCert == null) {
                    isValid = false;
                    FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                         "Document Required",
                                         "Please upload a Certificate of Incorporation");
                    context.addMessage(this.incorpCertIn.getId(), message);
                }
            }

            if (this.incorpCert != null) {
                boolean validIncorpCert=FileUtils.validateUploadFile(context, this.incorpCert);
                
                if(!validIncorpCert){
                   this.resetDocIncorp();
                }                
                isValid =isValid && validIncorpCert;
            }

            if (workCoverCertRqd) {
                //Worker's Cover
                Number workCoverDocId =
                    (Number)FacesUtil.resolveExpression("#{bindings.WrkrsCompDocCntntId.inputValue}");
                if (workCoverDocId == null && this.workCoverCert == null) {
                    isValid = false;
                    FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                         "Document Required",
                                         "Please upload a Workcover Certificate");
                    context.addMessage(this.workCoverCertIn.getId(), message);
                }
            }

            if (this.workCoverCert != null && this.workCoverExpiry == null) {
                isValid = false;
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Expiry Date",
                                     "Please enter an expiry date for Workcover Certificate");
                context.addMessage(this.workCoverCertIn.getId(), message);
            } else if (this.workCoverCert != null) {              
                boolean validWorkCover=FileUtils.validateUploadFile(context, this.workCoverCert);
                
                if(!validWorkCover){
                   this.resetDocWorkCover();
                }                
                isValid =isValid && validWorkCover;              
            }

            if (profIndemCertRqd) {
                //Professional Indemnity Cover
                Number profIndemCertDocId =
                    (Number)FacesUtil.resolveExpression("#{bindings.ProfIndmDocCntntId.inputValue}");
                if (profIndemCertDocId == null && this.profIndemCert == null) {
                    isValid = false;
                    FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                         "Document Required",
                                         "Please upload a Professional Indemnity Insurance Certificate");
                    context.addMessage(null, message);
                }
            }

            if (this.profIndemCert != null && this.profIndemExpiry == null) {
                isValid = false;
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Expiry Date",
                                     "Please enter an expiry date for Professional Indemnity Insurance Certificate");
                context.addMessage(null, message);
            } else if (this.profIndemCert != null) {
                boolean validProf =FileUtils.validateUploadFile(context, this.profIndemCert);
                
                if(!validProf){
                   this.resetDocProf();
                }                   
                isValid=isValid && validProf;                
            }

            if (publicLiabRqd) {
                //Public Liability
                Number publicLiabDocId =
                    (Number)FacesUtil.resolveExpression("#{bindings.PublLiabDocCntntId.inputValue}");
                if (publicLiabDocId == null && this.publicLiab == null) {
                    isValid = false;
                    FacesMessage message =
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                         "Document Required",
                                         "Please upload a Public Liability Insurance Certificate");
                    context.addMessage(null, message);
                }
            }

            if (this.publicLiab != null && this.publicLiabExpiry == null) {
                isValid = false;
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Expiry Date",
                                     "Please enter an expiry date for Public Liability Insurance Certificate");
                context.addMessage(null, message);
            } else if (this.publicLiab != null) {
                boolean validPublic=FileUtils.validateUploadFile(context, this.publicLiab);
                
                if(!validPublic){
                  this.resetDocPublic();
                }
                isValid=isValid && validPublic;                
            }
        }
      
        return isValid;
    }

    /**
     * Reset the file input component
     */
    public void resetComponents() {
        this.incorpCert = null;
        this.incorpCertIn.resetValue();
        this.incorpCertIn.setValue(null);

        this.workCoverCert = null;
        this.workCoverCertIn.resetValue();
        this.workCoverCertIn.setValue(null);

        this.profIndemCert = null;
        this.profIndemCertIn.resetValue();
        this.profIndemCertIn.setValue(null);

        this.publicLiab = null;
        this.publicLiabIn.resetValue();
        this.publicLiabIn.setValue(null);
    }
    
    public void resetDocIncorp(){
        this.incorpCert = null;
        this.incorpCertIn.resetValue();
        this.incorpCertIn.setValue(null);
    }
    
    public void resetDocWorkCover(){
        this.workCoverCert = null;
        this.workCoverCertIn.resetValue();
        this.workCoverCertIn.setValue(null);
    }
    
    public void resetDocProf(){
        this.profIndemCert = null;
        this.profIndemCertIn.resetValue();
        this.profIndemCertIn.setValue(null);
    }
    
    public void resetDocPublic(){
        this.publicLiab = null;
        this.publicLiabIn.resetValue();
        this.publicLiabIn.setValue(null);
    }

    /**
     * Process date submission for the certificates page stop
     */
    public boolean processData() {
        FacesContext context = FacesContext.getCurrentInstance();
        DCBindingContainer bindings = getBindings();
        boolean result = true;
        oracle.jbo.domain.Number docContentId = null;
        try {
            Boolean isOffline =
                (Boolean)FacesUtil.resolveExpression("#{bindings.SendOffline.inputValue}");
            DCIteratorBinding iterator =
                bindings.findIteratorBinding("PortalSignupIcCertificatesViewIterator");
            Row currentRow = iterator.getCurrentRow();
            LOGGER.finest("Current row:" + currentRow);
            if (!isOffline) {
                /* *************************************
                 * Certificate of Incorporation
                 * *************************************
                 */

                if (incorpCert != null) {
                    BlobDomain contractBlob =
                        FileUtils.createBlobDomain(this.incorpCert,this.incorpCertStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 contractBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 incorpCert.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 incorpCert.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 incorpCert.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_INCORPORATION']}"));
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");
                    docContentId =
                            (oracle.jbo.domain.Number)operationBinding.execute();
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        result = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }

                    if (result && currentRow != null) {
                        LOGGER.finest("Set IncorpCertDocCntntId to the newly created value");
                        currentRow.setAttribute("IncorpCertDocCntntId",
                                                docContentId);
                    }
                    LOGGER.finest("Returned docContentId:" + docContentId);
                }

                /* *************************************
                 * Workers Comp
                 * *************************************
                 */
                if (workCoverCert != null) {
                    BlobDomain contractBlob =
                        FileUtils.createBlobDomain(this.workCoverCert,this.workCoverCertStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 contractBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 workCoverCert.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 workCoverCert.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 workCoverCert.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_WORKCOVER']}"));
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");
                    docContentId =
                            (oracle.jbo.domain.Number)operationBinding.execute();
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        result = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }

                    if (result && currentRow != null) {
                        LOGGER.finest("Set WrkrsCompDocCntntId to the newly created value");
                        currentRow.setAttribute("WrkrsCompDocCntntId",
                                                docContentId);
                        currentRow.setAttribute("WrkrsCompExpiry",
                                                this.workCoverExpiry);
                    }
                    LOGGER.finest("Returned docContentId:" + docContentId);
                }

                /* *************************************
                 * Prof Indemnity
                 * *************************************
                 */

                if (profIndemCert != null) {
                    BlobDomain contractBlob =
                        FileUtils.createBlobDomain(this.profIndemCert,this.profIndemCertStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 contractBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 profIndemCert.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 profIndemCert.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 profIndemCert.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_PROF_INDEM']}"));
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");
                    docContentId =
                            (oracle.jbo.domain.Number)operationBinding.execute();
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        result = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }

                    if (result && currentRow != null) {
                        LOGGER.finest("Set ProfIndmDocCntntId to the newly created value");
                        currentRow.setAttribute("ProfIndmDocCntntId",
                                                docContentId);
                        currentRow.setAttribute("ProfIndmExpiry",
                                                this.profIndemExpiry);
                    }
                    LOGGER.finest("Returned docContentId:" + docContentId);
                }


                /* *************************************
                 * Public Liability
                 * *************************************
                 */
                if (publicLiab != null) {
                    BlobDomain publicLiabBlob =
                        FileUtils.createBlobDomain(this.publicLiab,this.publicLiabStream);
                    LOGGER.finest("Blob Created");
                    FacesUtil.setExpressionValue("#{requestScope.file_content}",
                                                 publicLiabBlob);
                    FacesUtil.setExpressionValue("#{requestScope.file_length}",
                                                 publicLiab.getLength());
                    FacesUtil.setExpressionValue("#{requestScope.file_mime}",
                                                 publicLiab.getContentType());
                    FacesUtil.setExpressionValue("#{requestScope.file_name}",
                                                 publicLiab.getFilename());
                    FacesUtil.setExpressionValue("#{requestScope.doc_name}",
                                                 FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_PUBL_LIAB']}"));
                    OperationBinding operationBinding =
                        bindings.getOperationBinding("uploadDoc");
                    docContentId =
                            (oracle.jbo.domain.Number)operationBinding.execute();
                    if (operationBinding.getErrors() != null &&
                        !operationBinding.getErrors().isEmpty()) {
                        result = false;
                        LOGGER.severe("Errors returned from uploadDoc AM call");
                    }

                    if (result && currentRow != null) {
                        LOGGER.finest("Set PublLiabDocCntntId to the newly created value");
                        currentRow.setAttribute("PublLiabDocCntntId",
                                                docContentId);
                        currentRow.setAttribute("PublLiabExpiry",
                                                this.publicLiabExpiry);
                    }
                    LOGGER.finest("Returned docContentId:" + docContentId);
                }
            } else {
                //Clear the documents
                OperationBinding operationBinding =
                    bindings.getOperationBinding("removeDoc");
                Object obj = operationBinding.execute();
                if (operationBinding.getErrors() != null &&
                    !operationBinding.getErrors().isEmpty()) {
                    result = false;
                    LOGGER.severe("Errors returned from removeDoc AM call");
                }
                if (result && currentRow != null) {
                    LOGGER.finest("Set DocContentId to NULL");
                    currentRow.setAttribute("PublLiabDocCntntId", null);
                    currentRow.setAttribute("ProfIndmDocCntntId", null);
                    currentRow.setAttribute("WrkrsCompDocCntntId", null);
                    currentRow.setAttribute("IncorpCertDocCntntId", null);
                    currentRow.setAttribute("PublLiabExpiry", null);
                    currentRow.setAttribute("WrkrsCompExpiry", null);
                    currentRow.setAttribute("ProfIndmExpiry", null);
                }
            }

        } catch (java.io.IOException ioe) {
            result = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File Upload Error",
                                 "An error while uploading the file. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Exception occurred", ioe);
        } catch (Exception exc) {
            result = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
                                 "An error while saving the data. Please try again");
            context.addMessage(null, message);
            LOGGER.log(Level.SEVERE, "Exception occurred", exc);
        }
        
        if(result){
          this.resetComponents();
        }       
       
        return result;
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATES_STEP_NAME']}");
    }

    /**
     * This method sets up the required documents flags
     */
    public void setupRequiredDocuments() {
        LOGGER.entering(SOURCE_CLASS, "setupRequiredFields");
        DCBindingContainer bindings = getBindings();
        DCIteratorBinding iter =
            bindings.findIteratorBinding("PortalUserTFStepDocSQLViewIterator");
        ViewObject vo = iter.getViewObject();
        RowSetIterator rowIterator = vo.createRowSetIterator(null);
        String incorpCert =
            (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_INCORPORATION']}");
        LOGGER.info("Incorp Cert Doc Name:" + incorpCert);
        String wrkCoverCert =
            (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_WORKCOVER']}");
        LOGGER.info("wrkCoverCert Doc Name:" + wrkCoverCert);
        String profIndemCert =
            (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_PROF_INDEM']}");
        LOGGER.info("profIndemCert Doc Name:" + profIndemCert);
        String publLiabCert =
            (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CERTIFICATE_PUBL_LIAB']}");
        LOGGER.info("publLiabCert Doc Name:" + publLiabCert);
        while (rowIterator.hasNext()) {
            LOGGER.info("next");
            Row row = rowIterator.next();
            String docName = (String)row.getAttribute("DocName");
            String mandatory = (String)row.getAttribute("Mandatory");
            LOGGER.info("DocName: " + docName);
            if (docName.equals(incorpCert)) {
                LOGGER.info("Incorp Cert:" + mandatory);
                setIncorpCertRqd("Y".equals(mandatory));
            } else if (docName.equals(wrkCoverCert)) {
                LOGGER.info("workcover Cert:" + mandatory);
                setWorkCoverCertRqd("Y".equals(mandatory));
            } else if (docName.equals(profIndemCert)) {
                LOGGER.info("profIndemCert:" + mandatory);
                setProfIndemCertRqd("Y".equals(mandatory));
            } else if (docName.equals(publLiabCert)) {
                LOGGER.info("publLiabCert:" + mandatory);
                setPublicLiabRqd("Y".equals(mandatory));
            }
        }
        rowIterator.closeRowSetIterator();
        LOGGER.exiting(SOURCE_CLASS, "setupRequiredFields");
    }

    public void setIncorpCertRqd(boolean incorpCertRqd) {
        this.incorpCertRqd = incorpCertRqd;
    }

    public boolean isIncorpCertRqd() {
        return incorpCertRqd;
    }

    public void setWorkCoverCertRqd(boolean workCoverCertRqd) {
        this.workCoverCertRqd = workCoverCertRqd;
    }

    public boolean isWorkCoverCertRqd() {
        return workCoverCertRqd;
    }

    public void setProfIndemCertRqd(boolean profIndemCertRqd) {
        this.profIndemCertRqd = profIndemCertRqd;
    }

    public boolean isProfIndemCertRqd() {
        return profIndemCertRqd;
    }

    public void setPublicLiabRqd(boolean publicLiabRqd) {
        this.publicLiabRqd = publicLiabRqd;
    }

    public boolean isPublicLiabRqd() {
        return publicLiabRqd;
    }

    public void setIncorpCert(UploadedFile incorpCert) throws IOException {
        this.incorpCert = incorpCert;
        
        if(this.incorpCert !=null){
              this.incorpCertStream=this.incorpCert.getInputStream();
        }
        
    }

    public UploadedFile getIncorpCert() {
        return incorpCert;
    }

    public void setWorkCoverCert(UploadedFile workCoverCert) throws IOException {
        this.workCoverCert = workCoverCert;
        
      if(this.workCoverCert !=null){
            this.workCoverCertStream=this.workCoverCert.getInputStream();
      }        
    }

    public UploadedFile getWorkCoverCert() {
        return workCoverCert;
    }

    public void setProfIndemCert(UploadedFile profIndemCert) throws IOException {
        this.profIndemCert = profIndemCert;
        
      if(this.profIndemCert !=null){
            this.profIndemCertStream=this.profIndemCert.getInputStream();
      }        
        
    }

    public UploadedFile getProfIndemCert() {
        return profIndemCert;
    }

    public void setPublicLiab(UploadedFile publicLiab) throws IOException {
        this.publicLiab = publicLiab;
        
      if(this.publicLiab !=null){
            this.publicLiabStream=this.publicLiab.getInputStream();
      }        
    }

    public UploadedFile getPublicLiab() {
        return publicLiab;
    }

    public void setIncorpCertIn(RichInputFile incorpCertIn) {
        this.incorpCertIn = incorpCertIn;
    }

    public RichInputFile getIncorpCertIn() {
        return incorpCertIn;
    }

    public void setWorkCoverCertIn(RichInputFile workCoverCertIn) {
        this.workCoverCertIn = workCoverCertIn;
    }

    public RichInputFile getWorkCoverCertIn() {
        return workCoverCertIn;
    }

    public void setProfIndemCertIn(RichInputFile profIndemCertIn) {
        this.profIndemCertIn = profIndemCertIn;
    }

    public RichInputFile getProfIndemCertIn() {
        return profIndemCertIn;
    }

    public void setPublicLiabIn(RichInputFile publicLiabIn) {
        this.publicLiabIn = publicLiabIn;
    }

    public RichInputFile getPublicLiabIn() {
        return publicLiabIn;
    }

    public void setWorkCoverExpiry(Object workCoverExpiry) {
        this.workCoverExpiry = workCoverExpiry;
    }

    public Object getWorkCoverExpiry() {
        return workCoverExpiry;
    }

    public void setProfIndemExpiry(Object profIndemExpiry) {
        this.profIndemExpiry = profIndemExpiry;
    }

    public Object getProfIndemExpiry() {
        return profIndemExpiry;
    }

    public void setPublicLiabExpiry(Object publicLiabExpiry) {
        this.publicLiabExpiry = publicLiabExpiry;
    }

    public Object getPublicLiabExpiry() {
        return publicLiabExpiry;
    }
}
