package com.kh.arround.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.arround.model.vo.Res;
import com.kh.common.util.PageInfo;


public class ResDAO {
	
	// 1. 전체 항목 거리순 top3 리스트
	
	public List<Res> selectTopByDistance(Connection conn, String bch_lat, String bch_lng, String radius) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Res> list = new ArrayList<Res>(); 
		
		String sql = "SELECT * FROM (SELECT RES_NO, RES_NAME, RES_IMG, RES_ADDRESS, RES_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (RES_LATITUDE)) * COS(RADIANS (RES_LONGITUDE) - RADIANS (?)) "
				+ "+ SIN (RADIANS (?)) * SIN (RADIANS (RES_LATITUDE))) AS DISTANCE FROM RES ORDER BY DISTANCE) "
				+ "WHERE DISTANCE <= ? AND ROWNUM <= 3";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setString(4, radius);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Res res = new Res();
				
				res.setRes_no(rs.getInt("RES_NO"));
				res.setRes_name(rs.getString("RES_NAME"));
				res.setRes_img(rs.getString("RES_IMG"));
				res.setRes_addr(rs.getString("RES_ADDRESS"));
				res.setRes_tel(rs.getString("RES_TEL"));
				res.setRes_distance(rs.getDouble("DISTANCE"));
				
				list.add(res);
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
				+ "FROM (SELECT 6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (RES_LATITUDE)) "
				+ "* COS(RADIANS (RES_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (RES_LATITUDE))) "
				+ "AS DISTANCE FROM RES ORDER BY DISTANCE) WHERE DISTANCE <= ?";
		
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
	
	public List<Res> selectAllByDistance(Connection conn, String bch_lat, String bch_lng, String radius, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Res> list = new ArrayList<Res>();
		
		String sql = "SELECT * "
				+ "FROM (SELECT ROWNUM AS RNUM, RES_NO, RES_NAME, RES_IMG, RES_LONGITUDE, RES_LATITUDE, RES_CONTENT, RES_ADDRESS, RES_TEL, DISTANCE "
				+ "FROM (SELECT RES_NO, RES_NAME, RES_IMG, RES_LONGITUDE, RES_LATITUDE, RES_CONTENT, RES_ADDRESS, RES_TEL, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (RES_LATITUDE)) * COS(RADIANS (RES_LONGITUDE) "
				+ "- RADIANS (?)) + SIN (RADIANS (?)) * SIN (RADIANS (RES_LATITUDE))) AS DISTANCE "
				+ "FROM RES ORDER BY DISTANCE) WHERE DISTANCE <= ?) "
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
				Res res = new Res();
				
				res.setRes_no(rs.getInt("RES_NO"));
				res.setRes_name(rs.getString("RES_NAME"));
				res.setRes_img(rs.getString("RES_IMG"));
				res.setRes_long(rs.getString("RES_LONGITUDE"));
				res.setRes_lat(rs.getString("RES_LATITUDE"));
				res.setRes_con(rs.getString("RES_CONTENT"));
				res.setRes_addr(rs.getString("RES_ADDRESS"));
				res.setRes_tel(rs.getString("RES_TEL"));
				res.setRes_distance(rs.getDouble("DISTANCE"));
				
				list.add(res);
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
	
	public Res selectOneWithBasic(Connection conn, int res_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Res res = new Res();
		
		String sql = "SELECT * FROM RES WHERE RES_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps. setInt(1, res_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				res.setRes_no(rs.getInt("RES_NO"));
				res.setCategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				res.setRes_name(rs.getString("RES_NAME"));
				res.setRes_img(rs.getString("RES_IMG"));
				res.setRes_long(rs.getString("RES_LONGITUDE"));
				res.setRes_lat(rs.getString("RES_LATITUDE"));
				res.setRes_con(rs.getString("RES_CONTENT"));
				res.setRes_addr(rs.getString("RES_ADDRESS"));
				res.setRes_tel(rs.getString("RES_TEL"));
				res.setArea_code(rs.getInt("AREA_CODE"));
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return res;
	}
	
	
	
	// 3b. 특정 항목 특정 상세 정보 - 거리정보
	
	public Res selectOneWithDistance(Connection conn, String bch_lat, String bch_lng, String radius, int res_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Res res = new Res();
		
		String sql = "SELECT * "
				+ "FROM (SELECT RES_NO, FACIL_CATEGORY_NO, RES_NAME, RES_IMG, RES_LONGITUDE, RES_LATITUDE, RES_CONTENT, RES_ADDRESS, RES_TEL, AREA_CODE, "
				+ "6371 * ACOS (COS (RADIANS (?)) * COS (RADIANS (RES_LATITUDE)) * COS(RADIANS (RES_LONGITUDE) - RADIANS (?)) + SIN (RADIANS (?)) "
				+ "* SIN (RADIANS (RES_LATITUDE))) AS DISTANCE FROM RES ORDER BY DISTANCE) "
				+ "WHERE RES_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bch_lat);
			ps.setString(2, bch_lng);
			ps.setString(3, bch_lat);
			ps.setInt(4, res_no);
			
			rs = ps.executeQuery();
			
			if (rs.next()) {
				res.setRes_no(rs.getInt("RES_NO"));
				res.setCategory_no(rs.getInt("FACIL_CATEGORY_NO"));
				res.setRes_name(rs.getString("RES_NAME"));
				res.setRes_img(rs.getString("RES_IMG"));
				res.setRes_long(rs.getString("RES_LONGITUDE"));
				res.setRes_lat(rs.getString("RES_LATITUDE"));
				res.setRes_con(rs.getString("RES_CONTENT"));
				res.setRes_addr(rs.getString("RES_ADDRESS"));
				res.setRes_tel(rs.getString("RES_TEL"));
				res.setArea_code(rs.getInt("AREA_CODE"));
				
				res.setRes_distance(rs.getDouble("DISTANCE"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return res;
	}

	
	
	public int insert(Connection conn, Res r) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "insert into res values(?, ?, ?, ?, ?,  ?, ?, ?, ?, ?)";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, r.getRes_no()); // RES_NO
			pstmt.setInt(2, r.getCategory_no()); // FACIL_CATEGORY_NO
			pstmt.setString(3, r.getRes_name()); // RES_NAME

			pstmt.setString(4, r.getRes_img()); // RES_IMG
			pstmt.setString(5, r.getRes_long()); // RES_LONGITUDE
			pstmt.setString(6, r.getRes_lat()); // RES_LATITUDE

			pstmt.setString(7, r.getRes_con()); // RES_CONTENT
			pstmt.setString(8, r.getRes_addr()); // RES_ADDRESS
			pstmt.setString(9, r.getRes_tel()); // 
			pstmt.setInt(10, r.getArea_code()); // AREA_CODE

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
