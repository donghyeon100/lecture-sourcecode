package edu.kh.todoList.controller;

import java.io.IOException;

import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/todo/detail")
public class TodoDetailServlet extends HttpServlet{
	
	TodoListService service = null;
	RequestDispatcher dispatcher = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			service = new TodoListServiceImpl();
			
			int index = Integer.parseInt(req.getParameter("index"));
			
			Todo todo = service.todoDetailView(index);
			
			if(todo == null) {
				req.getSession().setAttribute("message", "할 일이 존재하지 않습니다");
				resp.sendRedirect("/");
				
			} else {
				req.setAttribute("todo", todo);
				String path = "/WEB-INF/views/todo/detail.jsp";
				req.getRequestDispatcher(path).forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			String path = "/WEB-INF/views/error.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	}
}
