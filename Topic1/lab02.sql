CREATE DATABASE BikeStores;
USE BikeStores;

CREATE TABLE brands (
    brand_id INT AUTO_INCREMENT PRIMARY KEY,
    brand_name VARCHAR(255) NOT NULL
);

CREATE TABLE categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);

CREATE TABLE stores (
    store_id INT AUTO_INCREMENT PRIMARY KEY,
    store_name VARCHAR(255) NOT NULL,
    phone VARCHAR(25),
    email VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(10),
    zip_code VARCHAR(10)
);

CREATE TABLE customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone VARCHAR(25),
    email VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(10),
    zip_code VARCHAR(10)
);

CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    brand_id INT NOT NULL,
    category_id INT NOT NULL,
    model_year INT,
    list_price DECIMAL(10,2),

    FOREIGN KEY (brand_id) REFERENCES brands(brand_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id)
);

CREATE TABLE staffs (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(25),
    active TINYINT(1),
    store_id INT NOT NULL,
    manager_id INT,

    FOREIGN KEY (store_id) REFERENCES stores(store_id),
    FOREIGN KEY (manager_id) REFERENCES staffs(staff_id)
);

CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    order_status TINYINT NOT NULL,
    order_date DATE NOT NULL,
    required_date DATE NOT NULL,
    shipped_date DATE,
    store_id INT NOT NULL,
    staff_id INT NOT NULL,

    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (store_id) REFERENCES stores(store_id),
    FOREIGN KEY (staff_id) REFERENCES staffs(staff_id)
);

