package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ssafy.happyhouse.model.dto.Notice;
import com.ssafy.happyhouse.model.service.NoticeService;
import com.ssafy.happyhouse.model.service.NoticeServiceImpl;
import com.ssafy.happyhouse.util.PageNavigation;

@WebServlet("*.mdo")
public class MainController extends HttpServlet {
	
	private NoticeService service;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = new NoticeServiceImpl();
		
		String root = request.getContextPath();
		String reqString = request.getServletPath();
		String page = "";
		
		if("/notice.mdo".equals(reqString)) {
			String path = "/index.jsp";
			try {
			    int currentPage = Integer.parseInt(request.getParameter("pg"));//현재페이지번호
			    String spp = request.getParameter("spp");//size per page 한페이지당 글갯수
			    int sizePerPage = spp == null ? 10 : Integer.parseInt(spp);//sizePerPage
				String key = request.getParameter("key");
				String word = request.getParameter("word");
				List<Notice> list = service.listNotice(currentPage, sizePerPage, key, word);
				PageNavigation pageNavigation = service.makePageNavigation(currentPage, sizePerPage);
		         request.setAttribute("navigation", pageNavigation);
				request.setAttribute("list", list);	
				path = "/view/notice.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "목록을 읽어오는 중에 문제가 발생했습니다.");
				path = "/error/error.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}else if("/detail.mdo".equals(reqString)) {
			String path = "/index.jsp";
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Notice notice = service.getNotice(id);
				request.setAttribute("notice", notice);
				path = "/view/notice_detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("msg", "글을 읽어오는 중에 문제가 발생했습니다.");
				path = "/error/error.jsp";
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}else if("/write.mdo".equals(reqString)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/view/write.jsp");
			dispatcher.forward(request, response);
		}else if("/writeProcess.mdo".equals(reqString)) {
			String path = "/index.jsp";
			String uid = (String) request.getSession().getAttribute("uid");
			if(!"ssafy".equals(uid)) {
				request.setAttribute("msg", "관리자만 접근가능합니다.");
				path = "/error/error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}else {
				Notice notice = new Notice();
				notice.setManagerName(uid);
				notice.setTitle(request.getParameter("title"));
				notice.setContent(request.getParameter("content"));
				try {
					service.writeNotice(notice);
					path = "/notice.mdo?pg=1";
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("msg", "글을 작성하는 중에 문제가 발생했습니다.");
					path = "/error/error.jsp";
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}
		}else if("/delete.mdo".equals(reqString)) {
			String path = "/index.jsp";
			String uid = (String) request.getSession().getAttribute("uid");
			if(!"ssafy".equals(uid)) {
				request.setAttribute("msg", "관리자만 접근가능합니다.");
				path = "/error/error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}else {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					service.deleteNotice(id);
					path = "/notice.mdo?pg=1";
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("msg", "글을 지우는 중에 문제가 발생했습니다.");
					path = "/error/error.jsp";
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}
		}else if("/mvmodify.mdo".equals(reqString)) {
			String path = "/index.jsp";
			String uid = (String) request.getSession().getAttribute("uid");
			if(!"ssafy".equals(uid)) {
				request.setAttribute("msg", "관리자만 접근가능합니다.");
				path = "/error/error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}else {
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					Notice notice = service.getNotice(id);
					request.setAttribute("notice", notice);
					path = "/view/modify.jsp";
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("msg", "글을 수정하는 중에 문제가 발생했습니다.");
					path = "/error/error.jsp";
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}
		}else if("/modify.mdo".equals(reqString)) {
			String path = "/index.jsp";
			String uid = (String) request.getSession().getAttribute("uid");
			if(!"ssafy".equals(uid)) {
				request.setAttribute("msg", "관리자만 접근가능합니다.");
				path = "/error/error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(path);
				dispatcher.forward(request, response);
			}else {
				try {
					Notice notice = new Notice();
					notice.setId(Integer.parseInt(request.getParameter("id")));
					notice.setTitle(request.getParameter("title"));
					notice.setContent(request.getParameter("content"));
					service.modifyNotice(notice);
					response.sendRedirect(request.getContextPath() + "/notice.mdo?pg=1");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("msg", "글을 수정하는 중에 문제가 발생했습니다.");
					path = "/error/error.jsp";
					request.getRequestDispatcher(path).forward(request, response);
				}
			}
		}else if("/introduction.mdo".equals(reqString)) {
			String path = "/view/introduction.jsp";
			request.getRequestDispatcher(path).forward(request, response);
		}
		
	}
}
