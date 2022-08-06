package com.kh.arround.model.vo;

public class Camp {
	
	private int camp_no;
	private int fcategory_no;
	private String camp_name;
	private String camp_longitude;
	private String camp_latitude;
	private String camp_img;
	private String camp_content;
	private String camp_address;
	private String camp_tel;
	private int area_code;
	
	private double camp_distance;

	
	public Camp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Camp(int camp_no, int fcategory_no, String camp_name, String camp_longitude, String camp_latitude,
			String camp_img, String camp_content, String camp_address, String camp_tel, int area_code,
			double camp_distance) {
		super();
		this.camp_no = camp_no;
		this.fcategory_no = fcategory_no;
		this.camp_name = camp_name;
		this.camp_longitude = camp_longitude;
		this.camp_latitude = camp_latitude;
		this.camp_img = camp_img;
		this.camp_content = camp_content;
		this.camp_address = camp_address;
		this.camp_tel = camp_tel;
		this.area_code = area_code;
		this.camp_distance = camp_distance;
	}

	public int getCamp_no() {
		return camp_no;
	}

	public void setCamp_no(int camp_no) {
		this.camp_no = camp_no;
	}

	public int getFcategory_no() {
		return fcategory_no;
	}

	public void setFcategory_no(int fcategory_no) {
		this.fcategory_no = fcategory_no;
	}

	public String getCamp_name() {
		return camp_name;
	}

	public void setCamp_name(String camp_name) {
		this.camp_name = camp_name;
	}

	public String getCamp_longitude() {
		return camp_longitude;
	}

	public void setCamp_longitude(String camp_longitude) {
		this.camp_longitude = camp_longitude;
	}

	public String getCamp_latitude() {
		return camp_latitude;
	}

	public void setCamp_latitude(String camp_latitude) {
		this.camp_latitude = camp_latitude;
	}

	public String getCamp_img() {
		return camp_img;
	}

	public void setCamp_img(String camp_img) {
		this.camp_img = camp_img;
	}

	public String getCamp_content() {
		return camp_content;
	}

	public void setCamp_content(String camp_content) {
		this.camp_content = camp_content;
	}

	public String getCamp_address() {
		return camp_address;
	}

	public void setCamp_address(String camp_address) {
		this.camp_address = camp_address;
	}

	public String getCamp_tel() {
		return camp_tel;
	}

	public void setCamp_tel(String camp_tel) {
		this.camp_tel = camp_tel;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public double getCamp_distance() {
		return camp_distance;
	}

	public void setCamp_distance(double camp_distance) {
		this.camp_distance = camp_distance;
	}

	@Override
	public String toString() {
		return "Camp [camp_no=" + camp_no + ", fcategory_no=" + fcategory_no + ", camp_name=" + camp_name
				+ ", camp_longitude=" + camp_longitude + ", camp_latitude=" + camp_latitude + ", camp_img=" + camp_img
				+ ", camp_content=" + camp_content + ", camp_address=" + camp_address + ", camp_tel=" + camp_tel
				+ ", area_code=" + area_code + ", camp_distance=" + camp_distance + "]";
	}
	
	
}	