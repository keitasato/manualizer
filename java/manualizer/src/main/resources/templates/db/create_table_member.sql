create table member(
	MAIL varchar(255) not null,
	PASSWORD varchar(255) not null,
	REG_DATE date not null,
	UPD_DATE date not null,
	primary key (MAIL)
);