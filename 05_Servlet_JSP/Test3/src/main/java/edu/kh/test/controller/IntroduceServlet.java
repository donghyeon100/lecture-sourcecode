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

@WebServlet("/")
public class IntroduceServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String inputName = null;
		String inputAge = null;
		
		if(!inputName.equals("") && !inputAge.equals("") ) {
			
			req.setAttribute("message", String.format("%s은/는의 나이는 %s세 입니다.", inputName, inputAge));
			
			String path = "/WEB-INF/views/result.jsp";
			req.getRequestDispatcher(path);
			
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("message", "이름 또는 나이가 입력되지 않았습니다");
		}
	}
}
