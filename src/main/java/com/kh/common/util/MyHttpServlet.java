package com.kh.common.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.User;

public abstract class MyHttpServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	abstract public String getServletName();

	public User getSessionUser(HttpServletRequest req) {
		try {
			HttpSession session = req.getSession();
			User loginUser = (User) session.getAttribute("loginUser");
			return loginUser;
		} catch (Exception e) {
		}
		return null;
	}

	public void sendCommonPage(String msg, String path, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("path", path);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

	public void sendCommonPage(String msg, String path, String script, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setAttribute("msg", msg);
		req.setAttribute("path", path);
		req.setAttribute("script", script);
		req.getRequestDispatcher("/views/common/msg.jsp").forward(req, resp);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Call name : " + getServletName() + ", method : " + req.getMethod());
		super.service(req, resp);
	}

	public void deleteFile(String fileName) {
		if (fileName == null || fileName.length() <= 0) {
			return;
		}
		try {
			String path = getServletContext().getRealPath("resources/upload/board");
			File file = new File(path, fileName);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
