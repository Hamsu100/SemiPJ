package com.kh.board.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Reply;
import com.kh.common.util.PageInfo;

public class BoardDAO {

	// 1. 게시글 총 갯수
	// 2. 모든 게시글 검색
	// 3. 공지/자유/지역 카테고리 게시글 검색
	// 4. 게시물 검색 제목/내용/작성자
	// 5. 게시물 작성/수정/삭제
	// 6. 댓글 조회
	// 7. 댓글 작성/수정/삭제

	// 게시글 총 갯수
	public int BoardCnt(Connection conn) {
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(*) from board where board_status = 'Y'";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	public int BoardCnt(Connection conn, Map<String, String> searchMap) {
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(*) from board b, tbl_user u " + "where u.user_no = b.user_no "
				+ "and b.board_status = 'Y' ";

		String titleSql = "and b.board_title like ? ";
		String writerSql = "and u.user_id like ? ";
		String contentSql = "and b.board_content like ? ";
		String catSql = "and b.board_category = ? ";
		String cat2Sql = "and b.board_category in (3, 4, 5, 6) ";

		if (searchMap.containsKey("title")) {
			sql += titleSql;
		}
		if (searchMap.containsKey("writer")) {
			sql += writerSql;
		}
		if (searchMap.containsKey("content")) {
			sql += contentSql;
		}
		if (searchMap.containsKey("boardCat")) {
			sql += catSql;
		}
		if (searchMap.containsKey("boardCat2")) {
			sql += cat2Sql;
		}
		try {
			pstmt = conn.prepareStatement(sql);

			int index = 1;

			if (searchMap.containsKey("title")) {
				pstmt.setString(index++, "%" + searchMap.get("title") + "%");
			}
			if (searchMap.containsKey("writer")) {
				pstmt.setString(index++, "%" + searchMap.get("writer") + "%");
			}
			if (searchMap.containsKey("content")) {
				pstmt.setString(index++, "%" + searchMap.get("content") + "%");
			}
			if (searchMap.containsKey("boardCat")) {
				pstmt.setString(index++, searchMap.get("boardCat"));
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return result;
	}

	// 전체 List 출력
	public List<Board> searchBoard(Connection conn, PageInfo pageInfo) {

		List<Board> returnList = new ArrayList<>();
		String sql = "select * from (select BOARD_NO, BOARD_TITLE, BOARD_CONTENT, BOARD_CRT_DATE, "
				+ "BOARD_MDF_DATE, BOARD_STATUS, USER_NO, board_category, "
				+ "BOARD_ORIGINIMG, BOARD_RENAMEIMG, rownum as rnum, user_id from "
				+ "(select b.BOARD_NO, b.BOARD_TITLE, b.BOARD_CONTENT, b.board_category, "
				+ "b.BOARD_CRT_DATE, b.BOARD_MDF_DATE, b.BOARD_STATUS, "
				+ "b.USER_NO, b.BOARD_ORIGINIMG, b.BOARD_RENAMEIMG, u.user_id from board b, tbl_user u "
				+ "where BOARD_STATUS = 'Y' and u.user_no = b.user_no order by board_no desc)) "
				+ "where rnum between ? and ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pageInfo.getStartPage());
			pstmt.setInt(2, pageInfo.getEndPage());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board b = new Board();

				b.setBoard_content(rs.getString("BOARD_CONTENT"));
				b.setBoard_crt_date(rs.getDate("BOARD_CRT_DATE"));
				b.setBoard_mdf_date(rs.getDate("BOARD_MDF_DATE"));
				b.setBoard_no(rs.getInt("BOARD_NO"));
				b.setBoard_originimg(rs.getString("BOARD_ORIGINIMG"));
				b.setBoard_renameimg(rs.getString("BOARD_RENAMEIMG"));
				b.setBoard_status(rs.getString("BOARD_STATUS"));
				b.setBoard_title(rs.getString("BOARD_TITLE"));
				b.setUser_no(rs.getInt("USER_NO"));
				b.setBoard_writer(rs.getString("user_id"));
				b.setBoard_category(rs.getInt("board_category"));

				returnList.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return returnList;
	}

	// 카테고리 분류 (지역 / 공지 / 자게) + 검색 제목, 이름, 내용
	// 파라메터 받아 오는게 1. title / writer / content, 2. locationName, 3. categoryNo
	public List<Board> searchBoard(Connection conn, Map<String, String> searchMap, PageInfo pageInfo) {
		List<Board> returnList = new ArrayList<>();

		String sql = "select * from " + "(select board_no, board_title, board_category, board_content, board_crt_date, "
				+ "board_mdf_date, board_status, user_no, " + "user_id, rownum as rnum " + "from "
				+ "(select b.board_no, b.board_title, b.board_category, b.board_content, b.board_crt_date, "
				+ "b.board_mdf_date, b.board_status, b.user_no, " + "u.user_id " + "from board b, tbl_user u "
				+ "where u.user_no = b.user_no and b.board_status = 'Y' ";

		String titleSql = "and b.board_title like ? ";
		String writerSql = "and u.user_id like ? ";
		String contentSql = "and b.board_content like ? ";
		String catSql = "and b.board_category = ? ";
		String cat2Sql = "and b.board_category in (3, 4, 5, 6) ";

		if (searchMap.containsKey("title")) {
			sql += titleSql;
		}
		if (searchMap.containsKey("writer")) {
			sql += writerSql;
		}
		if (searchMap.containsKey("content")) {
			sql += contentSql;
		}
		if (searchMap.containsKey("boardCat")) {
			sql += catSql;
		}
		if (searchMap.containsKey("boardCat2")) {
			sql += cat2Sql;
		}
		sql += "order by b.board_no desc)) " + "where rnum between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = conn.prepareStatement(sql);

			int index = 1;

			if (searchMap.containsKey("title")) {
				pstmt.setString(index++, "%" + searchMap.get("title") + "%");
			}
			if (searchMap.containsKey("writer")) {
				pstmt.setString(index++, "%" + searchMap.get("writer") + "%");
			}
			if (searchMap.containsKey("content")) {
				pstmt.setString(index++, "%" + searchMap.get("content") + "%");
			}
			if (searchMap.containsKey("boardCat")) {
				pstmt.setString(index++, searchMap.get("boardCat"));
			}
			pstmt.setInt(index++, pageInfo.getStartList());
			pstmt.setInt(index++, pageInfo.getEndList());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board b = new Board();
				b.setBoard_content(rs.getString("BOARD_CONTENT"));
				b.setBoard_crt_date(rs.getDate("BOARD_CRT_DATE"));
				b.setBoard_mdf_date(rs.getDate("BOARD_MDF_DATE"));
				b.setBoard_no(rs.getInt("BOARD_NO"));
				b.setBoard_status(rs.getString("BOARD_STATUS"));
				b.setBoard_title(rs.getString("BOARD_TITLE"));
				b.setUser_no(rs.getInt("USER_NO"));
				b.setBoard_writer(rs.getString("user_id"));
				b.setBoard_category(rs.getInt("board_category"));
				returnList.add(b);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return returnList;
	}

	// 게시판 상세 보기
	public Board searchBoardOne(Connection conn, int boardNo) {
		Board board = new Board();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select b.board_no, b.board_title, b.board_category, b.board_content, b.board_crt_date, "
				+ "b.board_mdf_date, b.board_status, b.user_no, u.user_id " + "from board b, tbl_user u "
				+ "where b.user_no = u.user_no and b.board_status = 'Y' and b.board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setBoard_category(rs.getInt("board_category"));
				board.setBoard_content(rs.getString("board_content"));
				board.setBoard_crt_date(rs.getDate("board_crt_date"));
				board.setBoard_mdf_date(rs.getDate("board_mdf_date"));
				board.setBoard_no(rs.getInt("board_no"));
				board.setBoard_status(rs.getString("board_status"));
				board.setBoard_title(rs.getString("board_title"));
				board.setBoard_writer(rs.getString("user_id"));
				board.setBoard_reply(searchReplyBybNo(conn, boardNo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return board;
	}

	// 댓글 조회
	public List<Reply> searchReplyBybNo(Connection conn, int boardNo) {
		List<Reply> repList = new ArrayList<>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select br.board_reply_no, br.board_reply_content, br.board_reply_crt_date, "
				+ "br.board_reply_mdf_date, br.board_reply_status, br.user_no, br.board_no, u.user_id "
				+ "from board_reply br, tbl_user u "
				+ "where u.user_no = br.user_no and board_reply_status = 'Y' and br.board_no = ? order by br.board_reply_no desc";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Reply r = new Reply();

				r.setBoard_no(rs.getInt("board_no"));
				r.setBoard_reply_content(rs.getString("board_reply_content"));
				r.setBoard_reply_crt_date(rs.getDate("board_reply_crt_date"));
				r.setBoard_reply_mdf_date(rs.getDate("board_reply_mdf_date"));
				r.setBoard_reply_no(rs.getInt("board_reply_no"));
				r.setBoard_reply_status(rs.getString("board_reply_status"));
				r.setBoard_reply_writer(rs.getString("user_id"));
				r.setUser_no(rs.getInt("user_no"));

				repList.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return repList;
	}

	// 게시글 작성
	public int insertBoard(Connection conn, Board board) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "insert into board values(seq_board_no.nextval, ?, ?, ?, default, default, default, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getBoard_title()); // ok
			pstmt.setInt(2, board.getBoard_category()); // ok
			pstmt.setString(3, board.getBoard_content()); // ok
			pstmt.setInt(4, board.getUser_no()); // loginUser ok

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public Reply searchReplyByRNo(Connection conn, int rNo) {
		Reply r = new Reply();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select BOARD_REPLY_NO, BOARD_REPLY_CONTENT, BOARD_REPLY_CRT_DATE, "
				+ "BOARD_REPLY_MDF_DATE, BOARD_REPLY_STATUS, USER_ID, USER_NO, BOARD_NO "
				+ "from "
				+ "board_reply where board_reply_status = 'Y' and board_reply_no = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				r.setBoard_no(rs.getInt("BOARD_NO"));
				r.setBoard_reply_content(rs.getString("BOARD_REPLY_CONTENT"));
				r.setBoard_reply_crt_date(rs.getDate("BOARD_REPLY_CRT_DATE"));
				r.setBoard_reply_no(rNo);
				r.setBoard_reply_mdf_date(rs.getDate("BOARD_REPLY_MDF_DATE"));
				r.setBoard_reply_status(rs.getString("BOARD_REPLY_STATUS"));
				r.setBoard_reply_writer(rs.getString("USER_ID"));
				r.setUser_no(rs.getInt("USER_NO"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return r;
	}

	// 게시글 수정
	public int updateBoard(Connection conn, Board board) {
		int result = 0;

		PreparedStatement pstmt = null;
		// 변경 되는 내용 - 제목, 내용, 사진, 수정일
		String sql = "update board set board_title = ?, board_content = ?, BOARD_CATEGORY = ?, board_mdf_date = sysdate where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, board.getBoard_title());
			pstmt.setString(2, board.getBoard_content());
			pstmt.setInt(3, board.getBoard_category());
			pstmt.setInt(4, board.getBoard_no());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 게시글 삭제
	public int updateBoardState(Connection conn, Board board) {
		int result = 0;

		PreparedStatement pstmt = null;

		String sql = "update board set board_status = 'N' where board_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getBoard_no());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 댓글 작성
	public int insertReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;

		String sql = "insert into board_reply values (seq_board_reply_no.nextval, ?, default, default, default, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getBoard_reply_content());
			pstmt.setString(2, reply.getBoard_reply_writer());
			pstmt.setInt(3, reply.getUser_no());
			pstmt.setInt(4, reply.getBoard_no());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 댓글 수정
	public int updateReply(Connection conn, Reply reply) {
		int result = 0;
		PreparedStatement pstmt = null;
		// 수정 항목 내용, 수정일
		String sql = "update BOARD_REPLY set BOARD_REPLY_CONTENT = ?, BOARD_REPLY_MDF_DATE = sysdate where BOARD_REPLY_NO = ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, reply.getBoard_reply_content());
			pstmt.setInt(2, reply.getBoard_reply_no());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 댓글 삭제
	public int updateReplyStatus(Connection conn, int replyNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		// 수정 항목 내용, 수정일
		String sql = "update board_reply set board_reply_status = ? where board_reply_no = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "N");
			pstmt.setInt(2, replyNo);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}
