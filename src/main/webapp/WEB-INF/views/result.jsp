<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>여행 정보와 사진 목록</title>
</head>
<body>
    <h1>여행 정보</h1>
    
    <c:if test="${not empty tripInfo}">
        <p>해시태그: ${tripInfo.hashtag}</p>
        <!-- 다른 정보들도 필요한 만큼 출력 -->
    </c:if>
    
    <h2>사진 목록</h2>
    <ul>
        <c:forEach var="file" items="${files}">
            <li>
                <img src="${file.filepath}" style="width:150px;">
                <p>여행지명: ${file.place_name}</p>
                <!-- 기타 필요한 정보들도 출력 가능 -->
            </li>
        </c:forEach>
    </ul>
</body>
</html>
