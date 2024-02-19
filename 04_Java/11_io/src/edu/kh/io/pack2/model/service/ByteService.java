package edu.kh.io.pack2.model.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import edu.kh.io.pack2.model.dto.Member;

public class ByteService {
	/* Stream(스트림) : 데이터가 이동하는 통로
	 * 					(기본적으로 단(한쪽) 방향)
	 * 
	 * 바이트 기반 스트림
	 * - InputStream, OutputStream 최상위 인터페이스
	 * 
	 * - 1byte 단위로 데이터를 입/출력 하는 스트림
	 * 
	 * - 1byte 범위 문자열(아스키코드 (영어, 숫자, 특수문자))
	 *   또는 이미지, 영상, 오디오 등 문자가 아닌 파일/데이터
	 *   
	 * 
	 * 문자 기반 스트림
	 * - Reader, Writer 최상위 인터페이스
	 * 
	 * - 2byte 단위로 데이터(문자)를 입/출력 하는 스트림
	 * 
	 * - 2byte 범위 문자열, 문자만 저장된 파일
	 *   채팅, 인터넷 요청 시 데이터 전달 등에 이용
	 */
	
	
	/**
	 * 바이트 기반 파일 출력
	 */
	public void fileByteOutput() {
		// IO 관련 코드는 IOException 발생 시킬 가능성이 높음
		
		
		FileOutputStream fos = null;
		
		try {
			// byte 기반 파일 출력 스트림 생성
			// 매개 변수 true 작성 시 이어쓰기 / 기본값은 false(덮어쓰기)
			fos = new FileOutputStream("/io_test/20240215/바이트기반_테스트.txt" /* , true */);

			StringBuilder sb = new StringBuilder();
			sb.append("Hello Wolrd!!!!\n");
			sb.append("1234567890\n");
			sb.append("~!@#$%^&*()_+\n");
			sb.append("와 자바 재밌다");

			String content = sb.toString();
					
			// 출력 방법 1 :  데이터(문자열)를 한 글자씩 출력
//			for(int i=0 ; i<content.length() ; i++ ) { // 글자 수만큼 반복
//				char ch = content.charAt(i); // i번째 문자 하나
//				fos.write(ch); // 한 글자씩 출력
//				
//				// 영어, 숫자, 기본  특수문자는 ASCII(1byte) 범위에 지정되어 있어 
//				// 한 글자씩(2byte) 잘라와 1byte 단위로 잘라서 출력해도 문제가 없는데
//				
//				// 나머지 글자는 2byte(UnICODE, 2byte) 범위에 지정되어 있어
//				// 한 글자씩(2byte) 잘라온 문자를 1byte만 잘라서 출력해 데이터가 손실되어 깨지는 문제가 발생
//			}
			
	    long startTime = System.nanoTime();
			// 출력 방법 2 : String을 byte[]로 변환해서 출력
			// -> byte[]로 변환 시 모든 문자가 0,1 / 2,3 이런식으로
			// 2개의 인덱스 요소에 나눠져 저장되기 때문에 손실되는 데이터 없이 모두 출력 -> 문자가 깨지지 않음
			fos.write(content.getBytes());
			// 자동으로 byte[] 배열에서 index 하나씩 파일로 출력함
			// -> byte[] 길이 만큼 반복
			long endTime = System.nanoTime();
		  System.out.println("\n[총 수행 시간 : " + (endTime - startTime) + "ns]");
			
		  
		  fos.flush(); // 스트림에 남은 데이터 내보내기
		  
		  
			System.out.println("출력 완료");
			
		} catch(IOException e) {
			e.printStackTrace(); // 예외가 발생한 메서드 까지의 모든 내용 출력
			
		} finally { // 무조건 실행
			
			// 사용 완료된 스트림을 메모리에 반환하는 코드 작성
			// (close() 코드 수행 시 flush()가 자동으로 수행된다)
			try {
				if(fos != null) { fos.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	/**
	 * 바이트 기반 보조스트림을 활용한 성능 향상 출력
	 */
	public void bufferedFileByteOutput() {
		
		// [BufferedOutputStream] 
		// - 성능 향상을 위한 보조스트림
		// - 출력하려는 데이터를 일정량 모아서 한 번에 출력 == 버퍼링
		// (여러번 왕복하지 말고, 모아서 크게 한번씩 출력함)
		// - 출력 효율(성능, 속도)이 향상됨
		
		
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		
		try {
			// byte 기반 파일 출력 스트림 생성
			// 매개 변수 true 작성 시 이어쓰기 / 기본값은 false(덮어쓰기)
			fos = new FileOutputStream("/io_test/20240215/바이트기반_Bufferd_테스트.txt" /* , true */);
			bos = new BufferedOutputStream(fos);
			
			StringBuilder sb = new StringBuilder();
			sb.append("Hello Wolrd!!!!\n");
			sb.append("1234567890\n");
			sb.append("~!@#$%^&*()_+\n");
			sb.append("와 자바 재밌다");

			String content = sb.toString();
			
			long startTime = System.nanoTime();
			
			bos.write(content.getBytes());
			// index별 요소를 하나씩 파일로 출력하는 것이 아닌
			// 일정량 만큼 모아서 한 번에 파일로 출력하기 때문에 효율이 좋음!
			// 작은 조각으로 나눠서 출력하는 것보다 큰 덩어리로 출력하기 때문에 성능 향상!
			
			long endTime = System.nanoTime();
		  System.out.println("\n[총 수행 시간 : " + (endTime - startTime) + "ns]");
		  
			System.out.println("출력 완료");
			
		} catch(IOException e) {
			e.printStackTrace(); // 예외가 발생한 메서드 까지의 모든 내용 출력
			
		} finally { // 무조건 실행
			
			try {
				// 보조 스트림을 닫으면 연결된 기반 스트림도 같이 닫힘
				if(bos != null) { bos.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	
	/**
	 * 바이트 기반 파일 입력
	 */
	public void fileByteInput() {
		
		FileInputStream fis = null;
		
		try {
//			fis = new FileInputStream("/io_test/20240215/바이트기반_테스트.txt");
			fis = new FileInputStream("/io_test/20240215/SuperLady.txt");
			
			// 방법 1 : 1byte 씩 읽어오기
			// -> 문제점 : 1byte 단위로 끊어서 읽어오기 때문에
			// 2byte 범위의 문자가 1/1byte 씩 쪼깨져서 읽어와지기 때문에 글자 깨짐 발생
			
			// 읽어온 1byte 값을를 저장할 변수 선언
//			int value = 0; // 왜 int?  byte(정수형)으로 읽어오는데 byte를 다루기 어려워서 int로 저장
//			while(true) {
//				
//				value = fis.read(); // 다음 1byte를 읽어와 int로 저장
//				
//				if(value == -1) { // 다 읽었으면
//					break;
//				}
//				
//				System.out.print( (char)value );
//			}
			
			
			// 방법 2 : 파일에 저장된 byte 값을 모두 얻어와 String으로 변환
			// -> 모든 값을 얻어와 String으로 변환 시 2byte씩 묶어서 해석하기 때문에 문자 깨짐 없음
			long startTime = System.nanoTime();
			byte[] bytes = fis.readAllBytes();
			long endTime = System.nanoTime();

			String content = new String(bytes);
			
			System.out.println(content);

			System.out.println("\n[총 수행 시간 : " + (endTime - startTime) + "ns]");
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(fis != null) { fis.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	
	
	/**
	 * 바이트 기반 보조스트림을 활용한 성능 향상 입력
	 */
	public void bufferedFileByteInput() {
		// [BufferedInputStream] 
		// - 성능 향상을 위한 보조스트림
		// - 입력하려는 데이터를 일정량 모아서 한 번에 입력 == 버퍼링
		// (여러번 왕복하지 말고, 모아서 크게 한 번씩 입력함)
		// - 출력 효율(성능, 속도)이 향상됨
		
	
		
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		try {
			fis = new FileInputStream("/io_test/20240215/SuperLady.txt");
			bis = new BufferedInputStream(fis);
			
			long startTime = System.nanoTime();
			byte[] bytes = bis.readAllBytes();
			long endTime = System.nanoTime();
			
			String content = new String(bytes);
			System.out.println(content);
			System.out.println("\n[총 수행 시간 : " + (endTime - startTime) + "ns]");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				if(bis != null) { bis.close(); }
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	// file 복사하기
	public void fileCopy() {
		
		// byte 기반 스트림 이용
		// + 성능 향상을 위한 보조스트림
		// -> BufferedInput/OutputStream
		//   -> 모아서 한 번에 입/출력 (+깨진 글자도 모이면서 다시 붙음)
		
		BufferedReader br = null;
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.print("복사할 파일 경로 : ");
			String target = br.readLine(); // 한 줄 입력
			
//			System.out.print("복사본이 저장될 경로 + 파일명 : ");
//			String copy = br.readLine();
			
			StringBuilder sb = new StringBuilder();
			sb.append(target);
			
			sb.insert(target.lastIndexOf("."), "_copy");
			
			String copy = sb.toString();
			
			// 복사 대상을 읽어올 InputStream 생성 + 보조 스트림으로 성능 향상
			bis = new BufferedInputStream( new FileInputStream(target) );
						// 보조스트림 (  기반 스트림  )
			
			// 복사한 파일을 출력할 OutputStream 생성 + 보조 스트림으로 성능 향상
			bos = new BufferedOutputStream(new FileOutputStream(copy));
			
//			int value = 0; // 1byte씩 읽어서 저장할 임시 변수
//			
//			while(true) {
//				
//				value = bis.read(); // 1byte 읽어오기, 없으면 -1 반환
//				
//				if(value == -1)		break;
//				// if,for,while 등 {} 안에 코드가 1줄이면 생략 가능
//				
//				bos.write(value); // 1byte씩 출력하기
//			}
			bos.write(bis.readAllBytes());
			
			
			String copyFileName = copy.substring(copy.lastIndexOf("\\") + 1);
			
			System.out.println(copyFileName + " 복사 완료");
			
		} catch(FileNotFoundException e) {
			System.out.println("해당 파일이 존재하지 않습니다.");
		
		} catch(IOException e) {
			e.printStackTrace();
		
		} finally {
			// 다 쓴 스트림 없애기(메모리 반환)
			
			try {
				if(br != null)		br.close();
				if(bis != null)		bis.close();
				if(bos != null)		bos.close();
				
				// 보조 스트림을 close() 하면
				// 연결된 기반 스트림도 같이 close() 된다
			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	
	/**
	 * 객체 출력 보조 스트림 이용
	 */
	public void objectOutput() {
		
		// ObjectOutputStream
		// -> 객체를 바이트 기반으로 출력하는 보조 스트림
		// 사용 조건 : 
		// 출력하려는 객체에 직렬화 가능 여부를 나타내는
		// Serializable 인터페이스를 상속 받아야 한다
		
		ObjectOutputStream oos = null;
		
		try {
			
			// java.io.File : 파일/폴더를 참조하는 객체
			File folder = new File("object"); // object라는 이름의 파일/폴더 참조
			
			if(!folder.exists()) { // object폴더가 존재하지 않는다면
				folder.mkdir(); // make directory(==folder) : 폴더 만들기
			}
			
			oos = new ObjectOutputStream(new FileOutputStream("/io_test/20240215/Member.txt"));
			
			// 내보낼 회원 객체 생성
			Member mem =  new Member("mem01", "pass01", "일번멤버", 1000);
			
			// 회원 객체를 파일로 출력
			oos.writeObject(mem);
			
			System.out.println("회원 출력 완료");
			
			// NotSerializableException : 직렬화 되어있지 않음
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally {
			try {
				if(oos != null) oos.close(); // 스트림 닫기(메모리 반환)
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 * 객체 입력 보조 스트림 활용
	 */
	public void objectInput() {
		 
		ObjectInputStream ois = null;
		
		try {
			
			// 파일 내 객체를 읽어오는 스트림 생성
			ois = new ObjectInputStream( new FileInputStream("/io_test/20240215/Member.txt") );
			
			// readObject() : 직렬화된 객체를 읽어와 역직렬화함.
			Member mem = (Member)ois.readObject();
			
			System.out.println(mem);
			
		} catch(Exception e) { // Exception : 모든 예외의 최상위 부모
			// FileNotFoundException
			// IOException  
			// 둘다 한번에 잡아서 처리
			e.printStackTrace();
		} finally {
			try {
				if(ois != null) ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
