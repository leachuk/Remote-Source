package au.com.entitysolutions.taskflows.train.view;

import au.com.entitysolutions.taskflows.common.view.FileUtils;

import java.io.IOException;

import java.sql.SQLException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.myfaces.trinidad.model.UploadedFile;

public abstract class TaskflowTrainStopBean {

    private static final String SOURCE_CLASS =
        TaskflowTrainStopBean.class.getCanonicalName();
    private static final Logger LOGGER = Logger.getLogger(SOURCE_CLASS);


    public TaskflowTrainStopBean() {
        super();
    }

    /**
     * Perform validations related to the stop. For errors, create and add
     * FacesMessage to the context
     * @return true if no validation errors, false if there are errors.
     */
    public abstract boolean validate();

    /**
     * Perform any other changes required. For e.g. creating and setting up a
     * BlobDomain attribute
     * @return true if no errors, false if there are errors
     */
    public abstract boolean processData();
    
    /**
     * The implementation will reset values (if required) on the page components.
     * For e.g. the FileInputs should be reset to null on any errors.
     * Will be invoked by the TaskflowTrainBean if there are errors during the 
     * validateAndCommit phase
     */
    //public abstract void resetComponents();

    /****** HELPER METHODS ********/
    public DCBindingContainer getBindings() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }

    
    /**
     * Convenience method that will create a record in portal_doc_content
     * @return the doc content id
     */
     /*
    public Number generateDocRecord(DCBindingContainer bindings,
                                    String docContentIterator,
                                    UploadedFile file) throws SQLException,
                                                              IOException {
        LOGGER.entering(SOURCE_CLASS, "generateDocRecord");
        Number docContentId = null;
        if (file != null) {
            DCIteratorBinding iterBinding =
                bindings.findIteratorBinding(docContentIterator);
            NavigatableRowIterator iterator =
                iterBinding.getNavigatableRowIterator();
            Row docContentRow = iterator.createRow();
            docContentRow.setNewRowState(Row.STATUS_INITIALIZED);
            docContentRow.setAttribute("DocContentMime",
                                       file.getContentType());
            docContentRow.setAttribute("DocContentName", file.getFilename());
            docContentRow.setAttribute("DocContentBytesize",
                                       new Number(file.getLength()));
            docContentRow.setAttribute("DocContent",
                                       FileUtils.createBlobDomain(file));
            docContentId = (Number)docContentRow.getAttribute("DocContentId");
            LOGGER.finest("Doc -- " + docContentId + "," +
                          file.getContentType() + "," + file.getFilename() +
                          "," + file.getLength());
        }
        LOGGER.exiting(SOURCE_CLASS, "generateDocRecord");
        return docContentId;
    }
    */
    /**
     * Convenience method to process uploadable files.
     * @param userDocIterator - the iterator to fetch the PortalUserTFStepDoc record. Note
     * the query must be executed prior
     * @param docContentIterator iterator for the updatable DocContent record
     * @param file
     * @return
     */
    /*
    public boolean processDocumentUpload(String userDocIterator,
                                         String docContentIterator,
                                         UploadedFile file) {
        //TODO: Delete/update previously uploaded documents??
        LOGGER.entering(SOURCE_CLASS, "processDocumentUpload");
        boolean result = true;
        Row userStepDocRow = null;
        DCBindingContainer bindings = getBindings();
        //OperationBinding executeQuery =
        //bindings.getOperationBinding(queryUserDocExecutable);
        //Object qryResult = executeQuery.execute();
        //List errors = executeQuery.getErrors();
        //if (errors == null || errors.isEmpty()) {
        LOGGER.finest("No errors fetching contract doc");
        //No errors get the iterator
        DCIteratorBinding iterator =
            bindings.findIteratorBinding(userDocIterator);
        NavigatableRowIterator rowIterator =
            iterator.getNavigatableRowIterator();
        Row rows[] = rowIterator.getAllRowsInRange();
        if (rows != null && rows.length > 0) {
            userStepDocRow = rows[0];
            if (LOGGER.isLoggable(Level.FINEST)) {
                LOGGER.finest("Found user tf step doc row");
                LOGGER.finest("PortalUserTFStepDocView row found. UserTfStepDocId:" +
                              userStepDocRow.getAttribute("UserTfStepDocId"));
                LOGGER.finest("PortalUserTFStepDocView row found. Doc Sub Type:" +
                              userStepDocRow.getAttribute("DocSubType"));
            }
        } else {
            result = false;
        }
        //} else {
        //  result = false;
        //LOGGER.severe("Error fetching User Step Docs");
        //for (int i = 0; i < errors.size(); i++) {
        //  LOGGER.severe("Error " + i + ":" + errors.get(i));
        //}
        //}
        //Proceed only if row has been found
        if (result && userStepDocRow != null) {
            //Generate doc content row and set the userStepDocRow.DocContentId to the newly generated ID
            try {
                Number docContentId =
                    this.generateDocRecord(getBindings(), docContentIterator,
                                           file);
                LOGGER.finest("DocContentID: " + docContentId);
                if (docContentId != null) {
                    userStepDocRow.setAttribute("DocContentId", docContentId);
                } else {
                    LOGGER.severe("No DocContentId returned by generateDocRecord");
                    result = false;
                }
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error processing upload", e);
                result = false;
            }
        }
        LOGGER.finest("Result:" + result);
        LOGGER.exiting(SOURCE_CLASS, "processDocumentUpload");
        return result;
    }
    */
    public abstract String getStepName();
}
