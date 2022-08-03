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

@WebServlet("/beach/revupdate")
public class BeachRevUpdateServlet extends MyHttpServlet{

	private static final long serialVersionUID = 1L;
	
	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachRevUpdateServlet";
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rNo = req.getParameter("rNo");
		String writer = req.getParameter("writer");
		String content = req.getParameter("modifycomment");
		String beachCode =req.getParameter("beachCode");
		
		User loginUser = getSessionUser(req);
		
		if (loginUser != null && loginUser.getUser_id().equals(writer)==false) {
			sendCommonPage("세션이 만료되었습니다.", "/beach/view?beachCode="+beachCode, req, resp);
			return;
		}
		BchReply r = bs.searchReview(rNo);
		
		r.setBCH_REVIEW_CONTENT(content);
		int result = bs.modifyReview(r);
		
		if (result >0) {
			sendCommonPage("리뷰를 수정하였습니다.", "/beach/view?beachCode="+beachCode, req, resp);
		}else {
			sendCommonPage("리뷰 수정에 실패하였습니다.", "/beach/view?beachCode="+beachCode, req, resp);
		}
		
	}
	
}
