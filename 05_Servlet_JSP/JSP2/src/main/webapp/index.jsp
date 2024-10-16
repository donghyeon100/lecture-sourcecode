<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Project 2</title>
</head>
<body>

    <h2>
        <a href="/el">EL(Expression Language)</a>
    </h2>
    <pre>
        - JSP에서 표현식을 간단하고 효율적으로 작성할 수 있도록
        고안된 언어 (JSP 기본 내장)

        - Java 값을 HTML 형식으로 표시(출력) 언어

        - 기본 작성 형식 : \${ key } 
   
        <h3>EL 특징 1 : get이라는 단어를 사용하지 않음 </h3>
   
        - 출력 용도의 언어이기 때문에 set이라는 개념이 존재하지 않음
          (반대되는 get만 남아있기 때문에 get이라는 단어를 생략하여 사용)

        <h3>EL 특징 2 : null, NullPointerException은 빈칸으로 처리</h3>

    </pre>


    <hr><hr>

    <h2>
        <a href="/scope">Servlet/JSP 내장 객체와 범위(Scope)</a>
    </h2>

    <pre>
        Servlet/JSP에는 4종류 범위를 나타낸 내장 객체가 존재한다!
        -> 각 종류마다 영향을 끼치는 범위가 달라진다

        <h3>1. page (pageContext) : 현재 페이지</h3>
        -> 현재 JSP에서만 사용 가능한 객체(Servlet X)

        <h3>2. request (요청) == HttpServletRequest</h3>
        -> 요청 받은 페이지(Servlet/JSP)와
        위임 받은 페이지(Servlet/JSP)에서 유지되는 객체

        <h3>3. session (입회, 접속)</h3>
        - session : 서버에 접속한 클라이언트를
                    나타내거나, 관련 정보를 get/set 할 수 있는 객체
                    (session 객체는 서버에서 관리함)
        
        - [중요!] session은 브라우저 마다 하나씩 생성된다!!!
        (새탭, 새창은 아님)

        - [유지 범위]
        사이트 접속 ~ 브라우저 종료 | 세션 만료

        - session이 유지되는 상태에서는 
        아무 곳에서나 가져다 사용할 수 있다!


        <h3>4. apllication (ServletContext) </h3>
        - 하나의 웹 애플리케이션 마다 1개만 생성되는 객체
        (Server를 키면 1개만 생성되는 객체)

        - application 객체는 어디서든 사용 가능

        - [유지 범위]
        서버 실행 ~ 서버 종료

        <h3>5. 내장 객체의 우선 순위 :  page > request > session > application </h3>
    </pre>

    <hr><hr>

    <h2>
        <a href="/find">forward(요청 위임)</a>
    </h2>
    <pre>

	    - 클라이언트의 요청을 받은 Servlet/JSP에서 응답을 수행하는 것이 아닌
         HttpServletRequest, HttpServletResponse 객체를 
         다른 Servlet/JSP로 넘겨 대신 응답하게 하는 것

        - 보통 Servlet에서 요청 처리를 완료한 후  
          응답 화면을 만들 때 JSP로 위임함

        - RequestDispatcher 객체를 이용해 
        HttpServletRequest, HttpServletResponse를 
        지정된 Servlet/JSP로 요청 위임

        String path = "JSP 파일 경로";
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    </pre>

    <hr><hr>

    <h2>
        <a href="/signup">Redirect(재요청)</a>
    </h2>
    <pre>
        - 클라이언트의 요청을받은 Servlet/JSP에서
         직접 응답 또는 요청 위임을 하지 않고
         다른 Servlet/JSP를 요청하는 것

        - 보통 Servlet에서 요청 처리를 완료한 후
        보여줄 응답 화면이 없거나
        다른 응답화면을 만들어주는 요청을 다시 요청함

       ***********************************************
       redirect는 다시 요청하는 것이기 때문에
       새로운 HttpServletRequest 객체가 만들어진다

       -> 기존 HttpServletRequest가 사라지기 때문에
        파라미터, request에 세팅한 속성 등을 사용 불가
    
        -> 이 때 session을 이용하면 문제 해결 가능
       ************************************************

        ex) 메인 페이지 -> 로그인 -> 메인 페이지  
            (로그인 성공/실패 했다는 페이지는 보통 없음)

            게시글 목록 -> 게시글 작성 -> 작성한 게시글 페이지
            (게시글이 작성되면 작성한 게시글 페이지로 이동함)


        resp.sendRedirect("재요청할 주소");
    </pre>


    <hr><hr>

    <h2>
        <a href="/student">학생 검색</a>
    </h2>
    


    <%-- <h2>JSTL(Jsp Standard Tag Library)</h2> --%>
</body>
</html>