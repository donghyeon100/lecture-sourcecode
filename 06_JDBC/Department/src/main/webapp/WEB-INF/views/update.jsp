<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>부서 수정</title>
</head>
<body>
    <h1>부서 수정(부서 코드는 수정 불가)</h1>

    <form action="update" method="post">
    
        <div>
            부서 코드(DEPT_ID) :
            <input type="text" name="deptId" value="${param.deptId}" readonly>
        </div>

        <div>
            부서명(DEPT_TITLE) :
            <input type="text" name="deptTitle" 
                value="${param.deptTitle}" placeholder="${param.deptTitle}">
        </div>

        <div>
            지역코드(LOCATION_ID) :
            <input type="text" name="locationId" 
                value="${param.locationId}" placeholder="${param.locationId}">
        </div>
    
        <button>수정 하기</button>
    </form>

</body>
</html>