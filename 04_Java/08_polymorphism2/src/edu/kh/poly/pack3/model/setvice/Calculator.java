package edu.kh.poly.pack3.model.setvice;

public interface Calculator {
	
	public static final double PI = 3.14;
	int MAX_NUM = 100_000_000; // 최대값
	public int MIN_NUM = -100_000_000; // 최소값
	
	/** 전달 받은 두 정수의 합 반환
	 * @param a
	 * @param b
	 * @return a,b의 합
	 */
	public abstract int plus(int a, int b);  
	
	/** 전달 받은 두 정수의 차 반환
	 * @param a
	 * @param b
	 * @return a,b의 차
	 */
	public abstract int minus(int a, int b); 
	
	/** 전달 받은 두 정수의 곱 반환
	 * @param a
	 * @param b
	 * @return a,b의 곱
	 */
	public abstract int multi(int a, int b); 
	
	
	/** a를 b로 나눈 결과를 반환
	 * 단, b가 0인 경우 0.0 반환
	 * @param a
	 * @param b
	 * @return a에서 b를 나눴을 때의 결과
	 */
	public abstract double div(int a, int b);   
	
	
	/** a를 b로 나누었을 때 나머지 반환
	 * @param a
	 * @param b
	 * @return a에서 b를 나누었을 때 나머지
	 */
	public abstract int mod(int a, int b);   
	
	/** 가변 인자로 전달 받은 모든 정수 합 반환
	 * @param numbers : 전달인자를 배열로 저장하고 있음
	 * @return 합계 반환
	 */
	int sum(int ... numbers);
	
	/** 원의 넓이 ( PI * R * R  )
	 * @param r : 반지름
	 * @return 원의 넓이
	 */
	double areaOfCircle(double r); 
	
	
	/** 전달 받은 숫자가 계산기 최대, 최소 범위 내 숫자가 맞는지 확인
	 * @param num
	 * @return 최대, 최소 범위 내 숫자가 맞으면 true, 아니면 false
	 */
	boolean rangeCheck(int num);
	
	
	/** a를 x번 곱한 결과 반환
	 * @param a
	 * @param x
	 * @return a의 x제곱
	 */
	long pow(long a, long x); // 거듭 제곱
	
	/** 전달 받은 정수를 2진수 String으로 변환해서 반환
	 * @param num
	 * @return 2진수
	 */
	String toBinary(int num);
	
	/** 전달 받은 정수를 16진수 String으로 변환해서 반환
	 * @param num
	 * @return 16진수
	 */
	String toHexadecimal(int num);
}
