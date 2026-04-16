CREATE DATABASE api_lab04;
USE api_lab04;

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    contact_name VARCHAR(100),
    address VARCHAR(255),
    city VARCHAR(100),
    postal_code VARCHAR(20),
    country VARCHAR(100)
);

CREATE TABLE employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    last_name VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    birth_date DATE,
    photo VARCHAR(255),
    notes TEXT,
    supervisor_id INT,
    CONSTRAINT fk_employee_supervisor
        FOREIGN KEY (supervisor_id) REFERENCES employees(employee_id)
        ON DELETE SET NULL
        ON UPDATE CASCADE
);

INSERT INTO customers (customer_name, contact_name, address, city, postal_code, country)
VALUES
('ABC Company', 'Nguyen Van A', '123 Le Loi', 'Ha Noi', '100000', 'Viet Nam'),
('XYZ Store', 'Tran Thi B', '456 Tran Hung Dao', 'Hai Phong', '180000', 'Viet Nam');

INSERT INTO employees (last_name, first_name, birth_date, photo, notes, supervisor_id)
VALUES
('Nguyen', 'An', '1995-05-10', 'an.jpg', 'Sales staff', NULL),
('Tran', 'Binh', '1997-08-20', 'binh.jpg', 'Support staff', 1);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_date DATETIME,

    customer_id INT NOT NULL,
    employee_id INT NOT NULL,

    CONSTRAINT fk_order_customer
        FOREIGN KEY (customer_id)
        REFERENCES customers(customer_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_order_employee
        FOREIGN KEY (employee_id)
        REFERENCES employees(employee_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO orders (order_date, customer_id, employee_id)
VALUES (NOW(), 1, 1);

SELECT * FROM orders;



