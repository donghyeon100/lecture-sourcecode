package edu.kh.admin.board.service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.kh.admin.board.condition.BoardSpecification;
import edu.kh.admin.board.domain.BoardEntity;
import edu.kh.admin.board.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository boardRepository;
	
	@Override
	public List<BoardEntity> findAll() {
		return boardRepository.findAll();
	}
	
	@Override
	public List<BoardEntity> desc() {
		return boardRepository.findAll(Sort.by(Sort.Order.desc("boardNo")));
	}
	
	@Override
	public List<BoardEntity> pagingTest1(int cp) {
		/*
		Pageable 인터페이스는 직접 작성할 수 없으며, Spring Data JPA에서 제공하는 인터페이스입니다.
		
		Pageable 인터페이스는 다음과 같은 메서드를 제공하며, 페이징 처리에 필요한 정보를 담고 있습니다.
		
		getPageNumber(): 현재 페이지 번호를 반환합니다.
		getPageSize(): 페이지당 표시할 데이터 개수를 반환합니다.
		getOffset(): 현재 페이지의 시작 위치를 반환합니다.
		getSort(): 정렬 기준을 반환합니다.
		isSorted(): 정렬 여부를 반환합니다.
		previousPageable(): 이전 페이지 정보를 담은 Pageable 객체를 반환합니다.
		nextPageable(): 다음 페이지 정보를 담은 Pageable 객체를 반환합니다.
		firstPageable(): 첫 번째 페이지 정보를 담은 Pageable 객체를 반환합니다.
		lastPageable(): 마지막 페이지 정보를 담은 Pageable 객체를 반환합니다.
		*/
		
		// 0 == 1페이지
		Pageable pageable = PageRequest.of(cp-1, 10, Sort.by(Sort.Order.desc("boardNo")));
		
		
		
		/* Page 인터페이스 
		 * Page<T> 인터페이스는 특정 페이지에 대한 요소 목록을 나타냅니다. 페이지는 페이징된 데이터 결과 세트에서 하위 집합을 나타냅니다. 주로 데이터베이스 쿼리의 결과를 나타내는 데 사용됩니다.
			메서드 설명:
			getContent(): 현재 페이지의 내용을 반환합니다. 이는 실제 엔터티 또는 데이터 객체의 목록입니다.
			getNumber(): 현재 페이지 번호를 반환합니다. 페이지 번호는 0부터 시작합니다.
			getSize(): 페이지 크기를 반환합니다. 페이지 크기는 한 페이지당 요소 수입니다.
			getTotalElements(): 전체 요소 수를 반환합니다. 이는 전체 결과 세트의 크기입니다.
			getTotalPages(): 전체 페이지 수를 반환합니다.
			hasContent(): 페이지에 콘텐츠가 있는지 여부를 반환합니다.
			hasNext(): 다음 페이지가 있는지 여부를 반환합니다.
			hasPrevious(): 이전 페이지가 있는지 여부를 반환합니다.
			isFirst(): 현재 페이지가 첫 번째 페이지인지 여부를 반환합니다.
			isLast(): 현재 페이지가 마지막 페이지인지 여부를 반환합니다.
			nextPageable(): 다음 페이지의 Pageable 객체를 반환합니다.
			previousPageable(): 이전 페이지의 Pageable 객체를 반환합니다.
			iterator(): 페이지의 요소에 대한 반복자를 반환합니다.
			stream(): 페이지의 요소에 대한 스트림을 반환합니다.
		 * */
		Page<BoardEntity> result = boardRepository.findAll(pageable); // <BoardEntity>
		
		
		return result.getContent();
	}
	
	

	// 조건 처리 + 페이징 처리
	@Override
	public List<BoardEntity> conditionTest1(int cp, int boardCode) {
		
		Pageable pageable = PageRequest.of(cp-1, 10, Sort.by(Sort.Order.desc("boardNo")));
		
		Page<BoardEntity> result = boardRepository.findAll(BoardSpecification.equaledBoardCode(boardCode), pageable);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		for(BoardEntity entity : result) {
			entity.setWriteDate(sdf.format(entity.getBoardWriteDate()));
			if(entity.getBoardUpdateDate() != null)
					entity.setUpdateDate(sdf.format(entity.getBoardUpdateDate()));
		}
		
		return result.getContent();
	}
	
	
	// 복합 조건 처리(boardCode, 삭제 안된 게시글) + 페이징 처리
	@Override
	public List<BoardEntity> conditionTest2(int cp, int boardCode) {
		
		// boardNo 내림 차순으로 페이징 처리
		Pageable pageable = PageRequest.of(cp-1, 10, Sort.by(Sort.Order.desc("boardNo")));
		
		// 검색 조건을 담은 맵
		Map<String , Object> conditionMap = Map.of("boardCode", boardCode, "boardDelFl", "N");
		
		// 조건 + 페이징처리에 맞게 Board 테이블 조회
		Page<BoardEntity> result = boardRepository.findAll(BoardSpecification.compoundConfition(conditionMap), pageable);
			
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		for(BoardEntity entity : result) {
			entity.setWriteDate(sdf.format(entity.getBoardWriteDate()));
			if(entity.getBoardUpdateDate() != null)
					entity.setUpdateDate(sdf.format(entity.getBoardUpdateDate()));
		}
		
		return result.getContent();
	}
	
	// 6. 복합 조건 처리(boardCode, 삭제 안된 게시글, 제목에 "10" 포함) + 페이징 처리
	@Override
	public List<BoardEntity> conditionTest3(int cp, int boardCode, Map<String, Object> paramMap) {
		
		// boardNo 내림 차순으로 페이징 처리
		Pageable pageable = PageRequest.of(cp-1, 10, Sort.by(Sort.Order.desc("boardNo")));
		
		paramMap.remove("cp"); // cp 제거
		
		// 검색 조건을 담은 맵
		Map<String , Object> conditionMap = new HashMap<>();
//				Map.of("boardCode", boardCode, "boardDelFl", "N");
		
		conditionMap.put("boardCode", boardCode);
		conditionMap.put("boardDelFl", "N");
		
		// conditionMap에 paramMap의 값(key, query) 옮겨 담기
			
		String k = "t";
		switch((String)paramMap.get("key")) {
		case "t"  : k = "boardTitle";   break;
		case "c"  : k = "boardContent"; break;
		//	case "w"  : k = "MEMBER_NICKNAME";  break;
		//	case "tc" : k = "BOARD_TITLE or BOARD_CONTENT"; break;
		}
		
		
		conditionMap.put(k, paramMap.get("query"));
		
		
		// 조건 + 페이징처리에 맞게 Board 테이블 조회
		Page<BoardEntity> result = boardRepository.findAll(BoardSpecification.compoundConfition(conditionMap), pageable);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		
		for(BoardEntity entity : result) {
			entity.setWriteDate(sdf.format(entity.getBoardWriteDate()));
			if(entity.getBoardUpdateDate() != null)
					entity.setUpdateDate(sdf.format(entity.getBoardUpdateDate()));
		}
		
		return result.getContent();
	}
	
	
	// 7. 게시글 삽입 테스트1
	@Override
	public BoardEntity insertTest1(BoardEntity boardEntity) {
		BoardEntity result = boardRepository.save(boardEntity);
		return result;
	}
	

	// 8. 게시글 수정 테스트2
	@Override
	public BoardEntity updateTest1(BoardEntity boardEntity) {
		// PK에 해당되는 컬럼의 값이 
		// save() 매개변수로 대입된 엔티티의 필드 값이 
		// 없을 경우 INSERT
		// 있으면 UPDATE
		return  boardRepository.save(boardEntity);
	}
	
	// 9. 특정 게시판에 게시글 수 조회
	@Override
	public int boardCount(int boardCode) {
		return boardRepository.countByBoardCode(boardCode);
	}
	
	// 10. 특정 게시판에 삭제된 게시글 수 조회
	@Override
	public int deleteCount(int boardCode) {
		return boardRepository.countByBoardCodeAndBoardDelFl(boardCode, "Y");
	}
	
	
	// 11. 게시글 수정 테스트2 (상태 값만 'Y'로 변경)
	@Transactional
	@Override
	public int updateTest2(int boardCode, int boardNo) {
		return boardRepository.updateBoardDelFlByBoardNo(boardCode, boardNo);
	}
}
