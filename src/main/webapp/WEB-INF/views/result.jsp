<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 표시</title>
</head>

<body>
    <h1>이미지 표시</h1>
    <h2>사용자 정보</h2>
    <p>사용자 이름: ${user.username}</p>
    <p>사용자 내용: ${user.content}</p>

    <h2>파일 목록</h2>
    <ul>
        <c:forEach var="file" items="${files}">
            <li>
                <img src="${file.filepath}" style="width:150px;">
                <form action="/delete-images/${file.filename}" method="post">
                </form>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
