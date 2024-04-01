package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("example")
public class ExampleController {
	
	// Model 
	// - 데이터 전달용 객체(기본 scope: request)
	// - org.springframework.ui
	// - Model.addAttribute("key", value) : forward할 곳으로 전달할 값 세팅
	
	
	@GetMapping("ex1")
	public String ex1(Model model) {
		
		
		
		// 단일 값 전달
		model.addAttribute("v1", "테스트중");
		model.addAttribute("v2", 99999);
		
		// 객체 전달
		Student std = new Student();
		std.setStudentNo("12345");
		std.setName("김영희");
		std.setAge(20);
			
		model.addAttribute("std", std);
		
		return "example/ex1";
	}
	
	
	@GetMapping("ex2")
	public String ex2(Model model) {
		
		
		String str = "<h1>테스트</h1>";
		model.addAttribute("str",str);
		
		return "example/ex2";
	}
}
