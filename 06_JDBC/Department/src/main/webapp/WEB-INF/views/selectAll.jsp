<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL Core 라이브러리 추가 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>전체 부서 조회</title>
</head>
<body>

	<h1>전제 부서 조회</h1>


	<table border="1">
	
		<thead>
			<tr>
				<th>행 번호</th>
				<th>부서 코드 (DEPT_ID)</th>
				<th>부서 명   (DEPT_TITLE)</th>
				<th>지역 코드 (LOCATION_ID)</th>
				<th>수정 버튼</th>
				<th>삭제 버튼</th>
			</tr>
		</thead>
		
		
		<tbody>
			<c:forEach items="${deptList}" var="dept" varStatus="vs">
				
				<tr>
					<%-- vs.count : 현재 반복 횟수 (1부터 시작) --%>
					<td>${vs.count}</td>
					
					<td>${dept.deptId}</td>
					
					<td>${dept.deptTitle}</td>
					
					<td>${dept.locationId}</td>

					<th><button class="update-btn">수정</button></th>

					<th><button class="delete-btn">삭제</button></th>
				</tr>
			</c:forEach>
		</tbody>
	
	
	</table>


	<button onclick="location.href='/'">메인 페이지로 돌아가기</button>



	<%-- 삭제를 위한 form 태그 (화면에 안보이게 설정)--%>
	<form action="/department/delete" method="post" name="deleteForm">
		<input type="hidden" name="deptId" id="deleteDeptId">
	</form>





	<c:if test="${not empty message}" >
		<script>
				alert("${message}");        
		</script>

		<c:remove var="message" scope="session" />
	</c:if>


	<script src="/resources/js/selectAll.js"></script>

</body>
</html>