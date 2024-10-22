package edu.kh.project.common.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;

// @ControllerAdvice 어노테이션
// - 전역적 예외 처리: 모든 컨트롤러에서 발생하는 예외를 처리할 수 있도록 설정
// - 리소스 바인딩: 컨트롤러에 대한 공통적인 기능을 제공
// - 메시지 컨버전: 예외 메시지를 사용자에게 전달하기 위한 변환 기능 제공

@ControllerAdvice
@Slf4j
public class ExceptionController {

	// 404 발생 시 처리
	@ExceptionHandler(NoResourceFoundException.class)
	// 404 상태 코드를 반환하는 응답 상태 설정
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String notFound() {
		// 404 에러 페이지로 이동
		return "error/404";
	}

	// 파라미터가 누락된 경우 처리
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMessage", "필수 파라미터 '" + e.getParameterName() + "'가 누락되었습니다.");
		return "error/400"; // 400 에러 페이지로 이동
	}

	// 파라미터 타입 불일치 처리
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMessage", "파라미터 '" + e.getName() + "'의 타입이 일치하지 않습니다. " +
				"제출된 값: '" + e.getValue() + "' (매개 변수 타입: " + e.getRequiredType().getSimpleName() + ")");
		return "error/400"; // 400 에러 페이지로 이동
	}

	@ExceptionHandler(DataAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String handleDataAccessException(DataAccessException e, Model model) {
		e.printStackTrace();
		model.addAttribute("errorMessage", "DB(SQL) 오류 발생");
		model.addAttribute("e", e);
		model.addAttribute("stackTrace", getStackTraceAsString(e));
		return "error/500"; // 500 에러 페이지로 이동
	}

	// 예외의 스택 트레이스를 문자열로 변환하는 메서드
	private String getStackTraceAsString(Exception e) {
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement element : e.getStackTrace()) {
			String str = element.toString();
			str = str.replaceFirst("\\(([^)]+)\\)", "<span class='highlight'>($1)</span>");
			sb.append("<p>").append(str).append("</p>"); // 각 스택 트레이스를 줄바꿈으로 구분
		}
		return sb.toString();
	}


	
	// 프로젝트에서 발생하는 모든 종류의 예외를 처리
	//@ExceptionHandler(Exception.class)
	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String allExceptionHandler(Exception e, Model model) {
		// 예외 스택 트레이스를 콘솔에 출력
		e.printStackTrace();
		// 예외 객체를 모델에 추가하여 뷰에 전달
		model.addAttribute("errorMessage", "서버 내부 오류 발생");
		model.addAttribute("e", e);
		model.addAttribute("stackTrace", getStackTraceAsString(e));
		// 500 에러 페이지로 이동
		return "error/500";
	}

}
