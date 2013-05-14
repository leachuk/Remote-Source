package au.com.entitysolutions.taskflows.train.signup.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 25 14:07:07 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalSignupContactDetailsImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        ContactDetailsId {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getContactDetailsId();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setContactDetailsId((Number)value);
            }
        }
        ,
        UserTfId {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getUserTfId();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setUserTfId((Number)value);
            }
        }
        ,
        TelephoneHome {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getTelephoneHome();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setTelephoneHome((String)value);
            }
        }
        ,
        TelephoneMobile {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getTelephoneMobile();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setTelephoneMobile((String)value);
            }
        }
        ,
        TelephoneWork {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getTelephoneWork();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setTelephoneWork((String)value);
            }
        }
        ,
        HomeAddressLine {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getHomeAddressLine();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setHomeAddressLine((String)value);
            }
        }
        ,
        HomeSuburb {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getHomeSuburb();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setHomeSuburb((String)value);
            }
        }
        ,
        HomeState {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getHomeState();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setHomeState((String)value);
            }
        }
        ,
        HomePostCode {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getHomePostCode();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setHomePostCode((String)value);
            }
        }
        ,
        HomeCountry {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getHomeCountry();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setHomeCountry((String)value);
            }
        }
        ,
        PortalEmailAddress {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getPortalEmailAddress();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setPortalEmailAddress((String)value);
            }
        }
        ,
        Transformed {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getTransformed();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setTransformed((String)value);
            }
        }
        ,
        CreatedBy {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getCreationDate();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(PortalSignupContactDetailsImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(PortalSignupContactDetailsImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalSignupContactDetailsImpl object);

        public abstract void put(PortalSignupContactDetailsImpl object,
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


    public static final int CONTACTDETAILSID = AttributesEnum.ContactDetailsId.index();
    public static final int USERTFID = AttributesEnum.UserTfId.index();
    public static final int TELEPHONEHOME = AttributesEnum.TelephoneHome.index();
    public static final int TELEPHONEMOBILE = AttributesEnum.TelephoneMobile.index();
    public static final int TELEPHONEWORK = AttributesEnum.TelephoneWork.index();
    public static final int HOMEADDRESSLINE = AttributesEnum.HomeAddressLine.index();
    public static final int HOMESUBURB = AttributesEnum.HomeSuburb.index();
    public static final int HOMESTATE = AttributesEnum.HomeState.index();
    public static final int HOMEPOSTCODE = AttributesEnum.HomePostCode.index();
    public static final int HOMECOUNTRY = AttributesEnum.HomeCountry.index();
    public static final int PORTALEMAILADDRESS = AttributesEnum.PortalEmailAddress.index();
    public static final int TRANSFORMED = AttributesEnum.Transformed.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalSignupContactDetailsImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.signup.model.PortalSignupContactDetails");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for ContactDetailsId, using the alias name ContactDetailsId.
     * @return the ContactDetailsId
     */
    public Number getContactDetailsId() {
        return (Number)getAttributeInternal(CONTACTDETAILSID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ContactDetailsId.
     * @param value value to set the ContactDetailsId
     */
    public void setContactDetailsId(Number value) {
        setAttributeInternal(CONTACTDETAILSID, value);
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
     * Gets the attribute value for TelephoneHome, using the alias name TelephoneHome.
     * @return the TelephoneHome
     */
    public String getTelephoneHome() {
        return (String)getAttributeInternal(TELEPHONEHOME);
    }

    /**
     * Sets <code>value</code> as the attribute value for TelephoneHome.
     * @param value value to set the TelephoneHome
     */
    public void setTelephoneHome(String value) {
        setAttributeInternal(TELEPHONEHOME, value);
    }

    /**
     * Gets the attribute value for TelephoneMobile, using the alias name TelephoneMobile.
     * @return the TelephoneMobile
     */
    public String getTelephoneMobile() {
        return (String)getAttributeInternal(TELEPHONEMOBILE);
    }

    /**
     * Sets <code>value</code> as the attribute value for TelephoneMobile.
     * @param value value to set the TelephoneMobile
     */
    public void setTelephoneMobile(String value) {
        setAttributeInternal(TELEPHONEMOBILE, value);
    }

    /**
     * Gets the attribute value for TelephoneWork, using the alias name TelephoneWork.
     * @return the TelephoneWork
     */
    public String getTelephoneWork() {
        return (String)getAttributeInternal(TELEPHONEWORK);
    }

    /**
     * Sets <code>value</code> as the attribute value for TelephoneWork.
     * @param value value to set the TelephoneWork
     */
    public void setTelephoneWork(String value) {
        setAttributeInternal(TELEPHONEWORK, value);
    }

    /**
     * Gets the attribute value for HomeAddressLine, using the alias name HomeAddressLine.
     * @return the HomeAddressLine
     */
    public String getHomeAddressLine() {
        return (String)getAttributeInternal(HOMEADDRESSLINE);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomeAddressLine.
     * @param value value to set the HomeAddressLine
     */
    public void setHomeAddressLine(String value) {
        setAttributeInternal(HOMEADDRESSLINE, value);
    }

    /**
     * Gets the attribute value for HomeSuburb, using the alias name HomeSuburb.
     * @return the HomeSuburb
     */
    public String getHomeSuburb() {
        return (String)getAttributeInternal(HOMESUBURB);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomeSuburb.
     * @param value value to set the HomeSuburb
     */
    public void setHomeSuburb(String value) {
        setAttributeInternal(HOMESUBURB, value);
    }

    /**
     * Gets the attribute value for HomeState, using the alias name HomeState.
     * @return the HomeState
     */
    public String getHomeState() {
        return (String)getAttributeInternal(HOMESTATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomeState.
     * @param value value to set the HomeState
     */
    public void setHomeState(String value) {
        setAttributeInternal(HOMESTATE, value);
    }

    /**
     * Gets the attribute value for HomePostCode, using the alias name HomePostCode.
     * @return the HomePostCode
     */
    public String getHomePostCode() {
        return (String)getAttributeInternal(HOMEPOSTCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomePostCode.
     * @param value value to set the HomePostCode
     */
    public void setHomePostCode(String value) {
        setAttributeInternal(HOMEPOSTCODE, value);
    }

    /**
     * Gets the attribute value for HomeCountry, using the alias name HomeCountry.
     * @return the HomeCountry
     */
    public String getHomeCountry() {
        return (String)getAttributeInternal(HOMECOUNTRY);
    }

    /**
     * Sets <code>value</code> as the attribute value for HomeCountry.
     * @param value value to set the HomeCountry
     */
    public void setHomeCountry(String value) {
        setAttributeInternal(HOMECOUNTRY, value);
    }

    /**
     * Gets the attribute value for PortalEmailAddress, using the alias name PortalEmailAddress.
     * @return the PortalEmailAddress
     */
    public String getPortalEmailAddress() {
        return (String)getAttributeInternal(PORTALEMAILADDRESS);
    }

    /**
     * Sets <code>value</code> as the attribute value for PortalEmailAddress.
     * @param value value to set the PortalEmailAddress
     */
    public void setPortalEmailAddress(String value) {
        setAttributeInternal(PORTALEMAILADDRESS, value);
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
     * @param contactDetailsId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number contactDetailsId) {
        return new Key(new Object[]{contactDetailsId});
    }

    /**
     * Validation method for PortalSignupContactDetails.
     */
    public boolean validatePhoneNumbers() {
        if(this.getTelephoneHome() != null || this.getTelephoneMobile() != null || this.getTelephoneWork() != null)
        {
            //At lease one phone number is present
            return true;
        }
        else {
            return false;
        }
    }


}