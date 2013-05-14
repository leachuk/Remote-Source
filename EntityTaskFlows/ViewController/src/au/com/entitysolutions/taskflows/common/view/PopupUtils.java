package au.com.entitysolutions.taskflows.common.view;

import javax.faces.context.FacesContext;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class PopupUtils extends AdfUIUtils {
  
  
  public static void invokePopup(String popupId){
    ExtendedRenderKitService service =
             Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                       ExtendedRenderKitService.class);
    StringBuffer showPopup = new StringBuffer();
    showPopup.append("var hints = new Object();");
    showPopup.append("var popupObj=AdfPage.PAGE.findComponent('" +
                         popupId + "'); popupObj.show();");
     service.addScript(FacesContext.getCurrentInstance(),
                           showPopup.toString());
                                   
  }
  
  public static void hidePopup(String popupId){
    ExtendedRenderKitService service =
             Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                       ExtendedRenderKitService.class);
    StringBuffer showPopup = new StringBuffer();  
    showPopup.append("var popupObj=AdfPage.PAGE.findComponent('" +
                         popupId + "'); popupObj.hide();");
     service.addScript(FacesContext.getCurrentInstance(),
                           showPopup.toString());
  }
  
}
