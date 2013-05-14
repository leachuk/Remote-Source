package au.com.entitysolutions.taskflows.model.utils;

public class ModelUtil {
    private int[] nineDigitWeights = {10,7,8,4,6,3,5,2,1};
    private int[] eightDigitWeights = {10,7,8,4,6,3,5,1};
    
    public ModelUtil() {
        super();
    }
    
    /**
     * Uses the ATO algorithm to validate the TFN number
     * @param tfnString
     * @return
     */
    public boolean validateTFN(String tfnString) {
        boolean isValid = false;
        if(tfnString == null) {
            isValid = false;
        }
        else if(tfnString.length() == 9) {
            //9digit validator
            isValid = validate9DigitTFN(tfnString);
        }
        else if(tfnString.length() == 8) {
            //9digit validator
            isValid = validate8DigitTFN(tfnString);
        }        
        return isValid;
    }
    
    /**
     * Validation for the 9 digit TFNs
     * @param tfnString
     * @return
     */
    private boolean validate9DigitTFN(String tfnString) {
        boolean isValid = false;
        int sumOfValues = 0;
        for(int i = 0; i < 9; i++) {
            sumOfValues += Character.digit( tfnString.charAt(i),10) * nineDigitWeights[i];            
        }   
        int remainder = sumOfValues % 11;
        isValid = (remainder == 0);
        return isValid;
    }
    
    /**
     * Validation for the 8 digit TFNs
     * @param tfnString
     * @return
     */
    private boolean validate8DigitTFN(String tfnString) {
        boolean isValid = false;
        int sumOfValues = 0;
        for(int i = 0; i < 8; i++) {
            sumOfValues += Character.digit( tfnString.charAt(i),10) * eightDigitWeights[i];            
        }   
        int remainder = sumOfValues % 11;
        isValid = (remainder == 0);
        return isValid;
    }    
    
    public static void main(String[] args) {
        ModelUtil model = new ModelUtil();
        System.out.println(model.validateTFN("648188480"));
        System.out.println(model.validateTFN("648188499"));
        System.out.println(model.validateTFN("648188519"));
        System.out.println(model.validateTFN("648188527"));
        System.out.println(model.validateTFN("648188535"));
        
        System.out.println(model.validateTFN("37118629"));
        System.out.println(model.validateTFN("37118660"));
        System.out.println(model.validateTFN("37118705"));
        System.out.println(model.validateTFN("38593474"));
        
        System.out.println(model.validateTFN("648188481"));
        System.out.println(model.validateTFN("648188493"));
        System.out.println(model.validateTFN("648188522"));
        System.out.println(model.validateTFN("648188529"));
        System.out.println(model.validateTFN("648188536"));
        
        System.out.println(model.validateTFN("37118630"));
        System.out.println(model.validateTFN("37118664"));
        System.out.println(model.validateTFN("37118706"));
        System.out.println(model.validateTFN("38593478"));
    }
}
