<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%

	//request.setCharacterEncoding("UTF-8");

	//String userId = request.getParameter("id").trim();
	
	//String userName = request.getParameter("name").trim();
	
	//int userAge = 
			//Integer.parseInt(request.getParameter("age"));
	
	
%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
<%-- 	<div align="center">
	
		<h2>JSP 표현식을 이용하여 파라미터 값을 출력하는 방법</h2>
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>아이디</th>
				<td><%=request.getParameter("id").trim() %></td>			
			</tr>
			
			<tr>
				<th>이름</th>
				<td><%=request.getParameter("name").trim() %></td>			
			</tr>
			
			<tr>
				<th>아이디</th>
				<td><%=request.getParameter("age") %></td>			
			</tr>
			
		</table>
	
	</div> --%>
	
	
	
	<div align="center">
	
		<h2>표현언어(EL)을 이용하여 파라미터 값을 출력하는 방법</h2>
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>아이디</th>
				<td>${param.id}</td>			
			</tr>
			
			<tr>
				<th>이름</th>
				<td>${param.name}</td>			
			</tr>
			
			<tr>
				<th>아이디</th>
				<td>${param.age}</td>			
			</tr>
		</table>
	
	</div>
	
	
</body>
</html>