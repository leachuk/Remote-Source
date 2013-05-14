package au.com.entitysolutions.taskflows.common.view;

import oracle.adf.controller.faces.context.FacesPageLifecycleContext;
import oracle.adf.controller.v2.lifecycle.Lifecycle;
import oracle.adf.controller.v2.lifecycle.PagePhaseEvent;
import oracle.adf.controller.v2.lifecycle.PagePhaseListener;

import oracle.binding.BindingContainer;

public class OnPageLoadBackingBeanBase implements PagePhaseListener {
    private BindingContainer bc = null;

    public void beforePhase(PagePhaseEvent event) {
        FacesPageLifecycleContext ctx = 
            (FacesPageLifecycleContext)event.getLifecycleContext();
        if (event.getPhaseId() == Lifecycle.PREPARE_MODEL_ID) {
            bc = ctx.getBindingContainer();
            onPageLoad();
            bc = null;
        }
    }

    public void afterPhase(PagePhaseEvent event) {
    }
    
    public void onPageLoad() {
        
    }
}
