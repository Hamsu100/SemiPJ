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

@WebServlet("/board/repupdate")
public class BoardRepUpdateServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;

	private BoardService bs = new BoardService();

	@Override
	public String getServletName() {
		return "BoardRepUpdateServlet";
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int rNo = Integer.parseInt(req.getParameter("rNo"));
			String content = req.getParameter("modifycomment");
			String writer = req.getParameter("writer");
			
			User loginUser = getSessionUser(req);

			if (loginUser != null && !loginUser.getUser_id().equals(writer)) {
				sendCommonPage("세션이 만료 되었습니다.", "/board/view?boardNo=" + rNo, req, resp);
				return;
			}

			Reply r = bs.searchReply(rNo);

			r.setBoard_reply_content(content);

			int result = bs.modifyReply(r);

			if (result > 0) {
				sendCommonPage("댓글 수정에 성공하였습니다.", "/board/view?boardNo=" + r.getBoard_no(), req, resp);
			} else {
				sendCommonPage("댓글 수정에 실패하셨습니다.", "/board/view?boardNo=" + r.getBoard_no(), req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("DB 오류", "/board/list", req, resp);
		}
	}

}
