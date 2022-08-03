package com.kh.beach.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.util.MyHttpServlet;

@WebServlet("/popajax")
public class BeachPopAJAXServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public String getServletName() {
		return "BeachPopAJAXServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

}
