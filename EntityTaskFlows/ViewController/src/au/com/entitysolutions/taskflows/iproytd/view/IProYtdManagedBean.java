package au.com.entitysolutions.taskflows.iproytd.view;

import java.util.ArrayList;
import java.util.Calendar;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;

public class IProYtdManagedBean {
    private RichSelectOneChoice f_year;
    private String financialYear;
    private ArrayList<SelectItem> years;

    public IProYtdManagedBean() {
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }
    
    public ArrayList<SelectItem> getYears() {
        
        int year = getCurrentYear();
        years = new ArrayList<SelectItem>();
        for (int i=year; i>=year-2; i--){
            years.add(new SelectItem(String.valueOf(i), String.valueOf(i) + "-" + String.valueOf(i+1)));
        }
        return years;
    }

    private int getCurrentYear(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(cal.MONTH) + 1;
        int year = cal.get(cal.YEAR);
        if(month < 7){
            return year - 1;
        }else{
            return year;
        }
    }
    public String getFinancialYear() {
        if(financialYear == null){
            return String.valueOf(getCurrentYear());
        }
        return financialYear;
    }
}
