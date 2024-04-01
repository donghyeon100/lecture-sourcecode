package edu.kh.jsp.model.dto;

public class Student {
	private String studentNumber;
	private String name;
	
	public Student() {
		// TODO Auto-generated constructor stub
	}

	public Student(String studentNumber, String name) {
		super();
		this.studentNumber = studentNumber;
		this.name = name;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [studentNumber=" + studentNumber + ", name=" + name + "]";
	}
	
	
}
