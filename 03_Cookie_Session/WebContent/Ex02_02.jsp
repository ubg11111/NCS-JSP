<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String userId = request.getParameter("id").trim();
		String userPwd = request.getParameter("pwd").trim();
		
		
		// Ex02_01.jsp 페이지에서 넘어온 세션 정보도 받을 수 있음.
		String sessionId = (String)session.getAttribute("ID");
		
		String sessionPwd = (String)session.getAttribute("PWD");
		
		String sessionName = (String)session.getAttribute("NAME");
		
		// getMaxInactiveInterval() : 세션의 유효 시간을 확인하는 메서드
		int time = session.getMaxInactiveInterval();
	
	%>
	
	<h2>입력 폼 (Ex02.jsp)에서 넘어온 데이터 출력</h2>
	
	<h3>
		입력받은 아이디 : <%=userId %><br>
		
		입력받은 비밀번호 : <%=userPwd %><br>
	</h3>
	
	
	<h2>세션으로 넘어온 데이터</h2>
	
	<h3>
		세션으로받은 아이디 : <%=sessionId %><br>
		
		세션으로받은 비밀번호 : <%=sessionPwd %><br>
		
		세션으로받은 이름 : <%=sessionName %><br>
	
	</h3>
	
	<script type="text/javascript">
		location.href = "Ex02_03.jsp";
	</script>
	
</body>
</html>