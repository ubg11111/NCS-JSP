<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	// Ex04.jsp 페이지에서 넘어온 데이터를 받아 주어야 한다.
	String userId = request.getParameter("id");
	String userPwd = request.getParameter("pwd");	

	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<h2>
			입력 받은 아이디 : <%=userId %> <br>
			
			입력 받은 비밀번호 : <%=userPwd %>	<br>	
		</h2>
	</div>
	
</body>
</html>