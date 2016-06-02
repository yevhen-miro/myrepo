/*insert into country_table(id,country) values (1,'Germany');
insert into country_table(id,country) values (2,'Italy');
insert into country_table(id,country) values (3,'France');

insert into customer_table (pk,dob,fn,ln,country) values(1,sysdate,'Yevhen','Miroshnychenko',1);
insert into customer_table (pk,dob,fn,ln,country) values(2,sysdate,'David','Sinaga',1);
insert into customer_table (pk,dob,fn,ln,country) values(4,sysdate,'Stefan','Willems',3);
insert into customer_table (pk,dob,fn,ln,country) values(3,sysdate,'Jens','Reibiger',2);
insert into customer_table (pk,dob,fn,ln,country) values(4,sysdate,'Stefan','Willems',3);

insert into order_table (id,date,value,country,fk_cust) values(1,sysdate,300,1,1);
insert into order_table (id,date,value,country,fk_cust) values(2,sysdate,400,1,2);
insert into order_table (id,date,value,country,fk_cust) values(3,sysdate,500,2,1);
insert into order_table (id,date,value,country,fk_cust) values(4,sysdate,600,2,2);
*/
alter sequence hibernate_sequence restart with 200;
alter sequence test restart with 200;
alter sequence test1 restart with 200;
