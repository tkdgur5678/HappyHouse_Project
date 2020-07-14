package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.happyhouse.model.dto.UserInfo;
import com.ssafy.happyhouse.model.service.UserInfoService;
import com.ssafy.happyhouse.model.service.UserInfoServiceImpl;

@WebServlet("*.udo")
public class UserController extends HttpServlet {
	UserInfoService service;
	/*commit을 하고싶은데 왜 안되지?*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service = new UserInfoServiceImpl();
		
		String root = request.getContextPath();
		String reqString = request.getServletPath();
		String page = "";
		
		if(reqString.equals("/loginForm.udo")) {//로그인 화면으로 넘어간다.
			HttpSession session = request.getSession();
			session.setAttribute("loginfail", false);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/loginform.jsp");
			dispatcher.forward(request, response);
			
		}else if(reqString.equals("/loginProcess.udo")) {
			//로그인이 되었다면 session에 정보를 저장하고, 다시 목록으로 간다.
			
			String uid = request.getParameter("uid");
			String pass = request.getParameter("pass");
			UserInfo now = service.select(uid);
			if(now != null) {
				//session에 로그인 정보를 저장한다.
				if(pass.equals(now.getPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("uid", uid);
					if(uid.equals("ssafy")) {
						session.setAttribute("manager", true);
					}
					
					//***********아이디저장구현하기****************************
					
					//****************************************************
					response.sendRedirect("main.udo");
				}else {
					response.setContentType("text/html; charset=utf-8"); 
				    PrintWriter out = response.getWriter();
				    out.println("<script>alert('아이디나 비밀번호를 확인해주세요'); history.go(-1);</script>");
				    out.flush();
				    out.close();
					/*
					 * HttpSession session = request.getSession(); session.setAttribute("loginfail",
					 * true); response.sendRedirect("main.udo");
					 */
					//RequestDispatcher dispatcher = request.getRequestDispatcher("/user/findpassfail.jsp");
					//dispatcher.forward(request, response);
				}				
			}else {
				response.setContentType("text/html; charset=utf-8"); 
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('아이디나 비밀번호를 확인해주세요'); history.go(-1);</script>");
			    out.flush();
			    out.close();
				/*
				 * HttpSession session = request.getSession(); session.setAttribute("loginfail",
				 * true); response.sendRedirect("main.udo");
				 */
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/user/findpassfail.jsp");
				//dispatcher.forward(request, response);
			}
			
		} else if(reqString.equals("/logOut.udo")) {
			HttpSession session = request.getSession();
			//session에서 로그인정보를 제거한다.
			session.setAttribute("uid", null);
			session.setAttribute("manager", null);
			response.sendRedirect("main.udo");
		} else if(reqString.equals("/findPass.udo")) {//비밀번호 찾기를 눌렀을때, 비밀번호 화면 으로 넘어간다.
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/findpass.jsp");
			dispatcher.forward(request, response);
			
		}else if(reqString.equals("/passfindProcess.udo")) {
			//비밀 번호를 찾기위한 정보를 담아옴
			String uid = request.getParameter("uid");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			
			UserInfo now = service.select(uid);
			if(now!=null && now.getName().equals(name) && now.getPhone().equals(phone)) {
				//정보가 맞다면 비밀번호 바꾸는 페이지로 이동
				//해당 id에 정보를 저장해야 하므로 일단 session에 id정보를 저장한다.
				HttpSession session = request.getSession();
				session.setAttribute("uid", uid);
				page = "/user/changepass.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(page);
				dispatcher.forward(request, response);
			}else {
				//정보가 틀리므로 틀렸다고 말하고 목록으로 돌아간다.
				//page = "/user/findpassfail.jsp";
				
			    response.setContentType("text/html; charset=utf-8"); 
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('입력정보를 확인해주세요'); history.go(-1);</script>");
			    out.flush();
			    out.close();
				 
			}
			
			/*
			 * RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			 * dispatcher.forward(request, response);
			 */
			 
			 			
		}else if(reqString.equals("/chagepassProcess.udo")) {
			//입력된 비밀번호가 같은지 확인
			String newpass = request.getParameter("pass1");
			String twopass = request.getParameter("pass2");
			
			if(newpass!=null && twopass!=null && newpass.equals(twopass)) {
				HttpSession session = request.getSession();
				service.update((String)session.getAttribute("uid"), 2, newpass);
				
				//비밀번호를 바꾼 후에 session을 제거해준다.
				session.setAttribute("uid", null);
				response.sendRedirect("main.udo");
			}else {//입력된 값이 다르면 안됨
				/*
				 * RequestDispatcher dispatcher =
				 * request.getRequestDispatcher("/user/changefail.jsp");
				 * dispatcher.forward(request, response);
				 */
				response.setContentType("text/html; charset=utf-8"); 
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('비밀번호를 확인해주세요'); history.go(-1);</script>");
			    out.flush();
			    out.close();
			}
		}else if(reqString.equals("/signupForm.udo")){//회원가입 폼으로 넘어감
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/signupform.jsp");
			dispatcher.forward(request, response);
		}else if(reqString.equals("/signupProcess.udo")) {
			String uid = request.getParameter("uid");
			String name = request.getParameter("name");
			String phone = request.getParameter("phone");
			String pass = request.getParameter("pass");
			String address = request.getParameter("address");
			
			UserInfo ui = new UserInfo(uid, pass, name, address, phone);
			if(service.select(uid) == null) {
				service.insert(ui);
				response.sendRedirect("main.udo");
			}else {
				/*
				 * request.setAttribute("uid", uid); RequestDispatcher dispatcher =
				 * request.getRequestDispatcher("/user/doubleId.jsp");
				 * dispatcher.forward(request, response);
				 */
				response.setContentType("text/html; charset=utf-8"); 
			    PrintWriter out = response.getWriter();
			    out.println("<script>alert('이미 존재하는 아이디입니다.'); history.go(-1);</script>");
			    out.flush();
			    out.close();
			}
		} else if(reqString.equals("/userinfoData.udo")){
			HttpSession session = request.getSession();
			String uid = (String)session.getAttribute("uid");
			UserInfo ui = service.select(uid);
			
			request.setAttribute("ui", ui);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userinfoDetail.jsp");
			dispatcher.forward(request, response);
			
		} else if(reqString.equals("/userinfoChange.udo")){
			HttpSession session = request.getSession();
			String uid = (String)session.getAttribute("uid");
			UserInfo ui = service.select(uid);
			
			request.setAttribute("ui", ui);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userinfochange.jsp");
			dispatcher.forward(request, response);
		} else if(reqString.equals("/userinfochangeProcess.udo")){
			HttpSession session = request.getSession();
			String uid = (String)session.getAttribute("uid");
			UserInfo ui = service.select(uid);
			
			if(!request.getParameter("name").equals(ui.getName())) {
				service.update(uid, 1, request.getParameter("name"));
			}
			if(!request.getParameter("pass").equals(ui.getPassword())) {
				service.update(uid, 2, request.getParameter("pass"));
			}
			if(!request.getParameter("address").equals(ui.getAddress())) {
				service.update(uid, 3, request.getParameter("address"));
			}
			if(!request.getParameter("phone").equals(ui.getPhone())) {
				service.update(uid, 4, request.getParameter("phone"));
			}		
			
			ui = service.select(uid);
			request.setAttribute("ui", ui);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/user/userinfoDetail.jsp");
			dispatcher.forward(request, response);
			
		} else if(reqString.equals("/userdelete.udo")){
			//회원 탈퇴 버튼을 눌렀을때
			HttpSession session = request.getSession();
			String uid = (String)session.getAttribute("uid");
			service.delete(uid, "");
			response.sendRedirect("logOut.udo");
			
		}else if(reqString.equals("/main.udo")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
		
	}
}
