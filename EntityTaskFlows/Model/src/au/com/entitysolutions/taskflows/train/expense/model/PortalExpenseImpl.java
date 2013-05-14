package au.com.entitysolutions.taskflows.train.expense.model;

import au.com.entitysolutions.taskflows.model.CustomEntityImpl;

import oracle.jbo.Key;
import oracle.jbo.RowIterator;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Feb 22 22:05:53 EST 2012
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalExpenseImpl extends CustomEntityImpl {
  private static EntityDefImpl mDefinitionObject;

  /**
   * AttributesEnum: generated enum for identifying attributes and accessors. Do not modify.
   */
  public enum AttributesEnum {
    ExpenseId {
      public Object get(PortalExpenseImpl obj) {
        return obj.getExpenseId();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setExpenseId((Number)value);
      }
    }
    ,
    PersonId {
      public Object get(PortalExpenseImpl obj) {
        return obj.getPersonId();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setPersonId((Number)value);
      }
    }
    ,
    EmailSent {
      public Object get(PortalExpenseImpl obj) {
        return obj.getEmailSent();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setEmailSent((String)value);
      }
    }
    ,
    CreatedBy {
      public Object get(PortalExpenseImpl obj) {
        return obj.getCreatedBy();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setCreatedBy((Number)value);
      }
    }
    ,
    CreationDate {
      public Object get(PortalExpenseImpl obj) {
        return obj.getCreationDate();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setCreationDate((Date)value);
      }
    }
    ,
    LastUpdatedBy {
      public Object get(PortalExpenseImpl obj) {
        return obj.getLastUpdatedBy();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setLastUpdatedBy((Number)value);
      }
    }
    ,
    LastUpdateDate {
      public Object get(PortalExpenseImpl obj) {
        return obj.getLastUpdateDate();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setLastUpdateDate((Date)value);
      }
    }
    ,
    PortalExpenseItem {
      public Object get(PortalExpenseImpl obj) {
        return obj.getPortalExpenseItem();
      }

      public void put(PortalExpenseImpl obj, Object value) {
        obj.setAttributeInternal(index(), value);
      }
    }
    ;
    private static AttributesEnum[] vals = null;
    private static int firstIndex = 0;

    public abstract Object get(PortalExpenseImpl object);

    public abstract void put(PortalExpenseImpl object, Object value);

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
  public static final int EXPENSEID = AttributesEnum.ExpenseId.index();
  public static final int PERSONID = AttributesEnum.PersonId.index();
  public static final int EMAILSENT = AttributesEnum.EmailSent.index();
  public static final int CREATEDBY = AttributesEnum.CreatedBy.index();
  public static final int CREATIONDATE = AttributesEnum.CreationDate.index();
  public static final int LASTUPDATEDBY = AttributesEnum.LastUpdatedBy.index();
  public static final int LASTUPDATEDATE = AttributesEnum.LastUpdateDate.index();
  public static final int PORTALEXPENSEITEM = AttributesEnum.PortalExpenseItem.index();

  /**
   * This is the default constructor (do not remove).
   */
  public PortalExpenseImpl() {
  }

  /**
   * Gets the attribute value for ExpenseId, using the alias name ExpenseId.
   * @return the ExpenseId
   */
  public Number getExpenseId() {
    return (Number)getAttributeInternal(EXPENSEID);
  }

  /**
   * Sets <code>value</code> as the attribute value for ExpenseId.
   * @param value value to set the ExpenseId
   */
  public void setExpenseId(Number value) {
    setAttributeInternal(EXPENSEID, value);
  }

  /**
   * Gets the attribute value for PersonId, using the alias name PersonId.
   * @return the PersonId
   */
  public Number getPersonId() {
    return (Number)getAttributeInternal(PERSONID);
  }

  /**
   * Sets <code>value</code> as the attribute value for PersonId.
   * @param value value to set the PersonId
   */
  public void setPersonId(Number value) {
    setAttributeInternal(PERSONID, value);
  }

  /**
   * Gets the attribute value for EmailSent, using the alias name EmailSent.
   * @return the EmailSent
   */
  public String getEmailSent() {
    return (String)getAttributeInternal(EMAILSENT);
  }

  /**
   * Sets <code>value</code> as the attribute value for EmailSent.
   * @param value value to set the EmailSent
   */
  public void setEmailSent(String value) {
    setAttributeInternal(EMAILSENT, value);
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
   * @return the associated entity oracle.jbo.RowIterator.
   */
  public RowIterator getPortalExpenseItem() {
    return (RowIterator)getAttributeInternal(PORTALEXPENSEITEM);
  }

  /**
   * @param expenseId key constituent

   * @return a Key object based on given key constituents.
   */
  public static Key createPrimaryKey(Number expenseId) {
    return new Key(new Object[]{expenseId});
  }

  /**
   * @return the definition object for this instance class.
   */
  public static synchronized EntityDefImpl getDefinitionObject() {
    if (mDefinitionObject == null) {
      mDefinitionObject = EntityDefImpl.findDefObject("au.com.entitysolutions.taskflows.train.expense.model.PortalExpense");
    }
    return mDefinitionObject;
  }
}
