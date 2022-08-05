package com.kh.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.kh.common.jdbc.JDBCTemplate.*;

import com.kh.member.model.vo.User;

public class UserDAO {

	// 생성
	public int insertUser(User user, Connection conn) {
		int result = 0;

		String sql = "insert into tbl_user values(SEQ_USER_NO.nextval, ?, ?, ?, ?, default, default, default)";

		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_pw());
			pstmt.setString(4, user.getUser_phone());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// id로 검색
	public User searchById(Connection conn, String id) {
		String sql = "select * from tbl_user where user_status = 'Y' and user_id = ?";

		

		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				User user = new User();
				user.setUser_crt_date(rs.getDate("USER_CRT_DATE"));
				user.setUser_id(rs.getString("user_id"));
				user.setUser_mdf_date(rs.getDate("USER_MDF_DATE"));
				user.setUser_name(rs.getString("USER_NAME"));
				user.setUser_no(rs.getInt("USER_NO"));
				user.setUser_phone(rs.getString("USER_PHONE"));
				user.setUser_pw(rs.getString("USER_PW"));
				user.setUser_status(rs.getString("USER_STATUS"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return null;
	}
	
	// 메소드 추가
	// 1. 유저 정보 불러오는 메소드
	
	
}
