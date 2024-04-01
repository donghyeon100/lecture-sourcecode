package edu.kh.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	// 메인 페이지 매핑 주소 작성 시에만 / 작성
	@RequestMapping("/")
	public String mainPage() {
		
		return "main";
	}
}
