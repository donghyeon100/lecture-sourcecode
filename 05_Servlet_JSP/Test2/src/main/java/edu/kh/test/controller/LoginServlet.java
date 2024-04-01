package edu.kh.test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.test.model.dto.Member;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

// @WebServlet("/login") 
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputId = req.getParameter("inputId");
		String inputPw = req.getParameter("inputPw");
		
		if(inputId.equals("user01") && inputPw.equals("pass01")) {
			req.setAttribute("message", "user01 로그인 성공!");
			
			String path = "/WEB-INF/views/result.jsp";
		//	req.getRequestDispatcher(path).forward(req, resp);
			
		}else {
//			HttpSession session = req.getSession();
//			session.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
			req.setAttribute("message", "아이디 또는 비밀번호가 일치하지 않습니다");
			resp.sendRedirect("/");
			
		}
		
	}
	
	
}
