CREATE TABLE user (
    name VARCHAR(12) NOT NULL,
    password VARCHAR(255) NOT NULL,
    salt VARCHAR(255),
    PRIMARY KEY (name));