package com.kh.blog.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.blog.model.vo.Reply;


public class ReplyDAO {
	
	// 1a. 특정 블로그 댓글 리스트
	public List<Reply> selectReplyAllByNew(Connection conn, int blog_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Reply> list = new ArrayList<Reply>();
		
		String sql = "SELECT R.BLOG_REPLY_NO, R.BLOG_REPLY_CONTENT, R.BLOG_REPLY_CRT_DATE, "
				+ "R.BLOG_REPLY_MDF_DATE, R.BLOG_NO, R.USER_NO, R.BLOG_REPLY_STATUS, U.USER_ID "
				+ "FROM BLOG_REPLY R, TBL_USER U WHERE R.USER_NO = U.USER_NO AND "
				+ "R.BLOG_REPLY_STATUS = 'Y' AND R.BLOG_NO = ? ORDER BY R.BLOG_REPLY_NO ASC";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog_no);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Reply reply = new Reply();
				
				reply.setBlog_reply_no(rs.getInt("BLOG_REPLY_NO"));
				reply.setBlog_reply_content(rs.getString("BLOG_REPLY_CONTENT"));
				reply.setBlog_reply_crt_date(rs.getDate("BLOG_REPLY_CRT_DATE"));
				reply.setBlog_reply_mdf_date(rs.getDate("BLOG_REPLY_MDF_DATE"));
				reply.setBlog_no(rs.getInt("BLOG_NO"));
				reply.setUser_no(rs.getInt("USER_NO"));
				reply.setBlog_reply_status(rs.getString("BLOG_REPLY_STATUS"));
				reply.setUser_id(rs.getString("USER_ID"));
				
				list.add(reply);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return list;
	}
	
	
	// 1b. 특정 블로그 최신순 댓글 리스트 수
	public int selectReplyAllCountByNew(Connection conn, int blog_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM BLOG_REPLY WHERE BLOG_NO = ? AND BLOG_REPLY_STATUS = 'Y'";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog_no);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return result;
	}
	
	
	// 2. 댓글 작성
	public int insertReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO BLOG_REPLY VALUES (SEQ_BLOG_REPLY_NO.NEXTVAL, ?, DEFAULT, DEFAULT, ?, ?, DEFAULT)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, reply.getBlog_reply_content());
			ps.setInt(2, reply.getBlog_no());
			ps.setInt(3, reply.getUser_no());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}

	
	// 3. 댓글 삭제
	public int deleteReply(Connection conn, int blog_reply_no) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BLOG_REPLY SET BLOG_REPLY_STATUS = 'N' WHERE BLOG_REPLY_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog_reply_no);
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}
	
	// 4. 특정 댓글 조회
	public Reply searchReply(Connection conn, int blog_reply_no) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Reply reply = new Reply();
		
		String sql = "SELECT R.BLOG_REPLY_NO, R.BLOG_REPLY_CONTENT, R.BLOG_REPLY_CRT_DATE, R.BLOG_REPLY_MDF_DATE, R.BLOG_NO, R.USER_NO, R.BLOG_REPLY_STATUS, U.USER_ID "
				+ "FROM BLOG_REPLY R, TBL_USER U WHERE R.USER_NO = U.USER_NO AND BLOG_REPLY_STATUS = 'Y' AND BLOG_REPLY_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog_reply_no);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				reply.setBlog_reply_no(rs.getInt("BLOG_REPLY_NO"));
				reply.setBlog_reply_content(rs.getString("BLOG_REPLY_CONTENT"));
				reply.setBlog_reply_crt_date(rs.getDate("BLOG_REPLY_CRT_DATE"));
				reply.setBlog_reply_mdf_date(rs.getDate("BLOG_REPLY_MDF_DATE"));
				reply.setBlog_no(rs.getInt("BLOG_NO"));
				reply.setUser_no(rs.getInt("USER_NO"));
				reply.setBlog_reply_status(rs.getString("BLOG_REPLY_STATUS"));
				reply.setUser_id(rs.getString("USER_ID"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return reply;
		
	}

}
