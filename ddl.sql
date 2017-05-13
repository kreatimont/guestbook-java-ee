create table users
(
	id int not null auto_increment
		primary key,
	name varchar(45) not null,
	surname varchar(45) not null,
	email varchar(254) not null,
	phone_number varchar(45) null,
	city varchar(45) null,
	country varchar(45) null,
	password varchar(48) not null,
	bday date null,
	constraint email_UNIQUE
		unique (email)
)
;
