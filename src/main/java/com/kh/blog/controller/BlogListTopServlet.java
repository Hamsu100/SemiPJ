package com.kh.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.blog.model.service.BlogService;
import com.kh.blog.model.vo.Blog;



@WebServlet("/blog/list/top")
public class BlogListTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogService bs = new BlogService();
	
	@Override
	public String getServletName() {
		return "BlogListTopServlet";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Blog> blogListTop = null;
		
		blogListTop = bs.selectBlogTopByNew();
		
		req.setAttribute("blogListTop", blogListTop);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
