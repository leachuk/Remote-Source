package au.com.entitysolutions.taskflows.train.signup.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Sep 05 09:06:21 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalSignupIcCertificatesImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        CertificatesId {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getCertificatesId();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setCertificatesId((Number)value);
            }
        }
        ,
        UserTfId {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getUserTfId();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setUserTfId((Number)value);
            }
        }
        ,
        IncorpCertDocCntntId {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getIncorpCertDocCntntId();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setIncorpCertDocCntntId((Number)value);
            }
        }
        ,
        WrkrsCompExpiry {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getWrkrsCompExpiry();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setWrkrsCompExpiry((Date)value);
            }
        }
        ,
        WrkrsCompDocCntntId {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getWrkrsCompDocCntntId();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setWrkrsCompDocCntntId((Number)value);
            }
        }
        ,
        ProfIndmExpiry {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getProfIndmExpiry();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setProfIndmExpiry((Date)value);
            }
        }
        ,
        ProfIndmDocCntntId {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getProfIndmDocCntntId();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setProfIndmDocCntntId((Number)value);
            }
        }
        ,
        CreatedBy {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getCreationDate();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ,
        PublLiabExpiry {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getPublLiabExpiry();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setPublLiabExpiry((Date)value);
            }
        }
        ,
        PublLiabDocCntntId {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getPublLiabDocCntntId();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setPublLiabDocCntntId((Number)value);
            }
        }
        ,
        SendOffline {
            public Object get(PortalSignupIcCertificatesImpl obj) {
                return obj.getSendOffline();
            }

            public void put(PortalSignupIcCertificatesImpl obj, Object value) {
                obj.setSendOffline((String)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalSignupIcCertificatesImpl object);

        public abstract void put(PortalSignupIcCertificatesImpl object,
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


    public static final int CERTIFICATESID = AttributesEnum.CertificatesId.index();
    public static final int USERTFID = AttributesEnum.UserTfId.index();
    public static final int INCORPCERTDOCCNTNTID = AttributesEnum.IncorpCertDocCntntId.index();
    public static final int WRKRSCOMPEXPIRY = AttributesEnum.WrkrsCompExpiry.index();
    public static final int WRKRSCOMPDOCCNTNTID = AttributesEnum.WrkrsCompDocCntntId.index();
    public static final int PROFINDMEXPIRY = AttributesEnum.ProfIndmExpiry.index();
    public static final int PROFINDMDOCCNTNTID = AttributesEnum.ProfIndmDocCntntId.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
    public static final int PUBLLIABEXPIRY = AttributesEnum.PublLiabExpiry.index();
    public static final int PUBLLIABDOCCNTNTID = AttributesEnum.PublLiabDocCntntId.index();
    public static final int SENDOFFLINE = AttributesEnum.SendOffline.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalSignupIcCertificatesImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.signup.model.PortalSignupIcCertificates");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for CertificatesId, using the alias name CertificatesId.
     * @return the CertificatesId
     */
    public Number getCertificatesId() {
        return (Number)getAttributeInternal(CERTIFICATESID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CertificatesId.
     * @param value value to set the CertificatesId
     */
    public void setCertificatesId(Number value) {
        setAttributeInternal(CERTIFICATESID, value);
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
     * Gets the attribute value for IncorpCertDocCntntId, using the alias name IncorpCertDocCntntId.
     * @return the IncorpCertDocCntntId
     */
    public Number getIncorpCertDocCntntId() {
        return (Number)getAttributeInternal(INCORPCERTDOCCNTNTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for IncorpCertDocCntntId.
     * @param value value to set the IncorpCertDocCntntId
     */
    public void setIncorpCertDocCntntId(Number value) {
        setAttributeInternal(INCORPCERTDOCCNTNTID, value);
    }

    /**
     * Gets the attribute value for WrkrsCompExpiry, using the alias name WrkrsCompExpiry.
     * @return the WrkrsCompExpiry
     */
    public Date getWrkrsCompExpiry() {
        return (Date)getAttributeInternal(WRKRSCOMPEXPIRY);
    }

    /**
     * Sets <code>value</code> as the attribute value for WrkrsCompExpiry.
     * @param value value to set the WrkrsCompExpiry
     */
    public void setWrkrsCompExpiry(Date value) {
        setAttributeInternal(WRKRSCOMPEXPIRY, value);
    }

    /**
     * Gets the attribute value for WrkrsCompDocCntntId, using the alias name WrkrsCompDocCntntId.
     * @return the WrkrsCompDocCntntId
     */
    public Number getWrkrsCompDocCntntId() {
        return (Number)getAttributeInternal(WRKRSCOMPDOCCNTNTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for WrkrsCompDocCntntId.
     * @param value value to set the WrkrsCompDocCntntId
     */
    public void setWrkrsCompDocCntntId(Number value) {
        setAttributeInternal(WRKRSCOMPDOCCNTNTID, value);
    }

    /**
     * Gets the attribute value for ProfIndmExpiry, using the alias name ProfIndmExpiry.
     * @return the ProfIndmExpiry
     */
    public Date getProfIndmExpiry() {
        return (Date)getAttributeInternal(PROFINDMEXPIRY);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProfIndmExpiry.
     * @param value value to set the ProfIndmExpiry
     */
    public void setProfIndmExpiry(Date value) {
        setAttributeInternal(PROFINDMEXPIRY, value);
    }

    /**
     * Gets the attribute value for ProfIndmDocCntntId, using the alias name ProfIndmDocCntntId.
     * @return the ProfIndmDocCntntId
     */
    public Number getProfIndmDocCntntId() {
        return (Number)getAttributeInternal(PROFINDMDOCCNTNTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ProfIndmDocCntntId.
     * @param value value to set the ProfIndmDocCntntId
     */
    public void setProfIndmDocCntntId(Number value) {
        setAttributeInternal(PROFINDMDOCCNTNTID, value);
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
     * Gets the attribute value for PublLiabExpiry, using the alias name PublLiabExpiry.
     * @return the PublLiabExpiry
     */
    public Date getPublLiabExpiry() {
        return (Date)getAttributeInternal(PUBLLIABEXPIRY);
    }

    /**
     * Sets <code>value</code> as the attribute value for PublLiabExpiry.
     * @param value value to set the PublLiabExpiry
     */
    public void setPublLiabExpiry(Date value) {
        setAttributeInternal(PUBLLIABEXPIRY, value);
    }

    /**
     * Gets the attribute value for PublLiabDocCntntId, using the alias name PublLiabDocCntntId.
     * @return the PublLiabDocCntntId
     */
    public Number getPublLiabDocCntntId() {
        return (Number)getAttributeInternal(PUBLLIABDOCCNTNTID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PublLiabDocCntntId.
     * @param value value to set the PublLiabDocCntntId
     */
    public void setPublLiabDocCntntId(Number value) {
        setAttributeInternal(PUBLLIABDOCCNTNTID, value);
    }

    /**
     * Gets the attribute value for SendOffline, using the alias name SendOffline.
     * @return the SendOffline
     */
    public String getSendOffline() {
        return (String)getAttributeInternal(SENDOFFLINE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SendOffline.
     * @param value value to set the SendOffline
     */
    public void setSendOffline(String value) {
        setAttributeInternal(SENDOFFLINE, value);
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
     * @param certificatesId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number certificatesId) {
        return new Key(new Object[]{certificatesId});
    }


}
