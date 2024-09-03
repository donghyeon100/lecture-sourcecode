package edu.kh.jdbc.controller;

import java.io.IOException;
import java.util.List;

import edu.kh.jdbc.dto.User;
import edu.kh.jdbc.service.UserService;
import edu.kh.jdbc.service.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/selectAll")
public class SelectAll extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			UserService sevice = new UserServiceImpl();
			
			List<User> userList = sevice.selectAll();
			
			req.setAttribute("userList", userList);
			
			String path = "/WEB-INF/views/selectAll.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
}
