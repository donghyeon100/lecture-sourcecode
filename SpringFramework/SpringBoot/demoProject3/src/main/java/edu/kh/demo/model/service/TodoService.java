package edu.kh.demo.model.service;

import java.util.Map;

import edu.kh.demo.model.dto.Todo;

public interface TodoService {

	Map<String, Object> selectAll();

	int addTodo(Todo todo);

}
