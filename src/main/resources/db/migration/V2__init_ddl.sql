create table users
(
    id                             bigserial    not null primary key,
    created_at                     timestamp    not null,
    first_name                     varchar(128) not null,
    last_name                      varchar(128) not null,
    email                          varchar(128) not null,
    hash_password                  varchar(255) not null,
    language_code                  varchar(5)   not null,
    country                        varchar(255) not null,
    role                           varchar(128) not null,
    permissions                    varchar(255) not null,
    session_id                     varchar(255) not null
);

create unique index email_index on users (email);

create table payment
(
    id                             bigserial    not null primary key,
    r_user_id                      bigserial    not null,
    payment_date                   timestamp    not null,
    transaction_type               varchar(255) not null,
    price                          decimal(10,2) not null
);

