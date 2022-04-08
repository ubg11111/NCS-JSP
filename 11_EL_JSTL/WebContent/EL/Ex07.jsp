<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>표현언어 (EL)로 scope 내용 출력</h2>
	
	<h3>
		<!-- 해당페이지내에서만 출력  -->
		page >>> ${pageScope.K } == ${K }<br>
		
		<!-- 요청을 받은 페이지에서만 출력 -->
		request >>> ${requestScope.R } == ${R } <br>
		
		<!-- 세션기한이 있는 경우에만 출력  -->
		session >>> ${sessionScope.S } == ${S } <br>
		
		<!-- 톰캣서버가 가동되는 경우에만 출력 -->
		application >>> ${applicationScope.A } == ${A }<br>
	
	
	</h3>
	
	
</body>
</html>