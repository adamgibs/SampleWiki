create schema y_enterprises;

use y_enterprises;

create table user (
	
    id varchar(50) not null,
    password varchar(10) not null,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    create_date date not null,
    unique key (id)

);


insert into user values("yjing", "123", "Yanjun", "Jing", '2019-04-10');
insert into user values("amulhern", "321", "Andrew", "Mulhern", '2019-04-12');
insert into user values("agibson", "111", "Adam", "Gibson", '2019-04-14');

select * from user;

create table posting (
	
    id varchar(50) not null,
    created_date datetime not null,
    created_by varchar(50) not null,
    title varchar(20) not null,
    short_description varchar(30) not null,
    long_description varchar(500),
    last_modified datetime,
    last_modified_by varchar(50),
    foreign key(created_by)
		references user(id)
        on delete cascade,
	foreign key(last_modified_by)
		references user(id)
		on delete cascade,
    unique key (id)

);



	insert into posting values(UUID(), '2019-05-01 10:01:01', "agibson",
	"Hello", "This is the short description", "This is the long description", '2019-05-01 10:30:01',"agibson") ;
    
    insert into posting values(UUID(), '2019-05-02 10:01:01',"yjing",
	"Hey", "This is the short description", "This is the long description", '2019-05-02 10:30:01',"yjing");
    
    insert into posting values (UUID(),'2019-05-03 10:01:01',"amulhern",
	"Hello", "This is the short description", "This is the long description",'2019-05-03 10:30:01',"amulhern");

select * from posting;