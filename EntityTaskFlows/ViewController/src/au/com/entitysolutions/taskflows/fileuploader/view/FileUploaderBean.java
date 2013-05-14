package au.com.entitysolutions.taskflows.fileuploader.view;

import au.com.entitysolutions.taskflows.common.view.AdfUIUtils;
import au.com.entitysolutions.taskflows.common.view.FacesUtil;

import au.com.entitysolutions.taskflows.common.view.FileUtils;

import au.com.entitysolutions.taskflows.common.view.PopupUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStream;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Map;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.share.ADFContext;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
import oracle.adf.view.rich.component.rich.input.RichInputText;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.binding.OperationBinding;

import oracle.jbo.domain.Number;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.myfaces.trinidad.model.RowKeySet;
import org.apache.myfaces.trinidad.model.UploadedFile;
import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class FileUploaderBean {
  
  private RichInputFile[] fileUpload;
  private UploadedFile[] uploadedFile;
  private RichInputText[] file;
  private InputStream[] is;
  private static final String SUCCESS_RESPONSE="success";
  private RichCommandButton clearButton;
  private RichPopup popupProgress; //action success in taskflow
  
  public FileUploaderBean(){
    this.uploadedFile=new UploadedFile[3];
    this.fileUpload=new RichInputFile[3];
    this.file=new RichInputText[3];
    this.is=new InputStream[3];
  }
  
  
  public String upload() {
    String result=null;  
    FacesContext context = FacesContext.getCurrentInstance();
    UploadedFile fileUpload=null;
    String description=""; 
    String clientId=null;
    String attachmentPath=null;
    String filesDescription="";
    String fileUploadClientId=null;  
    
    
    hidePopup();
   
  
    Map<Integer,UploadedFile> validFileUpload=new HashMap<Integer,UploadedFile>();       
        
    for(int i=0;i<this.uploadedFile.length;i++) {        
      fileUpload=this.uploadedFile[i];      
      clientId=this.file[i].getClientId(context);    
      fileUploadClientId=this.fileUpload[i].getClientId(context);     
      description=(String)this.file[i].getValue();
      
      //There is no file uploaded but there is description
      if(!StringUtils.isEmpty(description) && fileUpload==null){
        context.addMessage(fileUploadClientId,new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload",
                             (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FILE_UPLOAD_WITH_TEXT_REQUIRED_ERROR']}")));               
        return null;
      }
      
      //The file is uploaded
      if(fileUpload!=null) {       
        
        //Validate file upload
        if(!FileUtils.validateUploadFile(context,fileUpload,fileUploadClientId)){           
          return null;
        }       
        
        //Description validation
        if(StringUtils.isEmpty(description)){    
          
          context.addMessage(clientId,new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Description",
                               (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FILE_DESCRIPTION_ERROR']}")));               
          return null;
        }
        validFileUpload.put(new Integer(i),fileUpload);
        filesDescription+=description+" - "+fileUpload.getFilename()+" <br><br>";
      }       
    } 
    
    if(validFileUpload.size()<=0){     
      context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "File Upload",
                               (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FILE_UPLOAD_REQUIRED_ERROR']}")));       
      return null;
    }
    else{
      attachmentPath=createZipFile(validFileUpload);
    }
    
    result=SUCCESS_RESPONSE;
    DCBindingContainer bindings =
        (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
    FacesUtil.setExpressionValue("#{requestScope.attachment_path}",
                                 attachmentPath);
    FacesUtil.setExpressionValue("#{requestScope.fileDescription}",
                                 filesDescription);
    OperationBinding operationBinding =
        bindings.getOperationBinding("emailFileUploader");    
    operationBinding.execute(); 
    
   
   
    
    context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "File Upload",
                             (String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['FILE_UPLOAD_SUCCESS']}")));       
    
    resetForm(null);
    

    
    return result;    
  }
  
  public String createZipFile(Map<Integer,UploadedFile> validFileUpload) {
    long timestamp = System.currentTimeMillis()/1000;
    String zipFile = FileUtils.TEMP_LOC+"attachment_"+timestamp+".zip";
    byte[] buffer = new byte[1024];   
    
    ZipOutputStream zout;

    try {
      zout = new ZipOutputStream(new FileOutputStream(zipFile));
      
      for (Map.Entry<Integer,UploadedFile> entry : validFileUpload.entrySet()) {
              InputStream stream=this.is[entry.getKey()];
              UploadedFile file=entry.getValue();
    
      File upfile=FileUtils.createTempFileFromUpload(file,stream);
               
      if(upfile!=null){
          
       FileInputStream fin = new FileInputStream(upfile);
       
       zout.putNextEntry(new ZipEntry(upfile.getName()));
        
        int length;
         
        while((length = fin.read(buffer)) > 0)
         {
               zout.write(buffer, 0, length);
         }       
        fin.close();
        zout.closeEntry();
        upfile.delete();
    }        
     }
     zout.close();
    } catch (FileNotFoundException e) {
     } catch (IOException e){} 
    
    return zipFile;
  }  

  public void setFile1(RichInputText file1) {
    this.file[0] = file1;
  }

  public RichInputText getFile1() {
    return this.file[0];
  }

  public void setFileUpload1(RichInputFile fileUpload1) {
   this.fileUpload[0] = fileUpload1;
  }

  public RichInputFile getFileUpload1() {
    return this.fileUpload[0];
  }

  public void setFile2(RichInputText file2) {
    this.file[1] = file2;
  }

  public RichInputText getFile2() {
    return this.file[1];
  }

  public void setFile3(RichInputText file3) {
    this.file[2] = file3;
  }

  public RichInputText getFile3() {
    return this.file[2];
  }

  public void setFileUpload2(RichInputFile fileUpload2) {
    this.fileUpload[1] = fileUpload2;
  }

  public RichInputFile getFileUpload2() {
    return this.fileUpload[1];
  }

  public void setFileUpload3(RichInputFile fileUpload3) {
    this.fileUpload[2] = fileUpload3;
  }

  public RichInputFile getFileUpload3() {
    return this.fileUpload[2];
  }


  public void setUploadedFile1(UploadedFile uploadedFile1) {  
    this.uploadedFile[0] = uploadedFile1;    
    try {
         this.is[0] =(this.uploadedFile[0]!=null) ? this.uploadedFile[0].getInputStream() : this.is[0];
         
       } catch (IOException e) {
         e.printStackTrace();
       }
   // removeNoWrapFileUpdate();
  }

  public UploadedFile getUploadedFile1() {
    return this.uploadedFile[0];
  }

  public void setUploadedFile2(UploadedFile uploadedFile2) {
   this.uploadedFile[1] = uploadedFile2;
    try {
      this.is[1] = (this.uploadedFile[1]!=null) ? this.uploadedFile[1].getInputStream() : this.is[1];
       } catch (IOException e) {
         e.printStackTrace();
       }
   // removeNoWrapFileUpdate();
  }

  public UploadedFile getUploadedFile2() {
    return this.uploadedFile[1];
  }

  public void setUploadedFile3(UploadedFile uploadedFile3) {
    this.uploadedFile[2] = uploadedFile3;
    try {
         this.is[2] =(this.uploadedFile[2]!=null) ? this.uploadedFile[2].getInputStream() : this.is[2];
       } catch (IOException e) {
         e.printStackTrace();
       }
   // removeNoWrapFileUpdate();
  }

  public UploadedFile getUploadedFile3() {
    return this.uploadedFile[2];
  }

  public void resetForm(ActionEvent actionEvent) {
    
    String index=(String)FacesUtil.resolveExpression("#{requestScope.fileUploadIndex}");  
    
    if(index==null){
      for(RichInputText inputfile : this.file){
        inputfile.resetValue();
        inputfile.setValue(null);
      }  
      
      this.uploadedFile=new UploadedFile[3];
      this.fileUpload=new RichInputFile[3];
      this.file=new RichInputText[3];
      this.is=new InputStream[3];
    }
    else if(StringUtils.isNumeric(index)){
      Integer intIndex=Integer.parseInt(index);
      
      this.file[intIndex].resetValue();
      this.file[intIndex].setValue(null);
      this.uploadedFile[intIndex]=null;
      this.fileUpload[intIndex]=null;
      this.file[intIndex]=null;
      this.is[intIndex]=null;
    } 
  }

  public void setClearButton(RichCommandButton clearButton) {
    this.clearButton = clearButton;
  }

  public RichCommandButton getClearButton() {
    return clearButton;
  }

  public void setPopupProgress(RichPopup popupProgress) {
    this.popupProgress = popupProgress;
  }

  public RichPopup getPopupProgress() {
    return popupProgress;
  }

  public void invokeFileUpload(ActionEvent actionEvent) {
    String popupId= this.popupProgress.getClientId(FacesContext.getCurrentInstance());
    PopupUtils.invokePopup(popupId);
    
  }
  
  public void fileUpdate(ValueChangeEvent valueChangeEvent) throws IOException {  
  FacesContext context = FacesContext.getCurrentInstance();   
  RichInputFile inputFileComponent = (RichInputFile)valueChangeEvent.getComponent();
  UploadedFile newFile = (UploadedFile)valueChangeEvent.getNewValue();
  String clientId=inputFileComponent.getClientId(context); 
   if(!FileUtils.validateUploadFile(context,newFile,clientId)){   
     inputFileComponent.resetValue();
     inputFileComponent.setValue(null);     
   } 
   hidePopup();   
  }   
    
  public void hidePopup(){
     FacesContext context = FacesContext.getCurrentInstance();   
     
    String popupId= this.popupProgress.getClientId(FacesContext.getCurrentInstance());
    PopupUtils.hidePopup(popupId);
  } 
}
