package edu.kh.jdbc.view;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc.model.dao.DepartmentDAO;
import edu.kh.jdbc.model.dto.Department;

public class DepartmentView {
	private Scanner sc = new Scanner(System.in);
	
	private DepartmentDAO dao = new DepartmentDAO();
	
	public void displayMenu() {
		
		int input = 0;
		
		do {
			
			try {
				System.out.println("\n----- 부서 관리 프로그램 -----\n");
				System.out.println("1. 부서 추가");
				System.out.println("2. 부서 전체 조회");
				System.out.println("3. 검색어가 부서명에 포함되는 부서 조회");
				System.out.println("4. 부서 삭제");
				System.out.println("5. 부서명 변경");
				System.out.println("0. 프로그램 종료");
				
				System.out.print("메뉴 선택 : ");
				input = sc.nextInt();
				System.out.println();
				
				switch (input) {
				case 1: insertDepartment();	break;
				case 2: selectAll();	break;
				case 3: selectDepartmentTitle();	break;
				case 4: deleteDepartment();	break;
				case 5: updateDepartment();	break;
				case 0: System.out.println("프로그램 종료"); break;
				default : System.out.println("메뉴에 없는 번호 입니다.");
				}
				
				
			}catch (InputMismatchException e) {
				System.out.println("알맞은 값을 입력하세요.");
				sc.nextLine();
				input = -1;
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while(input != 0);
	}
	
	/** 부서 추가
	 * @throws SQLException
	 */
	private void insertDepartment() throws SQLException {
		System.out.print("부서 코드 입력 : ");
		String deptId = sc.next();
		
		System.out.print("부서명 입력 : ");
		String deptTitle = sc.next();
		
		System.out.print("지역 코드 입력 : ");
		String locationId = sc.next();
		
		
		// ** DAO 메서드 호출 후 결과 반환 받기 ***
		Department dept = new Department(deptId, deptTitle, locationId);
				
		// DB 삽입 후 결과 반환 받기
		int result = dao.insertDepartment(dept);
		
		if(result > 0) {
			System.out.println("[삽입 성공]");
		} else {
			System.out.println("[삽입 실패]");
		}
	}
	
	
	/** 부서 전체 조회
	 * @throws SQLException
	 */
	private void selectAll() throws SQLException {
		List<Department> deptList = dao.selectAll();
		
		for(Department d : deptList) {
			System.out.println(d.toString());
		}
	}
	
	
	private void selectDepartmentTitle()  throws SQLException {
		System.out.print("부서명 입력 : ");
		String title = sc.next();
		
		List<Department> deptList = dao.selectDepartmentTitle(title);
		
		if(deptList == null) {
			System.out.println("일치하는 부서가 없습니다");
		}else {
			for(Department d : deptList) {
				System.out.println(d.toString());
			}
		}
		
	}
	
	private void deleteDepartment() throws SQLException {
		System.out.print("부서코드 입력 : ");
		String deptId = sc.next();
		
		int result = dao.deleteDepartment(deptId);
		
		if(result > 0) {
			System.out.println("[삭제 성공]");
		} else {
			System.out.println("[부서코드가 일치하는 부서가 존재하지 않습니다]");
		}
	}
	
	private void updateDepartment() throws SQLException{
		System.out.print("부서코드 입력 : ");
		String deptId = sc.next();
		
		System.out.print("변경할 부서명 입력 : ");
		String deptTitle = sc.next();
		
		int result = dao.updateDepartment(deptId, deptTitle);
		
		if(result > 0) {
			System.out.println("[수정 성공]");
		} else {
			System.out.println("[부서코드가 일치하는 부서가 존재하지 않습니다]");
		}
	}
	
}
