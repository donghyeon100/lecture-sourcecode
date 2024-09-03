package me.bdh;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import me.bdh.dto.BoardDTO;
import me.bdh.service.TestService;

@SpringBootTest
@Slf4j
class ProcedureTestApplicationTests {

	@Autowired
	private TestService service;
	
	@Test
	void contextLoads() {
	}

	
	@Test
	void dtoInsertTest() {
		BoardDTO boardDTO = BoardDTO.builder()
									.boardTitle("테스트3")
									.boardContent("테스트 내용3 입니다")
									.writeDate("2024-06-09")
									.build();
		
		service.dtoInsertTest(boardDTO);
		
		log.info(boardDTO.toString());
	}
	
	@Test
	void listInsertTest() {
		
		List<BoardDTO> boardList = new ArrayList<>();
		
		for(int i=1 ; i<=10 ; i++) {
		BoardDTO boardDTO = BoardDTO.builder()
				.boardTitle("테스트 " + i)
				.boardContent("테스트 내용 " + i)
				.writeDate("2024-06-09")
				.build();
			boardList.add(boardDTO);
		}
		
		service.listInsertTest(boardList);
	}
}
