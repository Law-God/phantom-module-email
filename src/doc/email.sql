/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/9/24 12:46:01                           */
/*==============================================================*/


drop table if exists t_email;

drop table if exists t_email_file;

/*==============================================================*/
/* Table: t_email                                               */
/*==============================================================*/
create table t_email
(
   id                   bigint not null auto_increment,
   sender               varchar(20),
   receiver             varchar(20),
   title                varchar(100),
   content              text,
   status               char(1) default '0' comment '0 ： 数据新增
            1 ： 准备发送
            2 ： 发送成功
            3 ： 发送失败',
   createtime           datetime,
   edittime             timestamp,
   comment              varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Table: t_email_file                                          */
/*==============================================================*/
create table t_email_file
(
   id                   bigint not null auto_increment,
   emailId              bigint,
   fileName             varchar(100),
   fileUrl              varchar(100),
   createtime           datetime,
   edittime             timestamp,
   primary key (id)
);

alter table t_email_file add constraint FK_Reference_1 foreign key (emailId)
      references t_email (id) on delete restrict on update restrict;

