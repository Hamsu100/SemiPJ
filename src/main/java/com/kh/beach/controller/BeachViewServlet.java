package com.kh.beach.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;
import com.kh.realtime.BeachIndex;

@WebServlet("/beach/view")
public class BeachViewServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BeachService bs = new BeachService();
	private BeachIndex bi = new BeachIndex();

	@Override
	public String getServletName() {
		return "BeachViewServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String beachCode = req.getParameter("beachCode");
		String beachIndex = bi.beachIndex(beachCode);
		User loginUser = getSessionUser(req);
		List<String> favor = new ArrayList<>();
		if (loginUser != null) {
			favor = bs.searchFavor(loginUser.getUser_no());
		}
		Beach b = bs.getBeach(beachCode);
		req.setAttribute("favor", favor);
		req.setAttribute("beach", b);
		req.setAttribute("bi", beachIndex);
		req.getRequestDispatcher("/views/beach/view.jsp").forward(req, resp);
	}

}
