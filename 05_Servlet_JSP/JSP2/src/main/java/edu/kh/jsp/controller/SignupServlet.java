package edu.kh.jsp.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/redirect/signup.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		String pwCheck = req.getParameter("pwCheck");

		

		String message = null;
		if(pw.equals(pwCheck)) message = id + "이 가입 되었습니다";
		else									 message = "비밀번호가 일치하지 않습니다.";
		

		// 우선 request scope로 확인
		//req.setAttribute("message", message);
		
		// request 안된 경우 session으로 확인
		HttpSession session = req.getSession();
		session.setAttribute("message", message);
		
		// 가입 결과를 출력하기 위한 별도의 JSP가 존재하지 않음
		// -> 대신 /signup을 GET 방식으로 다시 요청해 위에있는 doGet()이 실행되게 하여
		// /WEB-INF/views/redirect/signup.jsp를 이용해 응답화면을 만듦
		resp.sendRedirect("/signup");
		
	}


}