CREATE TABLE order_items (
    order_id INT NOT NULL,
    item_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    list_price DECIMAL(10,2) NOT NULL,
    discount DECIMAL(4,2) DEFAULT 0,

    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

CREATE TABLE stocks (
    store_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity INT NOT NULL,

    PRIMARY KEY (store_id, product_id),
    FOREIGN KEY (store_id) REFERENCES stores(store_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

-- Insert 

INSERT INTO brands (brand_id, brand_name) VALUES
(1,'Electra'),
(2,'Haro'),
(3,'Heller'),
(4,'Pure Cycles'),
(5,'Ritchey'),
(6,'Strider'),
(7,'Sun Bicycles'),
(8,'Surly'),
(9,'Trek');

INSERT INTO categories (category_id, category_name) VALUES
(1,'Children Bicycles'),
(2,'Comfort Bicycles'),
(3,'Cruisers Bicycles'),
(4,'Cyclocross Bicycles'),
(5,'Electric Bikes'),
(6,'Mountain Bikes'),
(7,'Road Bikes');

INSERT INTO products
(product_id, product_name, brand_id, category_id, model_year, list_price)
VALUES
(1,'Trek 820 - 2016',9,6,2016,379.99),
(2,'Ritchey Timberwolf Frameset - 2016',5,6,2016,749.99),
(3,'Surly Wednesday Frameset - 2016',8,6,2016,999.99),
(4,'Trek Fuel EX 8 29 - 2016',9,6,2016,2899.99),
(5,'Heller Shagamaw Frame - 2016',3,6,2016,1320.99),
(6,'Surly Ice Cream Truck Frameset - 2016',8,6,2016,469.99),
(7,'Trek Slash 8 27.5 - 2016',9,6,2016,3999.99),
(8,'Trek Remedy 29 Carbon Frameset - 2016',9,6,2016,1799.99),
(9,'Trek Conduit+ - 2016',9,5,2016,2999.99),
(10,'Surly Straggler - 2016',8,4,2016,1549.00);
    
INSERT INTO customers
(customer_id, first_name, last_name, phone, email, street, city, state, zip_code)
VALUES
(1,'Debra','Burks',NULL,'debra.burks@yahoo.com','9273 Thorne Ave.','Orchard Park','NY','14127'),
(2,'Kasha','Todd',NULL,'kasha.todd@yahoo.com','910 Vine Street','Campbell','CA','95008'),
(3,'Dameka','Fisher',NULL,'dameka.fisher@aol.com','7690 Honey Creek St.','Redondo Beach','CA','90278'),
(4,'Taryl','Spencer',NULL,'taryl.spencer@aol.com','988 Heal Lane','Riverside','CA','92503'),
(5,'Charlotte','Rice','(916) 381-6003','charlotte.rice@msn.com','107 River Dr.','Sacramento','CA','95820');

INSERT INTO stores
(store_id, store_name, phone, email, street, city, state, zip_code)
VALUES
(1,'Santa Cruz Bikes','(831) 476-4321','santacruz@bikes.shop','3700 Portola Drive','Santa Cruz','CA','95060'),
(2,'Baldwin Bikes','(516) 379-8888','baldwin@bikes.shop','420 Chestnut Lane','Baldwin','NY','11432'),
(3,'Rowlett Bikes','(972) 530-5555','rowlett@bikes.shop','800 Fairway Avenue','Rowlett','TX','75088');

INSERT INTO stocks (store_id, product_id, quantity) VALUES
(1,1,27),(1,2,5),(1,3,6),(1,4,23),(1,5,22),
(1,6,0),(1,7,8),(1,8,0),(1,9,11),(1,10,15);

INSERT INTO staffs
(staff_id, first_name, last_name, email, phone, active, store_id, manager_id)
VALUES
(1,'Fabiola','Jackson','fabiola.jackson@bikes.shop','(831) 555-5554',1,1,NULL),
(2,'Mireya','Copeland','mireya.copeland@bikes.shop','(831) 555-5555',1,1,1),
(3,'Genna','Serrano','genna.serrano@bikes.shop','(831) 555-5556',1,1,2),
(4,'Virgie','Wiggins','virgie.wiggins@bikes.shop','(831) 555-5557',1,1,2),
(5,'Jannette','David','jannette.david@bikes.shop','(516) 379-4444',1,2,1);

INSERT INTO orders
(order_id, customer_id, order_status, order_date, required_date, shipped_date, store_id, staff_id)
VALUES
(1,1,4,'2016-01-01','2016-01-03','2016-01-03',1,2),
(2,2,4,'2016-01-01','2016-01-04','2016-01-03',2,5),
(3,3,4,'2016-01-02','2016-01-05','2016-01-03',2,5),
(4,4,4,'2016-01-03','2016-01-04','2016-01-05',1,3),
(5,5,4,'2016-01-03','2016-01-06','2016-01-06',2,4);

INSERT INTO order_items
(order_id, item_id, product_id, quantity, list_price, discount)
VALUES
(1,1,10,1,599.99,0.20),
(1,2,8,2,1799.99,0.07),
(1,3,10,2,1549.00,0.05),
(1,4,10,2,599.99,0.05),
(1,5,4,1,2899.99,0.20),
(2,1,10,1,599.99,0.07),
(2,2,10,2,599.99,0.05),
(3,1,3,1,999.99,0.05),
(3,2,10,1,599.99,0.05),
(4,1,2,2,749.99,0.10);



-- DML


SELECT * FROM products;

SELECT first_name, last_name, email
FROM customers;

SELECT *
FROM customers
WHERE state = 'CA';

SELECT *
FROM customers
ORDER BY first_name ASC;

SELECT city, COUNT(*) AS number_of_customers
FROM customers
WHERE state = 'CA'
GROUP BY city;

SELECT city, COUNT(*) AS number_of_customers
FROM customers
WHERE state = 'CA'
GROUP BY city
HAVING COUNT(*) > 10;

SELECT product_name, model_year
FROM products
WHERE list_price BETWEEN 1000 AND 2000;

SELECT first_name, email
FROM staffs
WHERE active = 1;

SELECT product_name, brand_id
FROM products
WHERE model_year = 2016
AND list_price > 1000;

SELECT order_id, customer_id
FROM orders
WHERE shipped_date IS NOT NULL;

SELECT product_id, list_price, quantity
FROM order_items
WHERE discount > 0
AND quantity = 2;

SELECT store_id, COUNT(product_id) AS number_of_products
FROM stocks
GROUP BY store_id
HAVING COUNT(product_id) > 5
ORDER BY number_of_products;

SELECT first_name, last_name, email
FROM customers
WHERE email LIKE '%@yahoo.com'
ORDER BY first_name;

SELECT category_id, AVG(list_price) AS avg_price
FROM products
GROUP BY category_id
HAVING AVG(list_price) > 500
ORDER BY avg_price DESC;

SELECT brand_id, COUNT(product_id) AS total_products
FROM products
GROUP BY brand_id
HAVING COUNT(product_id) > 2
ORDER BY total_products DESC;


-- Joins

SELECT p.product_name, p.list_price, b.brand_name
FROM products p
JOIN brands b ON p.brand_id = b.brand_id
WHERE p.list_price > 1000;

SELECT c.*, o.*
FROM customers c
JOIN orders o 
    ON c.customer_id = o.customer_id
WHERE o.order_status = 4;

SELECT 
    s.first_name AS employee_first_name,
    s.last_name AS employee_last_name,
    m.first_name AS manager_first_name,
    m.last_name AS manager_last_name
FROM staffs s
JOIN staffs m
    ON s.manager_id = m.staff_id;
    
SELECT 
    p.product_name,
    b.brand_name
FROM products p
LEFT JOIN brands b
    ON p.brand_id = b.brand_id;
    
SELECT 
    p.product_name,
    p.model_year,
    b.brand_name
FROM products p
JOIN brands b
    ON p.brand_id = b.brand_id
WHERE p.model_year >= 2016;

SELECT 
    oi.order_id,
    p.product_name,
    oi.quantity
FROM order_items oi
JOIN products p
    ON oi.product_id = p.product_id;

SELECT 
    p.product_name,
    c.category_name
FROM products p
LEFT JOIN categories c
    ON p.category_id = c.category_id
WHERE c.category_name = 'Mountain Bikes';

SELECT 
    p.product_name,
    p.list_price,
    c.category_name,
    b.brand_name
FROM products p
JOIN brands b
    ON p.brand_id = b.brand_id
JOIN categories c
    ON p.category_id = c.category_id
WHERE p.list_price > 500
AND c.category_name = 'Electric Bikes';

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name,
    o.order_id,
    o.shipped_date
FROM customers c
LEFT JOIN orders o
    ON c.customer_id = o.customer_id
WHERE o.shipped_date IS NULL;

SELECT 
    s.store_name,
    COUNT(o.order_id) AS total_orders
FROM stores s
LEFT JOIN orders o
    ON s.store_id = o.store_id
GROUP BY s.store_id, s.store_name;

SELECT 
    o.order_id,
    o.order_date,
    s.first_name,
    s.last_name
FROM orders o
JOIN staffs s
    ON o.staff_id = s.staff_id
WHERE o.store_id = 1;

SELECT 
    c.customer_id,
    c.first_name,
    c.last_name
FROM customers c
JOIN orders o
    ON c.customer_id = o.customer_id
WHERE YEAR(o.order_date) = 2016;

SELECT 
    s.staff_id,
    s.first_name,
    s.last_name,
    o.order_id,
    o.order_date
FROM staffs s
JOIN orders o
    ON s.staff_id = o.staff_id
WHERE o.order_date >= DATE_SUB(CURDATE(), INTERVAL 12 MONTH);














