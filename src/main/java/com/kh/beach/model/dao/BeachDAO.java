package com.kh.beach.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kh.beach.model.vo.BchReply;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.PageInfo;

public class BeachDAO {

	// 1. 인기 - 카테고리별로 나눠야됨 전국 / 제주 / 서해 / 남해 / 동해 10개 가져오기
	// 비치 리스트 - 즐겨찾기 기준 상위 열개
	// 2. 해수욕장 리스트 - 지역별, 키워드
	// 비치 리스트 - 6개 씩 페이징
	// 3. 해수욕장 상세 보기
	// 해수욕장 - 전체 데이터 전부다
	// 4. 댓글
	// 댓글 조회 작성 수정 삭제
	// 5. 즐겨찾기
	// 즐겨찾기 삽입 삭제 조회

	// 인기 지역
	public List<Beach> getPopBch(Connection conn, String locName) { // 필요한 정보
		List<Beach> bList = new ArrayList<>();
		String sql = "select * from " + "(select b.beach_code, b.beach_name, " + "b.beach_content, "
				+ "b.beach_address, l.location_name, b.beach_img, b.BEACH_FAVOR_CNT, rank() over(order by b.BEACH_FAVOR_CNT desc) rank "
				+ "from beach b, area a, location l " + "where b.area_code = a.area_code "
				+ "and a.location_no = l.location_no ";
		if (locName.equals("전국") == false) {
			sql += "and l.location_name = ? ";
		}
		sql += ") where rank <=10 ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (locName.equals("전국") == false) {
				pstmt.setString(1, locName);
			}
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Beach b = new Beach();

				b.setBEACH_CODE(rs.getString("beach_code"));
				b.setBEACH_NAME(rs.getString("beach_name"));
				b.setBEACH_ADDRESS(rs.getString("beach_address"));
				b.setBEACH_CONTENT(rs.getString("beach_content"));
				b.setBEACH_IMG(rs.getString("beach_img"));
				b.setBEACH_FAVOR_CNT(rs.getString("BEACH_FAVOR_CNT"));

