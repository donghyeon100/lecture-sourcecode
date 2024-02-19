package edu.kh.poly.pack3.run;

import edu.kh.poly.pack3.model.setvice.BDHCalculator;
import edu.kh.poly.pack3.model.setvice.Calculator;

public class CalulatorRun {
	public static void main(String[] args) {
		Calculator cal = new BDHCalculator();
		
		long startTime = System.nanoTime();
		
		for(int i=0 ; i<200 ; i++) System.out.println( cal.pow(2, 50) );
		
		long endTime = System.nanoTime();
		long executionTime = endTime - startTime;
		System.out.println("총 실행 시간: " + executionTime + "ns");
		System.out.println("평균 실행 시간: " + executionTime/200L + "ns");
		
	}
}
