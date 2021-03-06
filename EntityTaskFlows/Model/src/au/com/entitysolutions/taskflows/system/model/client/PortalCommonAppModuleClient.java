package au.com.entitysolutions.taskflows.system.model.client;

import au.com.entitysolutions.taskflows.system.model.common.PortalCommonAppModule;

import oracle.jbo.client.remote.ApplicationModuleImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Jul 04 15:17:19 EST 2011
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class PortalCommonAppModuleClient extends ApplicationModuleImpl implements PortalCommonAppModule {
    /**
     * This is the default constructor (do not remove).
     */
    public PortalCommonAppModuleClient() {
    }


  public void emailFileUploader(String attachmentPath, String fileDetails) {
    Object _ret =
      this.riInvokeExportedMethod(this,"emailFileUploader",new String [] {"java.lang.String","java.lang.String"},new Object[] {attachmentPath, fileDetails});
    return;
  }

  public String sendEmailWithAttachments(String to, String cc, String bcc,
                                         String subject, String body,
                                         String attachmentPath) {
    Object _ret =
      this.riInvokeExportedMethod(this,"sendEmailWithAttachments",new String [] {"java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String","java.lang.String"},new Object[] {to, cc, bcc, subject, body, attachmentPath});
    return (String)_ret;
  }
}
