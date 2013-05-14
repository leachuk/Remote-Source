package au.com.entitysolutions.taskflows.common.view;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputFile;

import org.apache.myfaces.trinidad.model.UploadedFile;

public abstract class MultipleFileUploadSupportBean {
   
  protected Map<String,UploadedFile> uploadFileMap;
  protected Map<String,InputStream> uploadFileStreamMap; 
  
  public MultipleFileUploadSupportBean() {
    initializeMap();
  }  
  
  protected void initializeMap(){
    this.uploadFileMap=new HashMap<String,UploadedFile>();
    this.uploadFileStreamMap=new HashMap<String,InputStream>();
  }  
  
  protected boolean addUploadFileFromEvent(String fileKey,ValueChangeEvent valueChangeEvent) {  
    boolean result=false;
    
    FacesContext context = FacesContext.getCurrentInstance();   
    RichInputFile inputFileComponent = (RichInputFile)valueChangeEvent.getComponent();
    UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
    String clientId=inputFileComponent.getClientId(context);
    
    if(fileKey==null){
      fileKey=String.valueOf(clientId);
    }
    
    if(FileUtils.validateUploadFile(context,file,clientId) && fileKey!=null){        
      addUploadfile(fileKey,file);
      result=true;
    }  else{
      inputFileComponent.resetValue();
      inputFileComponent.setValue(null);     
    }        
    return result;
  }  
  
  public void onFileChange(ValueChangeEvent valueChangeEvent){
    addUploadFileFromEvent(null,valueChangeEvent);
  }
  
  protected void addUploadfile(String fileKey,UploadedFile file){
    this.uploadFileMap.put(fileKey,file);
    try {
      this.uploadFileStreamMap.put(fileKey,(file!=null)? file.getInputStream(): null);
    } catch (IOException e) {
      System.out.println("Error getting input file stream");
      e.printStackTrace();
    }
  }
  
  protected void remoteUploadFile(String fileKey){
    this.uploadFileMap.remove(fileKey);
    this.uploadFileStreamMap.remove(fileKey);
  }

  public Map<String, UploadedFile> getUploadFileMap() {
    return uploadFileMap;
  }

  public Map<String, InputStream> getUploadFileStreamMap() {
    return uploadFileStreamMap;
  }
}
