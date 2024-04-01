package edu.kh.todoList.model.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.todoList.model.dto.Todo;

// DAO (Data Access Object) : 
//  데이터가 저장된 파일/DB/외부 장치에 접근하는 역할
public interface TodoListDAO {

	// public abstract를 명시하지 않아도 public abstract으로 해석됨!!
	
	
	/** 할 일 목록 반환 
	 * @return todoList
	 */
	List<Todo> selectAll(Connection conn)  throws SQLException;

	
	/** 할 일 추가
	 * @param SQLException
	 * @param todoContent
	 * @return result
	 * @throws SQLException
	 */
	int addTodo(Connection conn, String todoTitle, String todoContent)  throws SQLException;
	
	
	
	/** todo 상세 조회
	 * @param conn
	 * @param todoNo
	 * @return
	 * @throws SQLException
	 */
	Todo selectTodo(Connection conn, int todoNo)  throws SQLException;

	
	
	/** 할 일 완료 여부 변경 (O <-> X)
	 * @param index
	 * @return 해당 index 요소의 완료 여부가 변경되면 true
	 * 				 index 요소가 없으면 false
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	boolean todoComplete(Connection conn, int index)  throws SQLException;

	
	/** 할 일 수정
	 * @param index
	 * @param title
	 * @param detail
	 * @return 성공 true, 실패 시 false
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	boolean todoUpdate(Connection conn, int index, String title, String detail) throws FileNotFoundException, IOException;


	/** 할 일 삭제
	 * @param index
	 * @return 성공 시 삭제된 할 일(Todo) 반환
	 * 				 인덱스 범위 초과로 실패 시 null 반환 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	Todo todoDelete(Connection conn, int index) throws FileNotFoundException, IOException;
}
