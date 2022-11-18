Create database shopping;
use shopping;
create table user(
	user_id int NOT NULL, 
    name varchar(30), 
    gender varchar(10),
    age int, 
    Primary key(user_id));
create table product(
	product_id int NOT NULL, 
    name varchar(30), 
    price float,
    product_type varchar(30),
    created datetime ,
    primary key (product_id));
create table order_detail(
	order_detail_id int NOT NUll, 
	user_id int, 
	ordered_at datetime,
	primary key(order_detail_id), foreign key(user_id) references user(user_id));
 
 create table order_items(
	order_items_id int NOT NULL,
    order_detail_id int,
    product_id int,
    quantity int,
    ordered_at datetime,
    primary key(order_items_id), foreign key(order_detail_id) references order_detail(order_detail_id), 
    foreign key(product_id) references product(product_id));
    
alter table order_items add column ordered_at datetime;
describe user;
describe product;
describe order_detail;
describe order_items;
show tables;
insert into user values(1,'siddiq', 'male',23 );
insert into user values(2,'nassar', 'male',25 );
insert into user values(3,'rayapati', 'male',31 );
insert into user values(4,'shahina', 'female', 47 );
select * from user;
insert into product values(1,'apple', 5, 'grade-1', '2022-07-18 19:24:00');
insert into product values(2,'banana', 2.5, 'grade-2', '2022-07-18');
insert into product values(3,'pineapple', 20, 'grade-1', '2022-05-06 18:00:24');
select * from product;
insert into order_detail values(1, 1,'2022-07-18 19:24:00');
insert into order_detail values(2,2,'2022-05-16 18:00:24');
insert into order_detail values(3, 3,'2022-07-01 19:24:00');
insert into order_detail values(4, 1,'2022-07-01 19:24:00');
insert into order_detail values(5,4,'2022-05-16 12:00:24');


insert into order_items values(1,1,1,10,'2022-07-18 19:24:00');
insert into order_items values(2,1,2,4,'2022-07-18 19:24:00');
insert into order_items values(3,1,3,1,'2022-07-18 19:24:00');

insert into order_items values(4,2,1,2,'2022-05-16 18:00:24');
insert into order_items values(5,2,2,5,'2022-05-16 18:00:24');

insert into order_items values(6,3,1,1,'2022-07-01 19:24:00');
insert into order_items values(7,3,3,2,'2022-07-01 19:24:00');

insert into order_items values(10,4,1,3,'2022-07-01 19:24:00');
insert into order_items values(11,4,2,1,'2022-07-01 19:24:00');
insert into order_items values(12,4,3,3,'2022-07-01 19:24:00');

select * from order_detail;
select * from order_items;
select * from product;
#update order_items set ordered_at = '2022-07-01 19:24:00' where order_items_id =9;
# Start writing the queries to get the required data
#inner Join query
select * from order_items inner join order_detail on order_items.order_detail_id = order_detail.order_detail_id;
#get the order products for the user with id=1 which are ordered after 2nd july 2022.
select * from user where user_id = 2 ;
select * from order_detail where user_id = 2;
select * from order_items where order_detail_id = 2 and ordered_at <= '2022-07-02';
#get product id and its details from table
select * from product where product_id in (select product_id from order_items where order_detail_id = 2 and ordered_at <= '2022-07-02');
#get the order quantity from order_items
select quantity from order_items where order_detail_id = 2 and ordered_at <= '2022-07-02';
select distinct(order_detail_id) from order_items where order_detail_id in (select order_detail_id from order_detail where user_id=1 and ordered_at <= '2022-07-02');

#Display the order quantity from order_items where user_id=2 
select p.name,p.price,oi.quantity from product p, order_items oi where oi.product_id=p.product_id and oi.order_detail_id=2;

select p.name,p.price,oi.quantity from product p, order_items oi where oi.product_id=p.product_id and oi.order_detail_id=(select distinct(order_detail_id) from order_items where order_detail_id in 
(select order_detail_id from order_detail where user_id=2 and ordered_at <= '2022-07-02')); 


