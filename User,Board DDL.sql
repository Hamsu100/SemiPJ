/* 

�ʿ��� table
1. tbl_user 2. location 3. board 4. board_reply

�ʿ��� seq
1. seq_user_no 2. seq_board_no 3. seq_board_reply_no

*/
drop table tbl_user cascade constraints;
drop table board cascade constraints;
drop table board_reply cascade constraints;
drop sequence seq_board_no;
drop sequence seq_board_reply_no;
drop sequence seq_user_no;

create table tbl_user (
    user_no         number 			primary key,
    user_id			varchar2(60)	unique,
	user_name		varchar2(100),
	user_pw			varchar2(100),
	user_phone		varchar2(22),
	user_status		varchar2(1)		default 'Y' check (user_status in('Y', 'N')),
	user_crt_date	date			default sysdate,
	user_mdf_date	date			default sysdate
);

create table board (
	board_no		number			primary key,
	board_title		varchar2(200),
    board_category  number,
	board_content	clob,
	board_crt_date	date			default sysdate,
	board_mdf_date	date			default sysdate,
	board_status	varchar2(1)		default 'Y' check (board_status in ('Y', 'N')),
	user_no			number,
	constraint fk_board_user_no foreign key(user_no) references tbl_user(user_no)
);

create table board_reply (
	board_reply_no			number			primary key,
	board_reply_content		varchar2(1200),
	board_reply_crt_date	date			default sysdate,
	board_reply_mdf_date	date			default sysdate,
	board_reply_status		varchar2(1)		default 'Y' check (board_reply_status in ('Y', 'N')),
    user_id                 varchar2(60),
	user_no					number,
	board_no				number,
	constraint fk_board_reply_user_no foreign key(user_no) references tbl_user(user_no),
    constraint fk_board_reply_user_id foreign key(user_id) references tbl_user(user_id),
	constraint fk_board_reply_board_no foreign key(board_no) references board(board_no)
);

create sequence seq_user_no;
create sequence seq_board_no;
create sequence seq_board_reply_no;
commit;
select * from tbl_user;
select * from board;
select * from tbl_user where user_no in (4);
select * from (select board_no, board_title, board_category, board_content, board_crt_date, board_mdf_date, board_status, user_no, user_id, rownum as rnum from (select b.board_no, b.board_title, b.board_category, b.board_content, b.board_crt_date, b.board_mdf_date, b.board_status, b.user_no, u.user_id from board b, tbl_user u where u.user_no = b.user_no and b.board_status = 'Y' and b.board_category in (3, 4, 5, 6) order by b.board_no desc));
select count(*) from board b, tbl_user u where u.user_no = b.user_no and b.board_status = 'Y' and b.board_category in (3, 4, 5, 6) ;


select br.board_reply_no, br.board_reply_content, br.board_reply_crt_date, br.board_reply_mdf_date, br.board_reply_status, br.user_no, br.board_no, u.user_id from board_reply br, tbl_user u where u.user_no = br.user_no and board_reply_status = 'Y' and br.board_no = 8 order by br.board_reply_no desc;

