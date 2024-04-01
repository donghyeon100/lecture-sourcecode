package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/add")
public class TodoAddServlet extends HttpServlet {
	TodoListService service = null;
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			
			service = new TodoListServiceImpl();
			
			String title = req.getParameter("title");
			String detail = req.getParameter("detail");
			
			int index = service.todoAdd(title, detail);
			
			String message = null;
			
			if(index > 0) {
				message = "추가 성공!!";
			}else {
				message = "추가 실패 ...";
			}
			
			req.getSession().setAttribute("message", message);
			
			resp.sendRedirect("/");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String path = "/WEB-INF/views/error.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	
	}
}
