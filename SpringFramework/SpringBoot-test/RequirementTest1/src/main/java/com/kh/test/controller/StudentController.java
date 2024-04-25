package com.kh.test.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.test.model.dto.Student;
import com.kh.test.model.service.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("student")
@RequiredArgsConstructor
public class StudentController {

	private final StudentService studentService;
	
	@ResponseBody 
	@GetMapping("select")
	public List<Student> studentSelect() {
		return studentService.studentSelect();
	}
	
	
	@ResponseBody
	@PostMapping("insert")
	public int studentInsert(@RequestBody Student student) {
		return studentService.studentInsert(student);
	}
	
	
	@ResponseBody
	@DeleteMapping("delete")
	public int studentDelete(@RequestBody int studentNo) {
		return studentService.studentDelete(studentNo);
	}
	
}
