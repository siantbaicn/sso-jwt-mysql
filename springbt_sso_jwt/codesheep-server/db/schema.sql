drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);
drop table if exists oauth_client_details;
CREATE TABLE oauth_client_details (
  client_id varchar(255) NOT NULL,
  resource_ids varchar(255) DEFAULT NULL,
  client_secret varchar(255) DEFAULT NULL,
  scope varchar(255) DEFAULT NULL,
  authorized_grant_types varchar(255) DEFAULT NULL,
  web_server_redirect_uri varchar(255) DEFAULT NULL,
  authorities varchar(255) DEFAULT NULL,
  access_token_validity integer(11) DEFAULT NULL,
  refresh_token_validity integer(11) DEFAULT NULL,
  additional_information varchar(255) DEFAULT NULL,
  autoapprove varchar(255) DEFAULT NULL
);
drop table if exists oauth_access_token;
create table `oauth_access_token` (
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONGBLOB,
  refresh_token VARCHAR(255)
);
drop table if exists oauth_refresh_token;
create table `oauth_refresh_token`(
  token_id VARCHAR(255),
  token LONGBLOB,
  authentication LONGBLOB
);
drop table if exists authority;
CREATE TABLE authority (
  id  integer,
  authority varchar(255),
  primary key (id)
);
drop table if exists credentials;

CREATE TABLE credentials (
  id  integer,
  enabled boolean not null,
  name varchar(255) not null,
  password varchar(255) not null,
  version integer,
  primary key (id)
);
drop table if exists credentials_authorities;
CREATE TABLE credentials_authorities (
  credentials_id bigint not null,
  authorities_id bigint not null
);
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication VARBINARY(255)
);
drop table if exists oauth_approvals;
create table oauth_approvals (
    userId VARCHAR(255),
    clientId VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expiresAt DATETIME,
    lastModifiedAt DATETIME
);


drop table if exists resources;

CREATE TABLE resources (
  id  varchar(64) not null,
  enabled boolean not null,
  name varchar(64) not null,
  app_id varchar(64) not null,
  app_name varchar(64),
  resource_desc varchar(128),
  parent_id varchar(64),
  url varchar(128),
  primary key (id,app_id)
);

drop table if exists authorities_resources;
CREATE TABLE authorities_resources (
  authorities_id varchar(64) not null,
  resources_id varchar(64) not null
);

DROP TABLE IF EXISTS `USER_INFO`;
CREATE TABLE `USER_INFO` (
  `user_id` varchar(49) NOT NULL COMMENT '用户id',
  `login_user` varchar(32) NOT NULL COMMENT '账号',
  `login_pwd` varchar(99) NOT NULL COMMENT '??????',
  `staffname` varchar(32) DEFAULT NULL COMMENT '姓名',
  `flag` varchar(2) DEFAULT NULL COMMENT '有效标志：默认为1；0--无效；1--有效',
  `validlength` varchar(19) DEFAULT NULL COMMENT '有效期',
  `expiredate` varchar(19) DEFAULT NULL COMMENT '失效时间',
  `loglock` varchar(2) NOT NULL COMMENT '锁定状态（0禁用，1启用，9删除）',
  `departmentcode` varchar(32) DEFAULT NULL COMMENT '所属部门（未启用）',
  `workcode` varchar(32) DEFAULT NULL COMMENT '所属工作组（未启用）',
  `note` varchar(128) DEFAULT NULL COMMENT '备注',
  `user_group` varchar(32) DEFAULT NULL COMMENT '修改时间',
  `user_role` varchar(32) DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
