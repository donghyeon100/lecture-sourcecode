package edu.kh.todoList.model.service;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.kh.todoList.model.dao.TodoListDAO;
import edu.kh.todoList.model.dao.TodoListDAOImpl;
import edu.kh.todoList.model.dto.Todo;

//Service : 비즈니스 로직
//- 알맞은 DAO 메서드 호출
//- 트랜잭션 처리 -> Connection 필요 -> Service에서 Connection 생성
//- 데이터 가공
public class TodoListServiceImpl implements TodoListService {

	// 필드
	private TodoListDAO dao = null;
	
	// 기본 생성자
	public TodoListServiceImpl() {
		
		// TodoListServiceImpl 객체가 생성 될 때
		dao = new TodoListDAOImpl(); // TodoListDAOImpl 객체 생성
	}
	
	// --------------------------------------------------------------------------

	/* selectAll */
	@Override
	public Map<String, Object> selectAll() throws SQLException {
		
		// 1. Connection 객체 생성
		Connection conn = getConnection();
		
		// 2. 할 일 목록 DAO에서 얻어오기
		List<Todo> todoList = dao.selectAll(conn);
		
		// 2. 할 일 목록에서 완료(complete 필드 값이 Y)인 요소가 몇 개인지 카운트
		int completeCount = 0;
		
		for(Todo todo : todoList) { // TodoList 순차 접근
			
			if(todo.getComplete().equals("Y")) { // Y인 경우
				completeCount++;
			}
		}
		close(conn);
		
		// 3. todoList, completeCount를 저장할 Map 생성
		// -> 메서드는 반환을 하나 밖에 못해서 
		//    여러 개를 반환해야 되는 경우 묶어서 반환
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("todoList", todoList);
		map.put("completeCount", completeCount);
		
		// 4. map 반환
		return map;
	}
	
	// --------------------------------------------------------------------------

	/* addTodo */
	@Override
	public int addTodo(String todoTitle, String todoContent) throws SQLException {
		
		Connection conn = getConnection();
		
		int result = dao.addTodo(conn, todoTitle, todoContent);
		
		if(result > 0) commit(conn);
		else					 rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	// --------------------------------------------------------------------------

	
	/* selectTodo(할 일 상세 조회) */
	@Override
	public Todo selectTodo(int todoNo) throws SQLException{
		
		
		Connection conn = getConnection();
		
		Todo todo = dao.selectTodo(conn, todoNo);
		
		close(conn);
		
		return todo;
	}

	
	

	
	// --------------------------------------------------------------------------

	/* todoComplete */
	@Override
	public boolean todoComplete(int index) throws SQLException {
		return dao.todoComplete(null, index);
	}
	// Service 메서드가 별도 처리를 하지 않음
	// -> 아무것도 안한다고 해서 서비스를 사용하지 않으면 안된다!!!
	
	// --------------------------------------------------------------------------

	
	/* todoUpdate */
	@Override
	public boolean todoUpdate(int index, String title, String detail) throws FileNotFoundException, IOException {
		return dao.todoUpdate(null, index, title, detail);
	}
	

	// --------------------------------------------------------------------------

	/* todoDelete */
	@Override
	public String todoDelete(int index) throws FileNotFoundException, IOException {
		
		// DAO 메서드 호출 후 결과 반환 받기
		// -> 삭제된 Todo 또는 null
//		Todo todo = dao.todoDelete(null, index);
//		
//		if(todo == null) return null;
//		
//		return todo.getTitle(); // 제목 반환
		return null;
	}

	
	
}
