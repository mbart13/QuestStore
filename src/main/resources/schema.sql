DROP TABLE IF EXISTS item;

CREATE TABLE item (
id INT AUTO_INCREMENT  PRIMARY KEY,
name VARCHAR(150) NOT NULL,
description VARCHAR(1000),
cost INT NOT NULL,
type VARCHAR(50)
);

DROP TABLE IF EXISTS students;

CREATE TABLE students (
id INT AUTO_INCREMENT  PRIMARY KEY,
username varchar(45) NOT NULL,
name VARCHAR(150) NOT NULL,
surname VARCHAR(150) NOT NULL,
role VARCHAR(50) NOT NULL,
password VARCHAR(150) NOT NULL,
email VARCHAR(150) NOT NULL,
balance INT NOT NULL,
earnings INT NOT NULL,
enabled TINYINT NOT NULL DEFAULT 1
);

DROP TABLE IF EXISTS user_roles;

CREATE TABLE user_roles (
  user_role_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username varchar(45) NOT NULL ,
  role varchar(45) NOT NULL,
    CONSTRAINT fk_username
          FOREIGN KEY(username)
          REFERENCES students(username)
);