package edu.kh.demo.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.demo.model.dto.Todo;
import edu.kh.demo.model.mapper.TodoMapper;
import lombok.extern.slf4j.Slf4j;

// --------------------------------------------------
// @Transactional
// - 트랜잭션 처리를 수행하라고 지시하는 어노테이션
//   (== 선언적 트랜잭션 처리)

// - 정상 코드 수행 시 COMMIT

// - 기본값 : Service 내부 코드 수행 중 RuntimeException 발생 시 rollback

// - rollbackFor 속성 : 어떤 예외가 발생했을 때 rollback할지 지정
// ---------------------------------------------------


@Transactional(rollbackFor = Exception.class)
@Slf4j
@Service // 비즈니스 로직(데이터 가공, 트랜잭션 처리) 역할임을 명시 + Bean 등록
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoMapper mapper;
	
	// Spring + Mybatis를 이용할 경우
	// Connection + Mybatis 전용 객체인 SqlSession이 생성되 Bean으로 등록되어 있음
	// -> 별도로 Connection을 얻어올 필요 없음
	// + @Mapper 인터페이스 메서드 호출 시 자동으로 *-mapper.xml 수행될 때 자동으로 SqlSession이 주입됨
	
	
	@Override
	public Map<String, Object> selectAll() {
		
		List<Todo> todoList = mapper.selectAll();
		
		int completeCount = mapper.getCompleteCount();
		
		log.debug(todoList.toString());
		log.debug("completeCount : " + completeCount);
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		return map;
	}
	
	
	
	@Override
	public int addTodo(Todo todo) {
		return mapper.addTodo(todo);
	}
}
