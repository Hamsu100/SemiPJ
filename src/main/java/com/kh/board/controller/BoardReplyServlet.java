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

@WebServlet("/board/reply")
public class BoardReplyServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardReplyServlet";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {

			User loginUser = getSessionUser(req);

			String replyWriter = req.getParameter("replyWriter");
			int boardNo = Integer.parseInt(req.getParameter("bNo"));
			String content = req.getParameter("replyContent");

			if (loginUser.getUser_id().equals(replyWriter) == false) {
				sendCommonPage("세션이 만료 되었습니다.", "/board", req, resp);
				return;
			}

			Reply reply = new Reply();
			reply.setBoard_no(boardNo);
			reply.setBoard_reply_content(content);
			reply.setUser_no(loginUser.getUser_no());
			reply.setBoard_reply_writer(replyWriter);

			int result = bs.writeReply(reply);

			if (result > 0) {
				sendCommonPage("리플 달았슈", "/board/view?boardNo=" + boardNo, req, resp);
			} else {
				sendCommonPage("리플 실패 ㅜ", "/board/view?boardNo=" + boardNo, req, resp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("abnormal access", "/board", req, resp);
		}

	}

}
