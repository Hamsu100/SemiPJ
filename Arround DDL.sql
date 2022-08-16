-- radian

--CREATE OR REPLACE FUNCTION RADIANS(nDegrees IN NUMBER) RETURN NUMBER DETERMINISTIC IS
--BEGIN
--RETURN nDegrees / 57.29577951308232087679815481410517033235;
--END RADIANS;

-- 1. cafe
create table cafe (
	cafe_no				number			primary key,
	facil_category_no	number,
	cafe_name			varchar2(200),
	cafe_img			varchar2(1000),
	cafe_longitude		varchar2(40),
	cafe_latitude		varchar2(40),
	cafe_content		clob,
	cafe_address		varchar2(200),
	area_code			number,
	cafe_tel varchar2(200)
);
drop table cafe CASCADE CONSTRAINTS;
-- 2. stay
drop table stay cascade constraints;
create table stay (
	stay_no				number			primary key,
	facil_category_no	number,
	stay_name			varchar2(200),
	stay_img			varchar2(1000),
	stay_longitude		varchar2(40),
	stay_latitude		varchar2(40),
	stay_content		clob,
	stay_address		varchar2(200),
	area_code			number,
	stay_tel varchar2(200)
);

-- 3. camp
drop table camp cascade constraints;
create table camp (
	camp_no				number			primary key,
	facil_category_no	number,
	camp_name			varchar2(200),
	camp_img			varchar2(1000),
	camp_longitude		varchar2(100),
	camp_latitude		varchar2(100),
	camp_content		clob,
	camp_address		varchar2(200),
	camp_tel			varchar2(100),
	area_code			number
);

-- 4. res
rollback;
create table res (
	res_no				number			primary key,
	facil_category_no	number,
	res_name			varchar2(200),
	res_img			    varchar2(1000),
	res_longitude		varchar2(40),
	res_latitude		varchar2(40),
	res_content		    clob,
	res_address		    varchar2(200),
	area_code			number,
	res_tel varchar2(200)
);

--5. lei
drop table leisure CASCADE CONSTRAINTS;
create table leisure (
	leisure_no			number			primary key,
	facil_category_no	number,
	leisure_name		varchar2(200),
	leisure_img			varchar2(1000),
	leisure_longitude	varchar2(40),
	leisure_latitude	varchar2(40),
	leisure_content		clob,
	leisure_address		varchar2(200),
	area_code			number,
	lei_tel varchar2(200)
);

-- 6. park

create table park (
	park_no				number			primary key,
	facil_category_no	number,
	park_longitude		varchar2(40),
	park_latitude		varchar2(40),
	park_wday_settime	varchar2(200),
    park_sat_settime    varchar2(200),
    park_hol_settime    varchar2(200),
	park_name			varchar2(200),
	park_addr   		varchar2(400),
    park_new_addr       varchar2(400),
	area_code			number
);
--drop table park cascade constraints;
create sequence seq_park_no;
drop sequence seq_park_no;



select * from res;
select * from cafe;
select * from leisure;


drop table res cascade constraints;
drop table cafe cascade constraints;
drop table leisure cascade constraints;
drop table park cascade constraints;
drop table camp cascade constraints;
drop table stay cascade constraints;

select count(*) from park;
select count(*) from stay;
select count(*) from camp;
select count(*) from res;
--delete from camp;
--delete from stay;

commit;