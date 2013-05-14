package au.com.entitysolutions.taskflows.common.view;

import java.util.ArrayList;
import java.util.List;

public class ViewConstants {
    public static long MAX_UPLOAD_FILE_SIZE = 0;
    
    public static final List SUPPORTED_MIME_TYPES = new ArrayList();
    
    static {
        SUPPORTED_MIME_TYPES.add("application/vnd.openxmlformats-officedocument.wordprocessingml.document");        
        SUPPORTED_MIME_TYPES.add("application/vnd.ms-word.document.12"); //.docx from WIN 2007       
        SUPPORTED_MIME_TYPES.add("application/msword");
        SUPPORTED_MIME_TYPES.add("application/vnd.ms-word");
        SUPPORTED_MIME_TYPES.add("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        SUPPORTED_MIME_TYPES.add("application/vnd.ms-excel");
        SUPPORTED_MIME_TYPES.add("application/pdf");        
        SUPPORTED_MIME_TYPES.add("image/jpeg");
        SUPPORTED_MIME_TYPES.add("image/png");
        SUPPORTED_MIME_TYPES.add("image/pjpeg");      
        SUPPORTED_MIME_TYPES.add("image/gif");
        SUPPORTED_MIME_TYPES.add("image/bmp");
        SUPPORTED_MIME_TYPES.add("image/tiff");       
    }
    
    public ViewConstants() {
        super();
    }
    
    public static long getMaxUploadFileSize() {
        if(MAX_UPLOAD_FILE_SIZE == 0) {
            String maxSize = FacesUtil.getCommonBundleValue("MAX_UPLOAD_FILE_SIZE_BYTES");
            System.out.println("************** Max Size:" + maxSize);
            MAX_UPLOAD_FILE_SIZE = Long.parseLong(maxSize);
        }
        return MAX_UPLOAD_FILE_SIZE;
    }
}
