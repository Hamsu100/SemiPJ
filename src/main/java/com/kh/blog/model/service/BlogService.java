package com.kh.blog.model.service;

import static com.kh.common.jdbc.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.blog.model.dao.BlogDAO;
import com.kh.blog.model.vo.Blog;
import com.kh.common.util.PageInfo;

public class BlogService {
	
	private Connection conn = getConnection();
	private BlogDAO dao = new BlogDAO();
	
	
	// 1. 전체 최신순 탑3
	public List<Blog> selectBlogTopByNew() {
		return dao.selectBlogTopByNew(conn);
	}
	
	// 2a. 전체 최신순 리스트
	public List<Blog> selectBlogAllByNew(PageInfo pageInfo) {
		return dao.selectBlogAllByNew(conn, pageInfo);
	}
	
	// 2b. 전체 최신순 리스트 수
	public int selectBlogAllCountByNew() {
		return dao.selectBlogAllCountByNew(conn);
	}
	
	// 3a. 전체 지역별 최신순 리스트
	public List<Blog> selectBlogBylocation(int area_code, PageInfo pageInfo) {
		return dao.selectBlogBylocation(conn, area_code, pageInfo);
	}
	
	// 3b. 전체 지역별 최신순 리스트 수
	public int  selectBlogCountBylocation(int area_code) {
		return dao.selectBlogCountBylocation(conn, area_code);
	}
	
	// 4a. 내글 최신순 리스트
	public List<Blog> selectMyBlogAllByNew(int my_user_no, PageInfo pageInfo) {
		return dao.selectMyBlogAllByNew(conn, my_user_no, pageInfo);
	}
	
	// 4b. 내글 최신순 리스트 수
	public int selectMyBlogAllCountByNew(int my_user_no) {
		return dao.selectMyBlogAllCountByNew(conn, my_user_no);
	}
	
	// 6. 블로그 작성
	public int insertBlog(Blog blog) {
		int result = dao.insertBlog(conn, blog);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	// 7. 블로그 수정
	public int updateBlog(Blog blog) {
		int result = dao.updateBlog(conn, blog);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	// 8. 블로그 삭제
	public int deleteBlog(int blog_no) {
		int result = dao.deleteBlog(conn, blog_no);
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		return result;
	}
	
	// 9. 단일 상세보기
	public Blog selectBlog(int blog_no) {
		return dao.selectBlog(conn, blog_no);
	}
}
