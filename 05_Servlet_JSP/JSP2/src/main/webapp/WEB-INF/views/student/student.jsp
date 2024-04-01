<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>학생 검색</title>
</head>
<body>
    <h1>학생 검색</h1>

    <form action="/student/search" method="GET">
        <div>
            학번 : <input type="text" name="studentNumber">
        </div>
        <div>
            이름 : <input type="text" name="studentName">
        </div>
        <button>검색</button>
    </form>
</body>
</html>