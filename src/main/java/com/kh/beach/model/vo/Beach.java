package com.kh.beach.model.vo;

import java.util.List;

public class Beach {
	private String BEACH_CODE;
	private String BEACH_NAME;
	private String BEACH_IMG;
	private String BEACH_CONTENT;
	private String BEACH_LONGITUDE;
	private String BEACH_LATITUDE;
	private String BEACH_ADDRESS;
	private String BEACH_LEN;
	private String BEACH_WID;
	private String BEACH_PROP;
	private String BEACH_FAVOR_CNT;
	private String AREA_CODE;
	private String area_name;
	private List<BchReply> bch_replyList;

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	@Override
	public String toString() {
		return "Beach [BEACH_CODE=" + BEACH_CODE + ", BEACH_NAME=" + BEACH_NAME + ", BEACH_IMG=" + BEACH_IMG
				+ ", BEACH_CONTENT=" + BEACH_CONTENT + ", BEACH_LONGITUDE=" + BEACH_LONGITUDE + ", BEACH_LATITUDE="
				+ BEACH_LATITUDE + ", BEACH_ADDRESS=" + BEACH_ADDRESS + ", BEACH_LEN=" + BEACH_LEN + ", BEACH_WID="
				+ BEACH_WID + ", BEACH_PROP=" + BEACH_PROP + ", BEACH_FAVOR_CNT=" + BEACH_FAVOR_CNT + ", AREA_CODE="
				+ AREA_CODE + ", bch_replyList=" + bch_replyList + "]";
	}

	public List<BchReply> getBch_replyList() {
		return bch_replyList;
	}

	public void setBch_replyList(List<BchReply> bch_replyList) {
		this.bch_replyList = bch_replyList;
	}

	public Beach(String bEACH_CODE, String bEACH_NAME, String bEACH_IMG, String bEACH_CONTENT, String bEACH_LONGITUDE,
			String bEACH_LATITUDE, String bEACH_ADDRESS, String bEACH_LEN, String bEACH_WID, String bEACH_PROP,
			String bEACH_FAVOR_CNT, String aREA_CODE, List<BchReply> bch_replyList) {
		super();
		BEACH_CODE = bEACH_CODE;
		BEACH_NAME = bEACH_NAME;
		BEACH_IMG = bEACH_IMG;
		BEACH_CONTENT = bEACH_CONTENT;
		BEACH_LONGITUDE = bEACH_LONGITUDE;
		BEACH_LATITUDE = bEACH_LATITUDE;
		BEACH_ADDRESS = bEACH_ADDRESS;
		BEACH_LEN = bEACH_LEN;
		BEACH_WID = bEACH_WID;
		BEACH_PROP = bEACH_PROP;
		BEACH_FAVOR_CNT = bEACH_FAVOR_CNT;
		AREA_CODE = aREA_CODE;
		this.bch_replyList = bch_replyList;
	}

	public Beach() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getBEACH_CODE() {
		return BEACH_CODE;
	}

	public void setBEACH_CODE(String bEACH_CODE) {
		BEACH_CODE = bEACH_CODE;
	}

	public String getBEACH_NAME() {
		return BEACH_NAME;
	}

	public void setBEACH_NAME(String bEACH_NAME) {
		BEACH_NAME = bEACH_NAME;
	}

	public String getBEACH_IMG() {
		return BEACH_IMG;
	}

	public void setBEACH_IMG(String bEACH_IMG) {
		BEACH_IMG = bEACH_IMG;
	}

	public String getBEACH_CONTENT() {
		return BEACH_CONTENT;
	}

	public void setBEACH_CONTENT(String bEACH_CONTENT) {
		BEACH_CONTENT = bEACH_CONTENT;
	}

	public String getBEACH_LONGITUDE() {
		return BEACH_LONGITUDE;
	}

	public void setBEACH_LONGITUDE(String bEACH_LONGITUDE) {
		BEACH_LONGITUDE = bEACH_LONGITUDE;
	}

	public String getBEACH_LATITUDE() {
		return BEACH_LATITUDE;
	}

	public void setBEACH_LATITUDE(String bEACH_LATITUDE) {
		BEACH_LATITUDE = bEACH_LATITUDE;
	}

	public String getBEACH_ADDRESS() {
		return BEACH_ADDRESS;
	}

	public void setBEACH_ADDRESS(String bEACH_ADDRESS) {
		BEACH_ADDRESS = bEACH_ADDRESS;
	}

	public String getBEACH_LEN() {
		return BEACH_LEN;
	}

	public void setBEACH_LEN(String bEACH_LEN) {
		BEACH_LEN = bEACH_LEN;
	}

	public String getBEACH_WID() {
		return BEACH_WID;
	}

	public void setBEACH_WID(String bEACH_WID) {
		BEACH_WID = bEACH_WID;
	}

	public String getBEACH_PROP() {
		return BEACH_PROP;
	}

	public void setBEACH_PROP(String bEACH_PROP) {
		BEACH_PROP = bEACH_PROP;
	}

	public String getBEACH_FAVOR_CNT() {
		return BEACH_FAVOR_CNT;
	}

	public void setBEACH_FAVOR_CNT(String bEACH_FAVOR_CNT) {
		BEACH_FAVOR_CNT = bEACH_FAVOR_CNT;
	}

	public String getAREA_CODE() {
		return AREA_CODE;
	}

	public void setAREA_CODE(String aREA_CODE) {
		AREA_CODE = aREA_CODE;
	}

}
