package com.kh.member.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_phone;
	private String user_email;
	private String user_status;
	private Date user_crt_date;
	private Date user_mdf_date;

	public User() {
		super();
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public Date getUser_crt_date() {
		return user_crt_date;
	}

	public void setUser_crt_date(Date user_crt_date) {
		this.user_crt_date = user_crt_date;
	}

	public Date getUser_mdf_date() {
		return user_mdf_date;
	}

	public void setUser_mdf_date(Date user_mdf_date) {
		this.user_mdf_date = user_mdf_date;
	}

	public User(int user_no, String user_id, String user_pw, String user_name, String user_phone, String user_email,
			String user_status, Date user_crt_date, Date user_mdf_date) {
		super();
		this.user_no = user_no;
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.user_email = user_email;
		this.user_status = user_status;
		this.user_crt_date = user_crt_date;
		this.user_mdf_date = user_mdf_date;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return "user_no : " + user_no + ", user_id : " + user_id + ", user_pw : " + user_pw + ", user_name : "
				+ user_name + ", user_phone : " + user_phone + ", user_email : " + user_email + ", user_status : "
				+ user_status + ", user_crt_date : " + sdf.format(user_crt_date) + ", user_mdf_date : "
				+ sdf.format(user_mdf_date);
	}

}
