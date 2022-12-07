CREATE TABLE IF NOT EXISTS CLIENTS
(
    `id`         int AUTO_INCREMENT NOT NULL,
    `first_name` varchar(100)       NOT NULL,
    `last_name`  varchar(100)       NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS ITEMS
(
    `id`       int AUTO_INCREMENT NOT NULL,
    `title`    varchar(100)       NOT NULL,
    `produced` date,
    `price`    float,
    PRIMARY KEY (`id`)
);

-------------
--create sequence SEQ$AGRO_MOB_SESSION$REC_ID  start with 1  increment by 1 maxvalue 9999999999999  cache 20;

--         constraint AGRO_EMP_LOGIN_UNQ1 unique
create table IF NOT EXISTS AGRO_MOB_EMP_LOGIN (    AEMP_ID    int AUTO_INCREMENT  not null,     LOGIN      VARCHAR2(254)        not null,     LOG_USER   VARCHAR2(20),     LOG_DATE   DATE,     REGION_IDS VARCHAR2(255),     HOLDING_ID NUMBER(10)           not null,     USER_TYPE  NUMBER(10) default 1 not null,
                                                   PRIMARY KEY (AEMP_ID));
--        constraint AGRO_MOB_SESSION_PK primary key,

create table IF NOT EXISTS AGRO_MOB_SESSION (    REC_ID           int AUTO_INCREMENT          not null,     SESSION_ID       VARCHAR2(128)        not null,     USER_ID          NUMBER(10)           not null,     TICKET           VARCHAR2(40)         not null,     DEVICE_ID        NUMBER(15)           not null,     DATE_END         DATE,     LAST_ACTION_DATE DATE,     HOLDING_ID       NUMBER(10)           not null ,
                                                 PRIMARY KEY (REC_ID));

create table IF NOT EXISTS AGRO_MOB_USER
(
    id                 int AUTO_INCREMENT NOT NULL,
    login              VARCHAR2(30) not null,
    password           VARCHAR2(30) not null,
    status             NUMBER(1) default 0 not null,
    bad_login_attempts NUMBER(1) default 0 not null,
    holding_id         NUMBER(10) not null,
    PRIMARY KEY (id)
);
