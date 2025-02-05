CREATE TABLE doctors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20) NOT NULL,
    crm VARCHAR(6) NOT NULL UNIQUE,
    expertise VARCHAR(50) NOT NULL,
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