package au.com.entitysolutions.taskflows.train.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Jul 27 10:32:30 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalUserTFStepDocImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    @Override
    public boolean hasHistoryColumns() {
        return false;
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        UserTfStepDocId {
            public Object get(PortalUserTFStepDocImpl obj) {
                return obj.getUserTfStepDocId();
            }

            public void put(PortalUserTFStepDocImpl obj, Object value) {
                obj.setUserTfStepDocId((Number)value);
            }
        }
        ,
        UserTfStepId {
            public Object get(PortalUserTFStepDocImpl obj) {
                return obj.getUserTfStepId();
            }

            public void put(PortalUserTFStepDocImpl obj, Object value) {
                obj.setUserTfStepId((Number)value);
            }
        }
        ,
        DocId {
            public Object get(PortalUserTFStepDocImpl obj) {
                return obj.getDocId();
            }

            public void put(PortalUserTFStepDocImpl obj, Object value) {
                obj.setDocId((Number)value);
            }
        }
        ,
        DocContentId {
            public Object get(PortalUserTFStepDocImpl obj) {
                return obj.getDocContentId();
            }

            public void put(PortalUserTFStepDocImpl obj, Object value) {
                obj.setDocContentId((Number)value);
            }
        }
        ,
        PortalUserTFStep {
            public Object get(PortalUserTFStepDocImpl obj) {
                return obj.getPortalUserTFStep();
            }

            public void put(PortalUserTFStepDocImpl obj, Object value) {
                obj.setPortalUserTFStep((PortalUserTFStepImpl)value);
            }
        }
        ,
        PortalDocContent {
            public Object get(PortalUserTFStepDocImpl obj) {
                return obj.getPortalDocContent();
            }

            public void put(PortalUserTFStepDocImpl obj, Object value) {
                obj.setAttributeInternal(index(), value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalUserTFStepDocImpl object);

        public abstract void put(PortalUserTFStepDocImpl object, Object value);

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

    public static final int USERTFSTEPDOCID = AttributesEnum.UserTfStepDocId.index();
    public static final int USERTFSTEPID = AttributesEnum.UserTfStepId.index();
    public static final int DOCID = AttributesEnum.DocId.index();
    public static final int DOCCONTENTID = AttributesEnum.DocContentId.index();
    public static final int PORTALUSERTFSTEP = AttributesEnum.PortalUserTFStep.index();
    public static final int PORTALDOCCONTENT = AttributesEnum.PortalDocContent.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalUserTFStepDocImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.model.PortalUserTFStepDoc");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for UserTfStepDocId, using the alias name UserTfStepDocId.
     * @return the UserTfStepDocId
     */
    public Number getUserTfStepDocId() {
        return (Number)getAttributeInternal(USERTFSTEPDOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserTfStepDocId.
     * @param value value to set the UserTfStepDocId
     */
    public void setUserTfStepDocId(Number value) {
        setAttributeInternal(USERTFSTEPDOCID, value);
    }

    /**
     * Gets the attribute value for UserTfStepId, using the alias name UserTfStepId.
     * @return the UserTfStepId
     */
    public Number getUserTfStepId() {
        return (Number)getAttributeInternal(USERTFSTEPID);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserTfStepId.
     * @param value value to set the UserTfStepId
     */
    public void setUserTfStepId(Number value) {
        setAttributeInternal(USERTFSTEPID, value);
    }

    /**
     * Gets the attribute value for DocId, using the alias name DocId.
     * @return the DocId
     */
    public Number getDocId() {
        return (Number)getAttributeInternal(DOCID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocId.
     * @param value value to set the DocId
     */
    public void setDocId(Number value) {
        setAttributeInternal(DOCID, value);
    }

    /**
     * Gets the attribute value for DocContentId, using the alias name DocContentId.
     * @return the DocContentId
     */
    public Number getDocContentId() {
        return (Number)getAttributeInternal(DOCCONTENTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocContentId.
     * @param value value to set the DocContentId
     */
    public void setDocContentId(Number value) {
        setAttributeInternal(DOCCONTENTID, value);
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

    /**
     * @return the associated entity au.com.entitysolutions.taskflows.model.CustomEntityImpl.
     */
    public PortalUserTFStepImpl getPortalUserTFStep() {
        return (PortalUserTFStepImpl)getAttributeInternal(PORTALUSERTFSTEP);
    }

    /**
     * Sets <code>value</code> as the associated entity au.com.entitysolutions.taskflows.model.CustomEntityImpl.
     */
    public void setPortalUserTFStep(PortalUserTFStepImpl value) {
        setAttributeInternal(PORTALUSERTFSTEP, value);
    }

    /**
     * @return the associated entity oracle.jbo.RowIterator.
     */
    public RowIterator getPortalDocContent() {
        return (RowIterator)getAttributeInternal(PORTALDOCCONTENT);
    }

    /**
     * @param userTfStepDocId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number userTfStepDocId) {
        return new Key(new Object[]{userTfStepDocId});
    }


}
