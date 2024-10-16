package edu.kh.project.board.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Comment {
    private int commentNo;
    private String commentContent;
    private String commentWriteDate;
    private String commentDelFl;
    private int boardNo;
    private int memberNo;
//    private int parentCommentNo;
    private int parentCommentNo;
    
    private String memberNickname;
    private String profileImg;
}
