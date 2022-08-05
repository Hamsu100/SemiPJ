package com.kh.member.controller;

import javax.servlet.annotation.WebServlet;

import com.kh.common.util.MyHttpServlet;

@WebServlet("/user/data")
public class UserDataServlet extends MyHttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public String getServletName() {
		return "UserDataServlet";
	}
	
}
