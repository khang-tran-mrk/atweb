<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	Đăng ký
	<form action="<%=request.getContextPath()%>/signup" method="post">
		<input type="text" name="username" placeholder="username">
		<input type="password" name="password" placeholder="password">
		<input type="email" name="email" placeholder="email">
		<input type="number" name="sdt" placeholder="sdt">
		<input type="submit">
	</form>
</body>
</html>