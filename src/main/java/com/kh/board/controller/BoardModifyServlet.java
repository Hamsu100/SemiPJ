package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;

@WebServlet("/board/modify")
public class BoardModifyServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardModifyServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));

		Board board = bs.viewBoard(boardNo);
		req.setAttribute("board", board);
		req.setAttribute("boardCat", req.getParameter("boardCat"));
		req.getRequestDispatcher("/views/board/update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			User loginUser = getSessionUser(req);
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			String boardCat = req.getParameter("boardCat");

			
			Board b = bs.viewBoard(boardNo);

			if (loginUser != null && b.getBoard_writer().equals(loginUser.getUser_id()) == false) {
				sendCommonPage("접근 불가", "/board", req, resp);
				return;
			}
			b.setBoard_title(req.getParameter("title"));
			b.setBoard_content(req.getParameter("content"));
			b.setBoard_category(Integer.parseInt(boardCat));
			
			int result = bs.modifyBoard(b);

			if (result > 0) {
				sendCommonPage("게시글 수정 성공", "/board/view?boardNo=" + boardNo + "&boardCat=" + boardCat, req, resp);
			} else {
				sendCommonPage("게시글 수정 실패", "/board/view?boardNo=" + boardNo, req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("unKnown error", "/board", req, resp);
		}

	}
}
