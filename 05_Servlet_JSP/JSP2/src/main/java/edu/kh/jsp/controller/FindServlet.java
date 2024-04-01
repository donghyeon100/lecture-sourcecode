package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/find")
public class FindServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/forward/find.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<String> nameList = new ArrayList<String>();
		
		nameList.add("김길동");
		nameList.add("나길동");
		nameList.add("최길동");
		nameList.add("홍길동");
		nameList.add("고길동");
		nameList.add("우길동");
		nameList.add("박길동");
		
		String findName = req.getParameter("findName");
		
		String result = null;
		if(nameList.contains(findName)) { // 찾는 이름이 리스트에 있을 경우
			result = String.format("%s는 nameList %d번째 인덱스에 있습니다.", findName, nameList.indexOf(findName));
		} else {
			result = String.format("%s는 nameList에 존재하지 않습니다.", findName);
		}
		
		req.setAttribute("result", result);
		
		String path = "/WEB-INF/views/forward/find_result.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
}
