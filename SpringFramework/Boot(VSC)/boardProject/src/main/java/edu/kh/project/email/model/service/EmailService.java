package edu.kh.project.email.model.service;

import java.util.Map;

public interface EmailService {

	/** 이메일 보내기
	 * @param string
	 * @param email
	 * @return authKey
	 */
	String sendEmail(String string, String email);

	/** 이메일, 인증번호 확인
	 * @param map
	 * @return 인증 성공 여부
	 */
	boolean checkAuthKey(Map<String, Object> map);

}
