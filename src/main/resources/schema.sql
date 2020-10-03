CREATE TABLE item (
id INT AUTO_INCREMENT  PRIMARY KEY,
name VARCHAR(150) NOT NULL,
description VARCHAR(1000),
cost INT NOT NULL,
type VARCHAR(50)
);

CREATE TABLE users (
id INT AUTO_INCREMENT  PRIMARY KEY,
username varchar(50) not null,
name VARCHAR(150) NOT NULL,
surname VARCHAR(150) NOT NULL,
role VARCHAR(50) NOT NULL,
password VARCHAR(150) NOT NULL,
email VARCHAR(150) NOT NULL,
balance INT NOT NULL,
earnings INT NOT NULL,
enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);

--create unique index ix_auth_username on authorities (username,authority);

--create table users(
--    username varchar(50) not null primary key,
--    password varchar(50) not null,
--    enabled boolean not null
--);
--
--create table authorities (
--    username varchar(50) not null,
--    authority varchar(50) not null,
--    constraint fk_authorities_users foreign key(username) references users(username)
--);
--create unique index ix_auth_username on authorities (username,authority);