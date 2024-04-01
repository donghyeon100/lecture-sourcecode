package edu.kh.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.demo.model.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그를 작성할 때 사용하는 어노테이션(Lombok 제공)

@Controller // 요청/응답 클래스 + bean 등록(Spring이 관리하는 객체)

@RequestMapping("param") // 클래스에 작성한 경우 : 공통된 형태의 주소를 매핑함
public class ParameterController {

	/* 요청 시 전달된 파라미터를 전달 방법
	 * 
	 * 1. HttpServletRequest 객체 이용
	 *  -> HttpServletRequest.getParameter("key")
	 * 
	 * 2. @RequestParam 어노테이션 이용
	 * 	-> 각종 속성 + 단수, 복수 개의 파라미터를 얻어오는 방법
	 * 
	 * 3. @ModelAttribute 어노테이션 이용
	 * */
	
	
	/* *** 매개 변수에 적으면 왜 사용 가능할까?? ***
	 * Spring Framework가 제공하는  ArgumentResolver(매개 변수 해결사)가 객체가
  	 * 
  	 * 
  	 * ArgumentResolver : 작성된 매개 변수의 타입을 확인하고 조건에 맞는 객체를 바인딩함
  	 * 
  	 * 바인딩 가능한 객체/어노테이션 확인
  	 * https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-methods/arguments.html
	 */
	
	@GetMapping("main")
	public String paramMain() {
		return "param/param-main";
	}
	
	
	/* 1. HttpServletRequest 객체 이용 */
	@PostMapping("test1")
	public String paramTest1(HttpServletRequest req) {
		String inputName = req.getParameter("inputName");
		int inputAge = Integer.parseInt(req.getParameter("inputAge"));
		String inputAddress = req.getParameter("inputAddress");
		
		log.info(inputName);
		log.info(inputAge + "");
		log.info(inputAddress);
		
		/* Redirect 방법 !!*/
		
		return "redirect:/param/main";
	}
	
	
	
	/* 2. @RequestParam 어노테이션 - 낱개(한 개, 단 수)개 파라미터 얻어오기
	 * 
	 * - request객체를 이용한 파라미터 전달 어노테이션 
	 * - 매개 변수 앞에 해당 어노테이션을 작성하면, 매개변수에 값이 주입됨.
	 * - 주입되는 데이터는 매개 변수의 타입이 맞게 형변환/파싱이 자동으로 수행됨!
	 * 
	 * [기본 작성법]
	 * @RequestParam("key") 자료형 매개변수명
	 * 
	 * 
	 * [속성 추가 작성법]
	 * @RequestParam(value="name", required="fasle", defaultValue="1") 
	 * 
	 * value : 전달 받은 input 태그의 name 속성값
	 * 
	 * required : 입력된 name 속성값 파라미터 필수 여부 지정(기본값 true) 
	 * 	-> required = true인 파라미터가 존재하지 않는다면 400 Bad Request 에러 발생 
	 * 	-> required = true인 파라미터가 null인 경우에도 400 Bad Request
	 * 
	 * defaultValue : 파라미터 중 일치하는 name 속성 값이 없을 경우에 대입할 값 지정. 
	 * 	-> required = false인 경우 사용
	 */
	@PostMapping("test2")
	public String paramTest2(
			@RequestParam("title") String title,
			@RequestParam("writer") String writer,
			@RequestParam("price") int price,
			@RequestParam(value="publisher", required = false, defaultValue = "교보") String publisher
			) {
		
		// input 태그 값을 작성하지 않은 경우 null이 아닌 빈칸 "" 이 제출된다!
		
		
		log.debug("title : " + title);
		log.debug("writer : " + writer);
		log.debug("price : " + price);
		log.debug("price : " + publisher);
		
		
		return "redirect:/param/main";
	}
	
	
	/* 3. @RequestParam 어노테이션 - 여러 개(복수) 파라미터 얻어오기
	 * 
	 * - String[], List, Map 형식으로 얻어올 수 있음
	 */
	
	@PostMapping("test3")
	public String paramTest3(
			@RequestParam("color") String[] colorArr,
			@RequestParam("fruit") List<String> fruitList,
			@RequestParam Map<String, Object> paramMap) {
		
		// input 태그 값을 작성하지 않은 경우 null이 아닌 빈칸 "" 이 제출된다!
		
		
		log.debug("colorArr : " + Arrays.toString(colorArr) );
		log.debug("fruitList : " + fruitList);
		
		
		// 모든 파라미터가 Map에 담김.
		// 단, 중복되는 key(name)의 파라미터가 Map에 담기면 덮어쓰기되어 마지막 하나만 남음
		log.debug("paramMap : " + paramMap);
		
		
		return "redirect:/param/main";
	}
	
	
	
	// 4. @ModelAttribute이용
	
	// - DTO(또는 VO)와 같이 사용하는 어노테이션
	
	// - 전달 받은 파라미터의 name 속성 값이
	//   같이 사용되는 DTO의 필드명과 같다면
	//   자동으로 setter를 호출해서 필드에 값을 세팅
	
	// *** @ModelAttribute 사용 시 주의사항 ***
	// - DTO에 기본 생성자가 필수로 존재해야 한다!
	// - DTO에 setter가 필수로 존재해야 한다!
	
	// *** @ModelAttribute 어노테이션은 생략이 가능하다! ***
	
	// *** @ModelAttribute를 이용해 값이 필드에 세팅된 객체를
	//		"커맨드 객체" 라고 한다 ***
	
	@PostMapping("test4")
	public String paramTest4(@ModelAttribute Member member) {
		
		log.debug("member : " + member);
		
		return "redirect:/param/main";
	}
}
