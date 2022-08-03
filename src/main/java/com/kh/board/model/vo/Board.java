package com.kh.board.model.vo;

import java.util.Date;
import java.util.List;

public class Board {

	private int board_no;
	private String board_title;
	private int board_category;
	private String board_content;
	private Date board_crt_date;
	private Date board_mdf_date;
	private String board_status;
	private int user_no;
	private String board_originimg;
	private String board_renameimg;
	private String board_writer;
	private List<Reply> board_reply;

	public Board() {
		super();
	}



	@Override
	public String toString() {
		return "Board [board_no=" + board_no + ", board_title=" + board_title + ", board_category=" + board_category
				+ ", board_content=" + board_content + ", board_crt_date=" + board_crt_date + ", board_mdf_date="
				+ board_mdf_date + ", board_status=" + board_status + ", user_no=" + user_no + ", board_originimg="
				+ board_originimg + ", board_renameimg=" + board_renameimg + ", board_writer=" + board_writer
				+ ", board_reply=" + board_reply + "]";
	}



	public Board(int board_no, String board_title, int board_category, String board_content, Date board_crt_date,
			Date board_mdf_date, String board_status, int user_no, String board_originimg, String board_renameimg,
			String board_writer, List<Reply> board_reply) {
		super();
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_category = board_category;
		this.board_content = board_content;
		this.board_crt_date = board_crt_date;
		this.board_mdf_date = board_mdf_date;
		this.board_status = board_status;
		this.user_no = user_no;
		this.board_originimg = board_originimg;
		this.board_renameimg = board_renameimg;
		this.board_writer = board_writer;
		this.board_reply = board_reply;
	}



	public int getBoard_category() {
		return board_category;
	}

	public void setBoard_category(int board_category) {
		this.board_category = board_category;
	}

	public List<Reply> getBoard_reply() {
		return board_reply;
	}

	public void setBoard_reply(List<Reply> board_reply) {
		this.board_reply = board_reply;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public Date getBoard_crt_date() {
		return board_crt_date;
	}

	public void setBoard_crt_date(Date board_crt_date) {
		this.board_crt_date = board_crt_date;
	}

	public Date getBoard_mdf_date() {
		return board_mdf_date;
	}

	public void setBoard_mdf_date(Date board_mdf_date) {
		this.board_mdf_date = board_mdf_date;
	}

	public String getBoard_status() {
		return board_status;
	}

	public void setBoard_status(String board_status) {
		this.board_status = board_status;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getBoard_originimg() {
		return board_originimg;
	}

	public void setBoard_originimg(String board_originimg) {
		this.board_originimg = board_originimg;
	}

	public String getBoard_renameimg() {
		return board_renameimg;
	}

	public void setBoard_renameimg(String board_renameimg) {
		this.board_renameimg = board_renameimg;
	}

}
