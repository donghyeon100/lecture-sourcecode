package edu.kh.dept.model.service;

// JDBCTemplate 클래스의 static 메서드 모두 가져오기
import static edu.kh.dept.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dao.DepartmentDAO;
import edu.kh.dept.model.dao.DepartmentDAOImpl;
import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

// Service 
// - 비즈니스 로직 처리
//  (데이터 가공, 트랜잭션 제어 처리)

// - 트랜잭션 제어 처리
//  -> 하나의 Service 메서드가 여러 DAO 메서드를 호출할 수 있다!
//   - 호출한 모드 DAO 메서드가 성공했을 때 Commit
//   - 하나라도 실패하면 Rollback 수행

public class DepartmentServiceImpl implements DepartmentService{

	// DAO 객체 생성
	private DepartmentDAO dao = new DepartmentDAOImpl();

	// 모든 부서 조회
	@Override
	public List<Department> selectAll() throws SQLException {
		
		/* 1. 커넥션 얻어오기 */
		Connection conn = getConnection();
		
		/* 2. DAO 메서드 호출 (매개 변수로 Connection 전달) + 조회 결과 반환 */
		List<Department> deptList = dao.selectAll(conn);
		
		/* 3. SELECT는 트랜잭션 제어 필요 없음*/
		
		/* 4. 사용 완료된 Connection 닫기*/
		close(conn);
		
		/* 5. 결과 반환*/
		return deptList;
	}
	
	
	// 부서 추가
	@Override
	public int insertDepartment(Department dept) throws DepartmentInsertException {
		
		/* 1. 커넥션 얻어오기 */
		Connection conn = getConnection();
		int result = 0;
		
		try {
			/* 2. DAO 메서드 호출 (매개 변수로 Connection 전달) +  결과 반환 */
			result = dao.insertDepartment(conn, dept);
			
			/* 3. 트랜잭션 제어*/ 
			if(result > 0)	commit(conn);
			else						rollback(conn);
			
		}catch(SQLException e) {
			// INSERT 시 부서 코드 PK 제약 조건 위배 시 SQLException 발생
			e.printStackTrace();
			rollback(conn); // 전체 롤백
			throw new DepartmentInsertException();
			
		}finally {
			/* 4. 사용 완료된 Connection 닫기*/
			close(conn);
		}
		
		/* 5. 결과 반환*/
		return result;
	}
	
	
	// 여러 부서 추가
	@Override
	public int multiInsert(List<Department> deptList) throws DepartmentInsertException{
		
		/* 1. 커넥션 얻어오기 */
		Connection conn = getConnection();
		
		int result = 0;
		String currentDeptId = null;
		
		try {
			
			for(Department dept : deptList) {
				currentDeptId = dept.getDeptId();
				result += dao.insertDepartment(conn, dept);
			}
			
			/* 3. 트랜잭션 제어*/ 
			
			// 삽입된 행의 개수와 List의 길이가 같을 경우
			// == 모두 삽입 성공
			if(result == deptList.size())	commit(conn);
			else						rollback(conn);
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			// INSERT 시 부서 코드 PK 제약 조건 위배 시 SQLException 발생
			rollback(conn); // 전체 롤백
			throw new DepartmentInsertException(currentDeptId + " 부서 코드가 이미 존재합니다");
			
		}finally {
			close(conn);
		}
		
		return result;
	}
	
	
	/// 부서 수정
	@Override
	public int updateDepartment(Department dept) throws SQLException {
		/* 1. 커넥션 얻어오기 */
		Connection conn = getConnection();
		
		/* 2. DAO 메서드 호출 + 결과 반환 받기 */
		int result = dao.updateDepartment(conn, dept);
		
		/* 3. 트랜잭션 처리 */
		if(result > 0)	commit(conn);
		else						rollback(conn);
		
		/* 4. 커넥션 반환 */
		close(conn);
		
		/* 5. 결과 반환 */
		return result;
	}
	
	
	
}
