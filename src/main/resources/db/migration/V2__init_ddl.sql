create table users
(
    id                             bigserial    not null primary key,
    first_name                     varchar(128) not null,
    last_name                      varchar(128) not null,
    email                          varchar(128) not null,
    hash_password                  varchar(255) not null,
    language_code                  varchar(5)   not null,
    role                           varchar(128) not null,
    permissions                    varchar(255) not null,
    session_id                     varchar(255) not null
);

create unique index email_index on users (email);
