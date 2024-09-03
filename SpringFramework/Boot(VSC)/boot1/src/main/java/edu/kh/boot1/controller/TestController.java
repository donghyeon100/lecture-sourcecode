package edu.kh.boot1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public class TestController {

  @GetMapping("/test1")
  public String test1(HttpServletRequest req) {
      req.setAttribute("message1", "메시지 1번 입니다");
      req.setAttribute("message2", "메시지 2번 입니다");
      
      return "test/test1";
  }
  
}
