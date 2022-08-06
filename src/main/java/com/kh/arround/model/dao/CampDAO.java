package com.kh.arround.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Camp;
import com.kh.common.util.PageInfo;


public class CampDAO {
	
	// 1. 전체 항목 거리순 top3 리스트 - camp
	
	public List<Camp> selectTopByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Camp> list = new ArrayList<Camp>(); 
		
		String sql = "SELECT * FROM (SELECT CAMP_NO, CAMP_NAME, CAMP_IMG, CAMP_ADDRESS, CAMP_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAMP_LATITUDE)) * COS(RADIANS (CAMP_LONGITUDE) - RADIANS (?)) "
				+ "+ SIN (RADIANS (?)) * SIN (RADIANS (CAMP_LATITUDE))) AS DISTANCE FROM CAMP ORDER BY DISTANCE) "
				+ "WHERE DISTANCE <= ? AND ROWNUM <= 3";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setString(4, radius);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Camp camp = new Camp();
				
				camp.setCamp_no(rs.getInt("CAMP_NO"));
				camp.setCamp_name(rs.getString("CAMP_NAME"));
				camp.setCamp_img(rs.getString("CAMP_IMG"));
				camp.setCamp_address(rs.getString("CAMP_ADDRESS"));
				camp.setCamp_tel(rs.getString("CAMP_TEL"));
				camp.setCamp_distance(rs.getDouble("DISTANCE"));
				
				list.add(camp);
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
				+ "FROM (SELECT 6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAMP_LATITUDE)) "
				+ "* COS(RADIANS (CAMP_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (CAMP_LATITUDE))) "
				+ "AS DISTANCE FROM CAMP ORDER BY DISTANCE) WHERE DISTANCE <= ?";
		
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
	
	public List<Camp> selectAllByDistance(Connection conn, String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Camp> list = new ArrayList<Camp>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM AS RNUM, CAMP_NO, CAMP_NAME, CAMP_IMG, CAMP_LONGITUDE, CAMP_LATITUDE, CAMP_CONTENT, CAMP_ADDRESS, CAMP_TEL, DISTANCE "
				+ "FROM (SELECT CAMP_NO, CAMP_NAME, CAMP_IMG, CAMP_LONGITUDE, CAMP_LATITUDE, CAMP_CONTENT, CAMP_ADDRESS, CAMP_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAMP_LATITUDE)) * COS(RADIANS (CAMP_LONGITUDE) "
				+ "- RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (CAMP_LATITUDE))) AS DISTANCE "
				+ "FROM CAMP ORDER BY DISTANCE) WHERE DISTANCE <= ?) "
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
				Camp camp = new Camp();
				
				camp.setCamp_no(rs.getInt("CAMP_NO"));
				camp.setCamp_name(rs.getString("CAMP_NAME"));
				camp.setCamp_img(rs.getString("CAMP_IMG"));
				camp.setCamp_longitude(rs.getString("CAMP_LONGITUDE"));
				camp.setCamp_latitude(rs.getString("CAMP_LATITUDE"));
				camp.setCamp_content(rs.getString("CAMP_CONTENT"));
				camp.setCamp_address(rs.getString("CAMP_ADDRESS"));
				camp.setCamp_tel(rs.getString("CAMP_TEL"));
				camp.setCamp_distance(rs.getDouble("DISTANCE"));
				
				list.add(camp);
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
	
	public Camp selectOneWithBasic(Connection conn, int camp_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Camp camp = new Camp();
		
		String sql = "SELECT * FROM CAMP WHERE CAMP_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps. setInt(1, camp_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				camp.setCamp_no(rs.getInt("CAMP_NO"));
				camp.setFcategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				camp.setCamp_name(rs.getString("CAMP_NAME"));
				camp.setCamp_img(rs.getString("CAMP_IMG"));
				camp.setCamp_longitude(rs.getString("CAMP_LONGITUDE"));
				camp.setCamp_latitude(rs.getString("CAMP_LATITUDE"));
				camp.setCamp_content(rs.getString("CAMP_CONTENT"));
				camp.setCamp_address(rs.getString("CAMP_ADDRESS"));
				camp.setCamp_tel(rs.getString("CAMP_TEL"));
				camp.setArea_code(rs.getInt("AREA_CODE"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return camp;
	}
	
	
	
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	
	public Camp selectOneWithDistance(Connection conn, String bch_lat, String bch_lng, String radius, int camp_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Camp camp = new Camp();
		
		String sql = "SELECT * "
				+ "FROM (SELECT CAMP_NO, FACIL_CATEGORY_NO, CAMP_NAME, CAMP_IMG, CAMP_LONGITUDE, CAMP_LATITUDE, CAMP_CONTENT, CAMP_ADDRESS, CAMP_TEL, AREA_CODE, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAMP_LATITUDE)) * COS(RADIANS (CAMP_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) "
				+ "* SIN (RADIANS (CAMP_LATITUDE))) AS DISTANCE FROM CAMP ORDER BY DISTANCE) "
				+ "WHERE CAMP_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps. setInt(4, camp_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				camp.setCamp_no(rs.getInt("CAMP_NO"));
				camp.setFcategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				camp.setCamp_name(rs.getString("CAMP_NAME"));
				camp.setCamp_img(rs.getString("CAMP_IMG"));
				camp.setCamp_longitude(rs.getString("CAMP_LONGITUDE"));
				camp.setCamp_latitude(rs.getString("CAMP_LATITUDE"));
				camp.setCamp_content(rs.getString("CAMP_CONTENT"));
				camp.setCamp_address(rs.getString("CAMP_ADDRESS"));
				camp.setCamp_tel(rs.getString("CAMP_TEL"));
				camp.setArea_code(rs.getInt("AREA_CODE"));
				
				camp.setCamp_distance(rs.getDouble("DISTANCE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return camp;
	}

	
	
	public int insert(Connection conn, Camp camp) {
		
		int result = 0;
		PreparedStatement ps = null; 
		
		String sql = "INSERT INTO CAMP (CAMP_NO, FACIL_CATEGORY_NO, CAMP_NAME, CAMP_IMG, "
				+ "CAMP_LONGITUDE, CAMP_LATITUDE, CAMP_CONTENT, CAMP_ADDRESS, CAMP_TEL, AREA_CODE) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			int count = 1;
			ps = conn.prepareStatement(sql);
			ps.setInt(count++, camp.getCamp_no());
			ps.setInt(count++, camp.getFcategory_no());
			ps.setString(count++, camp.getCamp_name());
			ps.setString(count++, camp.getCamp_img());
			ps.setString(count++, camp.getCamp_longitude());
			ps.setString(count++, camp.getCamp_latitude());
			ps.setString(count++, camp.getCamp_content());
			ps.setString(count++, camp.getCamp_address());
			ps.setString(count++, camp.getCamp_tel());
			ps.setInt(count++, camp.getArea_code());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}
	
}


