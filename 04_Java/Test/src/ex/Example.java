package ex;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		System.out.print("중간고사 점수 : ");
		int mid = sc.nextInt();
		
		System.out.print("기말고사 점수 : ");
		int fin = sc.nextInt();
		
		System.out.print("과제 점수 : ");
		int report = sc.nextInt();
		
		// 비율이 적용된 점수들의 합
		double total = (mid * 0.4) + (fin * 0.5) + (report * 0.1);
		
		String result; 
		
		// switch문 매개변수로는 정수, 문자열, 문자만 가능
		switch( (int)(total / 10) ) {
		case 10: case 9 : result = "A"; break;
		case 8 : result = "B"; break;
		case 7 : result = "C"; break;
		case 6 : result = "D"; break;
		default : result = "F";
		}
		
		if(total > 60 && total % 10 >=5) 	result += "+";
		

		System.out.println("최종 점수 : " + total + " 점");
		System.out.println("성적 : " + result);
	}
	
	
}
