use spring_hibernate;
select * from CUSTOMER;
SELECT COUNT(*) FROM CUSTOMER;
create database pharmc_seller_db;
use pharmc_seller_db;
SHOW DATABASES;
SHOW TABLES;

CREATE DATABASE coffee_store;
USE coffee_store;
CREATE TABLE products (
 id INT auto_increment primary Key,
 name varchar(30),
 price DECIMAL(3,2),
 coffee_origin varchar(30)
);
CREATE TABLE customers(
 id int auto_increment primary key,
 first_name varchar(30),
 last_name varchar(30),
 gender enum('M','F'),
 phone_number varchar(11)
);

CREATE TABLE orders(
 id INT auto_increment primary key,
 product_id INT,
 customer_id INT,
 order_time DATETIME,
 FOREIGN KEY (product_id) REFERENCES products(id),
 FOREIGN KEY (customer_id) REFERENCES customers(id)
);

alter TABLE products add column coffee_origin varchar(30);
insert into products (name,price,coffee_origin) values ('Espresso',2.50,'Columbia');
insert into products (name,price,coffee_origin) values ('Espresso',2.50,'Columbia'),('machiato',3.50,'columbia'),('capucchino',4.77,'CostRica'),('capucchino',4.77,'CostRica'),('Latte',3.5,'Indonesia'),('Americano',3.25,'Ethiopia'),('Flatwhite',3.5,'Indonesia'),('Filter',3.0,'Sri Lanka');
Update products set coffee_origin = "Srilanka" where id=10;
SET SQL_SAFE_UPDATES = 0;
update products set price = 3.4, coffee_origin='India' where coffee_origin = 'Brazil';
Delete from products where coffee_origin="India";
describe products;

INSERT INTO customers (first_name, last_name, gender, phone_number) VALUES ('Chris','Martin','M','01123147789'),('Emma','Law','F','01123439899'),('Mark','Watkins','M','01174592013'),('Daniel','Williams','M',NULL),('Sarah','Taylor','F','01176348290'),('Katie','Armstrong','F','01145787353'),('Michael','Bluth','M','01980289282'),('Kat','Nash','F','01176987789'),('Buster','Bluth','M','01173456782'),('Charlie',NULL,'F','01139287883'),('Lindsay','Bluth','F','01176923804'),('Harry','Johnson','M',NULL),('John','Smith','M','01174987221'),('John','Taylor','M',NULL),('Emma','Smith','F','01176984116'),('Gob','Bluth','M','01176985498'),('George','Bluth','M','01176984303'),('Lucille','Bluth','F','01198773214'),('George','Evans','M','01174502933'),('Emily','Simmonds','F','01899284352'),('John','Smith','M','01144473330'),('Jennifer',NULL,'F',NULL),('Toby','West','M','01176009822'),('Paul','Edmonds','M','01966947113');
INSERT INTO orders (product_id,customer_id,order_time) VALUES (1,1,'2017-01-01 08-02-11'),(1,2,'2017-01-01 08-05-16'),(5,12,'2017-01-01 08-44-34'),(3,4,'2017-01-01 09-20-02'),(1,9,'2017-01-01 11-51-56'),(6,22,'2017-01-01 13-07-10'),(1,1,'2017-01-02 08-03-41'),(3,10,'2017-01-02 09-15-22'),(2,2,'2017-01-02 10-10-10'),(3,13,'2017-01-02 12-07-23'),(1,1,'2017-01-03 08-13-50'),(7,16,'2017-01-03 08-47-09'),(6,21,'2017-01-03 09-12-11'),(5,22,'2017-01-03 11-05-33'),(4,3,'2017-01-03 11-08-55'),(3,11,'2017-01-03 12-02-14'),(2,23,'2017-01-03 13-41-22'),(1,1,'2017-01-04 08-08-56'),(3,10,'2017-01-04 11-23-43'),(4,12,'2017-01-05 08-30-09'),(7,1,'2017-01-06 09-02-47'),(3,18,'2017-01-06 13-23-34'),(2,16,'2017-01-07 09-12-39'),(2,14,'2017-01-07 11-24-15'),(4,5,'2017-01-08 08-54-11'),(1,1,'2017-01-09 08-03-11'),(6,20,'2017-01-10 10-34-12'),(3,3,'2017-01-10 11-02-11'),(4,24,'2017-01-11 08-39-11'),(4,8,'2017-01-12 13-20-13'),(1,1,'2017-01-14 08-27-10'),(4,15,'2017-01-15 08-30-56'),(1,7,'2017-01-16 10-02-11'),(2,10,'2017-01-17 09-50-05'),(1,1,'2017-01-18 08-22-55'),(3,9,'2017-01-19 09-00-19'),(7,11,'2017-01-19 11-33-00'),(6,12,'2017-01-20 08-02-21'),(3,14,'2017-01-21 09-45-50'),(5,2,'2017-01-22 10-10-34'),(6,24,'2017-01-23 08-32-19'),(6,22,'2017-01-23 08-45-12'),(6,17,'2017-01-23 12-45-30'),(2,11,'2017-01-24 08-01-27'),(1,1,'2017-01-25 08-05-13'),(6,11,'2017-01-26 10-49-10'),(7,3,'2017-01-27 09-23-57'),(7,1,'2017-01-27 10-08-16'),(3,18,'2017-01-27 10-13-09'),(4,19,'2017-01-27 11-02-40'),(3,10,'2017-01-28 08-03-21'),(1,2,'2017-01-28 08-33-28'),(1,12,'2017-01-28 11-55-33'),(1,13,'2017-01-29 09-10-17'),(6,6,'2017-01-30 10-07-13'),(1,1,'2017-02-01 08-10-14'),(2,14,'2017-02-02 10-02-11'),(7,10,'2017-02-02 09-43-17'),(7,20,'2017-02-03 08-33-49'),(4,21,'2017-02-04 09-31-01'),(5,22,'2017-02-05 09-07-10'),(3,23,'2017-02-06 08-15-10'),(2,24,'2017-02-07 08-27-26'),(1,1,'2017-02-07 08-45-10'),(6,11,'2017-02-08 10-37-10'),(3,13,'2017-02-09 08-58-18'),(3,14,'2017-02-10 09-12-40'),(5,4,'2017-02-10 11-05-34'),(1,2,'2017-02-11 08-00-38'),(3,8,'2017-02-12 08-08-08'),(7,20,'2017-02-12 09-22-10'),(1,1,'2017-02-13 08-37-45'),(5,2,'2017-02-13 12-34-56'),(4,3,'2017-02-14 08-22-43'),(5,4,'2017-02-14 09-12-56'),(3,5,'2017-02-15 08-09-10'),(6,7,'2017-02-15 09-05-12'),(1,8,'2017-02-15 09-27-50'),(2,9,'2017-02-16 08-51-12'),(3,10,'2017-02-16 13-07-46'),(4,11,'2017-02-17 08-03-55'),(4,12,'2017-02-17 09-12-11'),(5,10,'2017-02-17 11-41-17'),(6,18,'2017-02-17 13-05-56'),(7,19,'2017-02-18 08-33-27'),(1,17,'2017-02-19 08-12-31'),(1,1,'2017-02-20 09-50-17'),(3,5,'2017-02-20 09-51-29'),(4,6,'2017-02-20 10-43-39'),(3,1,'2017-02-21 08-32-17'),(1,1,'2017-02-21 10-30-11'),(3,2,'2017-02-21 11-08-45'),(4,3,'2017-02-22 11-46-32'),(2,15,'2017-02-22 13-35-16'),(6,13,'2017-02-23 08-34-48'),(4,24,'2017-02-24 08-32-03'),(2,13,'2017-02-25 08-03-12'),(7,17,'2017-02-25 09-34-23'),(7,23,'2017-02-25 11-32-54'),(5,12,'2017-02-26 11-47-34'),(6,4,'2017-02-27 12-12-34'),(1,1,'2017-02-28 08-59-22');
delete from customers where id!=1111;
drop table customers, orders, products ;
show tables;

