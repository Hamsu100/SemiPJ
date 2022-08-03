package com.kh.beach.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;

@WebServlet("/beach/delfavor")
public class BeachDelFavorServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachAddFavorServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String beachCode = req.getParameter("beachCode");
		User loginUser = getSessionUser(req);

		if (loginUser == null) {
			sendCommonPage("세션이 만료 되었습니다.", "/beach/view?beachCode=" + beachCode, req, resp);
			return;
		}
		Beach b = bs.getBeach(beachCode);
		int result = bs.delFavor(beachCode,b, loginUser.getUser_no() + "");

		if (result > 0) {
			sendCommonPage("즐겨 찾기에 추가 되었습니다.", "/beach/view?beachCode=" + beachCode, req, resp);
		} else {
			sendCommonPage("즐겨 찾기 추가에 실패하였습니다.", "/beach/view?beachCode=" + beachCode, req, resp);
		}
	}
}