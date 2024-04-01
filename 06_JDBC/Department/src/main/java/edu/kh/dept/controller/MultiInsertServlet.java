package edu.kh.dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/department/multiInsert")
public class MultiInsertServlet extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			DepartmentService service = new DepartmentServiceImpl();
			
			String[] idArr = req.getParameterValues("deptId");
			String[] titleArr = req.getParameterValues("deptTitle");
			String[] locationArr = req.getParameterValues("locationId");

			
			List<Department> deptList = new ArrayList<Department>();
			
			for(int i=0 ; i<idArr.length ; i++) {
				Department dept = new Department();
				dept.setDeptId(idArr[i]);
				dept.setDeptTitle(titleArr[i]);
				dept.setLocationId(locationArr[i]);
				
				deptList.add(dept);
			}
			
			int result = service.multiInsert(deptList);
			
			
			String message = null;
			HttpSession session = req.getSession();
			
			if(result == deptList.size()) {
				message = "부서 추가 성공";
			}else {				message = "부서 추가 실패";
			}
			
			session.setAttribute("message",  message);
			
			resp.sendRedirect("/department/selectAll");
			
		} catch (DepartmentInsertException e) {
			e.printStackTrace();
			
			req.setAttribute("message", e.getMessage());
			String path = "/WEB-INF/views/error.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}
