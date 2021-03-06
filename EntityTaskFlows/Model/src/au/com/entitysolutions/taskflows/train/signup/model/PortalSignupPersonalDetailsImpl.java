package au.com.entitysolutions.taskflows.train.signup.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 25 09:29:02 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalSignupPersonalDetailsImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        PersonalDetailsId {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getPersonalDetailsId();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setPersonalDetailsId((Number)value);
            }
        }
        ,
        UserTfId {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getUserTfId();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setUserTfId((Number)value);
            }
        }
        ,
        Prefix {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getPrefix();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setPrefix((String)value);
            }
        }
        ,
        Surname {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getSurname();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setSurname((String)value);
            }
        }
        ,
        FirstName {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getFirstName();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setFirstName((String)value);
            }
        }
        ,
        MiddleName {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getMiddleName();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setMiddleName((String)value);
            }
        }
        ,
        PreferredName {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getPreferredName();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setPreferredName((String)value);
            }
        }
        ,
        DateOfBirth {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getDateOfBirth();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setDateOfBirth((Date)value);
            }
        }
        ,
        Transformed {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getTransformed();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setTransformed((String)value);
            }
        }
        ,
        CreatedBy {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getCreationDate();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(PortalSignupPersonalDetailsImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(PortalSignupPersonalDetailsImpl obj,
                            Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalSignupPersonalDetailsImpl object);

        public abstract void put(PortalSignupPersonalDetailsImpl object,
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


    public static final int PERSONALDETAILSID = AttributesEnum.PersonalDetailsId.index();
    public static final int USERTFID = AttributesEnum.UserTfId.index();
    public static final int PREFIX = AttributesEnum.Prefix.index();
    public static final int SURNAME = AttributesEnum.Surname.index();
    public static final int FIRSTNAME = AttributesEnum.FirstName.index();
    public static final int MIDDLENAME = AttributesEnum.MiddleName.index();
    public static final int PREFERREDNAME = AttributesEnum.PreferredName.index();
    public static final int DATEOFBIRTH = AttributesEnum.DateOfBirth.index();
    public static final int TRANSFORMED = AttributesEnum.Transformed.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalSignupPersonalDetailsImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.signup.model.PortalSignupPersonalDetails");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for PersonalDetailsId, using the alias name PersonalDetailsId.
     * @return the PersonalDetailsId
     */
    public Number getPersonalDetailsId() {
        return (Number)getAttributeInternal(PERSONALDETAILSID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PersonalDetailsId.
     * @param value value to set the PersonalDetailsId
     */
    public void setPersonalDetailsId(Number value) {
        setAttributeInternal(PERSONALDETAILSID, value);
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
     * Gets the attribute value for Prefix, using the alias name Prefix.
     * @return the Prefix
     */
    public String getPrefix() {
        return (String)getAttributeInternal(PREFIX);
    }

    /**
     * Sets <code>value</code> as the attribute value for Prefix.
     * @param value value to set the Prefix
     */
    public void setPrefix(String value) {
        setAttributeInternal(PREFIX, value);
    }

    /**
     * Gets the attribute value for Surname, using the alias name Surname.
     * @return the Surname
     */
    public String getSurname() {
        return (String)getAttributeInternal(SURNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for Surname.
     * @param value value to set the Surname
     */
    public void setSurname(String value) {
        setAttributeInternal(SURNAME, value);
    }

    /**
     * Gets the attribute value for FirstName, using the alias name FirstName.
     * @return the FirstName
     */
    public String getFirstName() {
        return (String)getAttributeInternal(FIRSTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for FirstName.
     * @param value value to set the FirstName
     */
    public void setFirstName(String value) {
        setAttributeInternal(FIRSTNAME, value);
    }

    /**
     * Gets the attribute value for MiddleName, using the alias name MiddleName.
     * @return the MiddleName
     */
    public String getMiddleName() {
        return (String)getAttributeInternal(MIDDLENAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for MiddleName.
     * @param value value to set the MiddleName
     */
    public void setMiddleName(String value) {
        setAttributeInternal(MIDDLENAME, value);
    }

    /**
     * Gets the attribute value for PreferredName, using the alias name PreferredName.
     * @return the PreferredName
     */
    public String getPreferredName() {
        return (String)getAttributeInternal(PREFERREDNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for PreferredName.
     * @param value value to set the PreferredName
     */
    public void setPreferredName(String value) {
        setAttributeInternal(PREFERREDNAME, value);
    }


    /**
     * Gets the attribute value for DateOfBirth, using the alias name DateOfBirth.
     * @return the DateOfBirth
     */
    public Date getDateOfBirth() {
        return (Date)getAttributeInternal(DATEOFBIRTH);
    }

    /**
     * Sets <code>value</code> as the attribute value for DateOfBirth.
     * @param value value to set the DateOfBirth
     */
    public void setDateOfBirth(Date value) {
        setAttributeInternal(DATEOFBIRTH, value);
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
     * @param personalDetailsId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number personalDetailsId) {
        return new Key(new Object[]{personalDetailsId});
    }


}
