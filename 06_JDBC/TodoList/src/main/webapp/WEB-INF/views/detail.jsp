<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${todo.todoTitle}</title>

    <style>
        .todo-content {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
    <h3>${todo.todoTitle}</h3>

    <table border="1">
        <tr>
            <th>번호</th>
            <td>${todo.todoNo}</td>
        </tr>
        <tr>
            <th>등록일</th>
            <td>${todo.regDate}</td>
        </tr>
        <tr>
            <th>완료 여부</th>
            <td>
                <%-- data-* 속성
                    - 데이터에 대한 확장성을 고려하여 설계된 속성
                    - js에서 요소.dataset 을 이용해 해당 값을 얻어갈 수 있음
                --%>
                <button type="button" data-todo-no="${todo.todoNo}" class="complete-btn">${todo.complete}</button>
            </td>
        </tr>
        <tr>
            <th>내용</th>
            <td class="todo-content">${todo.todoContent}</td>
        </tr>
    </table>

    <button onclick="location.href='/'">메인페이지</button>

    <script src="/resources/js/detail.js"></script>
</body>
</html>