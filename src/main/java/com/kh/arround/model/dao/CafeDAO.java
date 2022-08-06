package com.kh.arround.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Cafe;
import com.kh.common.util.PageInfo;


public class CafeDAO {
	
	// 1. 전체 항목 거리순 top3 리스트

	public List<Cafe> selectTopByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Cafe> list = new ArrayList<Cafe>(); 
		
		String sql = "SELECT * FROM (SELECT CAFE_NO, CAFE_NAME, CAFE_IMG, CAFE_ADDRESS, CAFE_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAFE_LATITUDE)) * COS(RADIANS (CAFE_LONGITUDE) - RADIANS (?)) "
				+ "+ SIN (RADIANS (?)) * SIN (RADIANS (CAFE_LATITUDE))) AS DISTANCE FROM CAFE ORDER BY DISTANCE) "
				+ "WHERE DISTANCE <= ? AND ROWNUM <= 3";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setString(4, radius);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Cafe cafe = new Cafe();
				
				cafe.setCafe_no(rs.getInt("CAFE_NO"));
				cafe.setCafe_name(rs.getString("CAFE_NAME"));
				cafe.setCafe_img(rs.getString("CAFE_IMG"));
				cafe.setCafe_addr(rs.getString("CAFE_ADDRESS"));
				cafe.setCafe_tel(rs.getString("CAFE_TEL"));
				cafe.setCafe_distance(rs.getDouble("DISTANCE"));
				
				list.add(cafe);
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
				+ "FROM (SELECT 6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAFE_LATITUDE)) "
				+ "* COS(RADIANS (CAFE_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (CAFE_LATITUDE))) "
				+ "AS DISTANCE FROM CAFE ORDER BY DISTANCE) WHERE DISTANCE <= ?";
		
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
	
	public List<Cafe> selectAllByDistance(Connection conn, String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Cafe> list = new ArrayList<Cafe>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM AS RNUM, CAFE_NO, CAFE_NAME, CAFE_IMG, CAFE_LONGITUDE, CAFE_LATITUDE, CAFE_CONTENT, CAFE_ADDRESS, CAFE_TEL, DISTANCE "
				+ "FROM (SELECT CAFE_NO, CAFE_NAME, CAFE_IMG, CAFE_LONGITUDE, CAFE_LATITUDE, CAFE_CONTENT, CAFE_ADDRESS, CAFE_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAFE_LATITUDE)) * COS(RADIANS (CAFE_LONGITUDE) "
				+ "- RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (CAFE_LATITUDE))) AS DISTANCE "
				+ "FROM CAFE ORDER BY DISTANCE) WHERE DISTANCE <= ?) "
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
				Cafe cafe = new Cafe();
				
				cafe.setCafe_no(rs.getInt("CAFE_NO"));
				cafe.setCafe_name(rs.getString("CAFE_NAME"));
				cafe.setCafe_img(rs.getString("CAFE_IMG"));
				cafe.setCafe_long(rs.getString("CAFE_LONGITUDE"));
				cafe.setCafe_lat(rs.getString("CAFE_LATITUDE"));
				cafe.setCafe_con(rs.getString("CAFE_CONTENT"));
				cafe.setCafe_addr(rs.getString("CAFE_ADDRESS"));
				cafe.setCafe_tel(rs.getString("CAFE_TEL"));
				cafe.setCafe_distance(rs.getDouble("DISTANCE"));
				
				list.add(cafe);
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
	
	public Cafe selectOneWithBasic(Connection conn, int cafe_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Cafe cafe = new Cafe();
		
		String sql = "SELECT * FROM CAFE WHERE CAFE_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps. setInt(1, cafe_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				cafe.setCafe_no(rs.getInt("CAFE_NO"));
				cafe.setCategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				cafe.setCafe_name(rs.getString("CAFE_NAME"));
				cafe.setCafe_img(rs.getString("CAFE_IMG"));
				cafe.setCafe_long(rs.getString("CAFE_LONGITUDE"));
				cafe.setCafe_lat(rs.getString("CAFE_LATITUDE"));
				cafe.setCafe_con(rs.getString("CAFE_CONTENT"));
				cafe.setCafe_addr(rs.getString("CAFE_ADDRESS"));
				cafe.setCafe_tel(rs.getString("CAFE_TEL"));
				cafe.setArea_code(rs.getInt("AREA_CODE"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return cafe;
	}
	
	
	
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	
	public Cafe selectOneWithDistance(Connection conn, String bch_lat, String bch_lng, String radius, int cafe_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Cafe cafe = new Cafe();
		
		String sql = "SELECT * "
				+ "FROM (SELECT CAFE_NO, FACIL_CATEGORY_NO, CAFE_NAME, CAFE_IMG, CAFE_LONGITUDE, CAFE_LATITUDE, CAFE_CONTENT, CAFE_ADDRESS, CAFE_TEL, AREA_CODE, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (CAFE_LATITUDE)) * COS(RADIANS (CAFE_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) "
				+ "* SIN (RADIANS (CAFE_LATITUDE))) AS DISTANCE FROM CAFE ORDER BY DISTANCE) "
				+ "WHERE CAFE_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps. setInt(4, cafe_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				cafe.setCafe_no(rs.getInt("CAFE_NO"));
				cafe.setCategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				cafe.setCafe_name(rs.getString("CAFE_NAME"));
				cafe.setCafe_img(rs.getString("CAFE_IMG"));
				cafe.setCafe_long(rs.getString("CAFE_LONGITUDE"));
				cafe.setCafe_lat(rs.getString("CAFE_LATITUDE"));
				cafe.setCafe_con(rs.getString("CAFE_CONTENT"));
				cafe.setCafe_addr(rs.getString("CAFE_ADDRESS"));
				cafe.setCafe_tel(rs.getString("CAFE_TEL"));
				cafe.setArea_code(rs.getInt("AREA_CODE"));
				
				cafe.setCafe_distance(rs.getDouble("DISTANCE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return cafe;
	}

	
	
	public int insert(Connection conn, Cafe c) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "insert into cafe values(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getCafe_no()); // CAFE_NO
			pstmt.setInt(2, c.getCategory_no()); // FACIL_CATEGORY_NO
			pstmt.setString(3, c.getCafe_name()); // CAFE_NAME

			pstmt.setString(4, c.getCafe_img()); // CAFE_IMG
			pstmt.setString(5, c.getCafe_long()); // CAFE_LONGITUDE
			pstmt.setString(6, c.getCafe_lat()); // CAFE_LATITUDE

			pstmt.setString(7, c.getCafe_con()); // CAFE_CONTENT
			pstmt.setString(8, c.getCafe_addr()); // CAFE_ADDRESS
			pstmt.setString(9, c.getCafe_tel()); // 
			pstmt.setInt(10, c.getArea_code()); // AREA_CODE

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
		
}
