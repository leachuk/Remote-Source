package au.com.entitysolutions.taskflows.system.model.client;

import au.com.entitysolutions.taskflows.system.model.common.SharedAppModule;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 04 11:01:34 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class SharedAppModuleClient extends ApplicationModuleImpl implements SharedAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public SharedAppModuleClient() {
    }

    public String getGlobalVariable(String globalVariable) {
        Object _ret =
            this.riInvokeExportedMethod(this,"getGlobalVariable",new String [] {"java.lang.String"},new Object[] {globalVariable});
        return (String)_ret;
    }
}
