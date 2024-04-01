package edu.kh.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.kh.demo.model.dto.Todo;
import edu.kh.demo.model.service.TodoService;

//@RequiredArgsConstructor는 초기화 되지않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줍니다.
//새로운 필드를 추가할 때 다시 생성자를 만들어서 관리해야하는 번거로움을 없애준다. (@Autowired를 사용하지 않고 의존성 주입)
//@RequiredArgsConstructor


@Controller
public class MainController {
	
	@Autowired // DI(의존성 주입)
	private TodoService service;
	
	
	@RequestMapping("/")
	public String mainPage(Model model) {
		
		Map<String, Object> map = service.selectAll();
		
		// map에 담긴 데이터 분리
		List<Todo> todoList = (List<Todo>)map.get("todoList");
		int completeCount	  = (int)map.get("completeCount");
		
		model.addAttribute("todoList", todoList);
		model.addAttribute("completeCount", completeCount);
		
		return "common/main";
	}
}
