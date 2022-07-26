package com.kh.common.parse;

public class LocType {

	private int locNo;
	private String locName;

	public LocType() {
		super();
	}

	public LocType(int locNo, String locName) {
		super();
		this.locNo = locNo;
		this.locName = locName;
	}

	@Override
	public String toString() {
		return "LocType [locNo=" + locNo + ", locName=" + locName + "]";
	}

	public int getLocNo() {
		return locNo;
	}

	public void setLocNo(int locNo) {
		this.locNo = locNo;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

}
