package com.kh.common.parse;

public class AreaCode {
	private int area_code;
	private String area_name;
	private int locType;

	public AreaCode() {
		super();
	}

	public AreaCode(int area_code, String area_name, int locType) {
		super();
		this.area_code = area_code;
		this.area_name = area_name;
		this.locType = locType;
	}

	@Override
	public String toString() {
		return "AreaCode [area_code=" + area_code + ", area_name=" + area_name + ", locType=" + locType + "]";
	}

	public int getLocType() {
		return locType;
	}

	public void setLocType(int locType) {
		this.locType = locType;
	}

	public int getArea_code() {
		return area_code;
	}

	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

}
