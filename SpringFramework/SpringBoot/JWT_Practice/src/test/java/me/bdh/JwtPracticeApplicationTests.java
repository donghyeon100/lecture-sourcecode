package me.bdh;

import java.time.LocalDateTime;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import me.bdh.service.JWTService;

@SpringBootTest
class JwtPracticeApplicationTests {
	
	@Autowired
	private JWTService jwtService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void tokenCreate() {
		// 유저가 누구인지
		var claims = new HashMap<String, Object>();
		claims.put("user_id", 923);
		
		// 만료 기간 10분으로 지정
		var expriedAt = LocalDateTime.now().plusMinutes(10);
		
		var jwtToken = jwtService.create(claims, expriedAt);
		System.out.println(jwtToken);
		
		// https://jwt.io/ 에 가서 
		// 만들어진 토큰이 유효한지 확인하기
	}
	
	
	@Test
	public void tokenValidation() {
		var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX22lkIjo5MjMsImV4cCI6MTcxNzU1OTg4M30.bGTSygs2-5qc24R5jSOnLd4se5IExAWqvIWOSZ5Yvu0";
		jwtService.validation(token);
		
		// 만료 시간이 지난 경우 
		// 또는 토큰이 변조된 경우 테스트 실패
	}

}
