package edu.kh.project.common.interceptor;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import jakarta.servlet.http.HttpSession;


@Component
public class SessionHandshakeInterceptor implements HandshakeInterceptor{


	// WebsocketHandler 객체가 동작하기 전 필요한 처리를 하는 클래스
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
    	
    	// ServerHttpRequest : HttpServletRequest의 부모 인터페이스
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession();
            
            // 웹소켓 관련 코드 수행 처리 전 
            // 요청된 클라이언트의 session을 가로챔
            attributes.put("session", session);
        }
    	
        return true;
    }

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
	}
	
	
	
}
