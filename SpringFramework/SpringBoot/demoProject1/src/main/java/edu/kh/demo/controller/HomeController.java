package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


//Java 객체 : new 연산자에 의해서 class 내용대로 heap 영역에 생성된 것 

//instance : 개발자가 만들고 관리하는 객체
//bean : Spring (Spring Container)이 만들고 관리하는 객체

@Controller // bean 등록 + Controller 역할을 할 것이라고 명시
			// - Controller 역할 : 요청/응답 제어
public class HomeController {

	// @RequestMapping("요청 주소") 
	// 1) 메서드에 작성한 경우 : 요청 주소와 해당 메서드를 매핑 (GET/POST 가리지 않음)
	// 2) 클래스에 작성한 경우 : 공통된 형태의 주소를 매핑함
	
	
	@RequestMapping("/")
	// GET/POST 가리지 않고 "/" 요청 오면 매핑
	public String mainPage() {

		/* Spring에서 forward(요청 위임) 하는 방법!! */
		// - 메서드 return 구문에 forward할 파일 경로 작성!
		 
		
		/* Thymeleaf 템플릿 엔진 사용 시 접두사, 접미사 */
		// 접두사 : classpath:/templates/
		// 접미사 : .html
		
		// classpath == src/main/resources 폴더
		// classpath:/templates/index.html
		return "index";
		
	}
	
}
