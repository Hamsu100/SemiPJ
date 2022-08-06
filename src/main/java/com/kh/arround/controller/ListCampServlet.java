package com.kh.arround.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.arround.model.service.CampService;
import com.kh.arround.model.vo.Camp;
import com.kh.common.util.PageInfo;

@WebServlet("/arround/list/camp")
public class ListCampServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CampService cs = new CampService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String bch_lat = null;
		String bch_lng = null;
		String radius = "10";

		int page = 1;
		int listCnt = 0;
		PageInfo pageInfo = null;

		List<Camp> campList = null;

		try {
			bch_lat = req.getParameter("lat");
			bch_lng = req.getParameter("lng");
			if (req.getParameter("page") != null) {
				page = Integer.parseInt(req.getParameter("page"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		listCnt = cs.selectAllCntByDistance(bch_lat, bch_lng, radius);

		pageInfo = new PageInfo(page, 5, listCnt, 4);

		campList = cs.selectAllByDistance(bch_lat, bch_lng, radius, pageInfo);

		req.setAttribute("campList", campList);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/arround/listCamp.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
