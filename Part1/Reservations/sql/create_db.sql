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