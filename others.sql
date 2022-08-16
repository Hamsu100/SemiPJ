create table location (
    location_name   varchar2(100),
    location_no     number			primary key
);

create table area (
	area_code	number	primary key,
	area_name	varchar2(20),
	location_no	number,
	constraint fk_area_location_no foreign key (location_no) references location(location_no)
);

create table facil_category (
	facil_category_no	number			primary key,
	facil_category_name	varchar2(40)	not null
);