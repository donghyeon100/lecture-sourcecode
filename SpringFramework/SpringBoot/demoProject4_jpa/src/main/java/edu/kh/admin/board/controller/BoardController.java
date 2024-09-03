package edu.kh.admin.board.controller;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.kh.admin.board.domain.BoardEntity;
import edu.kh.admin.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	// 1. 게시판 테이블 조회
	@GetMapping("findAll")
	public List<BoardEntity> findAll() {
		return boardService.findAll();
	}
	
	// 2. 역순 조회
	@GetMapping("desc")
	public List<BoardEntity> desc() {
		return boardService.desc();
	}
	
	// 3. 페이징 처리
	@GetMapping("pagingTest1")
	public List<BoardEntity> pagingTest1(
			@RequestParam(name = "cp", required = false, defaultValue = "1") int cp) {
		return boardService.pagingTest1(cp);
	}
	
	// 4. 조건 처리 + 페이징 처리
	@GetMapping("conditionTest1/{boardCode:[0-9]+}")
	public List<BoardEntity> conditionTest1(
			@RequestParam(name = "cp", required = false, defaultValue = "1") int cp,
			@PathVariable("boardCode") int boardCode) {
		return boardService.conditionTest1(cp, boardCode);
	}
	
	
	// 5. 복합 조건 처리(boardCode, 삭제 안된 게시글) + 페이징 처리
	@GetMapping("conditionTest2/{boardCode:[0-9]+}")
	public List<BoardEntity> conditionTest2(
			@RequestParam(name = "cp", required = false, defaultValue = "1") int cp,
			@PathVariable("boardCode") int boardCode) {
		return boardService.conditionTest2(cp, boardCode);
	}
	
	
	// 6. 복합 조건 처리(boardCode, 삭제 안된 게시글, 게시글 검색) + 페이징 처리
	@GetMapping("conditionTest3/{boardCode:[0-9]+}")
	public List<BoardEntity> conditionTest3(
		@RequestParam(name = "cp", required = false, defaultValue = "1") int cp,
		@RequestParam Map<String, Object> paramMap,
		@PathVariable("boardCode") int boardCode) {
			
		return boardService.conditionTest3(cp, boardCode, paramMap);
	}
	
	
	// -----------------------------------------------------------------------------
	
	
	// 7. 게시글 삽입 테스트1
	@PostMapping("insertTest1/{boardCode:[0-9]+}")
	public String insertTest1 (@RequestBody BoardEntity boardEntity, @PathVariable("boardCode") int boardCode) {
		boardEntity.setBoardCode(boardCode);
		BoardEntity result = boardService.insertTest1(boardEntity);
		
		log.info("result : {}", result);
		return result.getBoardNo() + "게시글 삽입 성공";
	}
	
	
	// 8. 게시글 수정 테스트1
	@PutMapping("updateTest1/{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public String updateTest1(
			@RequestBody BoardEntity boardEntity, 
			@PathVariable("boardCode") int boardCode, 
			@PathVariable("boardNo") int boardNo) {
		
		boardEntity.setBoardCode(boardCode);
		boardEntity.setBoardNo(boardNo);
		
		// DEFAULT SYSDATE로 현재 시간으로 업데이트 하려고 했으나 잘 안됨 ...
		boardEntity.setBoardUpdateDate(new Date(System.currentTimeMillis()));
		BoardEntity result = boardService.updateTest1(boardEntity);
		
		log.info("result : {}", result);
		return result.getBoardNo() + "게시글 제목/내용 수정 성공";
	}
	
	// 게시글 수정 테스트2 (상태 값만 'Y'로 변경)
//	@PutMapping("updateTest2/{boardCode:[0-9]+}/{boardNo:[0-9]+}")
//	public String updateTest2(
//		@RequestBody BoardEntity boardEntity, 
//		@PathVariable("boardCode") int boardCode, 
//		@PathVariable("boardNo") int boardNo) {
//		
//		boardEntity.setBoardCode(boardCode);
//		boardEntity.setBoardNo(boardNo);
//		boardEntity.setBoardDelFl("Y");
//		
//		BoardEntity result = boardService.updateTets2(boardEntity);
//		
//		log.info("result : {}", result);
//		return result.getBoardNo() + "게시글 상태 'Y'로 변경 성공";
//		
//	}
	
	// 9. 특정 게시판에 게시글 수 조회
	@GetMapping("boardCount/{boardCode:[0-9]+}")
	public int boardCount(@PathVariable("boardCode") int boardCode) {
		return boardService.boardCount(boardCode);
	}
	
	
	// 10. 특정 게시판에 삭제된 게시글 수 조회
	@GetMapping("deleteCount/{boardCode:[0-9]+}")
	public int deleteCount(@PathVariable("boardCode") int boardCode) {
		return boardService.deleteCount(boardCode);
	}
	
	// 11. 게시글 수정 테스트2 (상태 값만 'Y'로 변경
	@PutMapping("updateTest2/{boardCode:[0-9]+}/{boardNo:[0-9]+}")
	public int updateTest2(
			@PathVariable("boardCode") int boardCode, 
			@PathVariable("boardNo") int boardNo) {
		return boardService.updateTest2(boardCode, boardNo);
	}
	
	
	
		
}
