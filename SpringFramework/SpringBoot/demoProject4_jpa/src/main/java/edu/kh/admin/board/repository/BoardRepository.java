package edu.kh.admin.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.kh.admin.board.domain.BoardEntity;

// JpaRepository : JPA 기본 구문
// JpaSpecificationExecutor : JPA 조건 구문
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, JpaSpecificationExecutor<BoardEntity>, CrudRepository<BoardEntity, Integer> {
	
	// 메서드명 짓는 방법
	// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
	// https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html#appendix.query.method.predicate
	
	public int countByBoardCode(int boardCode);

	public int countByBoardCodeAndBoardDelFl(int boardCode, String boardDelFl);
	
	// 트랜잭션 쿼리 방법 
	// https://docs.spring.io/spring-data/jpa/reference/jpa/transactions.html#transactional-query-methods
	// * Service 또는 Repository에 @Transactional 꼭 작성
	@Modifying
	@Query("update BOARD b set b.boardDelFl = 'Y' where b.boardCode = :boardCode and b.boardNo = :boardNo")
	public int updateBoardDelFlByBoardNo(@Param("boardCode") int boardCode, @Param("boardNo") int boardNo);
}
