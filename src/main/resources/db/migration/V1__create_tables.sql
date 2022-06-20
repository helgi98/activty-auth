CREATE TABLE Permission
(
    id   serial primary key,
    name varchar not null unique
);

CREATE TABLE User
(
    id       serial primary key,
    username varchar not null unique,
    password varchar not null
);

CREATE TABLE UserPermission
(
    user_id       int not null references User,
    permission_id int not null references Permission
);

CREATE TABLE RefreshToken
(
    id            serial primary key,
    username      varchar not null,
    refresh_token varchar not null,
    date_created  timestamptz,
    CONSTRAINT fk_rt_username
        FOREIGN KEY (username)
            REFERENCES User (username)
);