<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
   	<%
   		request.setCharacterEncoding("UTF-8");	
   	
   		// trim() : 맨 앞과 맨 뒤에 공백이 존재하면 공백을 제거해주는 메서드.
		String userId = request.getParameter("id").trim();
		String userName = request.getParameter("name");
		
		String userGender = request.getParameter("gender");
		String userAddr = request.getParameter("addr");
		
		
		String userPhone = request.getParameter("phone");
		String userEmail = request.getParameter("email");
	
	%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<h3>
			당신의 아이디는  <%=userId %> 입니다<br>
			
			당신의 이름은  <%=userName %> 입니다<br>
			
			당신의 성별은  <%=userGender %> 입니다<br>
					
			당신의 주소는  <%=userAddr %> 입니다<br>
			
			당신의 번호는  <%=userPhone %> 입니다<br>
			
			당신의 이메일은  <%=userEmail %> 입니다<br>
			
				
		</h3>
	</div>
	
</body>
</html>