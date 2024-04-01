package edu.kh.dept.model.exception;

public class DepartmentInsertException extends RuntimeException{

	public DepartmentInsertException() {
		super("부서 추가(INSERT) 중 예외 발생");
	}
	
	public DepartmentInsertException(String message) {
		super(message);
	}
}
