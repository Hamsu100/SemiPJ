package com.kh.arround.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.arround.model.service.CafeService;
import com.kh.arround.model.service.CampService;
import com.kh.arround.model.service.LeiService;
import com.kh.arround.model.service.ResService;
import com.kh.arround.model.service.StayService;
import com.kh.arround.model.vo.Cafe;
import com.kh.arround.model.vo.Camp;
import com.kh.arround.model.vo.Lei;
import com.kh.arround.model.vo.Res;
import com.kh.arround.model.vo.Stay;

@WebServlet("/arround/view")
public class ViewServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private StayService ss = new StayService();
	private CampService cs = new CampService();
	private LeiService ls = new LeiService();
	private CafeService cfs = new CafeService();
	private ResService rs = new ResService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String lat = null;
		String lng = null;
		int no = 0;
		
		try {
			lat = req.getParameter("lat");
			lng = req.getParameter("lng");
			no = Integer.parseInt(req.getParameter("no"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Stay stayDetail = ss.selectOneWithBasic(no);
		Camp campDetail = cs.selectOneWithBasic(no);
		Lei leiDetail = ls.selectOneWithBasic(no);
		Cafe cafeDetail = cfs.selectOneWithBasic(no);
		Res resDetail = rs.selectOneWithBasic(no);
		
		req.setAttribute("stayDetail", stayDetail);
		req.setAttribute("campDetail", campDetail);
		req.setAttribute("leiDetail", leiDetail);
		req.setAttribute("cafeDetail", cafeDetail);
		req.setAttribute("resDetail", resDetail);
		req.getRequestDispatcher("/views/arround/view.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
