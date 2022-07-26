package com.kh.member.model.service;

import static com.kh.common.jdbc.JDBCTemplate.close;
import static com.kh.common.jdbc.JDBCTemplate.commit;
import static com.kh.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.member.model.dao.UserDAO;
import com.kh.member.model.vo.User;

public class UserService {

	private UserDAO dao = new UserDAO();

	private Connection conn = null;

	public UserService() {
		super();
		conn = getConnection();
	}
	
	public User searchUser(String id ) {
		User user = dao.searchById(conn, id);
		return user;
	}
	
	public User login(String userId, String userPw) {
		User user = searchUser(userId);

		if (user != null && user.getUser_pw().equals(userPw)) {
			return user;
		} else {
			return null;
		}
	}

	//
	public int join(User user) {
		int result = dao.insertUser(user, conn);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public boolean isDuplicated(String userId) {
		User user = dao.searchById(conn, userId);

		if (user != null) {
			return false; // id가 중복
		} else {
			return true; // id가 중복 아님
		}
	}
	
	public void closeConn() {
		close(conn);
	}
}
