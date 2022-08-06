package com.kh.arround.model.vo;

public class Park {

	private long park_no;
	private int fcategory_no;
	private String park_name;
	private String park_new_addr;
	private String park_addr;
	private String park_wday_settime;
	private String park_sat_settime;
	private String park_hol_settime;
	private String park_latitude;
	private String park_longitude;
	private int area_code;
	
	private double park_distance;

	
	public Park() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Park(long park_no, int fcategory_no, String park_name, String park_new_addr, String park_addr,
			String park_wday_settime, String park_sat_settime, String park_hol_settime, String park_latitude,
			String park_longitude, int area_code, double park_distance) {
		super();
		this.park_no = park_no;
		this.fcategory_no = fcategory_no;
		this.park_name = park_name;
		this.park_new_addr = park_new_addr;
		this.park_addr = park_addr;
		this.park_wday_settime = park_wday_settime;
		this.park_sat_settime = park_sat_settime;
		this.park_hol_settime = park_hol_settime;
		this.park_latitude = park_latitude;
		this.park_longitude = park_longitude;
		this.area_code = area_code;
		this.park_distance = park_distance;
	}

	public long getPark_no() {
		return park_no;
	}

	public void setPark_no(long park_no) {
		this.park_no = park_no;
	}

	public int getFcategory_no() {
		return fcategory_no;
	}

	public void setFcategory_no(int fcategory_no) {
		this.fcategory_no = fcategory_no;
	}

	public String getPark_name() {
		return park_name;
	}

	public void setPark_name(String park_name) {
		this.park_name = park_name;
	}

	public String getPark_new_addr() {
		return park_new_addr;
	}

	public void setPark_new_addr(String park_new_addr) {
		this.park_new_addr = park_new_addr;
	}

	public String getPark_addr() {
		return park_addr;
	}

	public void setPark_addr(String park_addr) {
		this.park_addr = park_addr;
	}

	public String getPark_wday_settime() {
		return park_wday_settime;
	}

	public void setPark_wday_settime(String park_wday_settime) {
		this.park_wday_settime = park_wday_settime;
	}

	public String getPark_sat_settime() {
		return park_sat_settime;
	}

	public void setPark_sat_settime(String park_sat_settime) {
		this.park_sat_settime = park_sat_settime;
	}

	public String getPark_hol_settime() {
		return park_hol_settime;
	}

	public void setPark_hol_settime(String park_hol_settime) {
		this.park_hol_settime = park_hol_settime;
	}

	public String getPark_latitude() {
		return park_latitude;
	}

	public void setPark_latitude(String park_latitude) {
		this.park_latitude = park_latitude;
	}

	public String getPark_longitude() {
		return park_longitude;
	}

	public void setPark_longitude(String park_longitude) {
		this.park_longitude = park_longitude;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public double getPark_distance() {
		return park_distance;
	}

	public void setPark_distance(double park_distance) {
		this.park_distance = park_distance;
	}

	@Override
	public String toString() {
		return "Park [park_no=" + park_no + ", fcategory_no=" + fcategory_no + ", park_name=" + park_name
				+ ", park_new_addr=" + park_new_addr + ", park_addr=" + park_addr + ", park_wday_settime="
				+ park_wday_settime + ", park_sat_settime=" + park_sat_settime + ", park_hol_settime="
				+ park_hol_settime + ", park_latitude=" + park_latitude + ", park_longitude=" + park_longitude
				+ ", area_code=" + area_code + ", park_distance=" + park_distance + "]";
	}
	
}
