package com.kh.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.blog.model.service.ReplyService;
import com.kh.blog.model.vo.Reply;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;

@WebServlet("/blog/reply/insert")
public class ReBlogInsertServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ReplyService res = new ReplyService();

	@Override
	public String getServletName() {
		return "ReBlogInsertServlet";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			User loginUser = getSessionUser(req);
			
			int blog_no = Integer.parseInt(req.getParameter("bgno"));
			String blog_reply_content = req.getParameter("con");
			
			Reply reply = new Reply();
			reply.setBlog_no(blog_no);
			reply.setBlog_reply_content(blog_reply_content);
			reply.setUser_no(loginUser.getUser_no());
			
			int result = res.insertReply(reply);
			
			
			if (result > 0) {
				sendCommonPage("등록 완료", "/blog/list/new", req, resp);			
			} else {
				sendCommonPage("DB 삽입 오류", "/blog/list/new", req, resp);		
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("오류", "/blog/list/new", req, resp);			
		}
	}
	
	

}
