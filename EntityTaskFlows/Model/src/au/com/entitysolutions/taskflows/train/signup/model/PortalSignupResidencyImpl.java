package au.com.entitysolutions.taskflows.train.signup.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 25 14:13:57 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalSignupResidencyImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        ResidencyDetailsId {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getResidencyDetailsId();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setResidencyDetailsId((Number)value);
            }
        }
        ,
        UserTfId {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getUserTfId();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setUserTfId((Number)value);
            }
        }
        ,
        ConfirmTrueCopy {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getConfirmTrueCopy();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setConfirmTrueCopy((String)value);
            }
        }
        ,
        AlreadySubmitted {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getAlreadySubmitted();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setAlreadySubmitted((String)value);
            }
        }
        ,
        Transformed {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getTransformed();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setTransformed((String)value);
            }
        }
        ,
        CreatedBy {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getCreationDate();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        DocContentId1 {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getDocContentId1();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setDocContentId1((Number)value);
            }
        }
        ,
        DocContentId2 {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getDocContentId2();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setDocContentId2((Number)value);
            }
        }
        ,
        DocContentId3 {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getDocContentId3();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setDocContentId3((Number)value);
            }
        }
        ,
        ResidencyType {
            public Object get(PortalSignupResidencyImpl obj) {
                return obj.getResidencyType();
            }

            public void put(PortalSignupResidencyImpl obj, Object value) {
                obj.setResidencyType((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalSignupResidencyImpl object);

        public abstract void put(PortalSignupResidencyImpl object,
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


    public static final int RESIDENCYDETAILSID = AttributesEnum.ResidencyDetailsId.index();
    public static final int USERTFID = AttributesEnum.UserTfId.index();
    public static final int CONFIRMTRUECOPY = AttributesEnum.ConfirmTrueCopy.index();
    public static final int ALREADYSUBMITTED = AttributesEnum.AlreadySubmitted.index();
    public static final int TRANSFORMED = AttributesEnum.Transformed.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int DOCCONTENTID1 = AttributesEnum.DocContentId1.index();
    public static final int DOCCONTENTID2 = AttributesEnum.DocContentId2.index();
    public static final int DOCCONTENTID3 = AttributesEnum.DocContentId3.index();
    public static final int RESIDENCYTYPE = AttributesEnum.ResidencyType.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalSignupResidencyImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.signup.model.PortalSignupResidency");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for ResidencyDetailsId, using the alias name ResidencyDetailsId.
     * @return the ResidencyDetailsId
     */
    public Number getResidencyDetailsId() {
        return (Number)getAttributeInternal(RESIDENCYDETAILSID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ResidencyDetailsId.
     * @param value value to set the ResidencyDetailsId
     */
    public void setResidencyDetailsId(Number value) {
        setAttributeInternal(RESIDENCYDETAILSID, value);
    }

    /**
     * Gets the attribute value for UserTfId, using the alias name UserTfId.
     * @return the UserTfId
     */
    public Number getUserTfId() {
        return (Number)getAttributeInternal(USERTFID);
    }

    /**
     * Sets <code>value</code> as the attribute value for UserTfId.
     * @param value value to set the UserTfId
     */
    public void setUserTfId(Number value) {
        setAttributeInternal(USERTFID, value);
    }

    /**
     * Gets the attribute value for ConfirmTrueCopy, using the alias name ConfirmTrueCopy.
     * @return the ConfirmTrueCopy
     */
    public String getConfirmTrueCopy() {
        return (String)getAttributeInternal(CONFIRMTRUECOPY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ConfirmTrueCopy.
     * @param value value to set the ConfirmTrueCopy
     */
    public void setConfirmTrueCopy(String value) {
        setAttributeInternal(CONFIRMTRUECOPY, value);
    }

    /**
     * Gets the attribute value for AlreadySubmitted, using the alias name AlreadySubmitted.
     * @return the AlreadySubmitted
     */
    public String getAlreadySubmitted() {
        return (String)getAttributeInternal(ALREADYSUBMITTED);
    }

    /**
     * Sets <code>value</code> as the attribute value for AlreadySubmitted.
     * @param value value to set the AlreadySubmitted
     */
    public void setAlreadySubmitted(String value) {
        setAttributeInternal(ALREADYSUBMITTED, value);
    }

    /**
     * Gets the attribute value for Transformed, using the alias name Transformed.
     * @return the Transformed
     */
    public String getTransformed() {
        return (String)getAttributeInternal(TRANSFORMED);
    }

    /**
     * Sets <code>value</code> as the attribute value for Transformed.
     * @param value value to set the Transformed
     */
    public void setTransformed(String value) {
        setAttributeInternal(TRANSFORMED, value);
    }

    /**
     * Gets the attribute value for CreatedBy, using the alias name CreatedBy.
     * @return the CreatedBy
     */
    public Number getCreatedBy() {
        return (Number)getAttributeInternal(CREATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreatedBy.
     * @param value value to set the CreatedBy
     */
    public void setCreatedBy(Number value) {
        setAttributeInternal(CREATEDBY, value);
    }

    /**
     * Gets the attribute value for CreationDate, using the alias name CreationDate.
     * @return the CreationDate
     */
    public Date getCreationDate() {
        return (Date)getAttributeInternal(CREATIONDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreationDate.
     * @param value value to set the CreationDate
     */
    public void setCreationDate(Date value) {
        setAttributeInternal(CREATIONDATE, value);
    }

    /**
     * Gets the attribute value for LastUpdatedBy, using the alias name LastUpdatedBy.
     * @return the LastUpdatedBy
     */
    public Number getLastUpdatedBy() {
        return (Number)getAttributeInternal(LASTUPDATEDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdatedBy.
     * @param value value to set the LastUpdatedBy
     */
    public void setLastUpdatedBy(Number value) {
        setAttributeInternal(LASTUPDATEDBY, value);
    }

    /**
     * Gets the attribute value for LastUpdateDate, using the alias name LastUpdateDate.
     * @return the LastUpdateDate
     */
    public Date getLastUpdateDate() {
        return (Date)getAttributeInternal(LASTUPDATEDATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdateDate.
     * @param value value to set the LastUpdateDate
     */
    public void setLastUpdateDate(Date value) {
        setAttributeInternal(LASTUPDATEDATE, value);
    }

    /**
     * Gets the attribute value for DocContentId1, using the alias name DocContentId1.
     * @return the DocContentId1
     */
    public Number getDocContentId1() {
        return (Number)getAttributeInternal(DOCCONTENTID1);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocContentId1.
     * @param value value to set the DocContentId1
     */
    public void setDocContentId1(Number value) {
        setAttributeInternal(DOCCONTENTID1, value);
    }

    /**
     * Gets the attribute value for DocContentId2, using the alias name DocContentId2.
     * @return the DocContentId2
     */
    public Number getDocContentId2() {
        return (Number)getAttributeInternal(DOCCONTENTID2);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocContentId2.
     * @param value value to set the DocContentId2
     */
    public void setDocContentId2(Number value) {
        setAttributeInternal(DOCCONTENTID2, value);
    }

    /**
     * Gets the attribute value for DocContentId3, using the alias name DocContentId3.
     * @return the DocContentId3
     */
    public Number getDocContentId3() {
        return (Number)getAttributeInternal(DOCCONTENTID3);
    }

    /**
     * Sets <code>value</code> as the attribute value for DocContentId3.
     * @param value value to set the DocContentId3
     */
    public void setDocContentId3(Number value) {
        setAttributeInternal(DOCCONTENTID3, value);
    }

    /**
     * Gets the attribute value for ResidencyType, using the alias name ResidencyType.
     * @return the ResidencyType
     */
    public String getResidencyType() {
        return (String)getAttributeInternal(RESIDENCYTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ResidencyType.
     * @param value value to set the ResidencyType
     */
    public void setResidencyType(String value) {
        setAttributeInternal(RESIDENCYTYPE, value);
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
     * @param residencyDetailsId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number residencyDetailsId) {
        return new Key(new Object[]{residencyDetailsId});
    }


}
