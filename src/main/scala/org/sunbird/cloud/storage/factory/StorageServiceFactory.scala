package org.sunbird.cloud.storage.factory

import org.sunbird.cloud.storage.BaseStorageService
import org.sunbird.cloud.storage.exception.StorageServiceException
import org.sunbird.cloud.storage.service.{AzureStorageService, S3StorageService, GcloudStorageService}

case class StorageConfig(`type`: String, storageKey: String, storageSecret: String)

object StorageServiceFactory {

  /**
    * Creates StorageService based on the given config
    * @param config
    * @return
    */
    def getStorageService(config: StorageConfig): BaseStorageService = {
        config.`type`.toLowerCase() match {
            case "aws"      =>
                new S3StorageService(config);
            case "azure"   =>
                new AzureStorageService(config);
            case "gcloud"  =>
              new GcloudStorageService(config);
            case _         =>
                throw new StorageServiceException("Unknown storage type found");
        }
    }
}
