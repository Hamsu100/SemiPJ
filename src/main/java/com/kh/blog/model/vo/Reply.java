package com.kh.blog.model.vo;

import java.util.Date;

public class Reply {
	
	private int blog_reply_no;
	private String blog_reply_content;
	private Date blog_reply_crt_date;
	private Date blog_reply_mdf_date;
	private int blog_no;
	private int user_no;
	private String blog_reply_status;
	
	private String user_id;

	
	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reply(int blog_reply_no, String blog_reply_content, Date blog_reply_crt_date, Date blog_reply_mdf_date,
			int blog_no, int user_no, String blog_reply_status, String user_id) {
		super();
		this.blog_reply_no = blog_reply_no;
		this.blog_reply_content = blog_reply_content;
		this.blog_reply_crt_date = blog_reply_crt_date;
		this.blog_reply_mdf_date = blog_reply_mdf_date;
		this.blog_no = blog_no;
		this.user_no = user_no;
		this.blog_reply_status = blog_reply_status;
		this.user_id = user_id;
	}


	public int getBlog_reply_no() {
		return blog_reply_no;
	}


	public void setBlog_reply_no(int blog_reply_no) {
		this.blog_reply_no = blog_reply_no;
	}


	public String getBlog_reply_content() {
		return blog_reply_content;
	}


	public void setBlog_reply_content(String blog_reply_content) {
		this.blog_reply_content = blog_reply_content;
	}


	public Date getBlog_reply_crt_date() {
		return blog_reply_crt_date;
	}


	public void setBlog_reply_crt_date(Date blog_reply_crt_date) {
		this.blog_reply_crt_date = blog_reply_crt_date;
	}


	public Date getBlog_reply_mdf_date() {
		return blog_reply_mdf_date;
	}


	public void setBlog_reply_mdf_date(Date blog_reply_mdf_date) {
		this.blog_reply_mdf_date = blog_reply_mdf_date;
	}


	public int getBlog_no() {
		return blog_no;
	}


	public void setBlog_no(int blog_no) {
		this.blog_no = blog_no;
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public String getBlog_reply_status() {
		return blog_reply_status;
	}


	public void setBlog_reply_status(String blog_reply_status) {
		this.blog_reply_status = blog_reply_status;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	@Override
	public String toString() {
		return "Reply [blog_reply_no=" + blog_reply_no + ", blog_reply_content=" + blog_reply_content
				+ ", blog_reply_crt_date=" + blog_reply_crt_date + ", blog_reply_mdf_date=" + blog_reply_mdf_date
				+ ", blog_no=" + blog_no + ", user_no=" + user_no + ", blog_reply_status=" + blog_reply_status
				+ ", user_id=" + user_id + "]";
	}

}
