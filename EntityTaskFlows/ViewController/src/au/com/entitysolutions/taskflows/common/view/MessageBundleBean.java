package au.com.entitysolutions.taskflows.common.view;

import java.util.ResourceBundle;

public class MessageBundleBean {
    private String resourceBundle;
    private ResourceBundle bundle;

    public MessageBundleBean() {
        super();       
    }
    
    public MessageBundleBean(String resourceBundle) {
        super();
        this.resourceBundle = resourceBundle;
    }


    public void setResourceBundle(String resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    public String getResourceBundle() {
        return resourceBundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public ResourceBundle getBundle() {
        if (bundle == null) {
            bundle = ResourceBundle.getBundle(resourceBundle);
        }
        return bundle;
    }
    
    public static void main(String[] args) {
        MessageBundleBean bun = new MessageBundleBean("au.com.entitysolutions.taskflows.train.signup.ipro.view.IProSignUpBundle");
        bun.getBundle();
    }
}
