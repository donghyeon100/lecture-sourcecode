package edu.kh.todoList.model.dao;

import static edu.kh.todoList.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.kh.todoList.model.dto.Todo;

public class TodoListDAOImpl implements TodoListDAO{

	// 필드
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Properties prop; // Map<String, String> 형태, 파일 입출력 쉬움
	
	
	// 기본 생성자
	// - 객체 생성 시 sql.xml 파일 내용을 읽어와 prop에 저장
	public TodoListDAOImpl() {
		
		try {
			prop = new Properties();
			String path = TodoListDAOImpl.class.getResource("/edu/kh/todoList/sql/todo-sql.xml").getPath();
			prop.loadFromXML(new FileInputStream(path));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	// ------------------------------------------------------------------------------
	
	@Override
	public List<Todo> selectAll(Connection conn) throws SQLException{
		
		List<Todo> todoList = new ArrayList<Todo>();
		
		try {
			String sql = prop.getProperty("selectAll");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int todoNo = rs.getInt("TODO_NO");
				String todoTitle = rs.getString("TODO_TITLE");
				String complete = rs.getString("COMPLETE");
				String regDate = rs.getString("REG_DATE");
				
				Todo todo = new Todo(todoNo, todoTitle, complete, regDate);
				
				todoList.add(todo);
			}
			
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return todoList;
	}
	
	
	//------------------------------------------------------------------------------
	
	/* addTodo */
	@Override
	public int addTodo(Connection conn, String todoTitle, String todoContent) throws SQLException {
	
		int result = 0;
		
		try {
			String sql = prop.getProperty("addTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, todoTitle);
			pstmt.setString(2, todoContent);
			
			result = pstmt.executeUpdate();
			
		}finally {
			close(pstmt);
		}
		
		return result;
 	}

	//------------------------------------------------------------------------------
	
	/* todoDetailView */
	@Override
	public Todo selectTodo(Connection conn, int todoNo)  throws SQLException{
	
		Todo todo = null;
		
		try {
			String sql = prop.getProperty("selectTodo");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, todoNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("TODO_NO");
				String todoTitle = rs.getString("TODO_TITLE");
				String todoContent = rs.getString("TODO_CONTENT");
				String complete = rs.getString("COMPLETE");
				String regDate = rs.getString("REG_DATE");
				
				todo = new Todo(no, todoTitle, todoContent, complete, regDate);
			}
			
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return todo;
	}

	
	//------------------------------------------------------------------------------
	
	/* todoComplete */
	@Override
	public boolean todoComplete(Connection conn, int index)  throws SQLException {

		return true;
	}
	
	
	//------------------------------------------------------------------------------
	
	/* todoUpdate */
	@Override
	public boolean todoUpdate(Connection conn, int index, String title, String detail) throws FileNotFoundException, IOException {
	
		return false;
	}
	
	
	//------------------------------------------------------------------------------
	
	/* todoDelete */
	@Override
	public Todo todoDelete(Connection conn, int index) throws FileNotFoundException, IOException {
		
		return null;
	}


	
	
}
