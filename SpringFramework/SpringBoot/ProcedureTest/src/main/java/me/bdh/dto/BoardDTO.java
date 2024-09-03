package me.bdh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String writeDate;
}
