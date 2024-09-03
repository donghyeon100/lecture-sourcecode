package me.bdh.service;

import java.security.SignatureException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JWTService {
	
	// secretKey는 256 비트 이상
	private static String secretKey = "mySpringBootJwtTokenPracticetestsecretkey";
	
	/** 토큰 생성 메서드
	 * @param claims : 사용자에 대한 프로퍼티나 속성을 이야기 합니다. 토큰 자체가 정보를 가지고 있는 방식인데, JWT는 이 Claim을 JSON을 이용해서 정의 
	 * @param expireAt : 만료일자  
	 * @return
	 */
	public String create (
		Map<String, Object> claims,
		LocalDateTime expireAt
		) {
		
		// 커스텀키 생성
		var key = Keys.hmacShaKeyFor(secretKey.getBytes()); 
		
		// LocalDateTime -> Date로 변환
		var _expireAt = Date.from(expireAt.atZone(ZoneId.systemDefault()).toInstant());
		
		return Jwts.builder()
			.signWith(key, SignatureAlgorithm.HS256) // signWith(key, 알고리즘) : jwt 토큰을 signing할 때 사용하는 key 사용
													 // HS256 알고리즘 사용
			.setClaims(claims)
			.setExpiration(_expireAt)
			.compact();
	}
	
	/** 토큰 검증
	 */
	public void validation (String Token) {
		
		// 커스텀키 생성
		var key = Keys.hmacShaKeyFor(secretKey.getBytes()); 
		
		//  
		var parser = Jwts.parserBuilder()
			.setSigningKey(key)
			.build();
		
		// 전달 받은 토큰을 파싱하여 base64로 변환된 토큰을 원래대로 돌림
		// 토큰이 유효하지 않거나 만료된 경우 예외 발생
		try {
			var result = parser.parseClaimsJws(Token);
			
			
			result.getBody().entrySet().forEach(value -> {
				log.info("key : {}, vlaue : {}", value.getKey(), value.getValue());
			});
			
		}catch (Exception e) {
			if(e instanceof SignatureException) { // 유효하지 않은 토큰
				throw new RuntimeException("JWT Token Not Valid Exception");
				
			} else if (e instanceof ExpiredJwtException) { // 만료된 토큰
				throw new RuntimeException("JWT Token Expried Exception");
				
			} else { // 기타 예외
				throw new RuntimeException("JWT Token Validation Exception");
			}
		}
		
		
	}
	
	
	
}
