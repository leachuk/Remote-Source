package au.com.entitysolutions.corporate.taskflows.mybillingsummary;

import java.sql.Date;

import java.util.Calendar;

import au.com.entitysolutions.corporate.taskflows.common.CommonFunctionsBean;

public class MonthlyInvoicesManagedBean {
    private Number month;
    private Number year;

    public int getMaxYear() {

        Calendar cal = Calendar.getInstance();
        java.util.Date maxDate = new CommonFunctionsBean().getMaxDate();
        cal.setTime(maxDate);

        return cal.get(Calendar.YEAR);
    }
    
    public int getMinYear() {

        Calendar cal = Calendar.getInstance();
        java.util.Date minDate = new CommonFunctionsBean().getMinDate();
        cal.setTime(minDate);

        return cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        if (month == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -5);
            return cal.get(Calendar.MONTH) + 1;
        }
        return month.intValue();
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        if (year == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.MONTH, -5);
            return cal.get(Calendar.YEAR);
        }
        return year.intValue();
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Date getFromDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, getYear());
        cal.set(Calendar.MONTH, getMonth() - 1);
        cal.set(Calendar.DATE, 1);
        return new Date(cal.getTime().getTime());
    }

    public boolean getValidDates() {
        java.util.Date minDate = new CommonFunctionsBean().getMinDate();
        java.util.Date maxDate = new CommonFunctionsBean().getMaxDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(maxDate);
        cal.add(Calendar.MONTH, -6);
        return (getFromDate().getTime() >= minDate.getTime() && getFromDate().getTime() <= cal.getTime().getTime());
    }
}
