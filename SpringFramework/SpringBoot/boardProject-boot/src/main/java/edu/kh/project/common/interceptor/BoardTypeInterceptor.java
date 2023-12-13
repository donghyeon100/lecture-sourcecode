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
public class BoardTypeInterceptor implements HandlerInterceptor{
	
	@Autowired
	private BoardService service;
	
	/*
	 * preHandle (전처리) :  Dispatcher Servlet -> Controller 사이
	 * postHandle (후처리) : Controller -> Dispatcher Servlet 사이
	 * afterCompletion (뷰 완성 후): View Resolver -> Dispatcher Servlet 사이
	 * */
	
	
	
	// 전처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// application scope 내장 객체 얻어오기
		ServletContext application = request.getServletContext();
		log.info("인터셉터 동작");
		
		// application scope에 BOARD_TYPE이 조회되어 세팅되지 않았다면
		// -> 서버 시작 후 누구도 요청이 한적이 없을 경우
		if(application.getAttribute("boardTypeList") == null) {
			// 조회 서비스 호출
			List<Map<String, Object>> boardTypeList = service.selectBoardTypeList();
			// application scope에 세팅
			application.setAttribute("boardTypeList", boardTypeList);
			
		}
		
		
	
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	
	
	// 후처리
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
        
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
	
	
}


/*
 * INSERT INTO BOARD_TYPE VALUES (4,'테스트'); COMMIT;
 * 
 * DELETE FROM BOARD_TYPE WHERE BOARD_CODE = 4;
 */