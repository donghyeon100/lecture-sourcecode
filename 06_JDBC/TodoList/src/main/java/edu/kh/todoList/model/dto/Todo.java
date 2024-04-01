package edu.kh.todoList.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

// DTO (Data Trasfer Object) : (관련된 값의 묶음)값 전달 역할
// == VO(Value Object)

public class Todo{
	
	private int todoNo; // 할 일 번호
	private String todoTitle; // 할 일 제목
	private String todoContent; // 상세 내용
	private String complete; // 완료 여부
	private String regDate; // 등록 날짜

	
	// 기본 생성자
	public Todo() {  
		super();
	}

	
	
	public Todo(int todoNo, String todoTitle, String complete, String regDate) {
		super();
		this.todoNo = todoNo;
		this.todoTitle = todoTitle;
		this.complete = complete;
		this.regDate = regDate;
	}



	public Todo(int todoNo, String todoTitle, String todoContent, String complete, String regDate) {
		super();
		this.todoNo = todoNo;
		this.todoTitle = todoTitle;
		this.todoContent = todoContent;
		this.complete = complete;
		this.regDate = regDate;
	}


	public int getTodoNo() {
		return todoNo;
	}


	public void setTodoNo(int todoNo) {
		this.todoNo = todoNo;
	}


	public String getTodoTitle() {
		return todoTitle;
	}


	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}


	public String getTodoContent() {
		return todoContent;
	}


	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}


	public String getComplete() {
		return complete;
	}


	public void setComplete(String complete) {
		this.complete = complete;
	}


	public String getRegDate() {
		return regDate;
	}


	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}


	@Override
	public String toString() {
		return "Todo [todoNo=" + todoNo + ", todoTitle=" + todoTitle + ", todoContent=" + todoContent + ", complete="
				+ complete + ", regDate=" + regDate + "]";
	}

	
}
