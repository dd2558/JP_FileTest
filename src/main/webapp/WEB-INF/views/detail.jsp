<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>여행 정보 상세 페이지</title>
</head>
<body>
    <h1>장소 이름: ${placeName}</h1>
    
    <!-- 파일 목록 -->
    <h2>파일 목록</h2>
    <ul>
        <c:forEach var="file" items="${files}">
            <!-- 해당 파일의 placeName이 placeName과 같을 때 출력 -->
            <c:if test="${file.place_name eq placeName}">
                <li>
                    <img src="${file.filepath}" style="width:150px;">
                    <!-- 파일에 대한 기타 정보 -->
                    <p>여행지명: ${file.place_name}</p>
                    <p>주소: ${file.address}</p>
                    <p>연락처: ${file.phone_number}</p>
                    <p>SNS: ${file.sns_url}</p>
                    <p>기타정보: ${file.other_info}</p>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</body>
</html>
