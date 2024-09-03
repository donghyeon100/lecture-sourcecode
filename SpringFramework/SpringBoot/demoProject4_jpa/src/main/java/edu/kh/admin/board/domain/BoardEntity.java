package edu.kh.admin.board.domain;

import java.sql.Date;
import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.Query;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostPersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "BOARD")
@ToString
public class BoardEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOARD_NO")
	@SequenceGenerator(
		name  = "Board_NO_GENERATOR",
		sequenceName = "SEQ_BOARD_NO",
		allocationSize = 1  // allocationSize: 시퀀스 한 번 호출에 증가하는 수  
							// -> DB에서 다음 시퀀스 값을 가져와 메모리에 저장 후 호출 시 마다 
							//   allocationSize 만큼 증가시켜서 사용 
							//   가져오는 기본값 : 50  (시퀀스 INCREAMENT BY를 50으로 수정해야함)
		)  
    
	private int boardNo;
	
    
	// @Column : 컬럼과 필드를 연결해주는 어노테이션 (필드명 == 컬럼명 같으면 안해줘도 됨)
	@Column(name = "BOARD_TITLE" , columnDefinition = "VARCHAR(100)") 
    private String boardTitle;

    private String boardContent;
    
    
    // 날짜에 default값 적용하려니까 java.sql.Date 타입 호환안되서 Timestamp로 바꾸니 해결됨 // columnDefinition = "DATE DEFAULT CURRENT_DATE",
//    @Column(name = "BOARD_WRITE_DATE", insertable = false, updatable = false)
    @Column(name = "BOARD_WRITE_DATE", updatable = false)
    private Date boardWriteDate;
    
    @Column(name = "BOARD_UPDATE_DATE", insertable=false)
    @ColumnDefault("sysdate")
    private Date boardUpdateDate;
    
    private int readCount;
    
    
    // insertable = false : INSERT 시 포함되지 않는 컬럼 지정
    @Column(name="BOARD_DEL_FL", columnDefinition = "CHAR(1) DEFAULT 'N'", insertable = false, updatable = false)
    private String boardDelFl;
    
    
    private int memberNo;
    private int boardCode;
    
    @Transient
    private String writeDate;
    
    @Transient
    private String updateDate;
    
    

	//JPA의 @PostPersist 사용 방법
	//@PostPersist 어노테이션은 JPA에서 엔티티가 저장된 후 호출되는 메서드를 지정하는 데 사용됩니다. 
    // 엔티티 생성, 업데이트 또는 삭제 후 특정 작업을 수행해야 하는 경우 유용합니다.
    
//    @PostPersist
//    private void afterSave() {
//        // 엔티티가 저장된 후 수행할 작업
//        System.out.println("boardNo: " + boardNo);
//
//        // 이메일 전송, 로그 기록 등의 작업 수행 가능
//    }
}
