package au.com.entitysolutions.corporate.taskflows.mybillingsummary;

import au.com.entitysolutions.corporate.taskflows.common.CommonFunctionsBean;

import java.io.OutputStream;

import java.sql.Date;

import java.text.SimpleDateFormat;

import javax.faces.context.FacesContext;

public class MyBillingListManagedBean {
    private Date fromDate;
    private Date toDate;

    public String getFilename() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
        return "MyInvoiceReport-" + fmt.format(dt) + ".csv";
    }

    public Date getFromDate() {
        return fromDate;
    }
    
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void exportCSV(FacesContext facesContext,
                          OutputStream outputStream) {
        String [] attributes = {"InvoiceDate", "InvoiceNumber", "StartDate", "EndDate", "TotalBillableAmt", "DueDate", "InvoiceStatusScreenLabel", "PaidDate"};
        CommonFunctionsBean.exportCSV(outputStream, "InvoicesListVO1Iterator", attributes);
    }
}
