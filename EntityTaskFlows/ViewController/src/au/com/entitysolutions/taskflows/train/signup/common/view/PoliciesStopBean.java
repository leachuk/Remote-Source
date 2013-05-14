package au.com.entitysolutions.taskflows.train.signup.common.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.OutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.binding.BindingContainer;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;
public class PoliciesStopBean extends TaskflowTrainStopBean {
    private static String SOURCE_CLASS =
        PoliciesStopBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);
    
    private boolean policyViewed = false;
    
    private BlobDomain temporaryBlobStore;
    private RichCommandButton policyDownloadButton;

    public PoliciesStopBean() {
        super();
    }

    public boolean validate() {
        LOGGER.entering(SOURCE_CLASS,"validate");
        boolean isValid = true;
        FacesContext context = FacesContext.getCurrentInstance();
        Boolean acceptPolicy = (Boolean)FacesUtil.resolveExpression("#{bindings.AceeptPolicies.inputValue}");
        if(!acceptPolicy) {
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Accept Policy",
                                 (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['POLICY_DTLS_ACCEPT_ERROR']}"));
            context.addMessage(null, message);
        }
        LOGGER.exiting(SOURCE_CLASS,"validate");
        return isValid;
    }

    public boolean processData() {
        return true;
    }

    public String getStepName() {
        return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['POLICY_DTLS_STEP_NAME']}");
    }
    
    /**
     * Download listener for Policies File
     * @param facesContext
     * @param outputStream
     */
    public void downloadPolicies(FacesContext facesContext,
                                 OutputStream outputStream) {      
        FacesContext context = FacesContext.getCurrentInstance();
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
            context.addMessage(null, message);
        }
        else
        {
            for (int ctr = 0; ctr < rows.length; ctr++) {
                LOGGER.finest("Found row");
                LOGGER.finest("row.DocName:" + rows[ctr].getAttribute("DocName"));
                LOGGER.finest("row.DocSubType:" +
                              rows[ctr].getAttribute("DocSubType"));
                LOGGER.finest("row.UserTfStepId:" +
                              rows[ctr].getAttribute("UserTfStepId"));
                temporaryBlobStore = (BlobDomain)rows[ctr].getAttribute("DocContent");
            }                    
        }
      
         try {
                FileUtils.writeBlobToStream(temporaryBlobStore, outputStream);
                policyViewed = true;
                setPolicyViewed(true);
            } catch (java.io.IOException ioe) {
                LOGGER.log(Level.SEVERE, "Error downloading contract", ioe);
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                     "Download Error",
                                     "A system error occurred when downloading the file.");
                facesContext.addMessage(null, message);
            }            
        //}
        LOGGER.exiting(SOURCE_CLASS, "downloadPolicies");
    }
    
    /**
     * Action listener to reset the checkbox and queue an action on the fileDownloadListener
     * @param actionEvent
     */
    public void initiateDownload(ActionEvent actionEvent) {
        LOGGER.entering(SOURCE_CLASS, "initiateDownload");
        FacesContext context = FacesContext.getCurrentInstance();
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
            context.addMessage(null, message);
        }
        else
        {
            for (int ctr = 0; ctr < rows.length; ctr++) {
                LOGGER.finest("Found row");
                LOGGER.finest("row.DocName:" + rows[ctr].getAttribute("DocName"));
                LOGGER.finest("row.DocSubType:" +
                              rows[ctr].getAttribute("DocSubType"));
                LOGGER.finest("row.UserTfStepId:" +
                              rows[ctr].getAttribute("UserTfStepId"));
                temporaryBlobStore = (BlobDomain)rows[ctr].getAttribute("DocContent");
            }        
            setPolicyViewed(true);
//            String dloadButton =
//                policyDownloadButton.getClientId(context);
//            StringBuilder script = new StringBuilder();
//            script.append("var exportCmd = AdfPage.PAGE.findComponentByAbsoluteId('" +
//                          dloadButton + "'); ");
//            script.append("var actionEvent = new AdfActionEvent(exportCmd); ");
//            script.append("actionEvent.forceFullSubmit(); ");
//            script.append("actionEvent.noResponseExpected(); ");
//            script.append("actionEvent.queue(); ");
//            ExtendedRenderKitService erks =
//                Service.getService(context.getRenderKit(),
//                                   ExtendedRenderKitService.class);
//            erks.addScript(context, script.toString());          
              
        }
        LOGGER.exiting(SOURCE_CLASS, "initiateDownload");
    }
    

    public void setPolicyViewed(boolean policyViewed) {
        this.policyViewed = policyViewed;
    }

    public boolean isPolicyViewed() {
        return policyViewed;
    }

    public void setPolicyDownloadButton(RichCommandButton policyDownloadButton) {
        this.policyDownloadButton = policyDownloadButton;
    }

    public RichCommandButton getPolicyDownloadButton() {
        return policyDownloadButton;
    }
}
