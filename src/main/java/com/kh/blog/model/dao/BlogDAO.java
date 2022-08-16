package com.kh.blog.model.dao;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.kh.blog.model.service.ReplyService;
import com.kh.blog.model.vo.Blog;
import com.kh.common.util.PageInfo;

public class BlogDAO {
	
	// 1. 전체 최신순 탑4
	public List<Blog> selectBlogTopByNew(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Blog> list = new ArrayList<Blog>();
		
		String sql = "SELECT * FROM "
				+ "(SELECT B.BLOG_NO, B.BLOG_CONTENT, B.BLOG_SUBCONTENT, B.USER_NO ,U.USER_ID, B.BLOG_CRT_DATE, B.BLOG_MDF_DATE, B.AREA_CODE, A.AREA_NAME, B.BLOG_ORIGINIMG, B.BLOG_RENAMEIMG "
				+ "FROM BLOG B, TBL_USER U, AREA A  WHERE B.USER_NO = U.USER_NO AND B.AREA_CODE = A.AREA_CODE AND B.BLOG_STATUS = 'Y' ORDER BY B.BLOG_NO DESC) "
				+ "WHERE ROWNUM < = 4";
		
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Blog blog = new Blog();
				ReplyService res = new ReplyService();
				
				int blog_no = rs.getInt("BLOG_NO");
				
				blog.setBlog_no(blog_no);
				blog.setBlog_content(rs.getString("BLOG_CONTENT"));
				blog.setBlog_subcontent(rs.getString("BLOG_SUBCONTENT"));
				blog.setUser_no(rs.getInt("USER_NO"));
				blog.setUser_id(rs.getString("USER_ID"));
				blog.setBlog_crt_date(rs.getDate("BLOG_CRT_DATE"));
				blog.setBlog_mdf_date(rs.getDate("BLOG_MDF_DATE"));
				blog.setArea_code(rs.getInt("AREA_CODE"));
				blog.setArea_name(rs.getString("AREA_NAME"));
				blog.setBlog_originimg(rs.getString("BLOG_ORIGINIMG"));
				blog.setBlog_renameimg(rs.getString("BLOG_RENAMEIMG"));
				blog.setBlog_reply(res.selectReplyAllByNew(blog_no));
				
				list.add(blog);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return list;
	}
	
	
	// 2a. 전체 최신순 리스트
	public List<Blog> selectBlogAllByNew(Connection conn, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Blog> list = new ArrayList<Blog>();
		
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM AS RNUM, BLOG_NO, BLOG_CONTENT, BLOG_SUBCONTENT, USER_NO, USER_ID, BLOG_CRT_DATE, BLOG_MDF_DATE, BLOG_STATUS, AREA_CODE, AREA_NAME, BLOG_ORIGINIMG, BLOG_RENAMEIMG FROM "
				+ "(SELECT B.BLOG_NO, B.BLOG_CONTENT, B.BLOG_SUBCONTENT, B.USER_NO, U.USER_ID, B.BLOG_CRT_DATE, B.BLOG_MDF_DATE, B.BLOG_STATUS, B.AREA_CODE, A.AREA_NAME, B.BLOG_ORIGINIMG, B.BLOG_RENAMEIMG "
				+ "FROM BLOG B, TBL_USER U, AREA A WHERE B.USER_NO = U.USER_NO AND B.AREA_CODE = A.AREA_CODE AND B.BLOG_STATUS = 'Y' ORDER BY B.BLOG_NO DESC)) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, pageInfo.getStartList());
			ps.setInt(2, pageInfo.getEndList());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Blog blog = new Blog();
				ReplyService res = new ReplyService();
				
				int blog_no = rs.getInt("BLOG_NO");
				
				blog.setBlog_no(blog_no);
				blog.setBlog_content(rs.getString("BLOG_CONTENT"));
				blog.setBlog_subcontent(rs.getString("BLOG_SUBCONTENT"));
				blog.setUser_no(rs.getInt("USER_NO"));
				blog.setUser_id(rs.getString("USER_ID"));
				blog.setBlog_crt_date(rs.getDate("BLOG_CRT_DATE"));
				blog.setBlog_mdf_date(rs.getDate("BLOG_MDF_DATE"));
				blog.setBlog_status(rs.getString("BLOG_STATUS"));
				blog.setArea_code(rs.getInt("AREA_CODE"));
				blog.setArea_name(rs.getString("AREA_NAME"));
				blog.setBlog_originimg(rs.getString("BLOG_ORIGINIMG"));
				blog.setBlog_renameimg(rs.getString("BLOG_RENAMEIMG"));
				blog.setBlog_reply(res.selectReplyAllByNew(blog_no));

				list.add(blog);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return list;
	}
	
	
	// 2b. 전체 최신순 리스트 수
	public int selectBlogAllCountByNew(Connection conn) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = "SELECT COUNT(*) "
				+ "FROM (SELECT * FROM BLOG B, TBL_USER U, AREA A "
				+ "WHERE B.USER_NO = U.USER_NO AND B.AREA_CODE = A.AREA_CODE AND B.BLOG_STATUS = 'Y' ORDER BY B.BLOG_NO DESC)";
		
