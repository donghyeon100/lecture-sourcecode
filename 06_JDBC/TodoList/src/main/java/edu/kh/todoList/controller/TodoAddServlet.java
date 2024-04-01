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
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TodoListService service = null;
		RequestDispatcher dispatcher = null;
		
		try {
			
			service = new TodoListServiceImpl();
			
			String todoTtitle = req.getParameter("todoTitle");
			String todoContent = req.getParameter("todoContent");
			
			int result = service.addTodo(todoTtitle, todoContent);
			
			String message = null;
			
			if(result > 0) {
				message = "추가 성공!!";
			}else {
				message = "추가 실패 ...";
			}
			
			req.getSession().setAttribute("message", message);
			
			resp.sendRedirect("/");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			req.setAttribute("errorMessage", "할일 추가 중 예외 발생");
			
			String path = "/WEB-INF/views/error.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	
	}
}
