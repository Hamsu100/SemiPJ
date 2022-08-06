package com.kh.arround.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Lei;
import com.kh.common.util.PageInfo;


public class LeiDAO {
	
	// 1. 전체 항목 거리순 top3 리스트
	
	public List<Lei> selectTopByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Lei> list = new ArrayList<Lei>(); 
		
		String sql = "SELECT * FROM (SELECT LEISURE_NO, LEISURE_NAME, LEISURE_IMG, LEISURE_ADDRESS, LEISURE_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (LEISURE_LATITUDE)) * COS(RADIANS (LEISURE_LONGITUDE) - RADIANS (?)) "
				+ "+ SIN (RADIANS (?)) * SIN (RADIANS (LEISURE_LATITUDE))) AS DISTANCE FROM LEISURE ORDER BY DISTANCE) "
				+ "WHERE DISTANCE <= ? AND ROWNUM <= 3";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setString(4, radius);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Lei lei = new Lei();
				
				lei.setLeisure_no(rs.getInt("LEISURE_NO"));
				lei.setLeisure_name(rs.getString("LEISURE_NAME"));
				lei.setLeisure_img(rs.getString("LEISURE_IMG"));
				lei.setLeisure_address(rs.getString("LEISURE_ADDRESS"));
				lei.setLeisure_tel(rs.getString("LEISURE_TEL"));
				lei.setLeisure_distance(rs.getDouble("DISTANCE"));
				
				list.add(lei);
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
				+ "FROM (SELECT 6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (LEISURE_LATITUDE)) "
				+ "* COS(RADIANS (LEISURE_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (LEISURE_LATITUDE))) "
				+ "AS DISTANCE FROM LEISURE ORDER BY DISTANCE) WHERE DISTANCE <= ?";
		
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
	
	public List<Lei> selectAllByDistance(Connection conn, String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Lei> list = new ArrayList<Lei>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM AS RNUM, LEISURE_NO, LEISURE_NAME, LEISURE_IMG, LEISURE_LONGITUDE, LEISURE_LATITUDE, LEISURE_CONTENT, LEISURE_ADDRESS, LEISURE_TEL, DISTANCE "
				+ "FROM (SELECT LEISURE_NO, LEISURE_NAME, LEISURE_IMG, LEISURE_LONGITUDE, LEISURE_LATITUDE, LEISURE_CONTENT, LEISURE_ADDRESS, LEISURE_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (LEISURE_LATITUDE)) * COS(RADIANS (LEISURE_LONGITUDE) "
				+ "- RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (LEISURE_LATITUDE))) AS DISTANCE "
				+ "FROM LEISURE ORDER BY DISTANCE) WHERE DISTANCE <= ?) "
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
				Lei lei = new Lei();
				
				lei.setLeisure_no(rs.getInt("LEISURE_NO"));
				lei.setLeisure_name(rs.getString("LEISURE_NAME"));
				lei.setLeisure_img(rs.getString("LEISURE_IMG"));
				lei.setLeisure_longitude(rs.getString("LEISURE_LONGITUDE"));
				lei.setLeisure_latitude(rs.getString("LEISURE_LATITUDE"));
				lei.setLeisure_content(rs.getString("LEISURE_CONTENT"));
				lei.setLeisure_address(rs.getString("LEISURE_ADDRESS"));
				lei.setLeisure_tel(rs.getString("LEISURE_TEL"));
				lei.setLeisure_distance(rs.getDouble("DISTANCE"));
				
				list.add(lei);
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
	
	public Lei selectOneWithBasic(Connection conn, int Lei_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Lei lei = new Lei();
		
		String sql = "SELECT * FROM LEISURE WHERE LEISURE_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps. setInt(1, Lei_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				lei.setLeisure_no(rs.getInt("LEISURE_NO"));
				lei.setCategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				lei.setLeisure_name(rs.getString("LEISURE_NAME"));
				lei.setLeisure_img(rs.getString("LEISURE_IMG"));
				lei.setLeisure_longitude(rs.getString("LEISURE_LONGITUDE"));
				lei.setLeisure_latitude(rs.getString("LEISURE_LATITUDE"));
				lei.setLeisure_content(rs.getString("LEISURE_CONTENT"));
				lei.setLeisure_address(rs.getString("LEISURE_ADDRESS"));
				lei.setLeisure_tel(rs.getString("LEISURE_TEL"));
				lei.setArea_code(rs.getInt("AREA_CODE"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return lei;
	}
	
	
	
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	
	public Lei selectOneWithDistance(Connection conn, String bch_lat, String bch_lng, String radius, int lei_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Lei lei = new Lei();
		
		String sql = "SELECT * "
				+ "FROM (SELECT LEISURE_NO, FACIL_CATEGORY_NO, LEISURE_NAME, LEISURE_IMG, LEISURE_LONGITUDE, LEISURE_LATITUDE, LEISURE_CONTENT, LEISURE_ADDRESS, LEISURE_TEL, AREA_CODE, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (LEISURE_LATITUDE)) * COS(RADIANS (LEISURE_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) "
				+ "* SIN (RADIANS (LEISURE_LATITUDE))) AS DISTANCE FROM LEISURE ORDER BY DISTANCE) "
				+ "WHERE LEISURE_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps. setInt(4, lei_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				lei.setLeisure_no(rs.getInt("LEISURE_NO"));
				lei.setCategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				lei.setLeisure_name(rs.getString("LEISURE_NAME"));
				lei.setLeisure_img(rs.getString("LEISURE_IMG"));
				lei.setLeisure_longitude(rs.getString("LEISURE_LONGITUDE"));
				lei.setLeisure_latitude(rs.getString("LEISURE_LATITUDE"));
				lei.setLeisure_content(rs.getString("LEISURE_CONTENT"));
				lei.setLeisure_address(rs.getString("LEISURE_ADDRESS"));
				lei.setLeisure_tel(rs.getString("LEISURE_TEL"));
				lei.setArea_code(rs.getInt("AREA_CODE"));
				
				lei.setLeisure_distance(rs.getDouble("DISTANCE"));
			}          
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return lei;
	}

	
	
	public int insert(Connection conn, Lei l) {
		
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "insert into leisure values(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, l.getLeisure_no()); // RES_NO LEISURE_NO
			pstmt.setInt(2, l.getCategory_no()); // FACIL_CATEGORY_NO FACIL_CATEGORY_NO
			pstmt.setString(3, l.getLeisure_name()); // RES_NAME LEISURE_NAME

			pstmt.setString(4, l.getLeisure_img()); // RES_IMG LEISURE_IMG
			pstmt.setString(5, l.getLeisure_longitude()); // RES_LONGITUDE LEISURE_LONGITUDE
			pstmt.setString(6, l.getLeisure_latitude()); // RES_LATITUDE LEISURE_LATITUDE

			pstmt.setString(7, l.getLeisure_content()); // RES_CONTENT LEISURE_CONTENT
			pstmt.setString(8, l.getLeisure_address()); // RES_ADDRESS LEISURE_ADDRESS
			pstmt.setString(9, l.getLeisure_tel()); // 
			pstmt.setInt(10, l.getArea_code()); // AREA_CODE AREA_CODE

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
