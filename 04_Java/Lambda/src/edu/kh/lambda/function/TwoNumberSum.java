package edu.kh.lambda.function;

/*
2. 함수형 인터페이스란?
함수형 인터페이스(Functional Interface)는 
하나의 추상 메서드만을 가지는 인터페이스입니다. 
자바에서 람다 표현식은 이러한 함수형 인터페이스의 인스턴스를 생성하는 데 사용됩니다.
*/

@FunctionalInterface //해당 인터페이스가 함수형 인터페이스임을 명시
						// + 함수형 인터페이스 형식에 맞게 작성 되었는지 검사가 있습니다
public interface TwoNumberSum {

	void sum(int a, int b);
}
