CREATE DATABASE sms;
USE sms;

CREATE TABLE Customer (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL
);

CREATE TABLE Employee (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(255) NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    supervisor_id INT,
    FOREIGN KEY (supervisor_id) REFERENCES Employee(employee_id)
);

CREATE TABLE Product (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    list_price DECIMAL(10,2) NOT NULL
);

CREATE TABLE Orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    order_date DATETIME NOT NULL,
    customer_id INT NOT NULL,
    employee_id INT NOT NULL,
    total DECIMAL(10,2),
    FOREIGN KEY (customer_id) REFERENCES Customer(customer_id),
    FOREIGN KEY (employee_id) REFERENCES Employee(employee_id)
);

CREATE TABLE LineItem (
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10,2),
    PRIMARY KEY (order_id, product_id),
    FOREIGN KEY (order_id) REFERENCES Orders(order_id),
    FOREIGN KEY (product_id) REFERENCES Product(product_id)
);

INSERT INTO Customer (customer_name)
VALUES 
('Alice Johnson'),
('Bob Smith'),
('Charlie Brown');

INSERT INTO Employee (employee_name, salary, supervisor_id)
VALUES
('David Miller', 5000, NULL),
('Emma Wilson', 4500, 1),
('Frank Taylor', 4000, 1);

INSERT INTO Product (product_name, list_price)
VALUES
('Laptop', 1200),
('Smartphone', 800),
('Tablet', 500);

INSERT INTO Orders (order_date, customer_id, employee_id, total)
VALUES
('2025-03-01 10:00:00', 1, 1, 2000),
('2025-03-02 11:30:00', 2, 2, 800),
('2025-03-03 14:15:00', 3, 3, 500);

INSERT INTO LineItem (order_id, product_id, quantity, price)
VALUES
(1, 1, 1, 1200),
(1, 2, 1, 800),
(2, 2, 1, 800);

--
DELIMITER //
CREATE PROCEDURE GetCustomersWithOrders()
BEGIN
    SELECT DISTINCT 
        c.customer_id,
        c.customer_name
    FROM Customer c
    JOIN Orders o
        ON c.customer_id = o.customer_id;
END //
DELIMITER ;
CALL GetCustomersWithOrders();

SHOW PROCEDURE STATUS WHERE Db = 'sms';

--
DELIMITER //
CREATE PROCEDURE GetOrdersByCustomer(
    IN p_customer_id INT
)
BEGIN
    SELECT 
        order_id,
        order_date,
        customer_id,
        employee_id,
        total
    FROM Orders
    WHERE customer_id = p_customer_id;
END //
DELIMITER ;
CALL GetOrdersByCustomer(1);

--
DELIMITER //
CREATE PROCEDURE GetLineItemsByOrder(
    IN p_order_id INT
)
BEGIN
    SELECT 
        order_id,
        product_id,
        quantity,
        price
    FROM LineItem
    WHERE order_id = p_order_id;
END //
DELIMITER ;
CALL GetLineItemsByOrder(1);

--
DELIMITER //
CREATE FUNCTION CalculateOrderTotal(p_order_id INT)
RETURNS DECIMAL(10,2)
DETERMINISTIC
BEGIN
    DECLARE total_amount DECIMAL(10,2);

    SELECT SUM(quantity * price)
    INTO total_amount
    FROM LineItem
    WHERE order_id = p_order_id;

    RETURN total_amount;
END //
DELIMITER ;

SELECT CalculateOrderTotal(1);
SELECT 
    order_id,
    CalculateOrderTotal(order_id) AS order_total
FROM Orders;


--
DELIMITER //
CREATE PROCEDURE AddCustomer(
    IN p_customer_name VARCHAR(255)
)
BEGIN
    INSERT INTO Customer(customer_name)
    VALUES (p_customer_name);
END //
DELIMITER ;

CALL AddCustomer('John Lee');


--
DELIMITER //

CREATE PROCEDURE CreateOrder(
    IN p_order_date DATETIME,
    IN p_customer_id INT,
    IN p_employee_id INT,
    IN p_total DECIMAL(10,2)
)
BEGIN
    INSERT INTO Orders(order_date, customer_id, employee_id, total)
    VALUES (p_order_date, p_customer_id, p_employee_id, p_total);
END //

DELIMITER ;
CALL CreateOrder('2026-03-06 10:00:00', 1, 1, 1500);

--
DELIMITER //

CREATE PROCEDURE CreateLineItem(
    IN p_order_id INT,
    IN p_product_id INT,
    IN p_quantity INT,
    IN p_price DECIMAL(10,2)
)
BEGIN
    INSERT INTO LineItem(order_id, product_id, quantity, price)
    VALUES (p_order_id, p_product_id, p_quantity, p_price);
END //

DELIMITER ;
CALL CreateLineItem(1, 1, 2, 500);

--
DELIMITER //

CREATE PROCEDURE UpdateOrderTotal(IN p_order_id INT)
BEGIN
    UPDATE Orders
    SET total = (
        SELECT SUM(quantity * price)
        FROM LineItem
        WHERE order_id = p_order_id
    )
    WHERE order_id = p_order_id;
END //

DELIMITER ;
CALL UpdateOrderTotal(1);






