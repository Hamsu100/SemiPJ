package com.kh.beach.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.BchReply;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;

@WebServlet("/beach/revwrite")
public class BeachReviewServlet extends MyHttpServlet{

	private static final long serialVersionUID = 1L;
	
	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachReviewServlet";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String writer=req.getParameter("writer");
		String comment = req.getParameter("review");
		String beachCode = req.getParameter("beachCode");
		
		
		User loginUser = getSessionUser(req);
		
		if (loginUser != null && loginUser.getUser_id().equals(writer)==false) {
			sendCommonPage("세션이 만료되었습니다.", "/beach/view?beachCode="+beachCode, req, resp);
		}
		BchReply r = new BchReply();
		
		r.setBEACH_CODE(beachCode);
		r.setUSER_NO(loginUser.getUser_no()+"");
		r.setBCH_REVIEW_CONTENT(comment);
		int result = bs.writeReview(r);
		
		if (result > 0 ) {
			sendCommonPage("댓글작성에 성공하셨습니다", "/beach/view?beachCode="+beachCode, req, resp);
		} else {
			sendCommonPage("댓글작성에 실패하셨습니다.", "/beach/view?beachCode="+beachCode, req, resp);
		}
	}
	
}
