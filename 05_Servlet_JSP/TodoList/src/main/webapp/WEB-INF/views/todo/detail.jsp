<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${todo.title}</title>

    <style>
        .detail {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
    <ul>
        <li>제목 : ${todo.title}</li>
        <li class="detail">
            
        </li>
    </ul>
</body>
</html>