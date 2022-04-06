<%@page import="human_model.HumanDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	HumanDTO cont = (HumanDTO)request.getAttribute("update");


%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="skyblue">
			<h3>친구 정보 수정창</h3>
		<hr width="30%" color="skyblue">
		
		
		<form method="post" action="<%=request.getContextPath()%>/update_ok">
			<input type="hidden" name="num" value="<%=cont.getId()%>">
			
			<table border="1" cellspacing="0" width="400">
				<tr>
					<th>친구 이름</th>
					<td><input type="text" name="human_name" value="<%=cont.getName()%>" readonly></td>
				</tr>
				
				<tr>
					<th>친구 직업</th>
					<td><input type="text" name="human_job" value="<%=cont.getJob()%>"></td>
				</tr>
				
				<tr>
					<th>친구 나이</th>
					<td><input type="text" name="human_age" value="<%=cont.getAge()%>"></td>
				</tr>
				
				<tr>
					<th>친구 주소</th>
					<td><input type="text" name="human_addr" value="<%=cont.getAddr()%>"></td>
				</tr>
				
				<tr>
					<th>친구 전화번호</th>
					<td><input type="text" name="human_phone" value="<%=cont.getPhone()%>"></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="수정하기">
						<input type="reset" value="다시작성">
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
	
</body>
</html>