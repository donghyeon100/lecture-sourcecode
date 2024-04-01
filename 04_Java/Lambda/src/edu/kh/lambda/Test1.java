package edu.kh.lambda;

public class Test1 {
	public static void main(String[] args) {
		
		TwoNumberSum tns = (a, b) -> a + b;
		
		int result = tns.sum(10, 20);
		
		System.out.println(result);
	}
}
