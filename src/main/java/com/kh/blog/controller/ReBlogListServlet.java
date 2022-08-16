package com.kh.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.blog.model.service.ReplyService;
import com.kh.blog.model.vo.Reply;

@WebServlet("/reblog/list")
public class ReBlogListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyService res = new ReplyService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int blog_no = 0;
		
		List<Reply> reBlogList = null;
		
		reBlogList = res.selectReplyAllByNew(blog_no);
		
		req.setAttribute("reBlogList", reBlogList);
		req.getRequestDispatcher("/view/blog/.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
