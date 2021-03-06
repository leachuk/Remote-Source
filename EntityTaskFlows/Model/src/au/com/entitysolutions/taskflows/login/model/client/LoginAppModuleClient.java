package au.com.entitysolutions.taskflows.login.model.client;

import au.com.entitysolutions.taskflows.login.model.common.LoginAppModule;

import au.com.entitysolutions.taskflows.system.model.client.PortalCommonAppModuleClient;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 04 13:48:42 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LoginAppModuleClient extends PortalCommonAppModuleClient implements LoginAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public LoginAppModuleClient() {
    }


  public Boolean sendUserId(String emailAddress, String ipAddress) {
    Object _ret =
      this.riInvokeExportedMethod(this,"sendUserId",new String [] {"java.lang.String","java.lang.String"},new Object[] {emailAddress, ipAddress});
    return (Boolean)_ret;
  }

  public Boolean resetPassword(String emailAddress, String ipAddress) {
    Object _ret =
      this.riInvokeExportedMethod(this,"resetPassword",new String [] {"java.lang.String","java.lang.String"},new Object[] {emailAddress, ipAddress});
    return (Boolean)_ret;
  }

  public Boolean acceptTAndC(String userName, String ipAddress) {
    Object _ret =
      this.riInvokeExportedMethod(this,"acceptTAndC",new String [] {"java.lang.String","java.lang.String"},new Object[] {userName, ipAddress});
    return (Boolean)_ret;
  }

  public Boolean changePassword(String userName, String ipAddress,
                                String oldPassword, String newPassword) {
    Object _ret =
      this.riInvokeExportedMethod(this,"changePassword",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {userName, ipAddress, oldPassword, newPassword});
    return (Boolean)_ret;
  }

  public Boolean validatePasswordRules(String userName, String oldPassword,
                                       String newPassword) {
    Object _ret =
      this.riInvokeExportedMethod(this,"validatePasswordRules",new String [] {"java.lang.String","java.lang.String","java.lang.String"},new Object[] {userName, oldPassword, newPassword});
    return (Boolean)_ret;
  }

  public Boolean updateSysPrimaryEmail(String userName, String email) {
    Object _ret =
      this.riInvokeExportedMethod(this,"updateSysPrimaryEmail",new String [] {"java.lang.String","java.lang.String"},new Object[] {userName, email});
    return (Boolean)_ret;
  }
}
