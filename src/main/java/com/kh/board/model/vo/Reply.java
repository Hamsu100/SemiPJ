package com.kh.board.model.vo;

import java.util.Date;

public class Reply {
	private int board_reply_no;
	private String board_reply_content;
	private Date board_reply_crt_date;
	private Date board_reply_mdf_date;
	private String board_reply_status;
	private int user_no;
	private int board_no;
	private String board_reply_writer;

	public Reply() {
		super();
	}

	public Reply(int board_reply_no, String board_reply_content, Date board_reply_crt_date, Date board_reply_mdf_date,
			String board_reply_status, int user_no, int board_no, String board_reply_writer) {
		super();
		this.board_reply_no = board_reply_no;
		this.board_reply_content = board_reply_content;
		this.board_reply_crt_date = board_reply_crt_date;
		this.board_reply_mdf_date = board_reply_mdf_date;
		this.board_reply_status = board_reply_status;
		this.user_no = user_no;
		this.board_no = board_no;
		this.board_reply_writer = board_reply_writer;
	}

	@Override
	public String toString() {
		return "Reply [board_reply_no=" + board_reply_no + ", board_reply_content=" + board_reply_content
				+ ", board_reply_crt_date=" + board_reply_crt_date + ", board_reply_mdf_date=" + board_reply_mdf_date
				+ ", board_reply_status=" + board_reply_status + ", user_no=" + user_no + ", board_no=" + board_no
				+ ", board_reply_writer=" + board_reply_writer + "]";
	}

	public int getBoard_reply_no() {
		return board_reply_no;
	}

	public void setBoard_reply_no(int board_reply_no) {
		this.board_reply_no = board_reply_no;
	}

	public String getBoard_reply_content() {
		return board_reply_content;
	}

	public void setBoard_reply_content(String board_reply_content) {
		this.board_reply_content = board_reply_content;
	}

	public Date getBoard_reply_crt_date() {
		return board_reply_crt_date;
	}

	public void setBoard_reply_crt_date(Date board_reply_crt_date) {
		this.board_reply_crt_date = board_reply_crt_date;
	}

	public Date getBoard_reply_mdf_date() {
		return board_reply_mdf_date;
	}

	public void setBoard_reply_mdf_date(Date board_reply_mdf_date) {
		this.board_reply_mdf_date = board_reply_mdf_date;
	}

	public String getBoard_reply_status() {
		return board_reply_status;
	}

	public void setBoard_reply_status(String board_reply_status) {
		this.board_reply_status = board_reply_status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_reply_writer() {
		return board_reply_writer;
	}

	public void setBoard_reply_writer(String board_reply_writer) {
		this.board_reply_writer = board_reply_writer;
	}

}
