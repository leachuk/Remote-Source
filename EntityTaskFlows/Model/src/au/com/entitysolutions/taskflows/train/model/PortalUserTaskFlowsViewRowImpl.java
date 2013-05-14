package au.com.entitysolutions.taskflows.train.model;

import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.ViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 20 14:18:13 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalUserTaskFlowsViewRowImpl extends ViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        TfId {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getTfId();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setTfId((Number)value);
            }
        }
        ,
        UserTfId {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getUserTfId();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setUserTfId((Number)value);
            }
        }
        ,
        IsActive {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getIsActive();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setIsActive((String)value);
            }
        }
        ,
        Status {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getStatus();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setStatus((String)value);
            }
        }
        ,
        PersonId {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getPersonId();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setPersonId((Number)value);
            }
        }
        ,
        TfCode {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getTfCode();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setTfCode((String)value);
            }
        }
        ,
        TfName {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getTfName();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setTfName((String)value);
            }
        }
        ,
        TfDescription {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getTfDescription();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setTfDescription((String)value);
            }
        }
        ,
        UserName {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getUserName();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setUserName((String)value);
            }
        }
        ,
        UserId {
            public Object get(PortalUserTaskFlowsViewRowImpl obj) {
                return obj.getUserId();
            }

            public void put(PortalUserTaskFlowsViewRowImpl obj, Object value) {
                obj.setUserId((Number)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalUserTaskFlowsViewRowImpl object);

        public abstract void put(PortalUserTaskFlowsViewRowImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int TFID = AttributesEnum.TfId.index();
    public static final int USERTFID = AttributesEnum.UserTfId.index();
    public static final int ISACTIVE = AttributesEnum.IsActive.index();
    public static final int STATUS = AttributesEnum.Status.index();
    public static final int PERSONID = AttributesEnum.PersonId.index();
    public static final int TFCODE = AttributesEnum.TfCode.index();
    public static final int TFNAME = AttributesEnum.TfName.index();
    public static final int TFDESCRIPTION = AttributesEnum.TfDescription.index();
    public static final int USERNAME = AttributesEnum.UserName.index();
    public static final int USERID = AttributesEnum.UserId.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalUserTaskFlowsViewRowImpl() {
    }

    /**
     * Gets the attribute value for the calculated attribute TfId.
     * @return the TfId
     */
    public Number getTfId() {
        return (Number) getAttributeInternal(TFID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TfId.
     * @param value value to set the  TfId
     */
    public void setTfId(Number value) {
        setAttributeInternal(TFID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute UserTfId.
     * @return the UserTfId
     */
    public Number getUserTfId() {
        return (Number) getAttributeInternal(USERTFID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute UserTfId.
     * @param value value to set the  UserTfId
     */
    public void setUserTfId(Number value) {
        setAttributeInternal(USERTFID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute IsActive.
     * @return the IsActive
     */
    public String getIsActive() {
        return (String) getAttributeInternal(ISACTIVE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute IsActive.
     * @param value value to set the  IsActive
     */
    public void setIsActive(String value) {
        setAttributeInternal(ISACTIVE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Status.
     * @return the Status
     */
    public String getStatus() {
        return (String) getAttributeInternal(STATUS);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Status.
     * @param value value to set the  Status
     */
    public void setStatus(String value) {
        setAttributeInternal(STATUS, value);
    }

    /**
     * Gets the attribute value for the calculated attribute PersonId.
     * @return the PersonId
     */
    public Number getPersonId() {
        return (Number) getAttributeInternal(PERSONID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute PersonId.
     * @param value value to set the  PersonId
     */
    public void setPersonId(Number value) {
        setAttributeInternal(PERSONID, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TfCode.
     * @return the TfCode
     */
    public String getTfCode() {
        return (String) getAttributeInternal(TFCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TfCode.
     * @param value value to set the  TfCode
     */
    public void setTfCode(String value) {
        setAttributeInternal(TFCODE, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TfName.
     * @return the TfName
     */
    public String getTfName() {
        return (String) getAttributeInternal(TFNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TfName.
     * @param value value to set the  TfName
     */
    public void setTfName(String value) {
        setAttributeInternal(TFNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute TfDescription.
     * @return the TfDescription
     */
    public String getTfDescription() {
        return (String) getAttributeInternal(TFDESCRIPTION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute TfDescription.
     * @param value value to set the  TfDescription
     */
    public void setTfDescription(String value) {
        setAttributeInternal(TFDESCRIPTION, value);
    }

    /**
     * Gets the attribute value for the calculated attribute UserName.
     * @return the UserName
     */
    public String getUserName() {
        return (String) getAttributeInternal(USERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute UserName.
     * @param value value to set the  UserName
     */
    public void setUserName(String value) {
        setAttributeInternal(USERNAME, value);
    }

    /**
     * Gets the attribute value for the calculated attribute UserId.
     * @return the UserId
     */
    public Number getUserId() {
        return (Number) getAttributeInternal(USERID);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute UserId.
     * @param value value to set the  UserId
     */
    public void setUserId(Number value) {
        setAttributeInternal(USERID, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }
}
