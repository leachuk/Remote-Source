package au.com.entitysolutions.corporate.taskflows.mycontracts;

import java.io.OutputStream;
import au.com.entitysolutions.corporate.taskflows.common.CommonFunctionsBean;
import java.sql.Date;

import java.text.SimpleDateFormat;

import java.util.Calendar;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class ContractAssignmentDetailsManagedBean {
    private Date fromDate;
    private Date toDate;

    
    public ContractAssignmentDetailsManagedBean() {
    }

    public String getFilename() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        return "MyContracts-" + fmt.format(dt) + ".csv";
    }
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getFromDate() {
        if(fromDate == null){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.WEEK_OF_YEAR, -2);
            return new Date(cal.getTime().getTime());
        }
        return fromDate;
    }
    
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getToDate() {
        if(toDate == null){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.WEEK_OF_YEAR, 6);
            return new Date(cal.getTime().getTime());
        }
        return toDate;
    }

    public void exportCSV(FacesContext facesContext,
                          OutputStream outputStream) {
        String [] attributes = {"FullName", "PositionTitle", "EndUserOrgStateCode", "StartDate", "EndDate", "CostCentre", "IproNbrAsgnBeyondEndDate"};
        CommonFunctionsBean.exportCSV(outputStream, "MyContractsListVO1Iterator", attributes);
    }
}
