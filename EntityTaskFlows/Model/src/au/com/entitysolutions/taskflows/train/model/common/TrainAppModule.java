package au.com.entitysolutions.taskflows.train.model.common;

import au.com.entitysolutions.taskflows.system.model.common.PortalCommonAppModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 01 11:22:25 EST 2011
// ---------------------------------------------------------------------
public interface TrainAppModule extends PortalCommonAppModule {
    void startTaskflow(String userTFId, String userName, String ipAddress);
}
