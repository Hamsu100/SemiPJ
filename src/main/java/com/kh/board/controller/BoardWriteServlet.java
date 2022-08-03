package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;
import com.kh.locationdata.model.service.LocationService;
import com.kh.member.model.vo.User;

@WebServlet("/board/write")
public class BoardWriteServlet extends MyHttpServlet {

	private BoardService bs = new BoardService();

	private static final long serialVersionUID = 1L;

	@Override
	public String getServletName() {
		return "BoardWriteServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User loginUser = getSessionUser(req);
			System.out.println("boardCat" + req.getParameter("boardCat"));

			if (loginUser != null) {
				req.getRequestDispatcher("/views/board/write.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
		}
		sendCommonPage("비정상적인 접근입니다.", "/", req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			String boardCat = req.getParameter("boardCat");
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			String writer = req.getParameter("writer");
			String prevBoCat = req.getParameter("prevBoCat");

			User loginUser = getSessionUser(req);

			if (loginUser == null || loginUser.getUser_id().equals(writer) == false) {
				sendCommonPage("비정상적인 접근입니다.", "/board/list", req, resp);
				return;
			}
			
			Board b = new Board();
			b.setBoard_title(title);
			b.setBoard_category(Integer.parseInt(boardCat));
			b.setBoard_content(content);
			b.setUser_no(loginUser.getUser_no());

			int result = bs.writeBoard(b);

			if (result > 0) {
				sendCommonPage("등록", "/board/list?boardCat=" + b.getBoard_category(), req, resp);
			} else {
				sendCommonPage("DB 삽입 오류", "/board/list?boardCat="+prevBoCat, req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("오류", "/board/list", req, resp);
		}

	}

}
