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
import com.kh.common.util.PageInfo;

@WebServlet("/blog/list/loc")
public class BlogListLocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogService bs = new BlogService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int area_code = 0;
		
		int page = 1; 
		int listCount = 0;
		PageInfo pageInfo = null;
		
		List<Blog> blogListLoc = null;
		
		try {
			area_code = Integer.parseInt(req.getParameter("area"));
			
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		listCount = bs.selectBlogCountBylocation(area_code);
		pageInfo = new PageInfo(page, 5, listCount, 6);
		blogListLoc = bs.selectBlogBylocation(area_code, pageInfo);
		
		req.setAttribute("area", area_code);
		req.setAttribute("blogListLoc", blogListLoc);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/blog/listloc.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
