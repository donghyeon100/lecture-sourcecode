package edu.kh.project.board.model.dto;

import java.util.List;

import javax.xml.stream.events.Comment;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// DTO(Data Transfer Object) : 데이터 전달용 객체
// - 테이블 컬럼과 똑같이 만들 필요 없음!
// --> CRUD 시 필요한 데이터를 담을 수 있는 형태

@Getter
@Setter
@ToString
public class Board {
    private int boardNo;
    private String boardTitle;
    private String boardContent;
    private String boardWriteDate;
    private String boardUpdateDate;
    private int readCount;
    private String boardDelFl;
    private int memberNo;
    private int boardCode;
    
    // 서브쿼리
    private int commentCount; // 댓글 수
    private int likeCount;    // 좋아요 수
    
    // 회원 join
    private String memberNickname; 
    
    // BOARD_IMG 테이블 join
    private String thumbnail;
    
    private String profileImg;
    
    // 이미지 목록
    private List<BoardImg> imageList;
    
    // 댓글 목록
    private List<Comment> commentList;
}
