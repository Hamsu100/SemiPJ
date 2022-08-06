package com.kh.arround.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.arround.model.dao.ParkDAO;
import com.kh.arround.model.vo.Park;
import com.kh.common.util.PageInfo;


public class ParkService {
	
	private Connection conn = getConnection();
	private ParkDAO dao = new ParkDAO();
	
	
	// 1. 전체 항목 거리순 top3 리스트 
		public List<Park> selectTopByDistance(String bch_lat, String bch_lng, String radius) {
			return dao.selectTopByDistance(conn, bch_lat, bch_lng, radius);
		}
	
	// 2a. 특정 항목 거리순 전체 리스트 - 리스트 수
		public int selectAllCntByDistance(String bch_lat, String bch_lng, String radius) {
			return dao.selectAllCntByDistance(conn, bch_lat, bch_lng, radius);
		}
		
		
	// 2b. 특정 항목 거리순 전체 리스트 - 리스트 값	
		public List<Park> selectAllByDistance(String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
			return dao.selectAllByDistance(conn, bch_lat, bch_lng, radius, pageInfo);
		}
		
		
	public int insert(Park park) {
		int result = dao.insert(conn, park);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

}
