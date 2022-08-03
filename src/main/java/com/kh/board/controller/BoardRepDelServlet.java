package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;

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
			
			Reply r =bs.searchReply(replyNo);
			User loginUser = getSessionUser(req);
			if (loginUser!=null && !loginUser.getUser_id().equals(r.getBoard_reply_writer())) {
				sendCommonPage("세션이 만료되었습니다.", "/board/view?boardNo="+boardNo, req, resp);
				return;
			}
			
			int result = bs.deleteReply(replyNo);
			if (result > 0) {
				sendCommonPage("댓글 삭제에 성공하셨습니다.", "/board/view?boardNo=" + boardNo, req, resp);
			} else {
				sendCommonPage("댓글 삭제에 실패하셨습니다.", "/board/view?boardNo=" + boardNo, req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("잘못된 접근 입니다.", "/board", req, resp);
		}
	}
}
