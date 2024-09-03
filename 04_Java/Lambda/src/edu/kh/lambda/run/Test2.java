package edu.kh.lambda.run;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test2 {
	public static void main(String[] args) {
		
		/*  2.자바 내장 함수형 인터페이스
		 * 
		 * Predicate: 인수를 받아서 boolean을 반환합니다.
		 * Predicate<T> : boolean test(T t) -> true, false
		 * 
		 * Function: 인수를 받아서 결과를 반환합니다.
		 * Function<T, R> : R apply(T t) -> ()
		 * 
		 * Consumer: 인수를 받아서 처리하고 반환값이 없습니다.
		 * Consumer<T> : void accept(T t) -> ()
		 * 
		 * Supplier: 인수를 받지 않고 결과를 반환합니다.
		 * Supplier<T> : T get() -> ()
		 * 
		*/
		
		Predicate<Integer> isPositive = (x) -> x > 0;
		Predicate<Integer> isNegative = (x) -> x < 0;
		Predicate<Integer> isZero = (x) -> x == 0;
		
		System.out.println(isPositive.test(10)); // t
		System.out.println(isNegative.test(10)); // f
		System.out.println(isZero.test(10)); // f
		
		
		System.out.println("-----------------------------------");
		
		
		Consumer<Integer> printPos = (x) -> {
			if(x > 0) {
				System.out.println(x);
			}
		};
		Consumer<Integer> printNeg = (x) -> {
			if(x < 0) {
				System.out.println(x);
			}
		};
		Consumer<Integer> printZero = (x) -> {
			if(x == 0) {
				System.out.println(x);
			}
		};
		
		printPos.accept(10);
		printNeg.accept(10);
		printZero.accept(10);
		
		System.out.println("-----------------------------------");
		Function<Integer, String> checkPos = (x) -> x > 0 ? "양수" : "양수 아님";
		Function<Integer, String> checkNeg = (x) -> x < 0 ? "음수" : "음수 아님";		
		Function<Integer, String> checkZero = (x) -> x == 0 ? "0" : "0 아님";		
		
		System.out.println(checkPos.apply(10));
		System.out.println(checkNeg.apply(10));
		System.out.println(checkZero.apply(0));
		
		
		System.out.println("-----------------------------------");
		Supplier<Integer> getPos = () -> 10;
		Supplier<Integer> getNeg = () -> -10;
		Supplier<Integer> getZero = () -> 0;
		
		System.out.println(getPos.get()); // 10
		System.out.println(getNeg.get()); // -10
		System.out.println(getZero.get()); // 0
		
	}
	
	
}
