package au.com.entitysolutions.taskflows.train.expense.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.File;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanCheckbox;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.Number;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class SubmitStopBean extends TaskflowTrainStopBean {
  
  private RichSelectBooleanCheckbox[] checkboxTerms;
  
  private static final String EXIT_OUTCOME="finishExpense";
  
  public SubmitStopBean() {    
    super();
    this.checkboxTerms=new RichSelectBooleanCheckbox[3];
  }

  public boolean validate() {
    FacesContext context=FacesContext.getCurrentInstance();
    //Validating terms
    String errorTermMessage=(String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['TERM_ERROR']}");
    
    for(RichSelectBooleanCheckbox checkboxTerm: checkboxTerms){
      String clientId=checkboxTerm.getClientId(context);
      boolean termSelected=(Boolean)checkboxTerm.getValue();      
      if(!termSelected){       
        context.addMessage(clientId,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Term Agreement",
                             errorTermMessage));
        
        return false;
      }
    }    
    //End term validation   
    
    //Validate rows
    char newline=13;
    BindingContainer bindings = getBindings();
    DCIteratorBinding headerIteratorBinding =
    getBindings().findIteratorBinding("PortalExpenseItemView2Iterator");
    NavigatableRowIterator headerRowIterator =
    headerIteratorBinding.getNavigatableRowIterator();
    Row[] rows = headerRowIterator.getAllRowsInRange();
    boolean atLeastOneRowSelected=false;
    
    for (int ctr = 0; ctr < rows.length; ctr++) {  
      String itemType=(String)rows[ctr].getAttribute("ItemType");
      String itemDesc=(String)rows[ctr].getAttribute("ItemDescription");
      String meaning=(String)rows[ctr].getAttribute("ExpenseMeaning");
      Number amount=(Number)rows[ctr].getAttribute("Amount");
      Number percentUse=(Number)rows[ctr].getAttribute("PercentUse");
      String fileUploadPath=(String)rows[ctr].getAttribute("DocFilePath");
      String formUploadPath=(String)rows[ctr].getAttribute("FormFilePath");
      
      String combineMessage="";      
      //Item is selected
      if(!StringUtils.isEmpty(itemType)){
        atLeastOneRowSelected=true;
        
        if(StringUtils.isEmpty(itemDesc)){
          combineMessage+="Item Description \n";
        }  
        
        if(amount==null){
          combineMessage+="Amount \n";
        } 
        
        if(percentUse==null){
          combineMessage+="Percent Business Use \r\n";
        }  
        
        if(StringUtils.isEmpty(formUploadPath) || !new File(formUploadPath).exists()){
          combineMessage+="Upload expense form \r\n";
        } 
        
        if(StringUtils.isEmpty(fileUploadPath) || !new File(fileUploadPath).exists()){
          combineMessage+="Upload proof of payment \r\n";
        }         
        
        if(!StringUtils.isEmpty(combineMessage)){
          
          combineMessage=(String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['ROW_SUBMIT_ERROR']}")+" "+meaning+" : \r\n"+newline+combineMessage;
          
          context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expense Item Details",
                               combineMessage));
          return false;          
        }        
      }     
    }
    
    if(!atLeastOneRowSelected){
      context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expense Type",
                           (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['ITEM_SELECT_ONE_ERROR']}")));
      return false;
    }   
    
    return true;
  }

  public boolean processData() {
    return true;
  }
  
  public String submitExpense(){
    
    if(!validate()){
      return null;
    }   
    
    FacesContext context=FacesContext.getCurrentInstance();    
    DCBindingContainer bindings = getBindings();    
    OperationBinding operationBinding =
        bindings.getOperationBinding("submitExpense");
    Object obj = operationBinding.execute();
    
    //Object obj = operationBinding.execute();
    if (operationBinding.getErrors() != null &&
        !operationBinding.getErrors().isEmpty()) {
      
      context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Expense Submission",
                           (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FINAL_SUBMIT_ERROR']}")));
      
       return null;
    }
    
//    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Expense Submission",
//                         (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FINAL_SUBMIT_CONFIRM']}")));
      
    
    return EXIT_OUTCOME;
  }

  public String getStepName() {
    return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['SUBMIT_TRAIN_STEP_NAME']}");
  } 
  
  public void setCheckboxTerm1(RichSelectBooleanCheckbox checkboxTerm1) {
    this.checkboxTerms[0] = checkboxTerm1;
  }

  public RichSelectBooleanCheckbox getCheckboxTerm1() {
    return this.checkboxTerms[0];
  }

  public void setCheckboxTerm2(RichSelectBooleanCheckbox checkboxTerm2) {   
    this.checkboxTerms[1] = checkboxTerm2;
  }

  public RichSelectBooleanCheckbox getCheckboxTerm2() {
    return this.checkboxTerms[1];
  }

  public void setCheckboxTerm3(RichSelectBooleanCheckbox checkboxTerm3) {
    this.checkboxTerms[2] = checkboxTerm3;
  }

  public RichSelectBooleanCheckbox getCheckboxTerm3() {
    return this.checkboxTerms[2];
  }
  
}
