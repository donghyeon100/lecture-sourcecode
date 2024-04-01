<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>네트워크 프로그래밍_3</title>
</head>
<body>
    <h1>messge : ${message}</h1>
    <hr>
    
    <h1>이름, 나이 입력 받아 소개 메시지 만들기</h1>
    <form action="/introduce" method="get">
        이름 : <input type="text" name="inputName"><br>
        나이 : <input type="number" name="inputAge"><br>
        <button>로그인</button>
    </form>
</body>
</html>