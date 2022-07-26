package com.kh.index.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;
import com.kh.common.util.PageInfo;

@WebServlet("/index")
public class IndexServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "IndexServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int boardCnt = bs.boardCnt();
		PageInfo pageInfo = new PageInfo(1,5,boardCnt,1);
		List<Board> bList1 = bs.searchBoard(pageInfo);
		List<Board> bList2 = bs.searchBoard(pageInfo);
		List<Board> bList3 = bs.searchBoard(pageInfo);
		System.out.println("시작페이지");
		req.setAttribute("bList1", bList1);
		req.setAttribute("bList2", bList2);
		req.setAttribute("bList3", bList3);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}

}
