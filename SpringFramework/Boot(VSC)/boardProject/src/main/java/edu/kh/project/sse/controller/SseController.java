package edu.kh.project.sse.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.websocket.model.dto.Notification;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("sse")
public class SseController {

	// 클라이언트 연결 정보를 저장할 Map
	// ConcurrentHashMap : 멀티스레드 환경에서 동기화를 보장하는 Map
	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

	// 클라이언트 연결
	@GetMapping("connect")
	public SseEmitter connectSse(
			@SessionAttribute("loginMember") Member loginMember) {

		String clientId = loginMember.getMemberNo() + "";

		// SseEmitter 객체 생성
		SseEmitter emitter = new SseEmitter(10 * 60 * 1000L); // 10분

		// 클라이언트 정보를 Map에 저장
		emitters.put(clientId, emitter);

		// 클라이언트 연결 종료 시 Map에서 제거
		emitter.onCompletion(() -> emitters.remove(clientId));

		// 클라이언트 타임 아웃 시 Map에서 제거
		emitter.onTimeout(() -> emitters.remove(clientId));

		return emitter;
	}

	// 알림 웹소켓 메시지 전송
	@PostMapping("notify")
	public void notifyClient(
			@RequestBody Notification notification,
			@SessionAttribute("loginMember") Member loginMember) {

		log.debug("notification : " + notification);

		notification.setSendMemberNo(loginMember.getMemberNo());

		String clientId = loginMember.getMemberNo() + "";

		SseEmitter emitter = emitters.get(clientId);
		if (emitter != null) {
			try {
				emitter.send(notification);
			} catch (Exception e) {
				emitters.remove(clientId);
			}
		}
	}

	// Spring에서 비동기 요청 처리 중에 타임아웃이 발생했을 때 발생하는 예외 처리
	@ExceptionHandler(AsyncRequestTimeoutException.class)
	@ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
	public void handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex) {
		// 로그 기록 또는 추가 처리
		System.out.println("AsyncRequestTimeoutException 발생: " + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public void sseControllerException(Exception ex) {
		// 로그 기록 또는 추가 처리
		System.out.println("Exception 발생: " + ex.getMessage());
	}
}