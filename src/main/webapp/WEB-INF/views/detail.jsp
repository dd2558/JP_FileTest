<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>여행 정보 상세 페이지</title>
</head>
<body>
    <h1>장소 이름: ${tripInfo.place_name}</h1>
    
    <p>주소: ${tripInfo.address}</p>
    <p>매장번호: ${tripInfo.phone_number}</p>
    <p>SNS 주소: ${tripInfo.sns_url}</p>
    <p>기타 정보: ${tripInfo.other_info}</p>
    
    <!-- 여기서 파일 목록 등을 출력할 수 있음 -->
    
      <ul>
        <c:forEach var="file" items="${files}">
            <li>
                <img src="${file.filepath}" style="width:150px;">
                <!-- 기타 필요한 정보들도 출력 가능 -->
            </li>
        </c:forEach>
    </ul>
</body>
</html>
