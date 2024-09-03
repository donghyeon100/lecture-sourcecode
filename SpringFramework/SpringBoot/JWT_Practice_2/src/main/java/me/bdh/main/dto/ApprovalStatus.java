package me.bdh.main.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApprovalStatus {
	WAITING(0, "대기"),
	ON_GOING(1, "진행중"),
	APPROVAL(2, "승인"),
	DENIED(3, "반려");
	
	private final int code;
	private final String desciption;
	
	public static ApprovalStatus of(int code) {
		for(ApprovalStatus status : values()) {
			if(status.getCode() == code) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown Status code" + code);
	}
	
}
