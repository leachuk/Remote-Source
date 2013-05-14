package au.com.entitysolutions.taskflows.common.view;

import javax.faces.context.FacesContext;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class AdfUIUtils {
  public AdfUIUtils() {
    
  }
  
  public static ExtendedRenderKitService getCurrentService(){
    ExtendedRenderKitService service =
             Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                       ExtendedRenderKitService.class);
    
    return service;
  }
}
