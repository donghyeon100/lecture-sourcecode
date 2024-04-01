<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>redirect 확인하기</title>
</head>
<body>

    <h1>${sessionScope.message}</h1>
    
    <hr>
    
    <h1>회원 가입</h1>

    <form action="/signup" method="POST">
        <div>
            ID : <input type="text" name="id">
        </div>
        <div>
            PW : <input type="password" name="pw">
        </div>
        <div>
            PW Check : <input type="password" name="pwCheck">
        </div>

        <button>회원가입</button>
    </form>
</body>
</html>