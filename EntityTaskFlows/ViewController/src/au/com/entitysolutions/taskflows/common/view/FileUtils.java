package au.com.entitysolutions.taskflows.common.view;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Blob;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

import oracle.jbo.domain.Number;

import org.apache.commons.lang.StringUtils;

public class FileUtils {
    private static final String SOURCE_CLASS =
        FileUtils.class.getCanonicalName();
//    public static final String TEMP_LOC=FacesContext.getCurrentInstance().getExternalContext().getInitParameter(
//                                           "oracle.adf.view.faces.UPLOAD_TEMP_DIR"
//                                          );   
    public static final String TEMP_LOC="/tmpADFUploads/";
    private static final Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    public FileUtils() {
        super();
    }

    /**
     * Convenience method to write Blob content to OutputStream
     * @param file
     * @param outStream
     * @throws IOException
     */
    public static void writeBlobToStream(BlobDomain file,
                                         OutputStream outStream) throws IOException {
        try {
            writeInputStreamToOutputStream(file.getBinaryStream(), outStream);
        } finally {
            file.closeInputStream();
        }
    }

    /**
     * Read from input stream and write to an output stream
     * @param is
     * @param outStream
     * @throws IOException
     */
    public static void writeInputStreamToOutputStream(InputStream is,
                                                      OutputStream outStream) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(is);
        byte[] buffer = new byte[1024 * 4]; // Adjust if you want
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
    }

    /**
     * Create a BlobDomain from the uploaded file object
     * @param file
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public static BlobDomain createBlobDomain(UploadedFile file) throws SQLException,
                                                                        IOException {
        BlobDomain blob = new BlobDomain();
        OutputStream outputStream = null;
        InputStream istream = null;
        try {
            outputStream = blob.getBinaryOutputStream();
            istream = file.getInputStream();
            writeInputStreamToOutputStream(istream, outputStream);
        } finally {
            try {
                blob.closeOutputStream();
            } catch (Exception ee) { //Do nothing
            }
            try {
                istream.close();
            } catch (Exception ee) { //Do nothing
            }
        }
        return blob;
    }
    
  /**
   * Create a BlobDomain from the uploaded file object
   * @param file,is
   * @return
   * @throws SQLException
   * @throws IOException
   */
  public static BlobDomain createBlobDomain(UploadedFile file,InputStream is) throws SQLException,
                                                                      IOException {
      BlobDomain blob = new BlobDomain();
      OutputStream outputStream = null;
      InputStream istream = null;
      try {
          outputStream = blob.getBinaryOutputStream();
          istream =(is!=null) ? is : file.getInputStream();
          writeInputStreamToOutputStream(istream, outputStream);
      } finally {
          try {
              blob.closeOutputStream();
          } catch (Exception ee) { //Do nothing
          }
          try {
              istream.close();
          } catch (Exception ee) { //Do nothing
          }
      }
      return blob;
  }
    
    /**
     * Validate file against max size and supported mime types
     * @param context
     * @param file
     * @return
     */
    public static boolean validateUploadFile(FacesContext context,
                                             UploadedFile file) {       
        return validateUploadFile(context, file, null);
    }
    
    /**
     * Overloaded method to include clientId
     * @param context
     * @param file
     * @param clientId
     * @return
     */
    public static boolean validateUploadFile(FacesContext context,
                                             UploadedFile file, String clientId) {
        boolean isValid = true;
        if(file == null) {
            isValid = false;
        }
        LOGGER.finest("Max file size:" + ViewConstants.getMaxUploadFileSize());
        if (file != null &&
            file.getLength() > ViewConstants.getMaxUploadFileSize()) {
            LOGGER.severe("File Size Error:" + file.getLength());
            isValid = false;
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File size exceeded",
                                 FacesUtil.getCommonBundleValue("FILE_SIZE_ERROR"));
            context.addMessage(clientId, message);
        } else if (file != null &&
                   !ViewConstants.SUPPORTED_MIME_TYPES.contains(file.getContentType())) {
            isValid = false;
            LOGGER.severe("File Type Error:" + file.getContentType());
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "File type not supported",
                                 FacesUtil.getCommonBundleValue("FILE_TYPE_ERROR"));
            context.addMessage(clientId, message);
        }
        return isValid;
    }
    
    public static File createTempFileFromUpload(UploadedFile uploadFile,InputStream stream){      
     File file=null;   
      
      if(uploadFile ==null)
          return file;
      
     FileOutputStream out;
     InputStream in;
     long timestamp =new Random().nextInt(1000);
     
     //Generate the unique file name by appending timestamp to the end of the file name
     Pattern  pattern=Pattern.compile("^(.+)\\.(.+)$");
     Matcher matcher = pattern.matcher(uploadFile.getFilename());      
     String fileName="";     
      if( matcher.find() ) {          
        if(!StringUtils.isEmpty(matcher.group(1)) && !StringUtils.isEmpty(matcher.group(2))){
             fileName=matcher.group(1)+"_"+timestamp+"."+matcher.group(2);
             LOGGER.finest("Temporary file name generated: "+fileName);
        }       
      }   
      else{
        LOGGER.severe("File name does not match pattern "+uploadFile.getFilename());
        fileName=timestamp+"_"+uploadFile.getFilename();
      }          
     //End generating file name
      
     try {
      out=new FileOutputStream(TEMP_LOC+fileName);
      in=(stream==null) ? uploadFile.getInputStream() : stream;    
      byte[] buffer = new byte[1024];
      
      int length;
       
       while((length=in.read(buffer))>0){
         out.write(buffer,0,length);   
       }    
                 
      in.close();
      out.close(); 
      file=new File(TEMP_LOC+fileName);   
       
      LOGGER.finest("Temporary file generated: "+TEMP_LOC+fileName);
       
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }catch (IOException e) {
      e.printStackTrace();
    }
    
    return file;
      
    }    
  
}
