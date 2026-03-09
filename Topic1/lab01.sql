create database sales;
USE sales;

CREATE TABLE employees (
employee_id INT AUTO_INCREMENT PRIMARY KEY,
last_name VARCHAR(20) NOT NULL,
first_name VARCHAR(10) NOT NULL,
birth_date DATE,
supervisor_id INT
);

CREATE TABLE customers (
customer_id INT AUTO_INCREMENT PRIMARY KEY,
customer_name VARCHAR(255),
contact_name VARCHAR(255),
address VARCHAR(255),
city VARCHAR(255),
postal_code VARCHAR(10),
country VARCHAR(50)
);

CREATE TABLE orders (
order_id INT AUTO_INCREMENT PRIMARY KEY,
customer_id INT,
employee_id INT,
order_date DATETIME DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

Insert into customers (customer_name, contact_name, address, city, postal_code, country)
values ('Trần Bình', 'Trọng', 'Quận 8', 'HCM', 70000, 'VN'),
('Tran Bao', 'An', 'Bình Thạnh', 'HCM', 70000, 'VN'),
('Tasty', 'Finn', 'Streetroad 19B', 'Liverpool', 'L1 0AA', 'UK');

SELECT customer_id, customer_name, contact_name, address, city, postal_code, country
FROM customers;

SELECT *
FROM customers
WHERE country = 'VN';

SELECT country, COUNT(customer_id) NumberOfCustomers
FROM customers
GROUP BY country;

SELECT country, COUNT(customer_id) NumberOfCustomers
FROM customers
GROUP BY country
HAVING COUNT(customer_id) >= 2;

SELECT customer_id, customer_name, country
FROM customers
Order By customer_name;


select * from customers;

update orders set customer_id = 2 where order_id = 1;

select order_id, customers.customer_id, customer_name
from customers INNER JOIN orders
on customers.customer_id = orders.customer_id;

select customers.customer_id, customer_name, order_id
from customers LEFT JOIN orders
on orders.customer_id = customers.customer_id;

SELECT a.employee_id ,CONCAT(a.last_name,' ', a.first_name) as 'Employee Name'
,b.employee_id AS 'Supervisor ID'
,CONCAT(b.last_name,' ', b.first_name) AS 'Supervisor Name'
FROM employees a inner join employees b
on a.supervisor_id = b.employee_id;

SELECT A.customer_id, A.customer_name, A.country
FROM customers A inner join customers B
on A.customer_id <> B.customer_id
Where A.country = B.country;

select
order_id, customer_name,
CONCAT(first_name, ' ', last_name) EmployeeName,
order_date
from employees E INNER join orders O
on E.employee_id = O.employee_id
INNER JOIN customers C
on C.customer_id = O.customer_id













