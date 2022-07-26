package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyFileRenamePolicy;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

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

			String path = getServletContext().getRealPath("/resources/upload/board");
			int maxSize = 104857600; // 100MB;
			String encoding = "UTF-8";
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());

			User loginUser = getSessionUser(req);
			int boardNo = Integer.parseInt(mr.getParameter("boardNo"));
			Board b = bs.viewBoard(boardNo);

			if (loginUser != null && b.getBoard_writer().equals(loginUser.getUser_id()) == false) {
				sendCommonPage("접근 불가", "/board", req, resp);
				return;
			}
			b.setBoard_title(mr.getParameter("title"));
			b.setBoard_content(mr.getParameter("content"));
			String boardCat = mr.getParameter("boardCat");

			System.out.println("BoardModifyServlet의 보드캇 : " + boardCat);

			String renameFile = mr.getParameter("renameImg");
			String orgFile = mr.getOriginalFileName("upFile");
			String renFile = mr.getFilesystemName("upFile");

			if (orgFile != null && orgFile.length() > 0) {
				try {
					File deleteFile = new File(path, renameFile);
					deleteFile.delete();
				} catch (Exception e) {
				}

				b.setBoard_originimg(orgFile);
				b.setBoard_renameimg(renFile);
			}
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
