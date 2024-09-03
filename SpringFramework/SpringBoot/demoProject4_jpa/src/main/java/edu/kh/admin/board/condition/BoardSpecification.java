package edu.kh.admin.board.condition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import edu.kh.admin.board.domain.BoardEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class BoardSpecification {

	// criteria : 기준

	// boardCode가 같은 경우만 조회
	public static Specification<BoardEntity> equaledBoardCode(int boardCode) {
		return new Specification<BoardEntity>() {
			@Override
			public Predicate toPredicate(Root<BoardEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				return criteriaBuilder.equal(root.get("boardCode"), boardCode);
			}
		};
		
//		return (root, query, criteriaBuilder) 
//				-> criteriaBuilder.equal(root.get("boardCode"), boardCode);
	}
	
	
	
	// 복합 조건 처리(boardCode, 삭제 안된 게시글) 
	public static Specification<BoardEntity>  compoundConfition(Map<String, Object> conditionMap){
		
		return new Specification<BoardEntity>() {
			
			@Override
			public Predicate toPredicate(Root<BoardEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				for(String key : conditionMap.keySet()) {
					
					if(key.equals("boardTitle") || key.equals("boardContent"))
						predicates.add(criteriaBuilder.like(root.get(key), "%" + (String)conditionMap.get(key) + "%"));
					
					else
						predicates.add(criteriaBuilder.equal(root.get(key), conditionMap.get(key)));
				}
				
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		};
		
	}
}
