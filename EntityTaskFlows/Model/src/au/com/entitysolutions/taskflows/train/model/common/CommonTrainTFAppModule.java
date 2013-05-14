package au.com.entitysolutions.taskflows.train.model.common;

import oracle.jbo.ApplicationModule;
import oracle.jbo.domain.BlobDomain;
import oracle.jbo.domain.Number;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Mon Aug 01 15:35:42 EST 2011
// ---------------------------------------------------------------------
public interface CommonTrainTFAppModule extends ApplicationModule {


    BlobDomain downloadDoc(String userTFId, String stepName,
                           String documentType, String documentSubType);

    void completeStep(String userTFId, String stepName);

    Number uploadDoc(String userTFId, String stepName, String docName,
                     BlobDomain fileContent, String fileMime,
                     String fileLength, String fileName);

    Number removeDoc(String userTFId, String stepName, String docName);
}
