<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>파일 업로드 테스트</h1>


<form action="/addUserAndFile" method="post" enctype="multipart/form-data">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"><br><br>
    <label for="content">Content:</label>
    <input type="text" id="content" name="content"><br><br>
    <label for="file">Choose files:</label>
    <input type="file" id="file" name="file" multiple><br><br>
    <button type="submit">Submit</button>
</form>



</body>
</html>