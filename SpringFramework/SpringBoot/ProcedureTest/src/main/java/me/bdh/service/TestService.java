package me.bdh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.bdh.dto.BoardDTO;
import me.bdh.mapper.TestMapper;

@Service
@RequiredArgsConstructor
public class TestService {
	
	private final TestMapper mapper;
	
	/** DTO INSERT 테스트
	 * @param dto
	 */
	public void dtoInsertTest(BoardDTO dto) {
		mapper.dtoInsertTest(dto);
	}
	
	/** List<DTO> INSERT 테스트
	 * @param list
	 * @return count
	 */
	public void listInsertTest(List<BoardDTO> list) {
		mapper.listInsertTest(list);
	}

}
