package edu.kh.lambda.run;

import edu.kh.lambda.function.TwoNumberSum;

/*1. 람다 표현식이란?
람다 표현식(Lambda Expression)은 
익명 함수(anonymous function)를 작성하기 위한 간결한 문법입니다. 
이는 간단한 함수를 작성하고 사용할 수 있는 방법을 제공합니다.

[람다 표현식 작성법]

(parameters) -> expression
// 또는
(parameters) -> { statements; }

[람다 표현식의 장점]

간결성: 불필요한 코드가 줄어들어 가독성이 향상됩니다.
유연성: 익명 함수로 인하여 일회성 사용이 용이합니다.
병렬 처리: 스트림 API와 함께 사용하여 병렬 처리를 간단하게 구현할 수 있습니다.
 */
public class Test1 {
	public static void main(String[] args) {
//		TwoNumberSum sum = (a, b) -> a + b;
//		int result = sum.sum(10, 20);
//		System.out.println(result);
		
		TwoNumberSum sum = (a,b) -> {
			int result = a + b;
			System.out.printf("%d + %d = %d \n", a, b, result);
		};
		
		
		sum.sum(10, 20);
	}
}
