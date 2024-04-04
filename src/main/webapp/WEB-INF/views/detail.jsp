<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>여행 정보 상세 페이지</title>
</head>
<body>
    <!-- 여행지 이름 출력 -->
    <h1>장소 이름: ${placeName}</h1>
    
    <!-- 파일 목록 -->
    <h2>파일 목록</h2>
    <ul>
        <!-- 해당 장소에 대한 이미지를 우선 모두 출력 -->
        <c:forEach var="file" items="${files}">
            <!-- 현재 파일이 선택한 장소와 같은 장소일 때 이미지 출력 -->
            <c:if test="${file.place_name eq placeName}">
                <div>
                    <img src="${file.filepath}" style="width:150px;">
                </div>
            </c:if>
        </c:forEach>
    </ul>
    
    <!-- 장소 정보는 마지막에 한 번만 출력 -->
    <ul>
        <!-- 장소 정보가 이미 출력되었는지 확인하는 변수 설정 -->
        <c:set var="placeInfoDisplayed" value="false" />
        <!-- 파일 목록을 다시 순회하며 장소 정보 출력 -->
        <c:forEach var="file" items="${files}">
            <!-- 현재 파일이 선택한 장소와 같은 장소일 때 -->
            <c:if test="${file.place_name eq placeName}">
                <!-- 장소 정보가 아직 출력되지 않았다면 -->
                <c:if test="${not placeInfoDisplayed}">
                    <!-- 장소 정보 출력 -->
                    <li>
                        <p>여행지명: ${file.place_name}</p>
                        <p>주소: ${file.address}</p>
                        <p>연락처: ${file.phone_number}</p>
                        <p>SNS: ${file.sns_url}</p>
                        <p>기타정보: ${file.other_info}</p>
                    </li>
                    <!-- placeInfoDisplayed 변수를 true로 설정하여 다른 장소 정보를 더이상 출력하지 않도록 함 -->
                    <c:set var="placeInfoDisplayed" value="true" />
                </c:if>
            </c:if>
        </c:forEach>
    </ul>
</body>
</html>
