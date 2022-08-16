package com.kh.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.blog.model.service.ReplyService;
import com.kh.common.util.MyHttpServlet;

@WebServlet("/blog/reply/delete")
public class ReBlogDeleteServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyService res = new ReplyService(); 

	@Override
	public String getServletName() {
		return "ReBlogDeleteServlet";
	}

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int blog_reply_no = Integer.parseInt(req.getParameter("reno"));
			String user_id = req.getParameter("uid");		
			
			int result = res.deleteReply(blog_reply_no);
			
			if (result > 0) {
				sendCommonPage("댓글 삭제에 성공하셨습니다.", "/blog/list/new", req, resp);
			} else {
				sendCommonPage("댓글 삭제에 실패하셨습니다.", "/blog/list/new", req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("잘못된 접근 입니다.", "/blog/list/new", req, resp);
		}
		
	}
}
