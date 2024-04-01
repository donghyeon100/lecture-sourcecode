package edu.kh.dept.controller;

import java.io.IOException;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/department/update")
public class UpdateServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String path = "/WEB-INF/views/update.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		try {
			DepartmentService service = new DepartmentServiceImpl();

			String deptId = req.getParameter("deptId");
			String deptTitle = req.getParameter("deptTitle");
			String locationId = req.getParameter("locationId");
			
			Department dept = new Department(deptId, deptTitle, locationId);
	
			int result = service.updateDepartment(dept);
			
			String message = null;
			HttpSession session = req.getSession();
			if(result > 0)	message = "수정 성공";
			else						message = "수정 실패";
			
			session.setAttribute("message", message);
			resp.sendRedirect("/department/selectAll");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
