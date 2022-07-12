create table content(
	ID serial ,
	TITLE varchar(60) not null,
	CONTENT text ,
	REG_DATE date not null,
	UPD_DATE date not null,
	MAIN varchar(100) not null,
	primary key (ID)
);