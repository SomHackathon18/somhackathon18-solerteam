/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     03/02/2018 0:46:35                           */
/*==============================================================*/


drop table if exists USER;

/*==============================================================*/
/* Table: USER                                                  */
/*==============================================================*/
create table USER
(
   NAME                 varchar(35) not null,
   SURNAME              varchar(35) not null,
   EMAIL                varchar(300) not null,
   PASSWORD             varchar(350) not null,
   GENDER               varchar(35) not null,
   BIRTHDAY             date not null,
   ADDRESS              varchar(35) not null,
   TELF                 varchar(15) not null,
   CONFIRMATION_TOKEN   varchar(150),
   RESET_TOKEN          varchar(150),
   ENABLED              bool not null,
   primary key (EMAIL)
);

