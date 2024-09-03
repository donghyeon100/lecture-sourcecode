package edu.kh.admin.member.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor // 기본 생성자 자동 완성
@AllArgsConstructor
@Getter // getter 자동 완성
@Setter // setter 자동 완성
@ToString //  toString 자동 완성
@Builder
@Entity(name = "MEMBER") // JPA Entity 클래스를 나타내는 어노테이션, 매핑할 테이블 지정 (ORM)
public class MemberEntity {
	
	// @Entity(name = "MEMBER") : JPA Entity 클래스를 나타내는 어노테이션, 매핑할 테이블 지정 (ORM)
	// @Id : PK 역할 컬럼과 매핑된 필드 지정
	// @SequenceGenerator : 기본키 값을 시퀀스를 이용해서 값 증가
	// @Transient : 데이터베이스 테이블에 저장되지 않는 필드를 지정합니다 (임시 저장, 계산 결과를 저장하는 용도)
	
	
	@Id
	@SequenceGenerator(
		name  = "MEMBER_NO_GENERATOR",  // @SequenceGenerator 이름 지정
		sequenceName = "SEQ_MEMBER_NO", // 연결할 DB 시퀀스 이름 작성
		initialValue = 1,				// 시퀀스의 시작값 지정
		allocationSize = 1)				// 시퀀스 증가값 지정
	private int memberNo; // 해당 필드 값은 DB 시퀀스가 관리
	
	
	private String memberEmail;
	private String memberPw;
	private String memberNickname;
	
	@Column(name="MEMBER_TEL", columnDefinition = "CHAR")
	private String memberTel;
	
	private String memberAddress;
	private String profileImg;
	
	private Date enrollDate;
	
	@Transient // 데이터베이스 테이블에 저장되지 않는 필드를 지정합니다
	private String convertEnrollDate;
	
	
	
	@Column(name="MEMBER_DEL_FL", columnDefinition = "CHAR")
	private String memberDelFl;
	
	private int authority; 
	
}
