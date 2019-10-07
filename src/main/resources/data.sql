insert into customer values (1000,0,'admin@admin.com','admin',null,'Qw123456','+380687838321','USER');

insert into question values (100,'Раз'||chr(10)||'Два'||chr(10)||'Три',true,'What is your choice?',true,1,1000);

insert into question values (101,null,true,'What is your favourite language?',true,0,1000);

insert into answer values (100,'Два',100);

insert into answer values (101,'java',101);

insert into answer values (102,'Три',100);

insert into answer values (103,'С#',101);

insert into customer values (1001,0,'1@1.com','admin',null,'Qw123456','+380687838321','USER');

insert into question values (102,null,true,'What is your name?',true,0,1001);

insert into answer values (104,'secret',102);
