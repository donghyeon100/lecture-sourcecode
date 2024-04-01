package edu.kh.demo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.demo.model.dto.Todo;

//@Mapper
//MyBatis에서 제공하는 어노테이션 중 하나로
//해당 어노테이션이 작성된 인터페이스가 mapper.xml의 
//<mapper namespace=”패키지명.인터페이스명”> 에 작성된 경우 
//두 파일이 연결되어 인터페이스 메서드명으로 mapper.xml의 sql을 호출할 수 있다

@Mapper
public interface TodoMapper {

	List<Todo> selectAll();

	int getCompleteCount();

	int addTodo(Todo todo);

}
