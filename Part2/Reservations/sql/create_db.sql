/**
 * Author:  Juneau
 * Created: June 1, 2021
 */

 create table reservation (
   id int not null
     constraint reservation_pk primary key,
   first_name varchar(50),
   last_name varchar(50),
   reservation_date date,
   reservation_type varchar(25)
 );

create table reservation_type (
id int not null
  constraint rt_pk primary key,
reservation_type varchar(25));

insert into reservation_type values (
1,
'HOTEL');

insert into reservation_type values (
2,
'RESTAURANT');

-- The following SQL is for Part 2 of the article series.

create table reservation_business (
  id int not null
  constraint reservation_business_pk primary key,
  business_name varchar(150),
  business_type varchar(25),
  street_address varchar(100),
  city varchar(100),
  state varchar(2),
  zip varchar(15),
  phone varchar(25));

alter table reservation
add reservation_business int;