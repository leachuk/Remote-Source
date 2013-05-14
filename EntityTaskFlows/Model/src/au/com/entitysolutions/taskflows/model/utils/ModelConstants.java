package au.com.entitysolutions.taskflows.model.utils;

import oracle.jbo.domain.Number;

public interface ModelConstants {
    //public static final String SUPPORT_EMAIL_KEY = "SUPPORT_EMAIL_ID";
    public static final String PORTAL_NOTIFICATION_NOREPLY_SENDER_KEY = "PORTAL_NOTIFICATION_NOREPLY_SENDER";
    
    public static final String PORTAL_IPRO_COMPLETE_TASKFLOW_EMAIL_TEMPLATE_KEY = "PORTAL_IPRO_COMPLETE_TASKFLOW_EMAIL_TEMPLATE";
        
    public static final String PORTAL_FGT_PWD_EMAIL_TEMPLATE_KEY =
        "PORTAL_FGT_PWD_EMAIL_TEMPLATE";    
    public static final String PORTAL_FGT_USERID_EMAIL_TEMPLATE_KEY =
        "PORTAL_FGT_USERID_EMAIL_TEMPLATE"; 
    
    public static final String LOGGED_IN_PERSON_ID_KEY = "LOGGED_IN_PERSON_ID";
    
    public static final String TRAIN_TASKFLOW_COMPLETE_STATUS = "C";
    
    public static final String ipromgr = "ipromgr";
    public static final String custadm = "custadm";
    
    public static final String PORTAL_COMMUNICATION_TYPE="I";
    
    public static final String PORTAL_ACTIVITY_TYPE="OP";
    
    public static final Number PORTAL_LOGGED_IN_USER_ID=new Number(1);
    
}
