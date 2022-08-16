package com.kh.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.blog.model.service.BlogService;
import com.kh.blog.model.vo.Blog;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;

@WebServlet("/blog/delete")
public class BlogWrDeleteServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogService bs = new BlogService();

	@Override
	public String getServletName() {
		return "BlogWrDeleteServlet";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int blog_no = Integer.parseInt(req.getParameter("bgno"));
			
			User loginUser = getSessionUser(req);
			Blog blog = bs.selectBlog(blog_no);
			if (blog.getUser_id().equals(loginUser.getUser_id()) == false) {
				sendCommonPage("비정상적인 접근입니다.", "/blog/list/new", req, resp);
				return;
			}
			
			int result = bs.deleteBlog(blog_no);
			if (result > 0) {
				sendCommonPage("삭제에 성공하셨습니다.", "/blog/list/my?uno=" + loginUser.getUser_no(), req, resp);
			} else {
				sendCommonPage("삭제에 실패하셨습니다.", "/blog/list/new", req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("잘못된 접근 입니다.", "/blog/list/new", req, resp);
		}
	}
}
