package edu.kh.dept.model.service;

import java.sql.SQLException;
import java.util.List;

import edu.kh.dept.model.dto.Department;
import edu.kh.dept.model.exception.DepartmentInsertException;

public interface DepartmentService {

	// 선언되는 메서드는 모두 public abstract (추상 메서드)
	
	
	/** 모든 부서 조회
	 * @return deptList(부서 목록)
	 * @throws SQLException
	 */
	List<Department> selectAll() throws SQLException;

	
	/** 부서 추가
	 * @param dept
	 * @return result
	 * @throws DepartmentInsertException
	 */
	int insertDepartment(Department dept) throws DepartmentInsertException;


	/** 여러 부서 추가
	 * @param deptList
	 * @return result
	 * @throws DepartmentInsertException
	 */
	int multiInsert(List<Department> deptList) throws DepartmentInsertException;


	/** 부서 수정
	 * @param dept
	 * @return result
	 * @throws SQLException
	 */
	int updateDepartment(Department dept) throws SQLException;
	
	
	
}




