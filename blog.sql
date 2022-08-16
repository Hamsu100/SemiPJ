create table blog (
    blog_no			number			primary key,
    blog_content	clob,
    blog_subcontent	clob,
    blog_crt_date	date			default sysdate,
    blog_mdf_date	date			default sysdate,
    blog_status		varchar2(1)		default 'Y' check (blog_status in ('Y', 'N')),
    blog_originimg	varchar2(1000),
    blog_renameimg	varchar2(1000),
    user_no			number,
    area_code		number
);
drop table blog cascade constraints;

create SEQUENCE seq_blog_no;
create table blog_reply (
	blog_reply_no		number			primary key,
	blog_reply_content	varchar2(1200),
	blog_reply_crt_date	date			default sysdate,
	blog_reply_mdf_date	date			default sysdate,
	blog_no				number,
	user_no				number,
	blog_reply_status	varchar2(1)		default 'Y' check (blog_reply_status in ('Y', 'N'))
);
drop table blog_reply cascade constraints;
create SEQUENCE seq_blog_reply_no;

drop sequence seq_blog_reply_no;
drop sequence seq_blog_no;