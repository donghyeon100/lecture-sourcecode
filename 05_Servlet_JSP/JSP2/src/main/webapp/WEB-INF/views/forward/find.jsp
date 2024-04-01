<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>forward 확인하기</title>
</head>
<body>
    <form action="/find" method="POST">
        <h3>검색할 이름을 입력하세요</h3>
        <input type="text" name="findName">
        <button>검색</button>
    </form>
</body>
</html>