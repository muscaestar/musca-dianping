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