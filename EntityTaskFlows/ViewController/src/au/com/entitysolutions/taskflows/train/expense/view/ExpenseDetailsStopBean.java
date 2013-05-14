package au.com.entitysolutions.taskflows.train.expense.view;

import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;

import org.apache.commons.lang.StringUtils;

public class ExpenseDetailsStopBean extends TaskflowTrainStopBean {
  public ExpenseDetailsStopBean() {
    super();
  }

  public boolean validate() {    
    
    FacesContext context = FacesContext.getCurrentInstance();
    
    BindingContainer bindings = getBindings();
    DCIteratorBinding headerIteratorBinding =
    getBindings().findIteratorBinding("PortalExpenseItemView2Iterator");
    NavigatableRowIterator headerRowIterator =
    headerIteratorBinding.getNavigatableRowIterator();
    Row[] rows = headerRowIterator.getAllRowsInRange();
    
    boolean atleastOneSelected=false;
     
     for (int ctr = 0; ctr < rows.length; ctr++) {     
       String itemType=(String)rows[ctr].getAttribute("ItemType");
       String itemDesc=(String)rows[ctr].getAttribute("ItemDescription");
       
       if(!StringUtils.isEmpty(itemType)){         
         if(StringUtils.isEmpty(itemDesc)){
           context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Description",
                                (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['ITEM_DESCRIPTION_ERROR']}")));
           
           return false;
         }     
         atleastOneSelected=true;
       }      
       
       if(StringUtils.isEmpty(itemType) && !StringUtils.isEmpty(itemDesc)){
         context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Item Description",
                              (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['ITEM_SELECT_ERROR']}")));
         
         return false;
       }
       
     }
     
     if(!atleastOneSelected){
       context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expense Type",
                            (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['ITEM_SELECT_ONE_ERROR']}")));
       return false;
     }   
    
    return true;
  }

  public boolean processData() {   
    
    return true;
  }

  public String getStepName() {
    return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CLAIM_TRAIN_STEP_NAME']}");
  }
}
