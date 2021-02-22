create database truYum;
use truYum;

/*1. View Menu Item List Admin (TYUC001)*/

create table menu_item(
id int primary key,
Name varchar(100),
Price varchar(50),
active varchar(3),
date_of_launch date,
category varchar(50),
free_delivery varchar(3)
);

insert into menu_item values
	 (1,'Sandwich',99,'Yes','2017-03-15','Main Course','Yes'),
	 (2,'Burger',129,'Yes','2017-12-23','Main Course','No'),
	 (3,'Pizza',149,'Yes','2017-08-21','Main Course','NO'),
	 (4,'French Fries',57,'No','2017-07-02','Starters','Yes'),
	 (5,'Chocolate Brownie',32,'Yes','2022-11-02','Dessert','Yes');
     
select * from menu_item;

/*2. View Menu Item List Customer (TYUC002)*/

select * from menu_item
where date_of_launch >= '2017-08-21' and active = 'yes';

/*3. Edit Menu Item (TYUC003)*/

select *from menu_item
where id = 4;

update menu_item set name='Dosa',price=130,active='yes', date_of_launch = '2018-07-18',category = 'Main Course',
	    free_delivery = 'No' where id=3;

/*4. Add to Cart (TYUC004)*/

create table user(
	user_id int not null primary key,
	name varchar (20),
	email varchar(20),
	phone_no varchar(10),
	address varchar (20),
	password varchar(20) not null,
	role varchar(20) not null);
	    
insert into user values
                   (1,'John','john@gmail.com','9874546410','Chennai','john123','customer'),
                   (2,'Rocky','rocky@gmail.com','9946563339','Coimbatore','rocky987','admin'),
                   (3,'JD','jd@gmail.com','9123456780','Madurai','jd456','customer');
 select * from user  ;
 
 create table cart(
 cart_id int primary Key,
 user_id int ,
 item_id int,
 Name varchar(30),
 Free_delivery varchar(30),
 price int,
 category varchar(30),
  constraint fk1 foreign key (user_id) references user (user_id),
  constraint fk2 foreign key (item_id) references menu_item(id) );
  
  alter table cart drop column cart_id;
  insert into cart(user_id,item_id,Name,Free_delivery,price,category)
  values	(1,1,"Sandwich","Yes",99,"Main Course"),
			(1,2,"Burger","No",129,"Main Course"),
			(1,3,"Pizza","No",149,"Main Course");
            
select * from cart;

/*5. View Cart (TYUC005)*/

select * from cart
 where user_id=1;
 
 select sum(price) as Total from cart
 where user_id=1;
 
 /*6. Remove Item from Cart (TYUC006)*/
 
 delete from cart
where user_id=1 and item_id=3;