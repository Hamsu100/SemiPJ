package com.kh.arround.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Park;
import com.kh.common.util.PageInfo;


public class ParkDAO {
	
	// 1. db -> all vo : 전체 항목 거리순 top3 리스트 - park 
	// 2. db -> a vo  : 특정 항목 거리순 전체 리스트
	// 3. db -> a vo  : 특정 항목 특정 상세 정보
	
	// 1. 전체 항목 거리순 top3 리스트
	
	public List<Park> selectTopByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Park> list = new ArrayList<Park>(); 
		
		String sql = "SELECT * FROM (SELECT PARK_NO, PARK_NAME, PARK_NEW_ADDR, PARK_ADDR, PARK_WDAY_SETTIME, PARK_SAT_SETTIME, PARK_HOL_SETTIME, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (PARK_LATITUDE)) * COS(RADIANS (PARK_LONGITUDE) - RADIANS (?)) "
				+ "+ SIN (RADIANS (?)) * SIN (RADIANS (PARK_LATITUDE))) AS DISTANCE FROM PARK ORDER BY DISTANCE) "
				+ "WHERE DISTANCE <= ? AND ROWNUM <= 3";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setString(4, radius);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Park park = new Park();
				
				park.setPark_no(rs.getInt("PARK_NO"));
				park.setPark_name(rs.getString("PARK_NAME"));
				park.setPark_new_addr(rs.getString("PARK_NEW_ADDR"));
				park.setPark_addr(rs.getString("PARK_ADDR"));
				park.setPark_wday_settime(rs.getString("PARK_WDAY_SETTIME"));
				park.setPark_sat_settime(rs.getString("PARK_SAT_SETTIME"));
				park.setPark_hol_settime(rs.getString("PARK_HOL_SETTIME"));
				park.setPark_distance(rs.getDouble("DISTANCE"));
				
				list.add(park);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}

		return list;
		
	}
	
	
	
	// 2a. 특정 항목 거리순 전체 리스트 - 리스트 수
	
		public int selectAllCntByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			int resultCnt = 0;
			
			String sql = "SELECT COUNT(*) "
					+ "FROM (SELECT 6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (PARK_LATITUDE)) "
					+ "* COS(RADIANS (PARK_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (PARK_LATITUDE))) "
					+ "AS DISTANCE FROM PARK ORDER BY DISTANCE) WHERE DISTANCE <= ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, bch_lat);
				ps.setString(2, bch_lng);
				ps.setString(3, bch_lat);
				ps.setString(4, radius);
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					resultCnt = rs.getInt(1);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps);
				close(rs);
			}
			return resultCnt;
		}
	
	
		
		// 2b. 특정 항목 거리순 전체 리스트 - 리스트 값
		
		public List<Park> selectAllByDistance(Connection conn, String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			List<Park> list = new ArrayList<Park>();
			
			String sql = "SELECT * "
					+ "FROM (SELECT ROWNUM AS RNUM, PARK_NO, PARK_NAME, PARK_NEW_ADDR, PARK_ADDR, PARK_LONGITUDE, PARK_LATITUDE, PARK_WDAY_SETTIME, PARK_SAT_SETTIME, PARK_HOL_SETTIME, DISTANCE "
					+ "FROM (SELECT PARK_NO, PARK_NAME, PARK_NEW_ADDR, PARK_ADDR, PARK_LONGITUDE, PARK_LATITUDE, PARK_WDAY_SETTIME, PARK_SAT_SETTIME, PARK_HOL_SETTIME, "
					+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (PARK_LATITUDE)) * COS(RADIANS (PARK_LONGITUDE) "
					+ "- RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (PARK_LATITUDE))) AS DISTANCE "
					+ "FROM PARK ORDER BY DISTANCE) WHERE DISTANCE <= ?) "
					+ "WHERE RNUM BETWEEN ? AND ?";
			
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, bch_lat);
				ps.setString(2, bch_lng);
				ps.setString(3, bch_lat);
				ps.setString(4, radius);
				
				ps.setInt(5, pageInfo.getStartList());
				ps.setInt(6, pageInfo.getEndList());
				
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Park park = new Park();
					
					park.setPark_no(0);
					park.setPark_name(rs.getString("PARK_NAME"));
					park.setPark_new_addr(rs.getString("PARK_NEW_ADDR"));
					park.setPark_addr(rs.getString("PARK_ADDR"));
					park.setPark_longitude(rs.getString("PARK_LONGITUDE"));
					park.setPark_latitude(rs.getString("PARK_LATITUDE"));
					park.setPark_wday_settime(rs.getString("PARK_WDAY_SETTIME"));
					park.setPark_sat_settime(rs.getString("PARK_SAT_SETTIME"));
					park.setPark_hol_settime(rs.getString("PARK_HOL_SETTIME"));
					park.setPark_distance(rs.getDouble("DISTANCE"));
					
					list.add(park);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps);
				close(rs);
			}

			return list;
			
		}
	
	
	
	public int insert(Connection conn, Park park) {
		try {
			String sql = "INSERT INTO PARK (PARK_NO, FACIL_CATEGORY_NO, PARK_LONGITUDE, PARK_LATITUDE, "
					+ "PARK_WDAY_SETTIME, PARK_SAT_SETTIME, PARK_HOL_SETTIME, PARK_NAME, PARK_ADDR, "
					+ "PARK_NEW_ADDR, AREA_CODE) VALUES(SEQ_PARK_NO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int count = 1;
			ps.setInt(count++, park.getFcategory_no());
			ps.setString(count++, park.getPark_longitude());
			ps.setString(count++, park.getPark_latitude());
			ps.setString(count++, park.getPark_wday_settime());
			ps.setString(count++, park.getPark_sat_settime());
			ps.setString(count++, park.getPark_hol_settime());
			ps.setString(count++, park.getPark_name());
			ps.setString(count++, park.getPark_addr());
			ps.setString(count++, park.getPark_new_addr());
			ps.setInt(count++, park.getArea_code());
			
			int result = ps.executeUpdate();
			
			ps.close();
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

}
