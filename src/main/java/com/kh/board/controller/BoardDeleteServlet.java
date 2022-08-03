package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardDeleteServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));

			Board b = bs.viewBoard(boardNo);

			int result = bs.deleteBoard(b);

			if (result > 0) {
				sendCommonPage("게시글 삭제에 성공하였습니다.", "/board/list", req, resp);
			}else {
				sendCommonPage("게시글 삭제에 실패하였습니다", "/board/list?boardNo="+boardNo, req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("DB 오류", "/board", req, resp);
		}
	}
}