				bList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return bList;
	}

	public int getBeachCnt(Connection conn, Map<String, String> searchMap) {
		int result = 0;
		String sql = "select count(*) from " + "(select b.beach_code, b.beach_name, b.beach_content, "
				+ "b.beach_address, b.beach_img, rownum rnum " + "from beach b, location l, area a "
				+ "where b.area_code = a.area_code " + "and a.location_no = l.location_no ";

		if (searchMap.containsKey("searchValue")) {
			sql += "and b.beach_name like ? ";
		}

		if (!searchMap.get("locName").equals("전국")) {
			sql += "and l.location_name = ? ";
		}

		sql += ")";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if (searchMap.containsKey("searchValue")) {
				pstmt.setString(index++, "%" + searchMap.get("searchValue") + "%");
			}
			if (!searchMap.get("locName").equals("전국")) {
				pstmt.setString(index++, searchMap.get("locName"));
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

	// 비치 목록
	public List<Beach> getBeachList(Connection conn, PageInfo pageInfo, Map<String, String> searchMap) {
		// searchValue
		// locName
		List<Beach> bList = new ArrayList<>();

		String sql = "select * from " + "(select b.beach_code, b.beach_name, b.beach_content, "
				+ "b.beach_address, b.beach_img, b.BEACH_LONGITUDE, b.BEACH_LATITUDE, rownum rnum "
				+ "from beach b, location l, area a " + "where b.area_code = a.area_code "
				+ "and a.location_no = l.location_no ";

		if (searchMap.containsKey("searchValue")) {
			sql += "and b.beach_name like ? ";
		}

		if (!searchMap.get("locName").equals("전국")) {
			sql += "and l.location_name = ? ";
		}

		sql += ") where rnum between ? and ? ";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			if (searchMap.containsKey("searchValue")) {
				pstmt.setString(index++, "%" + searchMap.get("searchValue") + "%");
			}
			if (!searchMap.get("locName").equals("전국")) {
				pstmt.setString(index++, searchMap.get("locName"));
			}

			pstmt.setInt(index++, pageInfo.getStartList());
			pstmt.setInt(index++, pageInfo.getEndList());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Beach b = new Beach();
//				BEACH_CODE
//				BEACH_NAME
//				BEACH_IMG
//				BEACH_CONTENT
//				BEACH_LONGITUDE
//				BEACH_LATITUDE
//				BEACH_ADDRESS
//				BEACH_LEN
//				BEACH_WID
//				BEACH_PROP
//				BEACH_FAVOR_CNT
//				AREA_CODE
				b.setBEACH_CODE(rs.getString("BEACH_CODE"));
				b.setBEACH_NAME(rs.getString("BEACH_NAME"));
				b.setBEACH_CONTENT(rs.getString("BEACH_CONTENT"));
				b.setBEACH_ADDRESS(rs.getString("BEACH_ADDRESS"));
				b.setBEACH_IMG(rs.getString("BEACH_IMG"));
				b.setBEACH_LONGITUDE(rs.getString("BEACH_LONGITUDE"));
				b.setBEACH_LATITUDE(rs.getString("BEACH_LATITUDE"));

				bList.add(b);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return bList;
	}

	// 상세보기
	public Beach getBeach(Connection conn, String bchCode) {
		Beach b = new Beach();
//		BEACH_CODE
//		BEACH_NAME
//		BEACH_IMG

//		BEACH_CONTENT
//		BEACH_LONGITUDE
//		BEACH_LATITUDE

//		BEACH_ADDRESS
//		BEACH_LEN
//		BEACH_WID

//		BEACH_PROP
//		BEACH_FAVOR_CNT
//		AREA_CODE
		String sql = "select * from beach b, area a where b.area_code = a.area_code and b.beach_code = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bchCode);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				b.setBEACH_CODE(rs.getString("BEACH_CODE"));
				b.setBEACH_NAME(rs.getString("BEACH_NAME"));
				b.setBEACH_IMG(rs.getString("BEACH_IMG"));
				b.setBEACH_CONTENT(rs.getString("BEACH_CONTENT"));
				b.setBEACH_LONGITUDE(rs.getString("BEACH_LONGITUDE"));
				b.setBEACH_LATITUDE(rs.getString("BEACH_LATITUDE"));
				b.setBEACH_ADDRESS(rs.getString("BEACH_ADDRESS"));
				b.setBEACH_LEN(rs.getString("BEACH_LEN"));
				b.setBEACH_WID(rs.getString("BEACH_WID"));
				b.setBEACH_PROP(rs.getString("BEACH_PROP"));
				b.setBEACH_FAVOR_CNT(rs.getString("BEACH_FAVOR_CNT"));
				b.setBch_replyList(this.searchBchReply(conn, bchCode));
				b.setAREA_CODE(rs.getString("AREA_CODE"));
				b.setArea_name(rs.getString("area_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return b;
	}

	// 댓글 보기
	private List<BchReply> searchBchReply(Connection conn, String beach_CODE) {
		List<BchReply> rList = new ArrayList<BchReply>();
		String sql = "select * from bch_review br, tbl_user u where u.user_no = br.user_no and BCH_REVIEW_STATUS = 'Y' and br.BEACH_CODE = ?";
		PreparedStatement pstmt = null;

		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, beach_CODE);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BchReply br = new BchReply();

				br.setBCH_REVIEW_CONTENT(rs.getString("BCH_REVIEW_CONTENT"));
				br.setBCH_REVIEW_CRT_DATE(rs.getDate("BCH_REVIEW_CRT_DATE"));
				br.setBCH_REVIEW_MDF_DATE(rs.getDate("BCH_REVIEW_MDF_DATE"));
				br.setBCH_REVIEW_NO(rs.getString("BCH_REVIEW_NO"));
				br.setBCH_REVIEW_STATUS(rs.getString("BCH_REVIEW_STATUS"));
				br.setBEACH_CODE(beach_CODE);
				br.setUSER_NO(rs.getString("USER_NO"));
				br.setWriter(rs.getString("USER_ID"));

				rList.add(br);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return rList;
	}

	// 댓글 작성
	public int insertBchReply(Connection conn, BchReply r) {
		int result = 0;
		String sql = "insert into BCH_REVIEW values (SEQ_BCH_REVIEW_NO.nextval, ?, ?, ?, default, default, default)";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getBCH_REVIEW_CONTENT());
			pstmt.setString(2, r.getBEACH_CODE());
			pstmt.setString(3, r.getUSER_NO());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	// 댓글 수정
	public int updateBchReply(Connection conn, BchReply r) {
		int result = 0;
		String sql = "update bch_review set BCH_REVIEW_CONTENT = ?, BCH_REVIEW_MDF_DATE = sysdate where BCH_REVIEW_NO = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getBCH_REVIEW_CONTENT());
			pstmt.setString(2, r.getBCH_REVIEW_NO());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	// 댓글 삭제
	public int deleteBchReply(Connection conn, BchReply r) {
		int result = 0;
		String sql = "update bch_review set BCH_REVIEW_STATUS = 'N' where BCH_REVIEW_NO = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getBCH_REVIEW_NO());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public BchReply searchReply(Connection conn, String rNo) {
		BchReply r = new BchReply();

		String sql = "select * from bch_review where BCH_REVIEW_STATUS = 'Y' and BCH_REVIEW_NO = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rNo);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				r.setBCH_REVIEW_CONTENT(rs.getString("BCH_REVIEW_CONTENT"));
				r.setBCH_REVIEW_CRT_DATE(rs.getDate("BCH_REVIEW_CRT_DATE"));
				r.setBCH_REVIEW_MDF_DATE(rs.getDate("BCH_REVIEW_MDF_DATE"));
				r.setBCH_REVIEW_NO(rs.getString("BCH_REVIEW_NO"));
				r.setBCH_REVIEW_STATUS(rs.getString("BCH_REVIEW_STATUS"));
				r.setBEACH_CODE(rs.getString("BEACH_CODE"));
				r.setUSER_NO(rs.getString("USER_NO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}

		return r;
	}

	public int addFavor(Connection conn, String beachCode, String userNo) {
		int result = 0;
		String sql = "insert into BCH_FAVORITE values (?, ?)";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, beachCode);
			pstmt.setString(2, userNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int addFavorCnt(Connection conn, Beach b, int addCnt) {
		int result = 0;
		String sql = "update beach set BEACH_FAVOR_CNT = ? where BEACH_CODE = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println(b.getBEACH_FAVOR_CNT());
			pstmt.setInt(1, Integer.parseInt(b.getBEACH_FAVOR_CNT()) + addCnt);
			pstmt.setString(2, b.getBEACH_CODE());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public List<String> searchFavor(Connection conn, int userNo) {
		List<String> list = new ArrayList<>();
//		BEACH_CODE
//		USER_NO
		String sql = "select * from BCH_FAVORITE where USER_NO = ?";
		PreparedStatement pstmt = null;

		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("BEACH_CODE"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int delFavor(Connection conn, String beachCode, String userNo) {
		int result = 0;
		String sql = "delete from BCH_FAVORITE where BEACH_CODE = ? and USER_NO = ?";
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, beachCode);
			pstmt.setString(2, userNo);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

}
