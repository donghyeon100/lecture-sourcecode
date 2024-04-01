package edu.kh.dept.controller;

import java.io.IOException;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;
import edu.kh.dept.model.service.DepartmentService;
import edu.kh.dept.model.service.DepartmentServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/department/insert")
public class InsertServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/insert.jsp";
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
			
			int result = service.insertDepartment(dept);

			String message = null;
			HttpSession session = req.getSession();
			if(result > 0) {
				message = "부서 추가 성공";
			}else {
				message = "부서 추가 실패";
			}
			
			session.setAttribute("message",  message);
			
			resp.sendRedirect("/department/selectAll");
			
		} catch (DepartmentInsertException e) {
			
			// DEPARTMENT4 테이블 PK 제약 조건 추가
			// ALTER TABLE DEPARTMENT4
			// ADD CONSTRAINT DEPT4_PK PRIMARY KEY(DEPT_ID);
			
			e.printStackTrace();
			
			req.setAttribute("message", "PK 제약 조건 위배로 삽입 실패");
			String path = "/WEB-INF/views/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
