package edu.kh.lambda;

public class Test1 {
	public static void main(String[] args) {
		
		String str = "3"; 

		int num = 3;
		String str2 = num+"";
		
		System.out.println(str.hashCode());
		System.out.println(str2.hashCode());
		
		System.out.println("str == str2 : " + (str == str2));
		System.out.println("\"3\" == str2 : " + ("3" == str2));
		System.out.println("\"3\" == 3 + \"\" : " + ("3" == 3 + ""));
	}
}
