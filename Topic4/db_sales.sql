CREATE DATABASE sales;
USE sales;

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    contact_name VARCHAR(255),
    address VARCHAR(255),
    city VARCHAR(255),
    post_code VARCHAR(10),
    country VARCHAR(50)
);

