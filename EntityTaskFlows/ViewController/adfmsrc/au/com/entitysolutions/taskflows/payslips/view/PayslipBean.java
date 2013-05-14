package au.com.entitysolutions.taskflows.payslips.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;

import java.io.IOException;
import java.io.OutputStream;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

public class PayslipBean {
   
    private static String SOURCE_CLASS =
        au.com.entitysolutions.taskflows.payslips.view.PayslipBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);
    
    public PayslipBean() {
        super();
    }
    
    public void downloadingPayslip(FacesContext facesContext,
                                 OutputStream outputStream) {
        // Add event code here..
        LOGGER.entering(SOURCE_CLASS, "downloadingPayslip");
        BindingContainer bindings = getBindings();
        DCIteratorBinding headerIteratorBinding =
            getBindings().findIteratorBinding("PaylsipsView1Iterator");
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
        BlobDomain blob = null;
        for (int ctr = 0; ctr < rows.length; ctr++) {                      
            if (rows[ctr].getAttribute("PayslipId") == FacesUtil.resolveExpression("#{requestScope.payslip_id}")) {                
                blob = (BlobDomain)rows[ctr].getAttribute("PayslipContent"); 
            }
        }        
        if (blob != null) {
            try {
                FileUtils.writeBlobToStream(blob, outputStream);
            } catch (IOException ioe) {
                LOGGER.log(Level.SEVERE, "Error downloading payslip", ioe);
                FacesMessage message =
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Download Error",
                                 "A system error occurred when downloading the file.");
                facesContext.addMessage(null, message);
            }
        }
        LOGGER.exiting(SOURCE_CLASS, "downloadingPayslip");
    }
    
    /****** HELPER METHODS ********/
    public DCBindingContainer getBindings() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }
}
