package com.kh.blog.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.blog.model.dao.ReplyDAO;
import com.kh.blog.model.vo.Reply;

public class ReplyService {
	
	private Connection conn = getConnection();
	private ReplyDAO dao = new ReplyDAO();
	
	
	// 1a. 특정 블로그 최신순 댓글 리스트
	public List<Reply> selectReplyAllByNew(int blog_no) {
		return dao.selectReplyAllByNew(conn, blog_no);
	}
	
	// 1b. 특정 블로그 최신순 댓글 리스트 수
	public int selectReplyAllCountByNew(int blog_no) {
		return dao.selectReplyAllCountByNew(conn, blog_no);
	}
	
	// 2. 댓글 작성
	public int insertReply(Reply reply) {
		int result = dao.insertReply(conn, reply);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	// 3. 댓글 삭제
	public int deleteReply(int blog_reply_no) {
		int result = dao.deleteReply(conn, blog_reply_no);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	// 4. 특정 댓글 조회
	public Reply searchReply(int blog_reply_no) {
		return dao.searchReply(conn, blog_reply_no);
	}

}
