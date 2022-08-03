package com.kh.index.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;
import com.kh.common.util.PageInfo;

@WebServlet("/indexajax")
public class IndexAJAXServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "IndexAJAXServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");

		String boardCat = req.getParameter("category");

		HashMap<String, String> searchMap = new HashMap<String, String>();
		if (boardCat.equals("3456")) {
			searchMap.put("boardCat2", searchMap.getOrDefault("boardCat2", ""));
		} else {
			searchMap.put("boardCat", boardCat);
		}
		int boardCnt = bs.boardCnt(searchMap);
		PageInfo pageInfo = new PageInfo(1, 10, boardCnt, 10);
		List<Board> bList = bs.searchBoard(pageInfo, searchMap);
		resp.setContentType("application/json;charset=UTF-8");
		new GsonBuilder().create().toJson(bList, resp.getWriter());

	}

}
