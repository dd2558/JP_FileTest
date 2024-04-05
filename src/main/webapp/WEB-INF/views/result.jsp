<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>여행 정보 상세 페이지</title>
</head>
<body>
    <h1>장소 이름: ${tripInfo.place_name }</h1>
    
    <!-- 파일 목록 -->
    <h2>파일 목록</h2>
    <ul>
        <!-- 이미지를 차례로 표시 -->
        <c:forEach var="file" items="${files}" varStatus="loop">
            <!-- 현재 파일의 place_name과 이전 파일의 place_name이 같은지 확인 -->
            <c:if test="${loop.first or file.place_name ne files[loop.index - 1].place_name}">
                <li>
                    <a href="/detail?place=${file.place_name}&hashtag=${file.hashtag}">
                        <img src="${file.filepath}" style="width:150px;">
                    </a>
                </li>
                <p>여행지명: <a href="/detail?place=${file.place_name}&hashtag=${file.hashtag}">${file.place_name}</a></p>
                <p>한줄소개: ${file.description}</p>
                <p>주소: ${file.address}</p>
                <p>평점: ${file.rating}/5</p>
                <a href="/delete?filename=${file.filename}">삭제하기</a>
            </c:if>
        </c:forEach>
    </ul>
    
    <!-- 설명은 한 번만 표시 -->
</body>
</html>
