package com.kh.arround.model.vo;

public class Lei {
	private int leisure_no;
	private int category_no;
	private String leisure_name;
	private String leisure_img;
	private String leisure_longitude;
	private String leisure_latitude;
	private String leisure_content;
	private String leisure_address;
	private String leisure_tel;
	private int area_code;
	
	private double leisure_distance;

	public Lei() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lei(int leisure_no, int category_no, String leisure_name, String leisure_img, String leisure_longitude,
			String leisure_latitude, String leisure_content, String leisure_address, String leisure_tel, int area_code,
			double leisure_distance) {
		super();
		this.leisure_no = leisure_no;
		this.category_no = category_no;
		this.leisure_name = leisure_name;
		this.leisure_img = leisure_img;
		this.leisure_longitude = leisure_longitude;
		this.leisure_latitude = leisure_latitude;
		this.leisure_content = leisure_content;
		this.leisure_address = leisure_address;
		this.leisure_tel = leisure_tel;
		this.area_code = area_code;
		this.leisure_distance = leisure_distance;
	}

	@Override
	public String toString() {
		return "Lei [leisure_no=" + leisure_no + ", category_no=" + category_no + ", leisure_name=" + leisure_name
				+ ", leisure_img=" + leisure_img + ", leisure_longitude=" + leisure_longitude + ", leisure_latitude="
				+ leisure_latitude + ", leisure_content=" + leisure_content + ", leisure_address=" + leisure_address
				+ ", leisure_tel=" + leisure_tel + ", area_code=" + area_code + ", leisure_distance=" + leisure_distance
				+ "]";
	}

	public int getLeisure_no() {
		return leisure_no;
	}

	public void setLeisure_no(int leisure_no) {
		this.leisure_no = leisure_no;
	}

	public int getCategory_no() {
		return category_no;
	}

	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}

	public String getLeisure_name() {
		return leisure_name;
	}

	public void setLeisure_name(String leisure_name) {
		this.leisure_name = leisure_name;
	}

	public String getLeisure_img() {
		return leisure_img;
	}

	public void setLeisure_img(String leisure_img) {
		this.leisure_img = leisure_img;
	}

	public String getLeisure_longitude() {
		return leisure_longitude;
	}

	public void setLeisure_longitude(String leisure_longitude) {
		this.leisure_longitude = leisure_longitude;
	}

	public String getLeisure_latitude() {
		return leisure_latitude;
	}

	public void setLeisure_latitude(String leisure_latitude) {
		this.leisure_latitude = leisure_latitude;
	}

	public String getLeisure_content() {
		return leisure_content;
	}

	public void setLeisure_content(String leisure_content) {
		this.leisure_content = leisure_content;
	}

	public String getLeisure_address() {
		return leisure_address;
	}

	public void setLeisure_address(String leisure_address) {
		this.leisure_address = leisure_address;
	}

	public String getLeisure_tel() {
		return leisure_tel;
	}

	public void setLeisure_tel(String leisure_tel) {
		this.leisure_tel = leisure_tel;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public double getLeisure_distance() {
		return leisure_distance;
	}

	public void setLeisure_distance(double leisure_distance) {
		this.leisure_distance = leisure_distance;
	}
	
}
