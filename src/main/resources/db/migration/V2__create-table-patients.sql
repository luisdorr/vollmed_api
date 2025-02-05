﻿CREATE TABLE patients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    street VARCHAR(100) NOT NULL,
    neighbourhood VARCHAR(100) NOT NULL,
    address_code VARCHAR(9) NOT NULL,
    city VARCHAR(100) NOT NULL,
    fu CHAR(2) NOT NULL,
    number VARCHAR(20),
    complement VARCHAR(100),
    active BOOLEAN NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);