Select * from customers;
select * from customers where last_name like '%o%';
select * from products;
SELECT * FROM products Order By price DESC;
SELECT * FROM customers Order By first_name ASC;
Select * from customers limit 5 Offset 5;
select * from orders;
Select * from orders where customer_id=1 Order by order_time ASC;
Select * from orders where customer_id = 1 and order_time between '2017-02-01' and '2017-02-28' limit 3;
#inner Join
Select products.name,orders.order_time from orders inner Join products on products.id = orders.product_id;
Select p.name,o.order_time from orders o join products p on p.id = o.product_id order by o.order_time;
#Right Join
Select p.name, o.order_time from orders o RIGHT join products p on p.id = o.product_id order by o.order_time;

Select name,price from products where coffee_origin in('Columbia','Indonesia') Order By name;
select * from customers where first_name like '_o_';
select * from products where price='3.5' or coffee_origin = 'columbia';
select last_name, phone_number from customers;
Select substring(name,5) as product_short from products;
#Group By
select c.id,count(c.id),o.product_id from orders o join customers c group by c.id,o.product_id;

#My Interview
select * from customers;
select * from products;
select * from orders;
select max(price) from products where price not in (select max(price) from products);
select * from products order by price DESC limit 2,1;
select * from customers right join orders on orders.customer_id = customers.id;
#Nth hightest rate of product
Select price from products p1 where (N-1) = (Select Count(distinct(price)) from products p2 where p2.price > p1.price);
#Triggers
Create TRIGGER TRIGGER_NAME Before INSERT on TABLE_NAME FOR EACH ROW SET new.field_name = new.field_name + 100;
#views
CREATE VIEW view_name AS  SELECT column1, column2  FROM table_name  WHERE id < 3;  