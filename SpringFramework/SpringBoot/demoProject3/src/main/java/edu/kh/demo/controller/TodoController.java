package edu.kh.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.demo.model.dto.Todo;
import edu.kh.demo.model.service.TodoService;



@Controller
@RequestMapping("todo")
public class TodoController {

	@Autowired  // DI(의존성 주입)
	private TodoService service;
	
	
	@PostMapping("add")
	public String addTodo(
		Todo todo, Model model,
		RedirectAttributes ra
		) {
		
		// RedirectAttributes : 리다이렉트 시 값을 1회성으로 전달하는 객체
		
		// RedirectAttributes.addFlashAttribute("key", value) 형식으로 잠깐 세션에 속성 추가
		
		// [원리]
		// 응답 전 : request scope
		// redirect 중 : session scope로 이동
		// 응답 후 : reqeust scope로 복귀
		
		int result = service.addTodo(todo);
		
		String message = null;
		if(result > 0) message = "할 일 추가 성공!";
		else					 message = "할 일 추가 실패...";
		
		ra.addFlashAttribute("message", message);
		
		return "redirect:/";
	}
	
	
	
}
