package au.com.entitysolutions.taskflows.model;

import au.com.entitysolutions.taskflows.model.utils.ModelConstants;
import au.com.entitysolutions.taskflows.system.model.PortalCommonAppModuleImpl;

import java.math.BigDecimal;

import java.util.Map;
import java.util.logging.Logger;

import oracle.adf.share.ADFContext;

import oracle.jbo.AttributeList;
import oracle.jbo.domain.Date;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.domain.Number;
import oracle.jbo.server.DBTransaction;
import oracle.jbo.server.TransactionEvent;

public abstract class CustomEntityImpl extends EntityImpl {
    private static final String SOURCE_CLASS = CustomEntityImpl.class.getCanonicalName();
    private static final Logger LOGGER = Logger.getLogger(SOURCE_CLASS);        
    public CustomEntityImpl() {
        super();
    }
    
    /**
     * Convenience resuable method for getting the next sequence value from a
     * DB sequence. Use the Groovy script "adf.object.nextSeqVal('SEQ_NAME') to
     * initialise primary keys to sequence values
     * @param sequenceName
     * @return oracle.jbo.Number sequence value
     */
    protected Number nextSeqVal(String sequenceName) {
        SequenceImpl seqImpl =
            new SequenceImpl(sequenceName, getDBTransaction());        
        return seqImpl.getSequenceNumber();
    }    
    
    /**
     * Override and return false if the entity
     * does not include history columns - CreationDate, etc.
     * @return
     */
    public boolean hasHistoryColumns() {
        return true;
    }
    /**
     * @Override
     * Overridden create method to setup history attributes - CreationDate, LastUpdateDate, CreatedBy and LastUpdateBy
     * @param nameValuePair
     */
    protected void create(AttributeList nameValuePair) {
        LOGGER.entering(SOURCE_CLASS, "create");
        super.create(nameValuePair);
        DBTransaction bTransaction = this.getDBTransaction();
        
        
        if(this.hasHistoryColumns())
        {
            Date currentDate = new Date(Date.getCurrentDate());
            this.setAttribute("CreationDate", currentDate);
            this.setAttribute("LastUpdateDate", currentDate);
            
            Map sess = ADFContext.getCurrent().getSessionScope();
            Number loggedInPersonId = (Number)sess.get(ModelConstants.LOGGED_IN_PERSON_ID_KEY);
            if(loggedInPersonId == null) { loggedInPersonId = new Number(-111); }
            
            this.setAttribute("CreatedBy", loggedInPersonId);
            this.setAttribute("LastUpdatedBy", loggedInPersonId);
        }
        LOGGER.exiting(SOURCE_CLASS, "create");
    }    
    
    /**
     * @Override
     * Overridden create method to setup history attributes - CreationDate, LastUpdateDate, CreatedBy and LastUpdateBy
     * @param nameValuePair
     */
    protected void prepareForDML(int operation, TransactionEvent e) {
        LOGGER.entering(SOURCE_CLASS, "prepareForDML");
        if(this.hasHistoryColumns() && operation == DML_UPDATE) {
            Date currentDate = new Date(Date.getCurrentDate());
            this.setAttribute("LastUpdateDate", currentDate);
            Map sess = ADFContext.getCurrent().getSessionScope();
            Number loggedInPersonId = (Number)sess.get(ModelConstants.LOGGED_IN_PERSON_ID_KEY);
            if(loggedInPersonId == null) { loggedInPersonId = new Number(-111); }
            this.setAttribute("LastUpdatedBy", loggedInPersonId);
        }
        LOGGER.exiting(SOURCE_CLASS, "prepareForDML");
    }        

}