		try {
			ps = conn.prepareStatement(sql);
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
	
	
	// 3a. 전체 지역별 최신순 리스트
	public List<Blog> selectBlogBylocation(Connection conn, int area_code, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Blog> list = new ArrayList<Blog>();
		
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM AS RNUM, BLOG_NO, BLOG_CONTENT, BLOG_SUBCONTENT, USER_NO, USER_ID, BLOG_CRT_DATE, BLOG_MDF_DATE, BLOG_STATUS, AREA_CODE, AREA_NAME, BLOG_ORIGINIMG, BLOG_RENAMEIMG FROM "
				+ "(SELECT B.BLOG_NO, B.BLOG_CONTENT, B.BLOG_SUBCONTENT, B.USER_NO, U.USER_ID, B.BLOG_CRT_DATE, B.BLOG_MDF_DATE, B.BLOG_STATUS, B.AREA_CODE, A.AREA_NAME, B.BLOG_ORIGINIMG, B.BLOG_RENAMEIMG "
				+ "FROM BLOG B, TBL_USER U, AREA A WHERE B.USER_NO = U.USER_NO AND B.AREA_CODE = A.AREA_CODE AND B.AREA_CODE = ? AND B.BLOG_STATUS = 'Y' ORDER BY B.BLOG_NO DESC)) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, area_code);
			ps.setInt(2, pageInfo.getStartList());
			ps.setInt(3, pageInfo.getEndList());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Blog blog = new Blog();
				ReplyService res = new ReplyService();
				
				int blog_no = rs.getInt("BLOG_NO");
				
				blog.setBlog_no(blog_no);
				blog.setBlog_content(rs.getString("BLOG_CONTENT"));
				blog.setBlog_subcontent(rs.getString("BLOG_SUBCONTENT"));
				blog.setUser_no(rs.getInt("USER_NO"));
				blog.setUser_id(rs.getString("USER_ID"));
				blog.setBlog_crt_date(rs.getDate("BLOG_CRT_DATE"));
				blog.setBlog_mdf_date(rs.getDate("BLOG_MDF_DATE"));
				blog.setBlog_status(rs.getString("BLOG_STATUS"));
				blog.setArea_code(rs.getInt("AREA_CODE"));
				blog.setArea_name(rs.getString("AREA_NAME"));
				blog.setBlog_originimg(rs.getString("BLOG_ORIGINIMG"));
				blog.setBlog_renameimg(rs.getString("BLOG_RENAMEIMG"));
				blog.setBlog_reply(res.selectReplyAllByNew(blog_no));		//<<<<

				list.add(blog);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return list;
	}
	
	
	// 3b. 전체 지역별 최신순 리스트 수
	public int selectBlogCountBylocation(Connection conn, int area_code) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM BLOG WHERE AREA_CODE = ? AND BLOG_STATUS = 'Y'";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, area_code);
			rs = ps.executeQuery();
			
			if (rs.next()) {	//<<<<
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
	
	
	// 4a. 내글 최신순 리스트
	public List<Blog> selectMyBlogAllByNew(Connection conn, int my_user_no, PageInfo pageInfo) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Blog> list = new ArrayList<Blog>();
		
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM AS RNUM, BLOG_NO, BLOG_CONTENT, BLOG_SUBCONTENT, USER_NO, USER_ID, BLOG_CRT_DATE, BLOG_MDF_DATE, BLOG_STATUS, AREA_CODE, AREA_NAME, BLOG_ORIGINIMG, BLOG_RENAMEIMG FROM "
				+ "(SELECT B.BLOG_NO, B.BLOG_CONTENT, B.BLOG_SUBCONTENT, B.USER_NO, U.USER_ID, B.BLOG_CRT_DATE, B.BLOG_MDF_DATE, B.BLOG_STATUS, B.AREA_CODE, A.AREA_NAME, B.BLOG_ORIGINIMG, B.BLOG_RENAMEIMG "
				+ "FROM BLOG B, TBL_USER U, AREA A WHERE B.USER_NO = U.USER_NO AND B.AREA_CODE = A.AREA_CODE AND B.BLOG_STATUS = 'Y' AND B.USER_NO = ? ORDER BY B.BLOG_NO DESC)) "
				+ "WHERE RNUM BETWEEN ? AND ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, my_user_no);
			ps.setInt(2, pageInfo.getStartList());
			ps.setInt(3, pageInfo.getEndList());
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				Blog blog = new Blog();
				ReplyService res = new ReplyService();
				
				int blog_no = rs.getInt("BLOG_NO");
				
				blog.setBlog_no(blog_no);
				blog.setBlog_content(rs.getString("BLOG_CONTENT"));
				blog.setBlog_subcontent(rs.getString("BLOG_SUBCONTENT"));
				blog.setUser_no(rs.getInt("USER_NO"));
				blog.setUser_id(rs.getString("USER_ID"));
				blog.setBlog_crt_date(rs.getDate("BLOG_CRT_DATE"));
				blog.setBlog_mdf_date(rs.getDate("BLOG_MDF_DATE"));
				blog.setBlog_status(rs.getString("BLOG_STATUS"));
				blog.setArea_code(rs.getInt("AREA_CODE"));
				blog.setArea_name(rs.getString("AREA_NAME"));
				blog.setBlog_originimg(rs.getString("BLOG_ORIGINIMG"));
				blog.setBlog_renameimg(rs.getString("BLOG_RENAMEIMG"));
				blog.setBlog_reply(res.selectReplyAllByNew(blog_no));

				list.add(blog);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return list;
	}
	
	
	// 4b. 내글 최신순 리스트 수
	public int selectMyBlogAllCountByNew(Connection conn, int my_user_no) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = "SELECT COUNT(*) FROM BLOG WHERE USER_NO = ? AND BLOG_STATUS = 'Y'";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, my_user_no);
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
	
	
	// 6. 블로그 작성
	public int insertBlog(Connection conn, Blog blog) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO BLOG VALUES(SEQ_BOARD_NO.NEXTVAL, ?, ?, DEFAULT, DEFAULT, DEFAULT, ?, ?, ?, ?)";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, blog.getBlog_content());
			ps.setString(2, blog.getBlog_subcontent());
			ps.setString(3, blog.getBlog_originimg());
			ps.setString(4, blog.getBlog_renameimg());
			ps.setInt(5, blog.getUser_no());
			ps.setInt(6, blog.getArea_code());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}
	
	
	// 7. 블로그 수정
	public int updateBlog(Connection conn, Blog blog) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BLOG SET BLOG_CONTENT = ?, BLOG_SUBCONTENT = ?, BLOG_MDF_DATE = SYSDATE, BLOG_ORIGINIMG = ?, BLOG_RENAMEIMG = ?, AREA_CODE = ? WHERE BLOG_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, blog.getBlog_content());
			ps.setString(2, blog.getBlog_subcontent());
			ps.setString(3, blog.getBlog_originimg());
			ps.setString(4, blog.getBlog_renameimg());
			ps.setInt(5, blog.getArea_code());
			ps.setInt(6, blog.getBlog_no());
			
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}
	
	
	// 8. 블로그 삭제
	public int deleteBlog(Connection conn, int blog_no) {
		int result = 0;
		PreparedStatement ps = null;
		
		String sql = "UPDATE BLOG SET BLOG_STATUS = 'N' WHERE BLOG_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog_no);
			result = ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return result;
	}
	
	
	// 9. 단일 상세보기
	public Blog selectBlog(Connection conn, int blog_no) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Blog blog = new Blog();
		
		String sql= "SELECT B.BLOG_NO, B.BLOG_CONTENT, B.BLOG_SUBCONTENT, B.USER_NO, U.USER_ID, B.BLOG_CRT_DATE, "
				+ "B.BLOG_MDF_DATE, B.BLOG_STATUS, B.AREA_CODE, A.AREA_NAME, B.BLOG_ORIGINIMG, B.BLOG_RENAMEIMG "
				+ "FROM BLOG B, TBL_USER U, AREA A WHERE B.USER_NO = U.USER_NO AND B.AREA_CODE = A.AREA_CODE "
				+ "AND B.BLOG_STATUS = 'Y' AND B.BLOG_NO = ?";
		
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, blog_no);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				blog.setBlog_no(rs.getInt("blog_no"));
				blog.setBlog_content(rs.getString("BLOG_CONTENT"));
				blog.setBlog_subcontent(rs.getString("BLOG_SUBCONTENT"));
				blog.setUser_no(rs.getInt("USER_NO"));
				blog.setUser_id(rs.getString("USER_ID"));
				blog.setBlog_crt_date(rs.getDate("BLOG_CRT_DATE"));
				blog.setBlog_mdf_date(rs.getDate("BLOG_MDF_DATE"));
				blog.setBlog_status(rs.getString("BLOG_STATUS"));
				blog.setArea_code(rs.getInt("AREA_CODE"));
				blog.setArea_name(rs.getString("AREA_NAME"));
				blog.setBlog_originimg(rs.getString("BLOG_ORIGINIMG"));
				blog.setBlog_renameimg(rs.getString("BLOG_RENAMEIMG"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return blog;
	}
	
}
