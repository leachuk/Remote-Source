package au.com.entitysolutions.taskflows.train.expense.view;

import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainBean;

import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;



import java.util.HashMap;

import javax.el.ELContext;

import javax.el.ExpressionFactory;

import javax.el.ValueExpression;

import javax.faces.FacesException;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.controller.TaskFlowContext;
import oracle.adf.controller.TaskFlowTrainModel;
import oracle.adf.controller.TaskFlowTrainStopModel;
import oracle.adf.controller.ViewPortContext;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.adf.view.rich.event.DialogEvent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;
import oracle.adf.view.rich.event.DialogEvent.Outcome;

import org.apache.myfaces.trinidad.render.ExtendedRenderKitService;
import org.apache.myfaces.trinidad.util.Service;

public class ExpenseTrainBean extends TaskflowTrainBean {
  
  private RichCommandButton hiddenHome;

  public ExpenseTrainBean() {
    super();
  }

  public String completeTrain() {
    return null;
  }

  public void rollbackAndRequery(ActionEvent actionEvent) {
  }

  public void validateAndCommit(ActionEvent actionEvent) {     
    String expression =
        (String)FacesUtil.resolveExpression("#{requestScope.stopName}");           
    boolean isSuccess = true;
    if (expression != null) {
        TaskflowTrainStopBean stopBean = getTrainStops().get(expression);           
        if (stopBean != null) {         
            //Execute validation and post changes on the stop bean
            isSuccess = stopBean.validate();           
            if (isSuccess) {
                isSuccess = stopBean.processData();
            }
        }    
      
    }
    if (!isSuccess) {
        throw new FacesException("An error occurred while saving the data");
    }
  }

  public void initStopBeans() {
    
    HashMap<String,TaskflowTrainStopBean> trainStopsMap =
            new HashMap<String, TaskflowTrainStopBean>();
    
    ExpenseDetailsStopBean expenseStop=new ExpenseDetailsStopBean();
    DeclarationStopBean declareStop=new DeclarationStopBean();
    PaymentStopBean paymentStop=new PaymentStopBean();
    SubmitStopBean submitStop=new SubmitStopBean();
    
    trainStopsMap.put(expenseStop.getStepName(),expenseStop);
    trainStopsMap.put(declareStop.getStepName(),declareStop);
    trainStopsMap.put(paymentStop.getStepName(),paymentStop);
    trainStopsMap.put(submitStop.getStepName(),submitStop);
    
    setTrainStops(trainStopsMap);    
    
  }  
  
  public String validateAndNavigateNext(){
    return super.navigateNextStop();   
  }


  public void setHiddenHome(RichCommandButton hiddenHome) {
    this.hiddenHome = hiddenHome;
  }

  public RichCommandButton getHiddenHome() {
    return hiddenHome;
  }
  
  public void onDialogAction(DialogEvent dialogEvent) {
     Outcome outcome = dialogEvent.getOutcome();
     
    if(outcome == Outcome.yes){       
      ExtendedRenderKitService service =
               Service.getRenderKitService(FacesContext.getCurrentInstance(),
                                         ExtendedRenderKitService.class);
      StringBuffer showPopup = new StringBuffer();   
       showPopup.append("obj=document.getElementById('"+this.hiddenHome.getClientId(FacesContext.getCurrentInstance())+"');obj.click();");
       service.addScript(FacesContext.getCurrentInstance(),
                             showPopup.toString());      
       
    }
  }
  
  
}
