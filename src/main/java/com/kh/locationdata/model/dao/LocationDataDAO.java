package com.kh.locationdata.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.kh.common.jdbc.JDBCTemplate.*;

public class LocationDataDAO {

	// 1. 지역 이름을 가져와서 area_code로 바꿔주기

	public int getLocNumberByLocName(Connection conn, String locName) {
		int result = 0;
		String sql = "select location_no from location where location_name = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, locName);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("location_no");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

//	public static void main(String[] args) {
//		LocationDataDAO ldd = new LocationDataDAO();
//		Connection conn = getConnection();
//		String str = "서해";
//		int result = ldd.getLocNumberByLocName(conn, str);
//		System.out.println(result);
//	}
}
