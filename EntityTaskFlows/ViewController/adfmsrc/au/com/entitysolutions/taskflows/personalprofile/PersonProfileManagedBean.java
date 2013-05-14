package au.com.entitysolutions.taskflows.personalprofile;

import au.com.entitysolutions.corporate.taskflows.common.CommonFunctionsBean;
import au.com.entitysolutions.taskflows.common.view.FacesUtil;

import au.com.entitysolutions.taskflows.common.view.JSFUtils;

import java.util.Date;

import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import java.util.Iterator;

import java.util.ListIterator;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

public class PersonProfileManagedBean{
    private int response;
    private String landline;
    private String emergencyLandline;
    private String initLandlines;
    private boolean validPhoneNumbers;
    private HashMap person;
    
    
    public String getInitLandlines(){
        if(null == initLandlines){
            landline = (String)FacesUtil.resolveExpression("#{bindings.LandlineAreaCode.inputValue}#{bindings.LandlineNumber.inputValue}");
            emergencyLandline = (String)FacesUtil.resolveExpression("#{bindings.EmergencyAreaCode.inputValue}#{bindings.EmergencyNumber.inputValue}");
        }
        return initLandlines;
    }
    
    public Date getMaxDate(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -16);

        return new java.util.Date(cal.getTime().getTime());
    }
    
    public void setResponse(int response) {
        this.response = response;
    }

    public int getResponse() {
        return response;
    }
    
    public void setLandline(String landline) {
        this.landline = landline;
    }

    public String getLandline() {
        return landline;
    }

    public void setEmergencyLandline(String emergencyLandline) {
        this.emergencyLandline = emergencyLandline;
    }

    public String getEmergencyLandline() {
        return emergencyLandline;
    }
    
    public void setValidPhoneNumbers(boolean validPhoneNumbers) {
        this.validPhoneNumbers = validPhoneNumbers;
    }

    public boolean isValidPhoneNumbers() {
        return validPhoneNumbers;
    }

    public void setPerson(HashMap person) {
        this.person = person;
    }

    public HashMap getPerson() {
        return person;
    }
    
    public void validatePhoneNumbers(){
        String mobile = (String)FacesUtil.resolveExpression("#{bindings.MobileNumber.inputValue}");
        if((null == landline || landline.trim().equals("")) && (null == mobile || mobile.trim().equals(""))){
            setValidPhoneNumbers(false);
        }else{
            setValidPhoneNumbers(true);
        }
    }

    public void saveActionListener(ActionEvent actionEvent) {
        validatePhoneNumbers();
        if(isValidPhoneNumbers()){
            if(null != landline && !landline.trim().equals("")){
                JSFUtils.setExpressionValue("#{bindings.LandlineAreaCode.inputValue}", landline.substring(0, 2));
                JSFUtils.setExpressionValue("#{bindings.LandlineNumber.inputValue}", landline.substring(2, landline.length()));
            }else{
                JSFUtils.setExpressionValue("#{bindings.LandlineAreaCode.inputValue}", "");
                JSFUtils.setExpressionValue("#{bindings.LandlineNumber.inputValue}", "");
            }
            
            if(null != emergencyLandline && !emergencyLandline.trim().equals("")){
                JSFUtils.setExpressionValue("#{bindings.EmergencyAreaCode.inputValue}", emergencyLandline.substring(0, 2));
                JSFUtils.setExpressionValue("#{bindings.EmergencyNumber.inputValue}", emergencyLandline.substring(2, emergencyLandline.length()));
            }else{
                JSFUtils.setExpressionValue("#{bindings.EmergencyAreaCode.inputValue}", "");
                JSFUtils.setExpressionValue("#{bindings.EmergencyNumber.inputValue}", "");
            }
                    
            HashMap personBinding = (HashMap)FacesUtil.resolveExpression("#{bindings.resultIterator.currentRow.dataProvider}");                
            BindingContext bCtx = BindingContext.getCurrent();
            DCBindingContainer DcCon = (DCBindingContainer)bCtx.getCurrentBindingsEntry();
            
            //cleaning up the hashmaps returned from the bindings
            iterateHashMap(personBinding);
            setPerson(personBinding);
             
            /*OperationBinding oper = DcCon.getOperationBinding("updateProcess");
            oper.getParamsMap().put("inputId", FacesUtil.resolveExpression("#{pageFlowScope.user_name}"));
            oper.getParamsMap().put("inputObject", getPerson());
            oper.execute();
            Iterator result = (Iterator)oper.getResult();
            System.out.println("@@@Result string: " + result.toString() + "\n@@@Has Next: " + result.hasNext());*/
            
            //System.out.println("@@@Status: " + getStatus() + "\n@@@Message: " + getMessage());
        }/*else{
            new CommonFunctionsBean().showErrorMessage((String)FacesUtil.resolveExpression("#{pageFlowScope.msgs.bundle['CONTACT_DETAILS_REQUIRED_MSG']}"));
        }*/
    }
    
    private void iterateHashMap(HashMap map){
        Iterator iter = map.keySet().iterator();
        
        while(iter.hasNext()) {
            String key = (String)iter.next();
            Object val = map.get(key);
            
            //System.out.println("#### Key: " + key + " Class: " + val.getClass() + " ####");
            
            try{
                AbstractCollection collection = (AbstractCollection)val;
                HashMap tmp = iterateCollection(collection);
                if(!tmp.isEmpty()){
                    map.put(key, tmp);
                    iterateHashMap(tmp);
                }
            }catch(Exception e){
                //System.out.println("#### Exception: " + e.toString() + " ####");
            }            
        }
    }
    
    private HashMap iterateCollection(AbstractCollection col){
        Iterator iter = col.iterator();
        
        if(iter.hasNext()){
            return (HashMap)iter.next();
        }
        return new HashMap();
    }
}
