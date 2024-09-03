package edu.kh.test;

import java.util.Arrays;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		
		// 과일 바구니 만들기
		// 과일 이름을 5개 담을 수 있는 배열을 생성하고
		// 과일 이름이 5개가 배열에 저장될 때 까지 반복
		// 단, 중복되는 과일이 존재하는 경우 추가 X

		String[] fruits = new String[5];
		
		Scanner sc = new Scanner(System.in);
		
		int index = 0; // 과일이 삽입되어야 하는 index를 지정( 0 ~ 4 )
		
		while(true) {
			
			System.out.print("과일 이름 입력 >>> ");
			String f = sc.next();
			
			boolean flag = true; // 같은 이름의 과일이 없으면 true, 있으면 false라고 규정함
			
			for(int i=0 ; i < index ; i++) {
				if(fruits[i].equals(f)) { // 같은 이름의 파일이 이미 존재 한다면
					flag = false;
					break;
				}
			}
			
			if(flag) { // 같은 이름의 과일이 없을 경우
				fruits[index] = f;
				index++;
			}else { // 같은 이름의 과일이 있을 경우
				System.out.println(f + "는 이미 바구니에 존재합니다");
			}
			
			
			// 과일을 추가하기 위한 index가 배열 마지막 인덱스를 넘어 선 경우
			if(index >= 5) {
				break; // while문 반복 중지
			}
		}
		
		System.out.println("과일 목록 : " + Arrays.toString(fruits));
		
	}
}
