package com.kh.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;
import com.kh.common.util.PageInfo;

@WebServlet("/board/list")
public class BoardMainListServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardMainListServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int boardCnt = 0;
		PageInfo pageInfo = null;
		List<Board> bList = null;
		Map<String, String> searchMap = new HashMap<>();
		// 파라메타 받을 거 = title, writer, content, locType, boardCat(제목, 작성자, 내용/지역/게시판 종류)
		// key value
		// searchType title, writer, content
		// searchValue keyword
		// boardCat 1,2,3,4,5,6 공지 자유 동서남해 제주
		String[] catName = {"공지","자유","동해","서해","남해","제주"};
		
		try {
			String searchValue = req.getParameter("searchValue");
			if (searchValue != null && searchValue.length() > 0) {
				String searchType = req.getParameter("searchType");
				searchMap.put(searchType, searchValue);
			}
			String boardCat = req.getParameter("boardCat");
			if (boardCat != null && boardCat.length() > 0) {
				if (!boardCat.equals("null")) {
					searchMap.put("boardCat", boardCat);
				}
			}
			System.out.println("searchMap : " + searchMap);

			page = Integer.parseInt(req.getParameter("page"));
		} catch (Exception e) {
		}
		boardCnt = bs.boardCnt(searchMap);
		
		pageInfo = new PageInfo(page, 10, boardCnt, 10);
		bList = bs.searchBoard(pageInfo, searchMap);
		req.setAttribute("catName", catName);
		req.setAttribute("bList", bList);
		req.setAttribute("pageInfo", pageInfo);
		req.getRequestDispatcher("/views/board/list.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
