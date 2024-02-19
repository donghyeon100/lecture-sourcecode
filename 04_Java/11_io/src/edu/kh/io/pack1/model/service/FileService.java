package edu.kh.io.pack1.model.service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class FileService {
	
	/* File 클래스
	 * - java.io 패키지
	 * 
	 * - 파일/디렉토리(폴더)를 관리하는 클래스
	 *  (디렉토리 (Linux, Unix), 폴더 (windows))
	 *  
	 * - 파일 생성, 제거, 이름, 크기, 수정일 확인 등의 기능을 제공
	 * 
	 * - File 클래스 메서드
	 * 
	 * boolean  mkdir()          : 디렉토리 생성
	 * boolean  mkdirs()         : 경로상의 모든 디렉토리 생성
	 * boolean  createNewFile()  : 파일 생성
	 * boolean  delete()         : 파일/디렉토리 삭제
	 * String   getName()        : 파일 이름 반환
	 * String   parent()         : 파일이 저장된 디렉토리 반환
	 * String   getPath()        : 전체 경로 반환
	 * boolean  isFile()         : 현재 File 객체가 관리하는게 파일이면 true
	 * boolean  isDirectory      : 현재 File 객체가 관리하는게 디렉토리 true
	 * boolean  exists()         : 파일/디렉토리가 존재하면 true, 아님 false
	 * long     length()         : 파일 크기 반환
	 * long     lastModified()   : 파일 마지막 수정일 (1970.01.01 09:00 부터 현재까지 지난 시간을 ms 단위로 반환)
	 * String[] list()          : 디렉토리 내 파일 목록을 String[] 배열로 반환
	 * File[]   listFiles()     : 디렉토리 내 파일 목록을 File[] 배열로 반환 
	 */
	
	
	// 절대 경로 : 절대적인 기준으로 부터 목표 까지의 경로
	// 서울 기준 -> 서울시 중구 남대문로 120 2층 KH정보교육원
	// C드라이브 기준 -> C:/workspace/04_Java/a11_io/byte
	// 제일 앞에 / 또는 \\ 작성 시 루트 폴더(최상위 폴더)가 지정됨
	
	
	// 상대 경로 : 임의의(현재 위치) 기준으로 붙너 목표 까지의 경로
	// KH정보교육원 기준 우리은행 본점 까지의 경로 : 옆건물
	
	// 현재 프로젝트(a11_io) 폴더가 현재 위치(기준)
	// - 현재 위치에서 byte폴더 까지의 경로 :  byte
	
	
	
	/* 경로 작성 시 폴더(디렉토리)를 구분하기 위한  /, \\ 차이
	 * 
	 * windows OS 작성법 : \\ 
	 * 
	 * Linux 기반 OS 작성법 : /
	 * 
	 * Java에서는 운영체제를 알아서 판별해서 /, \\ 중 알맞은 형태로 변환해주기 때문에
	 * 신경 쓸 필요 없이 편한걸 작성하면 됨
	 * (또는  File.separator 이용 가능)
	 * 
	 */
	
	
	/**
	 * 파일 객체 생성 + 폴더 생성
	 */
	public void method1() {
		
		File directory = new File("/io_test/20240215");
		
		if(!directory.exists()) { // 관리하는 경로에 디렉토리(폴더)가 없는 경우
//			directory.mkdir(); // 디렉토리(폴더) 생성
			directory.mkdirs(); // 지정된 경로까지 모든 폴더 생성
			
			System.out.println(directory.getName() + " 디렉토리 생성!");
		}
	}
	
	
	/**
	 * 파일 생성
	 */
	public void method2() {
		File file = new File("/io_test/20240215/파일생성.txt");
		
		if(!file.exists()) { // 파일이 존재하지 않을 경우
			try {
				
				// boolean java.io.File.createNewFile() throws IOException
				// -> IOExecption에 대한 예외 처리 필요
				if(file.createNewFile()) {
					System.out.println(file.getName() + " 파일 생성!");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void method3() {
		
		// 파일 객체 생성 (현재 프로젝트 위치)
		File ioProejct = new File("\\lecture-sourcecode\\04_Java\\11_io");

		// File[] File.listFiles() : 디렉토리에 포함된 파일 및 서브 디렉토리 목록 전부 File 배열로 리턴
		File[] files = ioProejct.listFiles();

		// 향상된 for문
		for (File file : files) {

			// StringBuilder(mutable, 가변성)를 이용해 문자열 이어쓰기
			StringBuilder sb = new StringBuilder();

			// 파일명 얻어오기
			String fileName = file.getName();

			// 파일 / 폴더 여부 확인
			String type = null;
			if (file.isFile())
				type = "파일";
			else
				type = "폴더";

			// 파일 크기
			long byteSize = 0;
			if (file.isFile())
				byteSize = file.length(); // 파일이면 파일 크기 / 아니면 폴더 == 0

			// 마지막 수정일
			// SimpleDateFormat : 날짜를 간단히 지정된 포맷으로 변환하는 객체
			// SimpleDateFormat.format(long) : long 형태의 시간 데이터를 지정된 포맷의 시간으로 변경
			String lastModified = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss").format(file.lastModified());

			String str = String.format("%-30s %-10s %5dB %30s", fileName, type, byteSize, lastModified);

			sb.append(str);

			// 출력
			System.out.println(sb.toString());

		}

	}
	
	
	
	
	
	
	
	
	
}
