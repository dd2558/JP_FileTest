<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>여행 정보</title>
</head>
<body>
    <h1>여행 정보</h1>
    
    <h2>장소 이름: ${tripInfo.place_name}</h2>
    <p>설명: ${tripInfo.description}</p>
    <p>주소: ${tripInfo.address}</p>
    <p>평점: ${tripInfo.rating}/5</p>

    <h2>사진 목록</h2>
    <ul>
        <c:if test="${not empty files}">
    <img src="${files[0].filepath}" style="width:150px;">
		</c:if>
    </ul>
    <a href="/detail?place_name=${tripInfo.place_name}">상세보기</a>

</body>
</html>
