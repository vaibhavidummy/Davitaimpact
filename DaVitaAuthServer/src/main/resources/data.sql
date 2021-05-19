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
INSERT INTO `inbox_schema`.`oauth_client_details` (`client_id`,`access_token_validity`,`additional_information`,`authorities`,`authorized_grant_types`,`autoapprove`,`client_secret`,`refresh_token_validity`,`resource_ids`,`scope`,`web_server_redirect_uri`) VALUES ('kidclient',2900,NULL,'ROLE_CLIENT','password,refresh_token',NULL,'{bcrypt}$2a$10$cByTPhGb1C6rGTpxUJCLAOsIuH9f.3LfyBIHnNCFo2f1pACfE07xq',2592000,'ehealth','read,write,trust',NULL);
