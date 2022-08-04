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

@WebServlet("/beach/pop")
public class BeachPopListServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BeachService bs = new BeachService();

	@Override
	public String getServletName() {
		return "BeachPopListServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] locNameArray = { "전국", "동해", "서해", "남해", "제주" };
		StringBuffer img = new StringBuffer();
		String locName = "전국";
		for (int i = 0; i < locNameArray.length; i++) {
			List<Beach> tempList = bs.getPopBchList(locNameArray[i]);
			img.append(tempList.get(0).getBEACH_IMG().split(",")[0]+", ");
		}
		if (req.getParameter("locName") != null && req.getParameter("locName").length() > 0) {
			locName = req.getParameter("locName");
		}
		List<Beach> bpList = bs.getPopBchList(locName);
		req.setAttribute("bpList", bpList);
		req.setAttribute("img", img.toString());
		req.getRequestDispatcher("/views/beach/poplist.jsp").forward(req, resp);

	}

}
