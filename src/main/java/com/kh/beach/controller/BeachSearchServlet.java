package com.kh.beach.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.MyHttpServlet;

@WebServlet("/beach/search")
public class BeachSearchServlet extends MyHttpServlet{

	private static final long serialVersionUID = 1L;
	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachSearchServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Beach> bList = bs.getPopBchList("전체");
		
		req.setAttribute("bList", bList);
		
		req.getRequestDispatcher("/views/beach/search.jsp").forward(req, resp);
	}
}
