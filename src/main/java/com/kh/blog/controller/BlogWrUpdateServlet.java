package com.kh.blog.controller;

import java.io.File;
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

@WebServlet("/blog/update")
public class BlogWrUpdateServlet extends MyHttpServlet{
	private static final long serialVersionUID = 1L;
	private BlogService bs = new BlogService();

	@Override
	public String getServletName() {
		return "BlogWrUpdateServlet";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int blog_no = Integer.parseInt(req.getParameter("bgno"));
		
		try {
			User loginUser = getSessionUser(req);
			if (loginUser != null) {
				Blog blog = bs.selectBlog(blog_no);
				req.setAttribute("blog", blog);
				req.getRequestDispatcher("/views/blog/update.jsp").forward(req, resp);
				return;
			}
		} catch (Exception e) {
			sendCommonPage("로그인을 해주세요.", "/blog/list/new", req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String path = getServletContext().getRealPath("/resources/upload/blog");
			System.out.println(path);		//<<<<
			
			int maxSize = 314572800;
			String encoding = "UTF-8";
			
			MultipartRequest mr = new MultipartRequest(req, path, maxSize, encoding, new MyFileRenamePolicy());
			
			
			User loginUser = getSessionUser(req);
			
			String area_code = mr.getParameter("area");
			String blog_content = mr.getParameter("con1").strip();
			String blog_subcontent = mr.getParameter("con2").strip();
			
			String blog_no = mr.getParameter("bgno");
			String writer = mr.getParameter("writer");


			if (loginUser == null || loginUser.getUser_id().equals(writer) == false) {	
				sendCommonPage("비정상적인 접근입니다.", "/blog/list/new", req, resp);
				return;
			}
			
			Blog blog = bs.selectBlog(Integer.parseInt(blog_no));
			
			if ((blog.getUser_id().equals(loginUser.getUser_id())) == false) {
				sendCommonPage("비정상적인 접근입니다.", "/blog/list/new", req, resp);
				return;
			}
			
			blog.setBlog_content(blog_content);
			blog.setBlog_subcontent(blog_subcontent);
			blog.setArea_code(Integer.parseInt(area_code));

			
			String blog_renameimg = mr.getParameter("blog_renameimg");
			
			String blog_reloadoriginimg = mr.getOriginalFileName("imgfile");
			String blog_reloadrenameimg = mr.getFilesystemName("imgfile");
			
			if (blog_reloadoriginimg != null && blog_reloadoriginimg.length() > 0) {
				try {
					File deleteFile = new File(path, blog_renameimg);
					deleteFile.delete();
				} catch (Exception e) {
					e.printStackTrace();
				}
				blog.setBlog_originimg(blog_reloadoriginimg);
				blog.setBlog_renameimg(blog_reloadrenameimg);
			}
			
			
			int result = bs.updateBlog(blog);
			
			if (result > 0) {
				sendCommonPage("수정 완료", "/blog/list/my?uno=" + loginUser.getUser_no(), req, resp);		
			} else {
				sendCommonPage("수정 오류", "/blog/list/new", req, resp);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendCommonPage("오류", "/blog/list/new", req, resp);	
		}
	}

}
