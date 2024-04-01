package edu.kh.demo.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private String memberId;
	private String memberPw;
	private String memberName;
}
