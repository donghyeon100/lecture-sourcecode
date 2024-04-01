<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>네트워크 프로그래밍_2</title>
</head>
<body>
    <h1>messge : ${message}</h1>
    <hr>
    
    <h1>로그인</h1>
    <form action="/login" method="post">
        아이디 : <input type="text" name="inputId"><br>
        비밀번호 : <input type="password" name="inputPw"><br>
        <button>로그인</button>
    </form>
</body>
</html>