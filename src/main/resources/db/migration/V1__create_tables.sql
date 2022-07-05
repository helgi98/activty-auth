CREATE TABLE permission
(
    id   serial primary key,
    name varchar not null unique
);

CREATE TABLE app_user
(
    id       serial primary key,
    username varchar not null unique,
    password varchar not null
);

CREATE TABLE user_permission
(
    user_id       int not null references app_user,
    permission_id int not null references permission
);

CREATE TABLE refresh_token
(
    id            serial primary key,
    username      varchar not null,
    refresh_token varchar not null,
    revoked       bool    not null,
    date_created  timestamptz,
    CONSTRAINT fk_rt_username
        FOREIGN KEY (username)
            REFERENCES app_user (username)
);