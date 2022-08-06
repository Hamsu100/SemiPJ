package com.kh.arround.model.vo;

public class Cafe {
	private int cafe_no;
	private int category_no;
	private String cafe_name;
	private String cafe_img;
	private String cafe_long;
	private String cafe_lat;
	private String cafe_con;
	private String cafe_addr;
	private String cafe_tel;
	private int area_code;
	
	private double cafe_distance;

	public Cafe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cafe(int cafe_no, int category_no, String cafe_name, String cafe_img, String cafe_long, String cafe_lat,
			String cafe_con, String cafe_addr, String cafe_tel, int area_code, double cafe_distance) {
		super();
		this.cafe_no = cafe_no;
		this.category_no = category_no;
		this.cafe_name = cafe_name;
		this.cafe_img = cafe_img;
		this.cafe_long = cafe_long;
		this.cafe_lat = cafe_lat;
		this.cafe_con = cafe_con;
		this.cafe_addr = cafe_addr;
		this.cafe_tel = cafe_tel;
		this.area_code = area_code;
		this.cafe_distance = cafe_distance;
	}

	@Override
	public String toString() {
		return "Cafe [cafe_no=" + cafe_no + ", category_no=" + category_no + ", cafe_name=" + cafe_name + ", cafe_img="
				+ cafe_img + ", cafe_long=" + cafe_long + ", cafe_lat=" + cafe_lat + ", cafe_con=" + cafe_con
				+ ", cafe_addr=" + cafe_addr + ", cafe_tel=" + cafe_tel + ", area_code=" + area_code
				+ ", cafe_distance=" + cafe_distance + "]";
	}

	public int getCafe_no() {
		return cafe_no;
	}

	public void setCafe_no(int cafe_no) {
		this.cafe_no = cafe_no;
	}

	public int getCategory_no() {
		return category_no;
	}

	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}

	public String getCafe_name() {
		return cafe_name;
	}

	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}

	public String getCafe_img() {
		return cafe_img;
	}

	public void setCafe_img(String cafe_img) {
		this.cafe_img = cafe_img;
	}

	public String getCafe_long() {
		return cafe_long;
	}

	public void setCafe_long(String cafe_long) {
		this.cafe_long = cafe_long;
	}

	public String getCafe_lat() {
		return cafe_lat;
	}

	public void setCafe_lat(String cafe_lat) {
		this.cafe_lat = cafe_lat;
	}

	public String getCafe_con() {
		return cafe_con;
	}

	public void setCafe_con(String cafe_con) {
		this.cafe_con = cafe_con;
	}

	public String getCafe_addr() {
		return cafe_addr;
	}

	public void setCafe_addr(String cafe_addr) {
		this.cafe_addr = cafe_addr;
	}

	public String getCafe_tel() {
		return cafe_tel;
	}

	public void setCafe_tel(String cafe_tel) {
		this.cafe_tel = cafe_tel;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public double getCafe_distance() {
		return cafe_distance;
	}

	public void setCafe_distance(double cafe_distance) {
		this.cafe_distance = cafe_distance;
	}
	
}
