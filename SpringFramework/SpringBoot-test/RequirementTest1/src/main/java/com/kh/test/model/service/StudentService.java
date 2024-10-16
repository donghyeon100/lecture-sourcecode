package com.kh.test.model.service;

import java.util.List;

import com.kh.test.model.dto.Student;

public interface StudentService {

	List<Student> studentSelect();

	int studentInsert(Student student);

	int studentDelete(int studentNo);

}
