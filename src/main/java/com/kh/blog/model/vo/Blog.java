package com.kh.blog.model.vo;

import java.util.Date;
import java.util.List;

public class Blog {
	
	private int blog_no;
	private String blog_content;
	private String blog_subcontent;
	private int user_no;
	private Date blog_crt_date;
	private Date blog_mdf_date;
	private String blog_status;
	private int area_code;
	private String blog_originimg;
	private String blog_renameimg;
	
	private List<Reply> blog_reply;
	
	private String user_id;
	private String area_name;
	
	
	
	public Blog() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Blog(int blog_no, String blog_content, String blog_subcontent, int user_no, Date blog_crt_date,
			Date blog_mdf_date, String blog_status, int area_code, String blog_originimg, String blog_renameimg,
			List<Reply> blog_reply, String user_id, String area_name) {
		super();
		this.blog_no = blog_no;
		this.blog_content = blog_content;
		this.blog_subcontent = blog_subcontent;
		this.user_no = user_no;
		this.blog_crt_date = blog_crt_date;
		this.blog_mdf_date = blog_mdf_date;
		this.blog_status = blog_status;
		this.area_code = area_code;
		this.blog_originimg = blog_originimg;
		this.blog_renameimg = blog_renameimg;
		this.blog_reply = blog_reply;
		this.user_id = user_id;
		this.area_name = area_name;
	}


	public int getBlog_no() {
		return blog_no;
	}


	public void setBlog_no(int blog_no) {
		this.blog_no = blog_no;
	}


	public String getBlog_content() {
		return blog_content;
	}


	public void setBlog_content(String blog_content) {
		this.blog_content = blog_content;
	}


	public String getBlog_subcontent() {
		return blog_subcontent;
	}


	public void setBlog_subcontent(String blog_subcontent) {
		this.blog_subcontent = blog_subcontent;
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public Date getBlog_crt_date() {
		return blog_crt_date;
	}


	public void setBlog_crt_date(Date blog_crt_date) {
		this.blog_crt_date = blog_crt_date;
	}


	public Date getBlog_mdf_date() {
		return blog_mdf_date;
	}


	public void setBlog_mdf_date(Date blog_mdf_date) {
		this.blog_mdf_date = blog_mdf_date;
	}


	public String getBlog_status() {
		return blog_status;
	}


	public void setBlog_status(String blog_status) {
		this.blog_status = blog_status;
	}


	public int getArea_code() {
		return area_code;
	}


	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}


	public String getBlog_originimg() {
		return blog_originimg;
	}


	public void setBlog_originimg(String blog_originimg) {
		this.blog_originimg = blog_originimg;
	}


	public String getBlog_renameimg() {
		return blog_renameimg;
	}


	public void setBlog_renameimg(String blog_renameimg) {
		this.blog_renameimg = blog_renameimg;
	}


	public List<Reply> getBlog_reply() {
		return blog_reply;
	}


	public void setBlog_reply(List<Reply> blog_reply) {
		this.blog_reply = blog_reply;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public String getArea_name() {
		return area_name;
	}


	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}


	@Override
	public String toString() {
		return "Blog [blog_no=" + blog_no + ", blog_content=" + blog_content + ", blog_subcontent=" + blog_subcontent
				+ ", user_no=" + user_no + ", blog_crt_date=" + blog_crt_date + ", blog_mdf_date=" + blog_mdf_date
				+ ", blog_status=" + blog_status + ", area_code=" + area_code + ", blog_originimg=" + blog_originimg
				+ ", blog_renameimg=" + blog_renameimg + ", blog_reply=" + blog_reply + ", user_id=" + user_id
				+ ", area_name=" + area_name + "]";
	}
	
}
