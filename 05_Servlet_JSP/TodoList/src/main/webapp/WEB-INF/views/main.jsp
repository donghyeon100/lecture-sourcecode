<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- JSTL(Jsp Standard Tag Library) 
    - JSP에서 자주 사용하는 Java 기능(if, for 등)을  태그 형식으로 만들어 제공
    - JSP 개발을 간소화하고 유지 관리를 용이하게 하는 강력한 도구
--%>

<%-- JSTL Tag 라이브러리를 현재 JSP 페이지에 사용할 수 있게 하겠다는 지시문  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%-- JSTL에서 LocalDateTime, DateTimeFormatter 자료형을 지원하지 않아 별도로 import 받아와야 함 --%>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo List</title>
</head>
<body>
    <h1>Todo List</h1>
    
    <h3>전체 Todo 개수 : ${fn:length(todoList)} / 완료된 Todo 개수 : ${completeCount}</h3>

    <hr>    
    <form action="/todo/add">
        <h4> 할 일 추가 </h4>
        <div>
            제목 : <input type="text" name="title">
        </div>
        <div>
            <textarea name="detail" cols="50" rows="3" placeholder="상세 내용"></textarea>
        </div>
        <button>추가</button>
    </form>
    <hr>

    <table border="1" style="border-collapse: collapse;">
        <thead>
            <tr>
                <th>할 일 제목</th>
                <th>완료 여부</th>
                <th>등록 날짜</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach items="${todoList}" var="todo" varStatus="vs">
                <tr>
                    <td>
                       <a href="/todo/detail?index=${vs.index}">
                            ${todo.title}
                       </a>
                    </td>
                    <th>
                        <c:if test="${todo.complete}"> O </c:if>
                        <c:if test="${not todo.complete}"> X </c:if>
                    </th>
                    <td>${todo.regDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}</td>
                </tr>
            </c:forEach>

        </tbody>
    </table>

 
    <c:if test="${not empty message}">
        <script>
            alert("${message}");
        </script>

        <c:remove var="message" scope="session" />
    </c:if>

</body>
</html>