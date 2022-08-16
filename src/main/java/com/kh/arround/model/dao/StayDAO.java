package com.kh.arround.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Stay;
import com.kh.common.util.PageInfo;


public class StayDAO {
	
	// 1. 전체 항목 거리순 top3 리스트
	
	public List<Stay> selectTopByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Stay> list = new ArrayList<Stay>(); 
		
		String sql = "SELECT * FROM (SELECT STAY_NO, STAY_NAME, STAY_IMG, STAY_ADDRESS, STAY_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (STAY_LATITUDE)) * COS(RADIANS (STAY_LONGITUDE) - RADIANS (?)) "
				+ "+ SIN (RADIANS (?)) * SIN (RADIANS (STAY_LATITUDE))) AS DISTANCE FROM STAY ORDER BY DISTANCE) "
				+ "WHERE DISTANCE <= ? AND ROWNUM <= 3";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setString(4, radius);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Stay stay = new Stay();
				
				stay.setStay_no(rs.getInt("STAY_NO"));
				stay.setName(rs.getString("STAY_NAME"));
				stay.setImg(rs.getString("STAY_IMG"));
				stay.setAddr(rs.getString("STAY_ADDRESS"));
				stay.setTel(rs.getString("STAY_TEL"));
				stay.setDistance(rs.getDouble("DISTANCE"));
				
				list.add(stay);
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
				+ "FROM (SELECT 6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (STAY_LATITUDE)) "
				+ "* COS(RADIANS (STAY_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (STAY_LATITUDE))) "
				+ "AS DISTANCE FROM STAY ORDER BY DISTANCE) WHERE DISTANCE <= ?";
		
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
	
	public List<Stay> selectAllByDistance(Connection conn, String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Stay> list = new ArrayList<Stay>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM AS RNUM, STAY_NO, STAY_NAME, STAY_IMG, STAY_LONGITUDE, STAY_LATITUDE, STAY_CONTENT, STAY_ADDRESS, STAY_TEL, DISTANCE "
				+ "FROM (SELECT STAY_NO, STAY_NAME, STAY_IMG, STAY_LONGITUDE, STAY_LATITUDE, STAY_CONTENT, STAY_ADDRESS, STAY_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (STAY_LATITUDE)) * COS(RADIANS (STAY_LONGITUDE) "
				+ "- RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (STAY_LATITUDE))) AS DISTANCE "
				+ "FROM STAY ORDER BY DISTANCE) WHERE DISTANCE <= ?) "
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
				Stay stay = new Stay();
				
				stay.setStay_no(rs.getInt("STAY_NO"));
				stay.setName(rs.getString("STAY_NAME"));
				stay.setImg(rs.getString("STAY_IMG"));
				stay.setLongitude(rs.getString("STAY_LONGITUDE"));
				stay.setLatitude(rs.getString("STAY_LATITUDE"));
				stay.setContent(rs.getString("STAY_CONTENT"));
				stay.setAddr(rs.getString("STAY_ADDRESS"));
				stay.setTel(rs.getString("STAY_TEL"));
				stay.setDistance(rs.getDouble("DISTANCE"));
				
				list.add(stay);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}

		return list;
		
	}
	
	
	
	// 3a. 특정 항목 특정 상세 정보 - 기본정보
	
	public Stay selectOneWithBasic(Connection conn, int stay_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Stay stay = new Stay();
		
		String sql = "SELECT * FROM STAY WHERE STAY_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps. setInt(1, stay_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				stay.setStay_no(rs.getInt("STAY_NO"));
				stay.setFacil_category_code(rs.getInt("FACIL_CATEGORY_NO"));
				stay.setName(rs.getString("STAY_NAME"));
				stay.setImg(rs.getString("STAY_IMG"));
				stay.setLongitude(rs.getString("STAY_LONGITUDE"));
				stay.setLatitude(rs.getString("STAY_LATITUDE"));
				stay.setContent(rs.getString("STAY_CONTENT"));
				stay.setAddr(rs.getString("STAY_ADDRESS"));
				stay.setTel(rs.getString("STAY_TEL"));
				stay.setArea_code(rs.getInt("AREA_CODE"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return stay;
	}
	
	
	
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	
	public Stay selectOneWithDistance(Connection conn, String bch_lat, String bch_lng, String radius, int stay_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Stay stay = new Stay();
		
		String sql = "SELECT * "
				+ "FROM (SELECT STAY_NO, FACIL_CATEGORY_NO, STAY_NAME, STAY_IMG, STAY_LONGITUDE, STAY_LATITUDE, STAY_CONTENT, STAY_ADDRESS, STAY_TEL, AREA_CODE, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (STAY_LATITUDE)) * COS(RADIANS (STAY_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) "
				+ "* SIN (RADIANS (STAY_LATITUDE))) AS DISTANCE FROM STAY ORDER BY DISTANCE) "
				+ "WHERE STAY_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps. setInt(4, stay_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				stay.setStay_no(rs.getInt("STAY_NO"));
				stay.setFacil_category_code(rs.getInt("FACIL_CATEGORY_NO"));
				stay.setName(rs.getString("STAY_NAME"));
				stay.setImg(rs.getString("STAY_IMG"));
				stay.setLongitude(rs.getString("STAY_LONGITUDE"));
				stay.setLatitude(rs.getString("STAY_LATITUDE"));
				stay.setContent(rs.getString("STAY_CONTENT"));
				stay.setAddr(rs.getString("STAY_ADDRESS"));
				stay.setTel(rs.getString("STAY_TEL"));
				stay.setArea_code(rs.getInt("AREA_CODE"));
				
				stay.setDistance(rs.getDouble("DISTANCE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return stay;
	}


}
