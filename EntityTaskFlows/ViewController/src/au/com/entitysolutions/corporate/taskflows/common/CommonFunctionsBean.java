package au.com.entitysolutions.corporate.taskflows.common;

import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.Calendar;

import java.util.Date;

import java.util.ArrayList;

import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.AttributeHints;
import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class CommonFunctionsBean {

    public CommonFunctionsBean() {
        super();
    }

    public Date getMinDate() {
        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);

        cal.set(Calendar.YEAR, 2002);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DATE, 1);
        

        return new Date(cal.getTime().getTime());
    }

    public Date getMaxDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 5);

        return new Date(cal.getTime().getTime());
    }

    public void showErrorMessage(String message) {
        showErrorMessage(null, message);
    }
    
    public void showErrorMessage(String id, String message) {
        FacesMessage fm = new FacesMessage(message);

        fm.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, fm);
    }
    
    public void showErrorMessages(ArrayList<String> messages) {
        FacesContext context = FacesContext.getCurrentInstance();
        for(String message : messages){
            FacesMessage fm = new FacesMessage(message);
            fm.setSeverity(FacesMessage.SEVERITY_ERROR);
            context.addMessage(null, fm);
        }
        
    }

    public static void exportCSV(OutputStream outputStream, String iterator,
                                 String[] attributes) {
        DCBindingContainer dcBindings =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding itrBinding =
            (DCIteratorBinding)dcBindings.get(iterator);
        ViewObject vo = itrBinding.getViewObject();
        vo.setRangeSize(-1);
        PrintWriter out = new PrintWriter(outputStream);
        int count = 0;
        for (Row row : vo.getAllRowsInRange()) {
            if (count == 0) {
                String[] attributeNames = row.getAttributeNames();
                for (int i = 0; i < attributes.length; i++) {
                    AttributeHints hints =
                        row.getAttributeHints(attributes[i]);
                    if (i == attributes.length - 1) {
                        out.print(hints.getLabel(itrBinding.getDataControl().getLocaleContext()));
                    } else {
                        out.print(hints.getLabel(itrBinding.getDataControl().getLocaleContext()) +
                                  ",");
                    }
                }
                out.println();
            }
            for (int i = 0; i < attributes.length; i++) {
                if (i == attributes.length - 1) {
                    out.print(row.getAttribute(attributes[i]) == null ? " " :
                              row.getAttribute(attributes[i]));
                } else {
                    out.print((row.getAttribute(attributes[i]) == null ? " " :
                               row.getAttribute(attributes[i])) + ",");
                }
            }
            out.println();
            count++;
        }
        out.flush();
        out.close();
    }
}

