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

@WebServlet("/blog/list/my")
public class BlogListMyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogService bs = new BlogService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int my_user_no = 0;
		
		int page = 1;
		int listCount = 0;
		PageInfo pageInfo = null;
		
		List<Blog> blogListMy = null;
		
		try {
			my_user_no = Integer.parseInt(req.getParameter("uno"));
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		listCount = bs.selectMyBlogAllCountByNew(my_user_no);
		pageInfo = new PageInfo(page, 5, listCount, 8);
		blogListMy = bs.selectMyBlogAllByNew(my_user_no, pageInfo);

		req.setAttribute("blogListMy", blogListMy);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/blog/listmy.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
