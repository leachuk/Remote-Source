package au.com.entitysolutions.taskflows.train.view;


import au.com.entitysolutions.taskflows.common.view.FacesUtil;

import java.util.HashMap;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowContext;
import oracle.adf.controller.TaskFlowTrainModel;
import oracle.adf.controller.TaskFlowTrainStopModel;
import oracle.adf.controller.ViewPortContext;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;


public abstract class TaskflowTrainBean {

    boolean firstStop = false;
    boolean lastStop = false;
    private static String SOURCE_CLASS =
        TaskflowTrainBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);
    private HashMap<String, TaskflowTrainStopBean> trainStops;

    public TaskflowTrainBean() {
        super();
    }

    public abstract String completeTrain();
    
    /**
     * The action method to navigate to the next stop
     * @return outcome for the next stop
     */
    public String navigateNextStop() {
        LOGGER.entering(SOURCE_CLASS, "navigateNextStop");
        String nextStopAction = null;
        ControllerContext controllerContext = ControllerContext.getInstance();
        ViewPortContext currentViewPortCtx =
            controllerContext.getCurrentViewPort();
        TaskFlowContext taskFlowCtx = currentViewPortCtx.getTaskFlowContext();
        TaskFlowTrainModel taskFlowTrainModel =
            taskFlowCtx.getTaskFlowTrainModel();        
        TaskFlowTrainStopModel currentStop =
            taskFlowTrainModel.getCurrentStop();
        TaskFlowTrainStopModel nextStop =
            taskFlowTrainModel.getNextStop(currentStop);
        nextStopAction = nextStop.getOutcome();
        LOGGER.exiting(SOURCE_CLASS, "navigateNextStop");        
        return nextStopAction;
    }

    /**
     * The action method to navigate to previous stop from the 
     * current stop
     * @return outcome for the previous stop
     */
    public String navigatePrevStop() {
        LOGGER.entering(SOURCE_CLASS, "navigatePrevStop");
        String prevStopAction = null;
        ControllerContext controllerContext = ControllerContext.getInstance();
        ViewPortContext currentViewPortCtx =
            controllerContext.getCurrentViewPort();
        TaskFlowContext taskFlowCtx = currentViewPortCtx.getTaskFlowContext();
        TaskFlowTrainModel taskFlowTrainModel =
            taskFlowCtx.getTaskFlowTrainModel();
        TaskFlowTrainStopModel currentStop =
            taskFlowTrainModel.getCurrentStop();
        TaskFlowTrainStopModel prevStop =
            taskFlowTrainModel.getPreviousStop(currentStop);
        prevStopAction = prevStop.getOutcome();
        //is either null or has the value of outcome
        LOGGER.exiting(SOURCE_CLASS, "navigatePrevStop");
        return prevStopAction;
    }

    public void setFirstStop(boolean firstStop) {
        this.firstStop = firstStop;
    }
    //determine if no previous stop in train model
    //return true if so

    public boolean isFirstStop() {
        ControllerContext controllerContext = ControllerContext.getInstance();
        ViewPortContext currentViewPortCtx =
            controllerContext.getCurrentViewPort();
        TaskFlowContext taskFlowCtx = currentViewPortCtx.getTaskFlowContext();
        TaskFlowTrainModel taskFlowTrainModel =
            taskFlowCtx.getTaskFlowTrainModel();
        TaskFlowTrainStopModel currentStop =
            taskFlowTrainModel.getCurrentStop();
        TaskFlowTrainStopModel prevStop =
            taskFlowTrainModel.getPreviousStop(currentStop);
        if (prevStop == null) {
            //only first stop has no previous stop
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isFirstStop(TaskFlowTrainModel taskFlowTrainModel, TaskFlowTrainStopModel trainStop) {
        TaskFlowTrainStopModel prevStop =
            taskFlowTrainModel.getPreviousStop(trainStop);
        if (prevStop == null) {
            //only first stop has no previous stop
            return true;
        } else {
            return false;
        }
        
    }
    public void setLastStop(boolean lastStop) {
        this.lastStop = lastStop;
    }
    //determine if no further stops in train model
    //return true if so

    public boolean isLastStop() {
        ControllerContext controllerContext = ControllerContext.getInstance();
        ViewPortContext currentViewPortCtx =
            controllerContext.getCurrentViewPort();
        TaskFlowContext taskFlowCtx = currentViewPortCtx.getTaskFlowContext();
        TaskFlowTrainModel taskFlowTrainModel =
            taskFlowCtx.getTaskFlowTrainModel();
        TaskFlowTrainStopModel currentStop =
            taskFlowTrainModel.getCurrentStop();
        TaskFlowTrainStopModel nextStop =
            taskFlowTrainModel.getNextStop(currentStop);
        if (nextStop == null) {
            //no next stop means that this is the last stop in the train
            return true;
        } else {
            return false;
        }
    }

    public abstract void rollbackAndRequery(ActionEvent actionEvent);

    public abstract void validateAndCommit(ActionEvent actionEvent);

    /****** HELPER METHODS ********/
    public DCBindingContainer getBindings() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }

    public void setTrainStops(HashMap<String, TaskflowTrainStopBean> trainStops) {
        this.trainStops = trainStops;
    }

    public HashMap<String, TaskflowTrainStopBean> getTrainStops() {
        if (trainStops == null) {
            this.initStopBeans();
        }
        return trainStops;
    }

    public abstract void initStopBeans();
    
    /**
     * Action method for hyperlinks on the review page.
     * The stop order is passed through setPropertyListener
     * #{requestScope.stopOrder} which should be an Integer?
     * @return outcome for the Stop based on its order
     */
    public String navigateToStopOrder() {
        String outcome = null;
        FacesContext context = FacesContext.getCurrentInstance();
        Integer stopOrder = (Integer)FacesUtil.resolveExpression("#{requestScope.stopOrder}");
        if(stopOrder == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Navigation Error", "There was an error navigating to the stop. Please try again or use the train hyperlinks above.");
            context.addMessage(null,message);
        }
        else
        {
            LOGGER.finest("Stop Order:" + stopOrder);
            LOGGER.finest("Stop Order Class:" + stopOrder.getClass());
            ControllerContext controllerContext = ControllerContext.getInstance();
            ViewPortContext currentViewPortCtx =
                controllerContext.getCurrentViewPort();
            TaskFlowContext taskFlowCtx = currentViewPortCtx.getTaskFlowContext();
            TaskFlowTrainModel taskFlowTrainModel =
                taskFlowCtx.getTaskFlowTrainModel();
            outcome = this.navigateToStopOrder(taskFlowTrainModel, stopOrder.intValue());            
            LOGGER.finest("Outcome returned:" + outcome);
        }        
        return outcome;
    }
    
    
    /**
     * This method determines the navigationStep based on the stopOrder.
     * @param trainModel
     * @param stopOrder
     * @return
     */
    protected String navigateToStopOrder(TaskFlowTrainModel trainModel, int stopOrder) {
        LOGGER.entering(SOURCE_CLASS, "navigateToStopOrder");
        TaskFlowTrainStopModel currentStop = trainModel.getCurrentStop();        
        TaskFlowTrainStopModel firstStop = currentStop;
        TaskFlowTrainStopModel prevStop = null;
        TaskFlowTrainStopModel navStop = null;
        //Reverse to the first stop in the model and then navigate the linked-list
        while((prevStop = trainModel.getPreviousStop(firstStop)) != null) {                        
            firstStop = prevStop;
            //LOGGER.finest("Find first stop:" + firstStop);
        }
        
        navStop = firstStop;
        //Start at index 2 as we have already navigated to the first stop - the welcome page.
        for(int i = 2; i <= stopOrder; i++) {
            navStop = trainModel.getNextStop(navStop);
            //LOGGER.finest("Find stop by order:" + navStop);
        }
        LOGGER.finest("Navstop found:" + navStop);
        LOGGER.exiting(SOURCE_CLASS, "navigateToStopOrder");
        return (navStop != null)?navStop.getOutcome():null;
    }    
}
