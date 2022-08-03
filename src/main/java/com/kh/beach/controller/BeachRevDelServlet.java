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

@WebServlet("/beach/revdel")
public class BeachRevDelServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;
	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachRevDelServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String rNo = req.getParameter("replyNo");
		BchReply r = bs.searchReview(rNo);
		String beachCode = req.getParameter("beachCode");
		User loginUser = getSessionUser(req);

		if (loginUser != null && loginUser.getUser_id().equals(r.getWriter())) {
			sendCommonPage("세션이 만료되었습니다.", "/beach/view?beachCode=" + beachCode, req, resp);
		}

		int result = bs.delReview(r);

		if (result > 0) {
			sendCommonPage("리뷰 삭제에 성공하셨습니다", "/beach/view?beachCode=" + beachCode, req, resp);

		} else {
			sendCommonPage("리뷰 삭제에 실패하셨습니다.", "/beach/view?beachCode=" + beachCode, req, resp);
		}
	}
}