package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ExampleController {
	
	/* @GetMapping : Get 방식 요청을 작성된 메서드와 매핑 */
	@GetMapping("/example")
	public String exampleMethod1() {
		
		// forward(요청 위임)
		// classpath:/templates/example/.html
		return "example";
	}
	

	
	
	

}
