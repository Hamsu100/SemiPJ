package com.kh.arround.model.vo;

public class Stay {

	private int stay_no;
	private String name;
	private String content;
	private String longitude;
	private String latitude;
	private String addr;
	private String img;
	private String tel;
	private int area_code;
	private int facil_category_code = 2;
	
	private double distance;

	public Stay() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stay(int stay_no, String name, String content, String longitude, String latitude, String addr, String img,
			String tel, int area_code, int facil_category_code, double distance) {
		super();
		this.stay_no = stay_no;
		this.name = name;
		this.content = content;
		this.longitude = longitude;
		this.latitude = latitude;
		this.addr = addr;
		this.img = img;
		this.tel = tel;
		this.area_code = area_code;
		this.facil_category_code = facil_category_code;
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Stay [stay_no=" + stay_no + ", name=" + name + ", content=" + content + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", addr=" + addr + ", img=" + img + ", tel=" + tel + ", area_code="
				+ area_code + ", facil_category_code=" + facil_category_code + ", distance=" + distance + "]";
	}

	public int getStay_no() {
		return stay_no;
	}

	public void setStay_no(int stay_no) {
		this.stay_no = stay_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public int getFacil_category_code() {
		return facil_category_code;
	}

	public void setFacil_category_code(int facil_category_code) {
		this.facil_category_code = facil_category_code;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
}	