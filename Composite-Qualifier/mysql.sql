create database emp;
use emp;

SET SQL_SAFE_UPDATES = 0;
select * from employee ;
update employee set name = 'nassar' where employee_id = 10;
delete from employee where employee_id=22;
update employee set name="nassar" where employee_id =10;
select * from address;
delete from address where address_id =23;
delete from employee where employee_id=5;