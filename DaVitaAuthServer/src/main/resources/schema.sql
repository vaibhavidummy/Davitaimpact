ALTER TABLE `inbox_schema`.`oauth_access_token` 
CHANGE COLUMN `authentication` `authentication` LONGBLOB NULL DEFAULT NULL ,
CHANGE COLUMN `token` `token` LONGBLOB NULL DEFAULT NULL ;
 
ALTER TABLE `inbox_schema`.`oauth_client_token`
CHANGE COLUMN `token` `token` LONGBLOB NULL DEFAULT NULL ;
 
ALTER TABLE `inbox_schema`.`oauth_code`
CHANGE COLUMN `authentication` `authentication` LONGBLOB NULL DEFAULT NULL;
 
ALTER TABLE `inbox_schema`.`oauth_refresh_token`
CHANGE COLUMN `authentication` `authentication` LONGBLOB NULL DEFAULT NULL ,
CHANGE COLUMN `token` `token` LONGBLOB NULL DEFAULT NULL ;
 