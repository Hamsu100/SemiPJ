package com.kh.beach.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;
import com.kh.realtime.BeachIndex;

@WebServlet("/beach/view")
public class BeachViewServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BeachService bs = new BeachService();
	private BeachIndex bi = new BeachIndex();
	private CampService cs = new CampService();
	private StayService ss = new StayService();
	private LeiService ls = new LeiService();
	private CafeService cfs = new CafeService();
	private ResService rs = new ResService();
	private ParkService ps = new ParkService();

	@Override
	public String getServletName() {
		return "BeachViewServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String beachCode = req.getParameter("beachCode");
		String beachIndex = bi.beachIndex(beachCode);
		User loginUser = getSessionUser(req);
		List<String> favor = new ArrayList<>();
		if (loginUser != null) {
			favor = bs.searchFavor(loginUser.getUser_no());
		}
		Beach b = bs.getBeach(beachCode);
		req.setAttribute("favor", favor);
		req.setAttribute("beach", b);
		req.setAttribute("bi", beachIndex);

		String bch_lat = b.getBEACH_LATITUDE();
		String bch_lng = b.getBEACH_LONGITUDE();
		String radius = "10";
		List<Camp> campTopList = null;
		List<Stay> stayTopList = null;
		List<Lei> leiTopList = null;
		List<Cafe> cafeTopList = null;
		List<Res> resTopList = null;
		List<Park> parkTopList = null;

		campTopList = cs.selectTopByDistance(bch_lat, bch_lng, radius);
		stayTopList = ss.selectTopByDistance(bch_lat, bch_lng, radius);
		leiTopList = ls.selectTopByDistance(bch_lat, bch_lng, radius);
		cafeTopList = cfs.selectTopByDistance(bch_lat, bch_lng, radius);
		resTopList = rs.selectTopByDistance(bch_lat, bch_lng, radius);
		parkTopList = ps.selectTopByDistance(bch_lat, bch_lng, radius);

		req.setAttribute("lat", bch_lat);
		req.setAttribute("lng", bch_lng);

		req.setAttribute("campTopList", campTopList);
		req.setAttribute("stayTopList", stayTopList);
		req.setAttribute("leiTopList", leiTopList);
		req.setAttribute("cafeTopList", cafeTopList);
		req.setAttribute("resTopList", resTopList);
		req.setAttribute("parkTopList", parkTopList);

		req.getRequestDispatcher("/views/beach/view.jsp").forward(req, resp);
	}

}
