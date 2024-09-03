package edu.kh.project.common.interceptor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.kh.project.board.model.service.BoardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

// Interceptor : 요청/응답을 가로채는 객체
// Client <-> Filter <-> Dispatcher Servlet <-> Interceptor <-> Controller
@Slf4j
public class BoardNameInterceptor implements HandlerInterceptor{
	
	
	/*
	 * preHandle (전처리) :  Dispatcher Servlet -> Controller 사이
	 * postHandle (후처리) : Controller -> Dispatcher Servlet 사이
	 * afterCompletion (뷰 완성 후): View Resolver -> Dispatcher Servlet 사이
	 * */
	
	// 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
	// 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// application scope 내장 객체 얻어오기
		ServletContext application = request.getServletContext();
		
		List<Map<String, Object>> boardTypeList = (List<Map<String, Object>>)application.getAttribute("boardTypeList");
		
		// Uniform Resource Identifier : 통합 자원 식별자
		// - 자원 이름(주소)만 봐도 무엇인지 구별할 수 있는 문자열
		String uri = request.getRequestURI();
//				log.debug("uri : " + uri);
		
		try {
											// ["", "board", "1"]
			int boardCode = Integer.parseInt( uri.split("/")[2] );
			
			
			// boardTypeList에서 boardCode를 하나씩 꺼내어 비교
			for(Map<String, Object> boardType : boardTypeList) {
				
				// String.valueOf(값) : String으로 변환
				
				int temp = 
						Integer.parseInt( String.valueOf( boardType.get("boardCode") ));
				
				
				// 비교 결과가 같다면
				// request scope에 boardName을 추가
				if(temp == boardCode) {
					request.setAttribute("boardName", boardType.get("boardName"));
					break;
				}
			}
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}
