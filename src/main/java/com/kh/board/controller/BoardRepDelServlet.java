package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.common.util.MyHttpServlet;

@WebServlet("/board/repdel")
public class BoardRepDelServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardRepDelServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			int replyNo = Integer.parseInt(req.getParameter("replyNo"));
			int result = bs.deleteReply(replyNo);
			if (result > 0) {
				sendCommonPage("success", "/board/view?boardNo=" + boardNo, req, resp);
			} else {
				sendCommonPage("fail", "/board/view?boardNo=" + boardNo, req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("잘못된 접근 입니다.", "/board", req, resp);
		}
	}
}
