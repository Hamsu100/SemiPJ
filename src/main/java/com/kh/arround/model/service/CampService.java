package com.kh.arround.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.arround.model.dao.CampDAO;
import com.kh.arround.model.vo.Camp;
import com.kh.common.util.PageInfo;


public class CampService {
	
	private Connection conn = getConnection();
	private CampDAO dao = new CampDAO();
	
	
	// 1. 전체 항목 거리순 top3 리스트 - camp
	// 2a. 특정 항목 거리순 전체 리스트 - 리스트 수
	// 2b. 특정 항목 거리순 전체 리스트 - 리스트 값
	// 3a. 특정 항목 특정 상세 정보 - 기본정보
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	
	
	// 1. 전체 항목 거리순 top3 리스트 - camp
	public List<Camp> selectTopByDistance(String bch_lat, String bch_lng, String radius) {
		return dao.selectTopByDistance(conn, bch_lat, bch_lng, radius);
	}
	
	// 2a. 특정 항목 거리순 전체 리스트 - 리스트 수
	public int selectAllCntByDistance(String bch_lat, String bch_lng, String radius) {
		return dao.selectAllCntByDistance(conn, bch_lat, bch_lng, radius);
	}
	
	// 2b. 특정 항목 거리순 전체 리스트 - 리스트 값
	public List<Camp> selectAllByDistance(String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
		return dao.selectAllByDistance(conn, bch_lat, bch_lng, radius, pageInfo);
	}
	
	// 3a. 특정 항목 특정 상세 정보 - 기본정보
	public Camp selectOneWithBasic(int camp_no) {
		return dao.selectOneWithBasic(conn, camp_no);
	}
	
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	public Camp selectOneWithDistance(String bch_lat, String bch_lng, String radius, int camp_no) {
		return dao.selectOneWithDistance(conn, bch_lat, bch_lng, radius, camp_no);
	}
	
	
	public int insert(Camp camp) {
		int result = dao.insert(conn, camp);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
