package au.com.entitysolutions.taskflows.system.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 04 15:34:20 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalUserActionImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * This is the default constructor (do not remove).
     */
    public PortalUserActionImpl() {
    }
    

    /**
     * @param actionId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number actionId) {
        return new Key(new Object[]{actionId});
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.system.model.PortalUserAction");
        }
        return mDefinitionObject;
    }
    
    @Override
    public boolean hasHistoryColumns() {
        return false;
    }

}
