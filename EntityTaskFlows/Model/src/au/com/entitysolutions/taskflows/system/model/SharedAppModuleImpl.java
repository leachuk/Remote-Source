package au.com.entitysolutions.taskflows.system.model;

import au.com.entitysolutions.taskflows.system.model.common.SharedAppModule;

import oracle.jbo.Row;
import oracle.jbo.Variable;
import oracle.jbo.server.ApplicationModuleImpl;
import oracle.jbo.server.ViewObjectImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 04 10:48:32 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SharedAppModuleImpl extends ApplicationModuleImpl implements SharedAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public SharedAppModuleImpl() {
    }

    /**
     * Container's getter for SysCommonLookupsView.
     * @return SysCommonLookupsView
     */
    public ViewObjectImpl getSysCommonLookupsView() {
        return (ViewObjectImpl)findViewObject("SysCommonLookupsView");
    }

    /**
     * Container's getter for SysGlobalVariablesView.
     * @return SysGlobalVariablesView
     */
    public ViewObjectImpl getSysGlobalVariablesView() {
        return (ViewObjectImpl)findViewObject("SysGlobalVariablesView");
    }
    
    /**
     * Get Global Variables
     * @param globalVariable
     * @return
     */
    public String getGlobalVariable(String globalVariable) {
        //TODO Use a cache? Or maybe in FacesUtil?
        String value = null;
        ViewObjectImpl sysGlobal = getSysGlobalVariablesView();        
        sysGlobal.ensureVariableManager().setVariableValue("p_global_name", globalVariable);
        sysGlobal.executeQuery();
        Row[] globalRows = sysGlobal.getAllRowsInRange();
        if(globalRows != null && globalRows.length > 0) {
            value = (String)globalRows[0].getAttribute("GlobalValue");
        }        
        return value;    
    }

    /**
     * Container's getter for SysYesNoLookup.
     * @return SysYesNoLookup
     */
    public ViewObjectImpl getSysYesNoLookup() {
        return (ViewObjectImpl)findViewObject("SysYesNoLookup");
    }
}
