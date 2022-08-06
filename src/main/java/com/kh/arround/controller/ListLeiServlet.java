package com.kh.arround.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.arround.model.service.LeiService;
import com.kh.arround.model.vo.Lei;
import com.kh.common.util.PageInfo;

@WebServlet("/arround/list/lei")
public class ListLeiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LeiService ss = new LeiService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bch_lat = null;
		String bch_lng = null;
		String radius = "10";
		
		int page = 1;
		int listCnt = 0;
		PageInfo pageInfo = null;
		
		List<Lei> leiList = null;
		
		try {
			bch_lat = req.getParameter("lat");
			bch_lng = req.getParameter("lng");
			
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		listCnt = ss.selectAllCntByDistance(bch_lat, bch_lng, radius);
		
		pageInfo = new PageInfo(page, 5, listCnt, 4);
		
		leiList = ss.selectAllByDistance(bch_lat, bch_lng, radius, pageInfo);
		
		
		req.setAttribute("leiList", leiList);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/arround/listLei.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
