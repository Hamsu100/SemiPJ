package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.service.UserService;
import com.kh.member.model.vo.User;

@WebServlet(name="login", urlPatterns="/user/login")
public class LoginServlet extends MyHttpServlet {

	private UserService us = new UserService();

	private static final long serialVersionUID = 1L;

	@Override
	public String getServletName() {
		return "LoginServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/views/user/login.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String userId = req.getParameter("loginUsername");
			String userPw = req.getParameter("userPw");
			System.out.println(userId + ", " + userPw);
			User loginUser = us.login(userId, userPw);
			if (loginUser != null) {
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", loginUser); // login data save in session
				System.out.println(session.toString());
				resp.sendRedirect(req.getContextPath() + "/");
			} else {
				sendCommonPage("ID/PW 확인해 주세요.", "/", req, resp);
			}

	}

}
