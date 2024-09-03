package edu.kh.test;

import java.util.Arrays;
import java.util.Scanner;

public class Test1_오류 {
	public static void main(String[] args) {
		
		// 과일 바구니 만들기
		// 과일 이름을 5개 담을 수 있는 배열을 생성하고
		// 과일 이름이 5개가 배열에 저장될 때 까지 반복
		// 단, 중복되는 과일이 존재하는 경우 추가 X

		String[] fruits = new String[5];
		
		Scanner sc = new Scanner(System.in);
		
		int index = 0;
		
		while(true) {
			
			System.out.print("과일 이름 입력 >>> ");
			String f = sc.nextInt();
			
			boolean flag = false; 
			
			for(int i=0 ; i < index ; i++) {
				if(fruits[i] == f) { 
					flag = true;
					break;
				}
			}
			
			if(flag) { 
				fruits[index] = f;
				index++;
			}else { 
				System.out.println(f + "는 이미 바구니에 존재합니다");
			}
			
		}
		
		System.out.println("과일 목록 : " + Arrays.toString(fruits));
		
		
		
	}
}
