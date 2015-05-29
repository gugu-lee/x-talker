USE hss_db;
ALTER TABLE `impi` 
ADD COLUMN `email` VARCHAR(45) NULL AFTER `zh_default_auth_scheme`;
alter table application_server add column include_register_request int default 0;
alter table application_server add column include_register_response int default 0;
CREATE TABLE log(
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
host VARCHAR(128),
impi VARCHAR(256),
logdatetime DATETIME,
`action` VARCHAR(1024),
content VARCHAR(1024)
);