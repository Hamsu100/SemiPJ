package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.service.UserService;
import com.kh.member.model.vo.User;

@WebServlet(name = "enroll", urlPatterns = "/user/signup")
public class UserEnrollServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService us = new UserService();

	@Override
	public String getServletName() {
		return "UserEnrollServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath() + "/views/user/signup.jsp");
	}
// get , post
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String userId = req.getParameter("User_Email");
			System.out.println("패스워드 전");
			String userPw = req.getParameter("userPw");
			System.out.println("패스워드 후");
			String userName = req.getParameter("User_Name");
			String userPhone = req.getParameter("User_Phone");
			
			User u = new User();

			u.setUser_id(userId);
			u.setUser_pw(userPw);
			u.setUser_name(userName);
			u.setUser_phone(userPhone);

			int result = us.join(u);

			if (result > 0) {
				sendCommonPage("회원 가입에 성공 하셨습니다.", "/index", req, resp);
			} else {
				sendCommonPage("회원 가입에 실패했습니다. DB 오류", "/views/user/signup.jsp", req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("회원 가입에 실패했습니다. 알 수 없는 오류", "/views/user/signup.jsp", req, resp);
		}

	}

}
