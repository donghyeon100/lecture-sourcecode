<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>부서 추가 하기</title>
</head>
<body>
    <form action="/department/insert" method="post">
        <h1>부서 추가 하기</h1>

        <div>
            부서 코드(DEPT_ID) : <input type="text" name="deptId">
        </div>
        <div>
            부서명(DEPT_TITLE) : <input type="text" name="deptTitle">
        </div>
        <div>
            지역 코드(LOCATION_ID) : <input type="text" name="locationId">
        </div>

        <button>추가하기</button>
    </form>

    <hr>
    <hr>
    <hr>

    <h1>여러 부서 한 번에 추가 하기</h1>
    <form action="/department/multiInsert" method="post" name="multiInsertForm">
        <button type="button" id="addBtn">입력 추가</button>

        <table>
            <thead>
                <tr>
                    <th>부서 코드</th>
                    <th>부서명</th>
                    <th>지역 코드</th>
                    <th>삭제버튼</th>
                </tr>
            </thead>

            <tbody id="tbody">
                <tr>
                    <td>
                        <input type="text" name="deptId">
                    </td>
                    <td>
                        <input type="text" name="deptTitle">
                    </td>
                    <td>
                        <input type="text" name="locationId">
                    </td>
                    <th>
                        <button type="button" class="remove-btn">삭제</button>
                    </th>
                </tr>
            </tbody>
        </table>

        <button>부서 추가 하기</button>
    </form>


    <script src="/resources/js/insert.js"></script>
</body>
</html>