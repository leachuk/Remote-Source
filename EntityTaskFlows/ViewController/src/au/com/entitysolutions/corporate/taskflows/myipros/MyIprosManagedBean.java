package au.com.entitysolutions.corporate.taskflows.myipros;

import java.io.OutputStream;
import au.com.entitysolutions.corporate.taskflows.common.CommonFunctionsBean;

import java.text.SimpleDateFormat;

import java.sql.Date;

import java.util.Calendar;

import javax.faces.context.FacesContext;


public class MyIprosManagedBean {
    private Date fromDate;
    private Date toDate;
    private String filename;

    public MyIprosManagedBean() {
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        if (fromDate == null){
            Calendar cal = Calendar.getInstance();
            return new Date(cal.getTime().getTime());
        }
        return fromDate;
    }

    public String getFilename() {
        Calendar cal = Calendar.getInstance();
        Date dt = new Date(cal.getTime().getTime());
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        return "MyIPros-" + fmt.format(dt) + ".csv";
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getToDate() {
        if (toDate == null){
            Calendar cal = Calendar.getInstance();
            return new Date(cal.getTime().getTime());
        }
        return toDate;
    }

    public String printDates() {
        System.out.println("From Date: " + getFromDate());
        System.out.println("To Date: " + getToDate());
        return null;
    }

    public void exportCSV(FacesContext facesContext,
                          OutputStream outputStream) {
        String [] attributes = {"FullName", "PositionTitle", "StartDate", "EndDate", "OrganisationName", "EndUserOrgStateCode"};
        CommonFunctionsBean.exportCSV(outputStream, "MyIprosAssignmets1Iterator", attributes);
    }
}
