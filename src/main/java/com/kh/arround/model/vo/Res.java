package com.kh.arround.model.vo;

public class Res {
	private int res_no;
	private int category_no;
	private String res_name;
	private String res_img;
	private String res_long;
	private String res_lat;
	private String res_con;
	private String res_addr;
	private String res_tel;
	private int area_code;
	
	private double res_distance;

	public Res() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Res(int res_no, int category_no, String res_name, String res_img, String res_long, String res_lat,
			String res_con, String res_addr, String res_tel, int area_code, double res_distance) {
		super();
		this.res_no = res_no;
		this.category_no = category_no;
		this.res_name = res_name;
		this.res_img = res_img;
		this.res_long = res_long;
		this.res_lat = res_lat;
		this.res_con = res_con;
		this.res_addr = res_addr;
		this.res_tel = res_tel;
		this.area_code = area_code;
		this.res_distance = res_distance;
	}

	@Override
	public String toString() {
		return "Res [res_no=" + res_no + ", category_no=" + category_no + ", res_name=" + res_name + ", res_img="
				+ res_img + ", res_long=" + res_long + ", res_lat=" + res_lat + ", res_con=" + res_con + ", res_addr="
				+ res_addr + ", res_tel=" + res_tel + ", area_code=" + area_code + ", res_distance=" + res_distance
				+ "]";
	}

	public int getRes_no() {
		return res_no;
	}

	public void setRes_no(int res_no) {
		this.res_no = res_no;
	}

	public int getCategory_no() {
		return category_no;
	}

	public void setCategory_no(int category_no) {
		this.category_no = category_no;
	}

	public String getRes_name() {
		return res_name;
	}

	public void setRes_name(String res_name) {
		this.res_name = res_name;
	}

	public String getRes_img() {
		return res_img;
	}

	public void setRes_img(String res_img) {
		this.res_img = res_img;
	}

	public String getRes_long() {
		return res_long;
	}

	public void setRes_long(String res_long) {
		this.res_long = res_long;
	}

	public String getRes_lat() {
		return res_lat;
	}

	public void setRes_lat(String res_lat) {
		this.res_lat = res_lat;
	}

	public String getRes_con() {
		return res_con;
	}

	public void setRes_con(String res_con) {
		this.res_con = res_con;
	}

	public String getRes_addr() {
		return res_addr;
	}

	public void setRes_addr(String res_addr) {
		this.res_addr = res_addr;
	}

	public String getRes_tel() {
		return res_tel;
	}

	public void setRes_tel(String res_tel) {
		this.res_tel = res_tel;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public double getRes_distance() {
		return res_distance;
	}

	public void setRes_distance(double res_distance) {
		this.res_distance = res_distance;
	}
	
}
