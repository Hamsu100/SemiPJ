package com.kh.arround.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.arround.model.service.CafeService;
import com.kh.arround.model.service.CampService;
import com.kh.arround.model.service.LeiService;
import com.kh.arround.model.service.ParkService;
import com.kh.arround.model.service.ResService;
import com.kh.arround.model.service.StayService;
import com.kh.arround.model.vo.Cafe;
import com.kh.arround.model.vo.Camp;
import com.kh.arround.model.vo.Lei;
import com.kh.arround.model.vo.Park;
import com.kh.arround.model.vo.Res;
import com.kh.arround.model.vo.Stay;


/**
 *	bch main info 하단 아코디언 부분 연결
 *	
 * 
 */
@WebServlet("/arround/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CampService cs = new CampService();
	private StayService ss = new StayService();
	private LeiService ls = new LeiService();
	private CafeService cfs = new CafeService();
	private ResService rs = new ResService();
	private ParkService ps = new ParkService();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String bch_lat = null;
		String bch_lng = null;
		String radius = "10";
		List<Camp> campTopList = null;
		List<Stay> stayTopList = null;
		List<Lei> leiTopList = null;
		List<Cafe> cafeTopList = null;
		List<Res> resTopList = null;
		List<Park> parkTopList = null;

		
		try {
			bch_lat = req.getParameter("lat");
			bch_lng = req.getParameter("lng");		
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		campTopList = cs.selectTopByDistance(bch_lat, bch_lng, radius);
		stayTopList = ss.selectTopByDistance(bch_lat, bch_lng, radius);
		leiTopList = ls.selectTopByDistance(bch_lat, bch_lng, radius);
		cafeTopList = cfs.selectTopByDistance(bch_lat, bch_lng, radius);
		resTopList = rs.selectTopByDistance(bch_lat, bch_lng, radius);
		parkTopList = ps.selectTopByDistance(bch_lat, bch_lng, radius);
		
		req.setAttribute("campTopList", campTopList);
		req.setAttribute("stayTopList", stayTopList);
		req.setAttribute("leiTopList", leiTopList);
		req.setAttribute("cafeTopList", cafeTopList);
		req.setAttribute("resTopList", resTopList);
		req.setAttribute("parkTopList", parkTopList);
		req.getRequestDispatcher("/views/arround/main.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	

}
