package au.com.entitysolutions.taskflows.train.expense.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.common.view.FileUtils;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.io.File;

import java.io.IOException;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputFile;

import oracle.jbo.NavigatableRowIterator;

import oracle.jbo.Row;

import org.apache.myfaces.trinidad.model.UploadedFile;
import oracle.jbo.domain.Number;

import org.apache.commons.lang.StringUtils;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;


public class PaymentStopBean extends TaskflowTrainStopBean {
  
  private Map<Long,UploadedFile> uploadFileMap;
  private Map<Long,InputStream> uploadFileStreamMap;
  private RichTable table;
  
  public PaymentStopBean() {
    super();
    this.uploadFileMap=new HashMap<Long,UploadedFile>();
    this.uploadFileStreamMap=new HashMap<Long,InputStream>();
  }
  
  

  public boolean validate() {  
    FacesContext context = FacesContext.getCurrentInstance();       
    DCIteratorBinding headerIteratorBinding =
      getBindings().findIteratorBinding("PortalExpenseItemView2Iterator");
    NavigatableRowIterator iterator =
         headerIteratorBinding.getNavigatableRowIterator();
    
    Row row=iterator.first();
    
    while(row!=null){
      
      Number expenseId=(Number)row.getAttribute("ItemId");
      String itemType=(String)row.getAttribute("ItemType");
      String docFilePath=(String)row.getAttribute("DocFilePath");
      long expenseLongId=expenseId.longValue();
    
      if(!StringUtils.isEmpty(itemType)){
        if(StringUtils.isEmpty(docFilePath) && (!this.uploadFileMap.containsKey(expenseLongId) || !this.uploadFileStreamMap.containsKey(expenseLongId))){        
        context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "File  Upload",
                             (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FILE_UPLOAD_ERROR']}")));               
        return false;
        }        
      }     
      row=iterator.next();
    }    
    return true;
  }

  public boolean processData() {    
    
    FacesContext context = FacesContext.getCurrentInstance();       
    DCIteratorBinding headerIteratorBinding =
      getBindings().findIteratorBinding("PortalExpenseItemView2Iterator");
    NavigatableRowIterator iterator =
         headerIteratorBinding.getNavigatableRowIterator();
    
    Row row=iterator.first();
    
    while(row!=null){      
      Number expenseId=(Number)row.getAttribute("ItemId");
      String itemType=(String)row.getAttribute("ItemType");     
      long expenseLongId=expenseId.longValue();
       
      if(!StringUtils.isEmpty(itemType)){
        if(this.uploadFileMap.containsKey(expenseLongId) && this.uploadFileStreamMap.containsKey(expenseLongId)){         
          UploadedFile uploadedFile=this.uploadFileMap.get(expenseLongId);
          InputStream stream=this.uploadFileStreamMap.get(expenseLongId);         
         
          File tmpfile = FileUtils.createTempFileFromUpload(uploadedFile,stream);
            
          row.setAttribute("DocFilePath",(tmpfile==null)? null : tmpfile.getAbsoluteFile());                
        }           
      }     
      row=iterator.next();
    }    
    
    resetUploadComponent();
    
    return true;
  }

  public String getStepName() {
    return (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['PAYMENT_TRAIN_STEP_NAME']}");
  }
  
  public void fileUpdate(ValueChangeEvent valueChangeEvent) throws IOException {  
  FacesContext context = FacesContext.getCurrentInstance();   
  RichInputFile inputFileComponent = (RichInputFile)valueChangeEvent.getComponent();
  UploadedFile newFile = (UploadedFile)valueChangeEvent.getNewValue();
  String clientId=inputFileComponent.getClientId(context);

  Number expenseId=(Number)FacesUtil.resolveExpression("#{requestScope.itemId}");   
   if(FileUtils.validateUploadFile(context,newFile,clientId) && expenseId!=null){        
     this.uploadFileMap.put(expenseId.longValue(),newFile);   
     this.uploadFileStreamMap.put(expenseId.longValue(),newFile.getInputStream());     
   }  else{
     inputFileComponent.resetValue();
     inputFileComponent.setValue(null);     
   } 
   
    ExtendedRenderKitService service =
             Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                       ExtendedRenderKitService.class);
    StringBuffer script = new StringBuffer();  
    script.append("closePopup();");
    service.addScript(FacesContext.getCurrentInstance(),
                           script.toString());   
  }

  public void setTable(RichTable table) {
    this.table = table;
  }

  public RichTable getTable() {
    return table;
  }
  
  public void resetUploadComponent(){
    this.uploadFileMap=new HashMap<Long,UploadedFile>();
    this.uploadFileStreamMap=new HashMap<Long,InputStream>();
  }
}
