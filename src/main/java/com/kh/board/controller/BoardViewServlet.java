package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;

@WebServlet("/board/view")
public class BoardViewServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardViewServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));

		Board board = bs.viewBoard(boardNo);
		if (board == null) {
			resp.sendRedirect(req.getContextPath() + "/");
			return;
		}
		
		req.setAttribute("board", board);
		req.setAttribute("boardCat", req.getParameter("boardCat"));
		req.getRequestDispatcher("/views/board/view.jsp").forward(req, resp);
	}
}
