package com.kh.index.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.beach.model.service.BeachService;
import com.kh.beach.model.vo.Beach;
import com.kh.blog.model.service.BlogService;
import com.kh.blog.model.vo.Blog;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.util.MyHttpServlet;
import com.kh.common.util.PageInfo;

@WebServlet("/index")
public class IndexServlet extends MyHttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardService bs = new BoardService();
	private BeachService bchs = new BeachService();
	private BlogService bgs = new BlogService();

	@Override
	public String getServletName() {
		return "IndexServlet";
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HashMap<String, String> searchMap = new HashMap<>();

		searchMap.put("boardCat", "1");

		int boardCnt = bs.boardCnt(searchMap);

		PageInfo pageInfo = new PageInfo(1, 10, boardCnt, 10);

		List<Board> bList = bs.searchBoard(pageInfo, searchMap);

		req.setAttribute("bList", bList);

		List<Beach> pbList = bchs.getPopBchList("전국");

		req.setAttribute("pbList", pbList);

		List<Blog> blogListTop = bgs.selectBlogTopByNew();

		req.setAttribute("blogListTop", blogListTop);

		req.getRequestDispatcher("/index.jsp").forward(req, resp);

	}
}
