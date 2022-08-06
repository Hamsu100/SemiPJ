package com.kh.arround.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.arround.model.service.ParkService;
import com.kh.arround.model.vo.Park;
import com.kh.common.util.PageInfo;

@WebServlet("/arround/list/park")
public class ListParkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ParkService ps = new ParkService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bch_lat = null;
		String bch_lng = null;
		String radius = "5";
		
		int page = 1;
		int listCnt = 0;
		PageInfo pageInfo = null;
		
		List<Park> parkList = null;
		
		try {
			bch_lat = req.getParameter("lat");
			bch_lng = req.getParameter("lng");
			
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		listCnt = ps.selectAllCntByDistance(bch_lat, bch_lng, radius);
		
		pageInfo = new PageInfo(page, 5, listCnt, 4);
		
		parkList = ps.selectAllByDistance(bch_lat, bch_lng, radius, pageInfo);
		
		
		req.setAttribute("parkList", parkList);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/arround/listPark.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
