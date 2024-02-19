package edu.kh.poly.pack3.model.setvice;

import java.util.Arrays;

public class BDHCalculator implements Calculator {

	@Override
	public int plus(int a, int b) {
		return a+b;
	}

	@Override
	public int minus(int a, int b) {
		return a-b;
	}

	@Override
	public int multi(int a, int b) {
		return a*b;
	}

	@Override
	public double div(int a, int b) {
		if(b == 0) return 0.0;
		return a/b;
	}

	@Override
	public int mod(int a, int b) {
		return a%b;
	}
	
	@Override
	public int sum(int... numbers) {
//		System.out.println(Arrays.toString(numbers));
		
		int sum = 0;
		for(int num : numbers) {
			sum += num;
		}
		
		return sum;
	}

	@Override
	public double areaOfCircle(double r) {
		return 2 * Calculator.PI * r;
	}
	
	@Override
	public boolean rangeCheck(int num) {
		return num >= Calculator.MIN_NUM && num <= Calculator.MAX_NUM;
	}

	@Override
	public long pow(long a, long x) {
		// x가 무조건 양수인 경우
		// 재귀 호출
//		return x == 1  ?  a :  a * pow(a, x-1) ;
		
		long result = 1;
		for(int i = 0 ; i < x ; i++) {
			result *= a;
		}
		return result;
		
//		return (long)Math.pow(a, x);
		
	}

	@Override
	public String toBinary(int num) {
		return Integer.toBinaryString(num);
	}

	@Override
	public String toHexadecimal(int num) {
		return Integer.toHexString(num);
	}

}
