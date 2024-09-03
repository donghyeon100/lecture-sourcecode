package me.bdh.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import me.bdh.main.dto.ApprovalStatus;

@Controller
@Slf4j
public class MainController {

	@RequestMapping("/")
	public String mainPage() {
		
		log.info(  ApprovalStatus.of(0).getDesciption() );
		log.info(  ApprovalStatus.of(1).getDesciption() );
		log.info(  ApprovalStatus.of(2).getDesciption() );
		log.info(  ApprovalStatus.of(3).getDesciption() );
		
		return "main/sign-in";
	}
	
}
