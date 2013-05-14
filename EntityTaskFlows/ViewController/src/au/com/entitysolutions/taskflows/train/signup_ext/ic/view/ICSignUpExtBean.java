package au.com.entitysolutions.taskflows.train.signup_ext.ic.view;

import au.com.entitysolutions.taskflows.train.signup.common.view.ContractStopBean;
import au.com.entitysolutions.taskflows.train.signup.ipro.view.IProNewSignUpBean;
import au.com.entitysolutions.taskflows.train.view.TaskflowTrainStopBean;

import java.util.HashMap;

public class ICSignUpExtBean extends IProNewSignUpBean {
    public ICSignUpExtBean() {       
    }
    
    @Override
    public void initStopBeans() {
      HashMap<String, TaskflowTrainStopBean> trainStopsMap =
          new HashMap<String, TaskflowTrainStopBean>();
      
      ContractStopBean contractBean = new ContractStopBean();
      contractBean.setContractViewIteratorName("PortalSignupContractExtViewIterator");
      trainStopsMap.put(contractBean.getStepName(), contractBean);
      
      this.setTrainStops(trainStopsMap);
    }
}
