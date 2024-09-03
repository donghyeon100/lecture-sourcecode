package me.bdh.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.bdh.dto.BoardDTO;

@Mapper
public interface TestMapper {

	void dtoInsertTest(BoardDTO dto);

//	void listInsertTest(@Param("boardList") List<BoardDTO> list);
	void listInsertTest(List<BoardDTO> list);
	

}
