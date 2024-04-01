package edu.kh.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) {
		
		// DEPARTMENT4 테이블에 새로운 행 삽입하기
		
		
		
		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null; // DB 연결 정보를 저장한 객체
		Statement stmt = null; // DB에 SQL 수행 -> 결과 반환 받는 객체
		ResultSet rs = null; // SELECT 결과를 저장하는 객체
		
		// PreparedStatement(준비된 Statement)
		// - 외부 변수 값을 SQL에 받아드릴(삽입할) 준비가 되어있는 Statement
		// -> 성능, 속도면에서 우위를 가지고 있음
		// - ? (placeholder) : 변수를 위치시킬 자리 지정
		PreparedStatement pstmt = null;
		
		try {
			
			// 2. DriverManager를 이용해 Connection 객체 생성
			// 2-1) Oracle JDBC Driver 객체를 메모리에 로드(적재) 하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2-2) DB 연결 정보를 이용해서 Connection 객체 생성
			String type = "jdbc:oracle:thin:@"; // 드라이버 종류
			String host = "localhost"; // DB 서버 컴퓨터 IP 주소
			String port = ":1521"; // DB 서버 컴퓨터 내에서 DB 프로그램 번호
			String dbName = ":xe"; // DB 이름
			String userName = "KH_BDH"; // 사용자 계정
			String pw = "KH1234"; // 비밀번호
			
			conn = DriverManager.getConnection(type + host + port + dbName, userName, pw);     
			
			// 자동 커밋 끄기!!!
			conn.setAutoCommit(false);
			
			// 3. SQL 작성(; 미포함)
			Scanner sc = new Scanner(System.in);
			
			System.out.print("부서코드 입력 : ");
			String deptCode = sc.next(); 
			
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.next(); 
			
			System.out.print("지역코드 입력 : ");
			String locationCode = sc.next(); 
			
			String sql = "INSERT INTO DEPARTMENT4 VALUES(?, ?, ?)";
			
			
			//  4. PreparedStatement 객체 생성
			// (Statement와 다르게 생성 시 sql을 매개 변수로 사용함)
			// -> SQL을 생성할 때 전달하여 ?에 값 대입을 준비함
			pstmt = conn.prepareStatement(sql);
			
			
			// 5. ?(placeholder)에 알맞은 값 대입
			// *****  pstmt.set자료형(순서, 값);  *****
			
			// ** pstmt.setString() 으로 문자열을 SQL에 대입하면
			//  자동으로 양쪽에 ' '(홑따옴표)를 추가해준다!! (굉장히 편함)
			pstmt.setString(1, deptCode);
			pstmt.setString(2, deptTitle);
			pstmt.setString(3, locationCode);
			
			
			// 6.  SQL 수행(INSERT) 후 결과 반환(결과 행의 수) 받기
			int result = pstmt.executeUpdate();
		
			
			if(result > 0) { 
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch(Exception e) {
			// 예외 최상위 부모로 모든 예외 잡아서 처리
			e.printStackTrace(); 
	
		} finally {
			// 7. JDBC 객체 자원 반환(역순)
			try {
				if(rs != null) rs.close();
				if(pstmt != null) stmt.close();
				if(conn != null) conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}