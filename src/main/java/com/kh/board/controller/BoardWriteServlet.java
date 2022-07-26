package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyFileRenamePolicy;
import com.kh.common.util.MyHttpServlet;
import com.kh.locationdata.model.service.LocationService;
import com.kh.member.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/write")
public class BoardWriteServlet extends MyHttpServlet {

	private BoardService bs = new BoardService();
	private LocationService ls = new LocationService();

	private static final long serialVersionUID = 1L;

	@Override
	public String getServletName() {
		return "BoardWriteServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User loginUser = getSessionUser(req);
			System.out.println(req.getParameter("boardCat"));


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

			String path = getServletContext().getRealPath("resources/upload/board");

			int maxSize = 104867800;

			String encoding = "utf-8";

			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());

			User loginUser = getSessionUser(req);

			if (loginUser == null || loginUser.getUser_id().equals(mr.getParameter("writer")) == false) {
				sendCommonPage("로그인 유저 뭔가 이상함.", "/board", req, resp);
				return;
			}
			int boardCatNo = 0;
			String boardCat = mr.getParameter("boardCat");
			if (!boardCat.equals("null") && boardCat.length() > 0) {
				boardCatNo = Integer.parseInt(boardCat);
			}

			String locType = mr.getParameter("locType");

			int locNo = 0;
			
			if (locType != null && locType.length() > 0) {
				locNo = ls.getLocNo(locType);
			}

			Board b = new Board();
			b.setBoard_title(mr.getParameter("tilte"));
			b.setBoard_category(boardCatNo);
			b.setBoard_content(mr.getParameter("content"));
			b.setLocation_no(locNo);
			b.setUser_no(loginUser.getUser_no());
			b.setBoard_originimg(mr.getOriginalFileName("upFile"));
			b.setBoard_renameimg(mr.getFilesystemName("upFile"));
			b.setBoard_writer(loginUser.getUser_id());

			int result = bs.writeBoard(b);

			if (result > 0) {
				sendCommonPage("등록", "/board?boardCat="+b.getBoard_category(), req, resp);
			} else {
				sendCommonPage("?", "/board", req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("오류", "/board", req, resp);
		}

	}
}
