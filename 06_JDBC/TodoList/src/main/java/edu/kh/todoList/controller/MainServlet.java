package edu.kh.todoList.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.common.JDBCTemplate;
import edu.kh.todoList.model.dto.Todo;
import edu.kh.todoList.model.service.TodoListService;
import edu.kh.todoList.model.service.TodoListServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("") // 메인 페이지 요청
public class MainServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		TodoListService service = null;
		RequestDispatcher dispatcher = null;
		try {
		
			service = new TodoListServiceImpl();
			
			Map<String, Object> map = service.selectAll();
			
			List<Todo> todoList = (List<Todo>)map.get("todoList");
			int completeCount = (int)map.get("completeCount");
			
			req.setAttribute("todoList", todoList);
			req.setAttribute("completeCount", completeCount);
			
			String path = "/WEB-INF/views/main.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
			
		}catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMessage", "할 일 목록 조회 중 예외 발생");
			String path = "/WEB-INF/views/error.jsp";
			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}
	
	}
}
