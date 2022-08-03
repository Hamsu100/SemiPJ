package com.kh.beach.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.beach.model.dao.BeachDAO;
import com.kh.beach.model.vo.BchReply;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.PageInfo;

public class BeachService {

	private BeachDAO dao = new BeachDAO();

	public Connection conn = null;

	public BeachService() {
		conn = getConnection();
	}

	// 1. 페이징 처리용 카운팅
	// 2. 보드 리스트
	// 3. 파퓰러 보드 리스트
	// 4. 보드 상세 보기
	// 5. 댓글 작성
	// 6. 댓글 수정
	// 7. 댓글 삭제
	// 8. 즐겨찾기 추가
	// 9. 즐겨찾기 삭제

	public int listCnt(Map<String, String> searchMap) {
		return dao.getBeachCnt(conn, searchMap);
	}

	public List<Beach> getBchList(Map<String, String> searchMap, PageInfo pageInfo) {
		return dao.getBeachList(conn, pageInfo, searchMap);
	}

	public List<Beach> getPopBchList(String locName) {
		return dao.getPopBch(conn, locName);
	}

	public Beach getBeach(String bchCode) {
		return dao.getBeach(conn, bchCode);
	}

	public int writeReview(BchReply r) {
		int result = dao.insertBchReply(conn, r);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int modifyReview(BchReply r) {
		int result = dao.updateBchReply(conn, r);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public BchReply searchReview(String rNo) {
		return dao.searchReply(conn, rNo);
	}

	public int delReview(BchReply r) {
		int result = dao.deleteBchReply(conn, r);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int addFavor(String bchCode, Beach b, String userNo) {
		int result = dao.addFavor(conn, bchCode, userNo);
		int result2 = dao.addFavorCnt(conn, b, 1);
		if (result > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public int delFavor(String bchCode, Beach b, String userNo) {
		int result = dao.delFavor(conn, bchCode, userNo);
		int result2 = dao.addFavorCnt(conn, b, 1);
		if (result > 0 && result2 > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}

	public List<String> searchFavor(int userNo) {
		return dao.searchFavor(conn, userNo);
	}
}
