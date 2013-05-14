package au.com.entitysolutions.taskflows.model.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class EntityZipAttachment {
  
  public static final String TEMP_LOC="/tmpADFUploads/";
  private String fileName;
  private ZipOutputStream zipStream; 
  private byte[] buffer ;   
 
  
  public EntityZipAttachment() {
    this.buffer= new byte[1024];
    long timestamp = System.currentTimeMillis()/1000;
    this.fileName = TEMP_LOC+"attachment_"+timestamp+".zip";
    try {
      this.zipStream = new ZipOutputStream(new FileOutputStream(this.fileName));
    } catch (FileNotFoundException e) {
    }
  }
  
  public void addFileEntry(String fileName,String filePath){
    
    File file=new File(filePath);  
    
    if(file.exists()){
      try {
        FileInputStream fin = new FileInputStream(file);    
        
        if(fileName==null)
            fileName=file.getName();
        
        this.zipStream.putNextEntry(new ZipEntry(fileName));
        int length;
         
        while((length = fin.read(buffer)) > 0)
         {
               this.zipStream.write(buffer, 0, length);
         }       
        fin.close();
        this.zipStream.closeEntry();       
      } catch (IOException e) {
      }
    }        
  }
  
  public void closeFile(){
    try {
      this.zipStream.close();
    } catch (IOException e) {
    }
  }
  
  public void addFileEntryRemove(String fileName,String filePath){   
    addFileEntry(fileName,filePath);  
    File file=new File(filePath);
    if(file.exists()){
      file.delete();
    }        
  }

  public String getFileName() {
    return this.fileName;
  }
}
