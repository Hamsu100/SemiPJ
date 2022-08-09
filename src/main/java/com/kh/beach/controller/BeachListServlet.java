package com.kh.beach.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.common.util.MyHttpServlet;
import com.kh.common.util.PageInfo;
import com.kh.member.model.vo.User;

@WebServlet("/beach/list")
public class BeachListServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachListServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String> searchMap = new HashMap<>();
		int page = 1;
		int beachCnt = 0;
		PageInfo pageInfo = null;
		User loginUser = getSessionUser(req);
		List<String> favorList = new ArrayList<String>();
		if (loginUser != null) {
			favorList.addAll(bs.searchFavor(loginUser.getUser_no()));
		}
		try {
			String searchValue = req.getParameter("searchValue");

			String locName = req.getParameter("locName");
			if (searchValue != null && searchValue.length() > 0) {
				searchMap.put("searchValue", searchValue);
			}
			if (locName != null && locName.length() > 0) {
				searchMap.put("locName", locName);
			}
			String reqPage = req.getParameter("page");
			if (reqPage != null && reqPage.length() > 0) {
				page = Integer.parseInt(reqPage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		beachCnt = bs.listCnt(searchMap);
		pageInfo = new PageInfo(page, 10, beachCnt, 4);
		List<Beach> bchList = bs.getBchList(searchMap, pageInfo);
		double[] avgLatLng = avgCoordi(bchList);
		req.setAttribute("favorList", favorList);
		req.setAttribute("bchCnt", beachCnt+"");
		req.setAttribute("avgLatLng", avgLatLng);
		req.setAttribute("bchList", bchList);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/beach/list.jsp").forward(req, resp);
	}

	public double[] avgCoordi(List<Beach> bchList) {

		double lat = 0;
		double lng = 0;
		int index = 0;
		if (bchList != null && bchList.isEmpty() == false) {
			for (Beach b : bchList) {
				if (b.getBEACH_LATITUDE() != null && b.getBEACH_LONGITUDE() != null) {
					lat += Double.parseDouble(b.getBEACH_LATITUDE());
					lng += Double.parseDouble(b.getBEACH_LONGITUDE());
					index++;
				}
			}
		}
		if (index > 0) {
			lat = lat / index;
			lng = lng / index;
		}
		double[] returnArray = { lat, lng };
		return returnArray;
	}

}
