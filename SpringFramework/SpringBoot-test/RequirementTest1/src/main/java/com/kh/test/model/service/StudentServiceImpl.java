package com.kh.test.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.test.model.dto.Student;
import com.kh.test.model.mapper.StudentMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private final StudentMapper studentMapper;
	
	@Override
	public List<Student> studentSelect() {
		return studentMapper.studentSelect();
	}
	
	@Override
	public int studentInsert(Student student) {
		return studentMapper.studentInsert(student);
	}
	
	@Override
	public int studentDelete(int studentNo) {
		return studentMapper.studentDelete(studentNo);
	}
}
