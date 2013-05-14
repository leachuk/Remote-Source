package au.com.entitysolutions.taskflows.common.view;

import java.util.ResourceBundle;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;


public class FacesUtil {
    
    private static MessageBundleBean COMMON_BUNDLE = new MessageBundleBean("au.com.entitysolutions.taskflows.common.view.CommonTaskflowMessageBundle");
    public FacesUtil() {
        super();
    }
    
    public static String getCommonBundleValue(String key) {
        System.out.println("************** Common Bundle Value");
        ResourceBundle bundle = COMMON_BUNDLE.getBundle();
        return bundle.getString(key);
    }

    public static Object resolveExpression(String expression) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = ctx.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, expression,
                                            Object.class);
        return valueExp.getValue(elContext);
    }

    public static void setExpressionValue(String expression, Object value) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Application app = ctx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = ctx.getELContext();
        ValueExpression valueExp =
            elFactory.createValueExpression(elContext, expression,
                                            Object.class);
        valueExp.setValue(elContext, value);
    }

    public static Object executeOperationBinding(BindingContainer bindings,
                                                 String bindingName) {
        OperationBinding operationBinding =
            bindings.getOperationBinding(bindingName);
        Object obj = operationBinding.execute();
        return obj;
    }

    public static boolean isUserInRole(String roleName) {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole(roleName);
    }
    
    public static void main(String[] args) {
        System.out.println(getCommonBundleValue("MAX_UPLOAD_FILE_SIZE_BYTES"));
    }
}
