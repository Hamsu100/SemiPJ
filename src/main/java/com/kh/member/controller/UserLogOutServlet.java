package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.util.MyHttpServlet;

@WebServlet("/user/logout")
public class UserLogOutServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public String getServletName() {
		return "UserLogOutServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();

		if (session != null) {
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath() + "/index");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
