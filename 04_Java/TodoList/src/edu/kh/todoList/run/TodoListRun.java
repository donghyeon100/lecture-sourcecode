package edu.kh.todoList.run;

import edu.kh.todoList.view.TodoListView;

public class TodoListRun {
	public static void main(String[] args) {
		
		// Run -> View <-> Service <-> DAO <-> File
		
		new TodoListView().startView();
	}
}
