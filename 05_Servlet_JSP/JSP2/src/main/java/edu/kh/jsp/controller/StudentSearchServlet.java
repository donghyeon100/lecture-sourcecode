package edu.kh.jsp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.kh.jsp.model.dto.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/student/search")
public class StudentSearchServlet extends HttpServlet{
	
	private List<Student> studentList = null;
	
	public StudentSearchServlet() {
		studentList = new ArrayList<Student>();
		
		studentList.add(new Student("202401", "이지은"));
		studentList.add(new Student("202402", "차은우"));
		studentList.add(new Student("202403", "홍길동"));
		studentList.add(new Student("202404", "김개똥"));
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String studentNumber = req.getParameter("studentNumber");
		String studentName = req.getParameter("studentName");

		
		boolean flag = false;
		String result = null;
				
		
		// 입력 받은 학번, 이름이 일치하는 학생이 stduentList에 존재하면
		// student_result.jsp로 forward
		// 몇번 인덱스에 학번, 이름 학생이 존재합니다	
		
		// 존재하지 않으면
		// /error로 redirect
		// OOO 학생은 존재하지 않습니다
		
		for(int i=0 ; i<studentList.size() ; i++) {
			Student std = studentList.get(i);
			
			if(std.getStudentNumber().equals(studentNumber) && std.getName().equals(studentName)) {
				flag = true;
				result = String.format("%d번째 인덱스에 %s/%s 학생이 존재합니다", i, studentNumber, studentName);	
				break;
			}
		}
		
		if(flag) { // forward
			String path = "/WEB-INF/views/forward/student_result.jsp";
			req.setAttribute("result", result);
			req.getRequestDispatcher(path).forward(req, resp);
		
		} else { // redirect
			
			HttpSession session = req.getSession();
			session.setAttribute("result", result);
			resp.sendRedirect("/error");
		}
		
		
		
	}
}
