package edu.kh.admin.board.service;

import java.util.List;
import java.util.Map;

import edu.kh.admin.board.domain.BoardEntity;

public interface BoardService {

	List<BoardEntity> findAll();

	List<BoardEntity> desc();

	List<BoardEntity> pagingTest1(int cp);

	List<BoardEntity> conditionTest1(int cp, int boardCode);

	List<BoardEntity> conditionTest2(int cp, int boardCode);

	List<BoardEntity> conditionTest3(int cp, int boardCode, Map<String, Object> paramMap);

	
	BoardEntity insertTest1(BoardEntity boardEntity);

	BoardEntity updateTest1(BoardEntity boardEntity);

	int boardCount(int boardCode);

	int deleteCount(int boardCode);

	int updateTest2(int boardCode, int boardNo);

//	BoardEntity updateTets2(BoardEntity boardEntity);
}
