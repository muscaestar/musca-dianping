-- auto-generated definition
create table table_name
(
    id         int auto_increment
        primary key,
    created_at datetime     default CURRENT_TIMESTAMP not null,
    updated_at datetime     default CURRENT_TIMESTAMP not null,
    telephone  varchar(40)  default ''                not null,
    password   varchar(200) default ''                not null,
    nick_name  varchar(40)  default ''                not null,
    gender     int          default 0                 not null,
    constraint table_name_telephone_uindex
        unique (telephone)
);

-- auto-generated definition
create table seller
(
    id            int auto_increment
        primary key,
    name          varchar(80)   default ''                not null,
    created_at    datetime      default CURRENT_TIMESTAMP not null,
    updated_at    datetime      default CURRENT_TIMESTAMP not null,
    remark_score  decimal(2, 1) default 0.0               not null,
    disabled_flag int           default 0                 not null
);
