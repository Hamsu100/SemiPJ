package com.kh.blog.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.blog.model.service.BlogService;
import com.kh.blog.model.vo.Blog;
import com.kh.common.util.MyFileRenamePolicy;
import com.kh.common.util.MyHttpServlet;
import com.kh.member.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/blog/insert")
public class BlogWrInsertServlet extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BlogService bs = new BlogService();

	@Override
	public String getServletName() {
		return "BlogWrInsertServlet";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User loginUser = getSessionUser(req);
			
			if (loginUser != null) {
				req.getRequestDispatcher("/views/blog/insert.jsp").forward(req, resp);
				return;
			} else {
				sendCommonPage("로그인을 해주세요.", "/blog/list/new", req, resp);
			}
		} catch (Exception e) {
			sendCommonPage("로그인을 해주세요.", "/blog/list/new", req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = getServletContext().getRealPath("/resources/upload/blog");
			System.out.println(path);		
			
			int maxSize = 314572800;
			
			String encoding = "UTF-8";
			
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());
			
			
			User loginUser = getSessionUser(req);
			
			String area_code = mr.getParameter("area");
			String blog_originimg = mr.getOriginalFileName("imgfile");
			String blog_renameimg = mr.getFilesystemName("imgfile");
			String blog_content = mr.getParameter("con1").strip();
			String blog_subcontent = mr.getParameter("con2").strip();
			String writer = mr.getParameter("writer");	


			if (loginUser == null || loginUser.getUser_id().equals(writer) == false) {	
				sendCommonPage("비정상적인 접근입니다.", "/blog/list/new", req, resp);
				return;
			}
			
			Blog blog = new Blog();
			
			blog.setBlog_content(blog_content);
			blog.setBlog_subcontent(blog_subcontent);
			blog.setBlog_originimg(blog_originimg);
			blog.setBlog_renameimg(blog_renameimg);
			blog.setUser_no(loginUser.getUser_no());
			blog.setArea_code(Integer.parseInt(area_code));

			int result = bs.insertBlog(blog);

			if (result > 0) {
				sendCommonPage("등록 완료", "/blog/list/my?uno=" + loginUser.getUser_no(), req, resp);			
			} else {
				sendCommonPage("DB 삽입 오류", "/blog/list/new", req, resp);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("오류", "/blog/list/new", req, resp);				
		}
	}

}
