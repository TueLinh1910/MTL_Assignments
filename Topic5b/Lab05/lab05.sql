CREATE DATABASE lab05;
use lab05;

CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    roles_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, roles_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (roles_id) REFERENCES roles(id)
);

INSERT INTO roles(name) VALUES ('USER');
INSERT INTO roles(name) VALUES ('ADMIN');