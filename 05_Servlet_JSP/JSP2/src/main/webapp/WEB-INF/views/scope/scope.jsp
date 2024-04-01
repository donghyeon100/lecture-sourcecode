<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Servlet/JSP 내장 객체와 범위</title>
  <style>
    pre{ 
      font-size : 16px;
      font-family : "맑은 고딕";
    }
  </style>
</head>
<body>
  <h1>내장 객체 확인</h1>

  <% // 스크립틀릿 : JSP에다가 Java 코드 작성하는 영역
    // pageContext == page 범위(scope) 객체
    pageContext.setAttribute("pageMessage", "page scope 입니다");
  %>

  <ul>
    <li> page : ${pageMessage}</li>

    <li> request : ${requestMessage} </li>

    <li> session : ${sessionMessage} </li>

    <li> application : ${applicationMessage} </li>

    <li> <a href="/scopecheck">scope 확인하기</a> </li>
  </ul>

  <hr><hr><hr>
  
  <h1>우선 순위 확인</h1>

  <% // page scope
    pageContext.setAttribute("str", "page scope에 세팅된 문자열");
  %>

  <h2>\${key} : 
    4종류의 scope 객체를 좁은 순서대로 탐색해서
    key가 일치하는 속성이 존재하면 출력
  </h2>

  <h2> ${str}</h2>


  <hr>

  <h2>\${XXXScope.key} : 
    XXX 자리에 scope를 작성하면
    해당 scope에서 key가 일치하는 속성을 찾아 출력
  </h2>

  <h4>${pageScope.str}</h4>

  <h4>${requestScope.str}</h4>

  <h4>${sessionScope.str}</h4>

  <h4>${applicationScope.str}</h4>

</body>
</html>