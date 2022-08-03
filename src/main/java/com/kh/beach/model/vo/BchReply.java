package com.kh.beach.model.vo;

import java.util.Date;

public class BchReply {
	private String BCH_REVIEW_NO;
	private String BCH_REVIEW_CONTENT;
	private String BEACH_CODE;
	private String USER_NO;
	private Date BCH_REVIEW_CRT_DATE;
	private Date BCH_REVIEW_MDF_DATE;
	private String BCH_REVIEW_STATUS;
	private String writer;

	public BchReply() {
		super();
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	@Override
	public String toString() {
		return "BchReply [BCH_REVIEW_NO=" + BCH_REVIEW_NO + ", BCH_REVIEW_CONTENT=" + BCH_REVIEW_CONTENT
				+ ", BEACH_CODE=" + BEACH_CODE + ", USER_NO=" + USER_NO + ", BCH_REVIEW_CRT_DATE=" + BCH_REVIEW_CRT_DATE
				+ ", BCH_REVIEW_MDF_DATE=" + BCH_REVIEW_MDF_DATE + ", BCH_REVIEW_STATUS=" + BCH_REVIEW_STATUS
				+ ", writer=" + writer + "]";
	}

	public BchReply(String bCH_REVIEW_NO, String bCH_REVIEW_CONTENT, String bEACH_CODE, String uSER_NO,
			Date bCH_REVIEW_CRT_DATE, Date bCH_REVIEW_MDF_DATE, String bCH_REVIEW_STATUS, String writer) {
		super();
		BCH_REVIEW_NO = bCH_REVIEW_NO;
		BCH_REVIEW_CONTENT = bCH_REVIEW_CONTENT;
		BEACH_CODE = bEACH_CODE;
		USER_NO = uSER_NO;
		BCH_REVIEW_CRT_DATE = bCH_REVIEW_CRT_DATE;
		BCH_REVIEW_MDF_DATE = bCH_REVIEW_MDF_DATE;
		BCH_REVIEW_STATUS = bCH_REVIEW_STATUS;
		this.writer = writer;
	}

	public String getBCH_REVIEW_NO() {
		return BCH_REVIEW_NO;
	}

	public void setBCH_REVIEW_NO(String bCH_REVIEW_NO) {
		BCH_REVIEW_NO = bCH_REVIEW_NO;
	}

	public String getBCH_REVIEW_CONTENT() {
		return BCH_REVIEW_CONTENT;
	}

	public void setBCH_REVIEW_CONTENT(String bCH_REVIEW_CONTENT) {
		BCH_REVIEW_CONTENT = bCH_REVIEW_CONTENT;
	}

	public String getBEACH_CODE() {
		return BEACH_CODE;
	}

	public void setBEACH_CODE(String bEACH_CODE) {
		BEACH_CODE = bEACH_CODE;
	}

	public String getUSER_NO() {
		return USER_NO;
	}

	public void setUSER_NO(String uSER_NO) {
		USER_NO = uSER_NO;
	}

	public Date getBCH_REVIEW_CRT_DATE() {
		return BCH_REVIEW_CRT_DATE;
	}

	public void setBCH_REVIEW_CRT_DATE(Date bCH_REVIEW_CRT_DATE) {
		BCH_REVIEW_CRT_DATE = bCH_REVIEW_CRT_DATE;
	}

	public Date getBCH_REVIEW_MDF_DATE() {
		return BCH_REVIEW_MDF_DATE;
	}

	public void setBCH_REVIEW_MDF_DATE(Date bCH_REVIEW_MDF_DATE) {
		BCH_REVIEW_MDF_DATE = bCH_REVIEW_MDF_DATE;
	}

	public String getBCH_REVIEW_STATUS() {
		return BCH_REVIEW_STATUS;
	}

	public void setBCH_REVIEW_STATUS(String bCH_REVIEW_STATUS) {
		BCH_REVIEW_STATUS = bCH_REVIEW_STATUS;
	}

}
