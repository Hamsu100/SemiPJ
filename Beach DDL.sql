create table beach (
	beach_code		varchar2(12) 	primary key,
	beach_name		varchar2(60),
	beach_img		varchar2(2000),
	beach_content	clob,
	beach_longitude	varchar2(200),
	beach_latitude	varchar2(200),
	beach_address	varchar2(200), 
	beach_len		varchar2(20),
	beach_wid		varchar2(20),
	beach_prop		varchar2(40),
    beach_favor_cnt number          default 0,
	area_code		number			not null,
    constraint fk_beach_area_code foreign key (area_code) references area(area_code)
);
delete from beach;
commit;
drop table beach cascade constraints;

create table bch_favorite (
	beach_code	varchar2(12),
	user_no		number,
	primary key (beach_code, user_no),
    constraint fk_bch_favor_beach_code foreign key(beach_code) references beach(beach_code),
    constraint fk_bch_favor_user_no foreign key(user_no) references tbl_user(user_no)
);

create table bch_review (
	bch_review_no		number			primary key,
	bch_review_content	varchar2(600),
	beach_code			varchar2(12),
	user_no				number,
    user_id             varchar2(60),
	bch_review_crt_date	date			default sysdate,
	bch_review_mdf_date	date			default sysdate,
	bch_review_status	varchar2(1)		default 'Y' check (bch_review_status in ('Y', 'N')),
	constraint fk_bch_review_beach_code foreign key(beach_code) references beach(beach_code),
	constraint fk_bch_review_user_no foreign key(user_no) references tbl_user(user_no)
);

 drop sequence seq_bch_review_no;

create sequence seq_bch_review_no;
select* from beach;
select * from bch_review br, tbl_user u where u.user_no=br.user_no and BCH_REVIEW_STATUS = 'Y';
select * from beach b, area a, location l where b.area_code=a.area_code and a.location_no = l.location_no;
select b.beach_code, b.beach_name, b.beach_content, b.beach_address, l.location_name, rank() over(order by b.beach_favor_cnt) rank from beach b, area a, location l where b.area_code=a.area_code and a.location_no = l.location_no;
select * from (select b.beach_code, b.beach_name, b.beach_content, b.beach_address, l.location_name, b.beach_img, rank() over(order by b.beach_favor_cnt) rank from beach b, area a, location l where b.area_code=a.area_code and a.location_no = l.location_no and l.location_name = '서울') where rank <=10;
select b.beach_code, b.beach_name, b.beach_content, b.beach_address, b.beach_img, rownum rnum from beach b, location l, area a where b.area_code = a.area_code and a.location_no = l.location_no and b.beach_name like '%해운대%' and l.location_name = '부산';
select * from (select b.beach_code, b.beach_name, b.beach_content, b.beach_address, b.beach_img, rownum rnum from beach b, location l, area a where b.area_code = a.area_code and a.location_no = l.location_no and b.beach_name like '%해운대%' and l.location_name = '부산') where rnum between 1 and 10;
select * from bch_review br, tbl_user u where u.user_no = br.user_no and BCH_REVIEW_STATUS = 'Y' and br.BEACH_CODE = 'a';
select count(*) from (select b.beach_code, b.beach_name, b.beach_content, b.beach_address, b.beach_img, rownum rnum from beach b, location l, area a where b.area_code = a.area_code and a.location_no = l.location_no and b.beach_name like '%해운대%' and l.location_name = '부산');
select count(*) from (select b.beach_code, b.beach_name, b.beach_content, b.beach_address, b.beach_img, rownum rnum from beach b, location l, area a where b.area_code = a.area_code and a.location_no = l.location_no and b.beach_name like ? and l.location_name = ? );
select count(*) from res;
select * from (select b.beach_code, b.beach_name, b.beach_content, b.beach_address, l.location_name, b.beach_img, b.BEACH_FAVOR_CNT, rank() over(order by b.BEACH_FAVOR_CNT desc) rank from beach b, area a, location l where b.area_code = a.area_code and a.location_no = l.location_no ) where rank <=10 ;


