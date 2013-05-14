package au.com.entitysolutions.taskflows.train.view;


import au.com.entitysolutions.taskflows.common.view.FacesUtil;
import au.com.entitysolutions.taskflows.login.view.LoginFlowBean;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class TrainLauncherBean {
    private static String SOURCE_CLASS =
        LoginFlowBean.class.getCanonicalName();
    private static Logger LOGGER = Logger.getLogger(SOURCE_CLASS);

    public TrainLauncherBean() {
        super();
    }

    /**
     * The action method to launch the train taskflow.
     * @return outcome that navigates to the taskflow
     */
    public String launchTrain() {
        LOGGER.entering(SOURCE_CLASS, "launchTrain");
        FacesContext context = FacesContext.getCurrentInstance();
        String launchOutcome =
            (String)FacesUtil.resolveExpression("#{requestScope.portal_tf_code}");
        LOGGER.finest("Launch TF Code:" + launchOutcome);
        if (launchOutcome == null) {
            LOGGER.severe("The launch TF code was null and the train could not be launched.");
            FacesMessage message =
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                 "Error launching the process",
                                 "A system error occurred. Please contact the system administrator.");
            context.addMessage(null, message);
        }
        else {
            LOGGER.finest("Start taskflow");
            BindingContainer bindings = getBindings();
            OperationBinding startTFOper = bindings.getOperationBinding("startTaskflow");
            Object result = startTFOper.execute();
            if(startTFOper.getErrors() == null || startTFOper.getErrors().isEmpty()) {
                LOGGER.finest("Commit");    
                OperationBinding commitOper = bindings.getOperationBinding("Commit");
                result = commitOper.execute();
                if(commitOper.getErrors() != null && !commitOper.getErrors().isEmpty()) {
                    LOGGER.severe("Error on commit");    
                    launchOutcome = null;
                }                
            }
            else {
                LOGGER.finest("Errors on startTaskflow");    
                launchOutcome = null;
            }
        }
        LOGGER.exiting(SOURCE_CLASS, "launchTrain");
        //launchOutcome = null;
        return launchOutcome;
    }

    public void testActionListener(ActionEvent actionEvent) {
        System.out.println("***************************************************** ACTION LISTENER ");
    }

    public void taskFlowReturnListener() {
        System.out.println("***************************************************** TASKFLOW RETURN LISTENER ");
    }
    
    /****** HELPER METHODS ********/
    public DCBindingContainer getBindings() {
        DCBindingContainer bindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        return bindings;
    }
}
