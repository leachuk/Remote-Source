package au.com.entitysolutions.taskflows.train.signup.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 25 14:14:00 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalSignupSuperDetailsImpl extends CustomEntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
     */
    public enum AttributesEnum {
        SuperDetailsId {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperDetailsId();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperDetailsId((Number)value);
            }
        }
        ,
        UserTfId {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getUserTfId();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setUserTfId((Number)value);
            }
        }
        ,
        SuperFundName {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperFundName();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperFundName((String)value);
            }
        }
        ,
        SuperFundSpin {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperFundSpin();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperFundSpin((String)value);
            }
        }
        ,
        SuperFundAbn {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperFundAbn();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperFundAbn((String)value);
            }
        }
        ,
        SuperMemberNumber {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperMemberNumber();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperMemberNumber((String)value);
            }
        }
        ,
        Transformed {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getTransformed();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setTransformed((String)value);
            }
        }
        ,
        SuperChoice {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperChoice();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperChoice((String)value);
            }
        }
        ,
        SuperFundType {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperFundType();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperFundType((String)value);
            }
        }
        ,
        SuperMemberName {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperMemberName();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperMemberName((String)value);
            }
        }
        ,
        SuperPaymentType {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSuperPaymentType();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSuperPaymentType((String)value);
            }
        }
        ,
        EftBsb {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getEftBsb();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setEftBsb((String)value);
            }
        }
        ,
        EftAccountNo {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getEftAccountNo();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setEftAccountNo((String)value);
            }
        }
        ,
        EftAccountName {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getEftAccountName();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setEftAccountName((String)value);
            }
        }
        ,
        EftEmail {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getEftEmail();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setEftEmail((String)value);
            }
        }
        ,
        BpayBillerCode {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getBpayBillerCode();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setBpayBillerCode((String)value);
            }
        }
        ,
        BpayRefNo {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getBpayRefNo();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setBpayRefNo((String)value);
            }
        }
        ,
        SacBpayBillerCode {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSacBpayBillerCode();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSacBpayBillerCode((String)value);
            }
        }
        ,
        SacBpayRefNo {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getSacBpayRefNo();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setSacBpayRefNo((String)value);
            }
        }
        ,
        ChqPayTo {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getChqPayTo();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setChqPayTo((String)value);
            }
        }
        ,
        ChqAddress {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getChqAddress();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setChqAddress((String)value);
            }
        }
        ,
        ChqSuburb {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getChqSuburb();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setChqSuburb((String)value);
            }
        }
        ,
        ChqState {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getChqState();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setChqState((String)value);
            }
        }
        ,
        ChqPostcode {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getChqPostcode();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setChqPostcode((String)value);
            }
        }
        ,
        ConfirmSuperCompliance {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getConfirmSuperCompliance();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setConfirmSuperCompliance((String)value);
            }
        }
        ,
        CreatedBy {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getCreatedBy();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setCreatedBy((Number)value);
            }
        }
        ,
        CreationDate {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getCreationDate();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setCreationDate((Date)value);
            }
        }
        ,
        LastUpdatedBy {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getLastUpdatedBy();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setLastUpdatedBy((Number)value);
            }
        }
        ,
        LastUpdateDate {
            public Object get(PortalSignupSuperDetailsImpl obj) {
                return obj.getLastUpdateDate();
            }

            public void put(PortalSignupSuperDetailsImpl obj, Object value) {
                obj.setLastUpdateDate((Date)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static int firstIndex = 0;

        public abstract Object get(PortalSignupSuperDetailsImpl object);

        public abstract void put(PortalSignupSuperDetailsImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() +
                AttributesEnum.staticValues().length;
        }

        public static AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int SUPERDETAILSID = AttributesEnum.SuperDetailsId.index();
    public static final int USERTFID = AttributesEnum.UserTfId.index();
    public static final int SUPERFUNDNAME = AttributesEnum.SuperFundName.index();
    public static final int SUPERFUNDSPIN = AttributesEnum.SuperFundSpin.index();
    public static final int SUPERFUNDABN = AttributesEnum.SuperFundAbn.index();
    public static final int SUPERMEMBERNUMBER = AttributesEnum.SuperMemberNumber.index();
    public static final int TRANSFORMED = AttributesEnum.Transformed.index();
    public static final int SUPERCHOICE = AttributesEnum.SuperChoice.index();
    public static final int SUPERFUNDTYPE = AttributesEnum.SuperFundType.index();
    public static final int SUPERMEMBERNAME = AttributesEnum.SuperMemberName.index();
    public static final int SUPERPAYMENTTYPE = AttributesEnum.SuperPaymentType.index();
    public static final int EFTBSB = AttributesEnum.EftBsb.index();
    public static final int EFTACCOUNTNO = AttributesEnum.EftAccountNo.index();
    public static final int EFTACCOUNTNAME = AttributesEnum.EftAccountName.index();
    public static final int EFTEMAIL = AttributesEnum.EftEmail.index();
    public static final int BPAYBILLERCODE = AttributesEnum.BpayBillerCode.index();
    public static final int BPAYREFNO = AttributesEnum.BpayRefNo.index();
    public static final int SACBPAYBILLERCODE = AttributesEnum.SacBpayBillerCode.index();
    public static final int SACBPAYREFNO = AttributesEnum.SacBpayRefNo.index();
    public static final int CHQPAYTO = AttributesEnum.ChqPayTo.index();
    public static final int CHQADDRESS = AttributesEnum.ChqAddress.index();
    public static final int CHQSUBURB = AttributesEnum.ChqSuburb.index();
    public static final int CHQSTATE = AttributesEnum.ChqState.index();
    public static final int CHQPOSTCODE = AttributesEnum.ChqPostcode.index();
    public static final int CONFIRMSUPERCOMPLIANCE = AttributesEnum.ConfirmSuperCompliance.index();
    public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
    public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
    public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
    public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();

    /**
     * This is the default constructor (do not remove).
     */
    public PortalSignupSuperDetailsImpl() {
    }


    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.signup.model.PortalSignupSuperDetails");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for SuperDetailsId, using the alias name SuperDetailsId.
     * @return the SuperDetailsId
     */
    public Number getSuperDetailsId() {
        return (Number)getAttributeInternal(SUPERDETAILSID);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperDetailsId.
     * @param value value to set the SuperDetailsId
     */
    public void setSuperDetailsId(Number value) {
        setAttributeInternal(SUPERDETAILSID, value);
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
     * Gets the attribute value for SuperFundName, using the alias name SuperFundName.
     * @return the SuperFundName
     */
    public String getSuperFundName() {
        return (String)getAttributeInternal(SUPERFUNDNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperFundName.
     * @param value value to set the SuperFundName
     */
    public void setSuperFundName(String value) {
        setAttributeInternal(SUPERFUNDNAME, value);
    }


    /**
     * Gets the attribute value for SuperFundSpin, using the alias name SuperFundSpin.
     * @return the SuperFundSpin
     */
    public String getSuperFundSpin() {
        return (String)getAttributeInternal(SUPERFUNDSPIN);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperFundSpin.
     * @param value value to set the SuperFundSpin
     */
    public void setSuperFundSpin(String value) {
        setAttributeInternal(SUPERFUNDSPIN, value);
    }

    /**
     * Gets the attribute value for SuperFundAbn, using the alias name SuperFundAbn.
     * @return the SuperFundAbn
     */
    public String getSuperFundAbn() {
        return (String)getAttributeInternal(SUPERFUNDABN);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperFundAbn.
     * @param value value to set the SuperFundAbn
     */
    public void setSuperFundAbn(String value) {
        setAttributeInternal(SUPERFUNDABN, value);
    }

    /**
     * Gets the attribute value for SuperMemberNumber, using the alias name SuperMemberNumber.
     * @return the SuperMemberNumber
     */
    public String getSuperMemberNumber() {
        return (String)getAttributeInternal(SUPERMEMBERNUMBER);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperMemberNumber.
     * @param value value to set the SuperMemberNumber
     */
    public void setSuperMemberNumber(String value) {
        setAttributeInternal(SUPERMEMBERNUMBER, value);
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
     * Gets the attribute value for SuperChoice, using the alias name SuperChoice.
     * @return the SuperChoice
     */
    public String getSuperChoice() {
        return (String)getAttributeInternal(SUPERCHOICE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperChoice.
     * @param value value to set the SuperChoice
     */
    public void setSuperChoice(String value) {
        setAttributeInternal(SUPERCHOICE, value);
    }

    /**
     * Gets the attribute value for SuperFundType, using the alias name SuperFundType.
     * @return the SuperFundType
     */
    public String getSuperFundType() {
        return (String)getAttributeInternal(SUPERFUNDTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperFundType.
     * @param value value to set the SuperFundType
     */
    public void setSuperFundType(String value) {
        setAttributeInternal(SUPERFUNDTYPE, value);
    }

    /**
     * Gets the attribute value for SuperMemberName, using the alias name SuperMemberName.
     * @return the SuperMemberName
     */
    public String getSuperMemberName() {
        return (String)getAttributeInternal(SUPERMEMBERNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperMemberName.
     * @param value value to set the SuperMemberName
     */
    public void setSuperMemberName(String value) {
        setAttributeInternal(SUPERMEMBERNAME, value);
    }

    /**
     * Gets the attribute value for SuperPaymentType, using the alias name SuperPaymentType.
     * @return the SuperPaymentType
     */
    public String getSuperPaymentType() {
        return (String)getAttributeInternal(SUPERPAYMENTTYPE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SuperPaymentType.
     * @param value value to set the SuperPaymentType
     */
    public void setSuperPaymentType(String value) {
        setAttributeInternal(SUPERPAYMENTTYPE, value);
    }

    /**
     * Gets the attribute value for EftBsb, using the alias name EftBsb.
     * @return the EftBsb
     */
    public String getEftBsb() {
        return (String)getAttributeInternal(EFTBSB);
    }

    /**
     * Sets <code>value</code> as the attribute value for EftBsb.
     * @param value value to set the EftBsb
     */
    public void setEftBsb(String value) {
        setAttributeInternal(EFTBSB, value);
    }

    /**
     * Gets the attribute value for EftAccountNo, using the alias name EftAccountNo.
     * @return the EftAccountNo
     */
    public String getEftAccountNo() {
        return (String)getAttributeInternal(EFTACCOUNTNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for EftAccountNo.
     * @param value value to set the EftAccountNo
     */
    public void setEftAccountNo(String value) {
        setAttributeInternal(EFTACCOUNTNO, value);
    }

    /**
     * Gets the attribute value for EftAccountName, using the alias name EftAccountName.
     * @return the EftAccountName
     */
    public String getEftAccountName() {
        return (String)getAttributeInternal(EFTACCOUNTNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for EftAccountName.
     * @param value value to set the EftAccountName
     */
    public void setEftAccountName(String value) {
        setAttributeInternal(EFTACCOUNTNAME, value);
    }

    /**
     * Gets the attribute value for EftEmail, using the alias name EftEmail.
     * @return the EftEmail
     */
    public String getEftEmail() {
        return (String)getAttributeInternal(EFTEMAIL);
    }

    /**
     * Sets <code>value</code> as the attribute value for EftEmail.
     * @param value value to set the EftEmail
     */
    public void setEftEmail(String value) {
        setAttributeInternal(EFTEMAIL, value);
    }

    /**
     * Gets the attribute value for BpayBillerCode, using the alias name BpayBillerCode.
     * @return the BpayBillerCode
     */
    public String getBpayBillerCode() {
        return (String)getAttributeInternal(BPAYBILLERCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for BpayBillerCode.
     * @param value value to set the BpayBillerCode
     */
    public void setBpayBillerCode(String value) {
        setAttributeInternal(BPAYBILLERCODE, value);
    }

    /**
     * Gets the attribute value for BpayRefNo, using the alias name BpayRefNo.
     * @return the BpayRefNo
     */
    public String getBpayRefNo() {
        return (String)getAttributeInternal(BPAYREFNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for BpayRefNo.
     * @param value value to set the BpayRefNo
     */
    public void setBpayRefNo(String value) {
        setAttributeInternal(BPAYREFNO, value);
    }

    /**
     * Gets the attribute value for SacBpayBillerCode, using the alias name SacBpayBillerCode.
     * @return the SacBpayBillerCode
     */
    public String getSacBpayBillerCode() {
        return (String)getAttributeInternal(SACBPAYBILLERCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for SacBpayBillerCode.
     * @param value value to set the SacBpayBillerCode
     */
    public void setSacBpayBillerCode(String value) {
        setAttributeInternal(SACBPAYBILLERCODE, value);
    }

    /**
     * Gets the attribute value for SacBpayRefNo, using the alias name SacBpayRefNo.
     * @return the SacBpayRefNo
     */
    public String getSacBpayRefNo() {
        return (String)getAttributeInternal(SACBPAYREFNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for SacBpayRefNo.
     * @param value value to set the SacBpayRefNo
     */
    public void setSacBpayRefNo(String value) {
        setAttributeInternal(SACBPAYREFNO, value);
    }

    /**
     * Gets the attribute value for ChqPayTo, using the alias name ChqPayTo.
     * @return the ChqPayTo
     */
    public String getChqPayTo() {
        return (String)getAttributeInternal(CHQPAYTO);
    }

    /**
     * Sets <code>value</code> as the attribute value for ChqPayTo.
     * @param value value to set the ChqPayTo
     */
    public void setChqPayTo(String value) {
        setAttributeInternal(CHQPAYTO, value);
    }

    /**
     * Gets the attribute value for ChqAddress, using the alias name ChqAddress.
     * @return the ChqAddress
     */
    public String getChqAddress() {
        return (String)getAttributeInternal(CHQADDRESS);
    }

    /**
     * Sets <code>value</code> as the attribute value for ChqAddress.
     * @param value value to set the ChqAddress
     */
    public void setChqAddress(String value) {
        setAttributeInternal(CHQADDRESS, value);
    }

    /**
     * Gets the attribute value for ChqSuburb, using the alias name ChqSuburb.
     * @return the ChqSuburb
     */
    public String getChqSuburb() {
        return (String)getAttributeInternal(CHQSUBURB);
    }

    /**
     * Sets <code>value</code> as the attribute value for ChqSuburb.
     * @param value value to set the ChqSuburb
     */
    public void setChqSuburb(String value) {
        setAttributeInternal(CHQSUBURB, value);
    }

    /**
     * Gets the attribute value for ChqState, using the alias name ChqState.
     * @return the ChqState
     */
    public String getChqState() {
        return (String)getAttributeInternal(CHQSTATE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ChqState.
     * @param value value to set the ChqState
     */
    public void setChqState(String value) {
        setAttributeInternal(CHQSTATE, value);
    }

    /**
     * Gets the attribute value for ChqPostcode, using the alias name ChqPostcode.
     * @return the ChqPostcode
     */
    public String getChqPostcode() {
        return (String)getAttributeInternal(CHQPOSTCODE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ChqPostcode.
     * @param value value to set the ChqPostcode
     */
    public void setChqPostcode(String value) {
        setAttributeInternal(CHQPOSTCODE, value);
    }

    /**
     * Gets the attribute value for ConfirmSuperCompliance, using the alias name ConfirmSuperCompliance.
     * @return the ConfirmSuperCompliance
     */
    public String getConfirmSuperCompliance() {
        return (String)getAttributeInternal(CONFIRMSUPERCOMPLIANCE);
    }

    /**
     * Sets <code>value</code> as the attribute value for ConfirmSuperCompliance.
     * @param value value to set the ConfirmSuperCompliance
     */
    public void setConfirmSuperCompliance(String value) {
        setAttributeInternal(CONFIRMSUPERCOMPLIANCE, value);
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
     * @param superDetailsId key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number superDetailsId) {
        return new Key(new Object[]{superDetailsId});
    }

    /**
     * Validation method for PortalSignupSuperDetails.
     */
    public boolean validateMandatoryFields() {
        boolean isValid = true;
        //if own choice
        if ("OWN_CHOICE".equals(this.getSuperChoice())) {
            String fundType = this.getSuperFundType();
            String superFundName = this.getSuperFundName();

            isValid = !isNullOrEmpty(superFundName);
            if (isValid) {
                String superFundSPIN = this.getSuperFundSpin();
                String superFundMemberName = this.getSuperMemberName();
                String superFundMemberNo = this.getSuperMemberNumber();
                String superFundABN = this.getSuperFundAbn();

                if ("INDUSTRY_CORPORATE".equals(fundType)) {
                    isValid =
                            !(isNullOrEmpty(superFundSPIN) || isNullOrEmpty(superFundMemberName) ||
                              isNullOrEmpty(superFundMemberNo));
                } else if ("SELF_MANAGED".equals(fundType)) {
                    isValid =
                            !(isNullOrEmpty(superFundABN) || isNullOrEmpty(superFundMemberName));

                    if (isValid) {
                        //Based on Payment Choice
                        String paymentType = this.getSuperPaymentType();
                        if ("EFT".equals(paymentType)) {
                            isValid =
                                    !(isNullOrEmpty(this.getEftAccountName()) ||
                                      isNullOrEmpty(this.getEftAccountNo()) ||
                                      isNullOrEmpty(this.getEftBsb()) ||
                                      isNullOrEmpty(this.getEftEmail()));
                        } else if ("BPAY".equals(paymentType)) {
                            isValid =
                                    !(isNullOrEmpty(this.getBpayBillerCode()) ||
                                      isNullOrEmpty(this.getBpayRefNo()));
                        } else if ("CHEQUE".equals(paymentType)) {
                            isValid =
                                    !(isNullOrEmpty(this.getChqAddress()) || isNullOrEmpty(this.getChqPayTo()) ||
                                      isNullOrEmpty(this.getChqPostcode()) ||
                                      isNullOrEmpty(this.getChqState()) ||
                                      isNullOrEmpty(this.getChqSuburb()));
                        }
                        else {
                            isValid = false;
                        }
                    }
                }

            }            
        }
        return isValid;
    }

    private boolean isNullOrEmpty(String value) {
        return (value == null || value.isEmpty());
    }


}