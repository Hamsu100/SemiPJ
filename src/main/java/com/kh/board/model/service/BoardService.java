package com.kh.board.model.service;

import static com.kh.common.jdbc.JDBCTemplate.commit;
import static com.kh.common.jdbc.JDBCTemplate.getConnection;
import static com.kh.common.jdbc.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.board.model.dao.BoardDAO;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Reply;
import com.kh.common.util.PageInfo;

public class BoardService {

	private BoardDAO dao = new BoardDAO();
	private Connection conn = null;

	public BoardService() {
		super();
		conn = getConnection();
	}

	// 1. 게시글 총 갯수
	// 2. 모든 게시글 검색
	// 3. 공지/자유/지역 카테고리 게시글 검색
	// 4. 게시물 검색 제목/내용/작성자
	// 5. 게시물 작성/수정/삭제
	// 6. 댓글 조회
	// 7. 댓글 작성/수정/삭제

	// 1. 게시물 갯수
	public int boardCnt(Map<String, String> searchMap) {
		return dao.BoardCnt(conn, searchMap);
	}
	
	public int boardCnt() {
		return dao.BoardCnt(conn);
	}

	// 2. 총 게시물 리스트 / 카테고리 / 검색
	public List<Board> searchBoard(PageInfo pageInfo, Map<String, String> searchMap) {
		return dao.searchBoard(conn, searchMap, pageInfo);
	}

	public List<Board> searchBoard(PageInfo pageInfo) {
		return dao.searchBoard(conn, pageInfo);
	}

	// 3. 게시물 작성
	public int writeBoard(Board board) {
		int result = dao.insertBoard(conn, board);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 4. 게시물 수정
	public int modifyBoard(Board board) {
		int result = dao.updateBoard(conn, board);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 5. 게시물 삭제
	public int deleteBoard(Board board) {
		int result = dao.updateBoardState(conn, board);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 6. 댓글 작성
	public int writeReply(Reply reply) {
		int result = dao.insertReply(conn, reply);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 7. 댓글 수정
	public int modifyReply(Reply reply) {
		int result = dao.updateReply(conn, reply);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 8. 댓글 삭제
	public int deleteReply(int reply_no) {
		int result = dao.updateReplyStatus(conn, reply_no);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	// 9. 게시물 상세 보기
	public Board viewBoard(int boardNo) {
		Board b = new Board();

		b = dao.searchBoardOne(conn, boardNo);

		return b;
	}
}
