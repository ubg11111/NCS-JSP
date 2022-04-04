<%@page import="human_model.HumanDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	HumanDTO content = (HumanDTO)request.getAttribute("human_content");

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
			<h3>친구 상세 정보</h3>
		<hr width="30%" color="skyblue">
		
		
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<% 
				if(content != null){
			%>	
				<tr>
					<th>친구 아이디</th>
					<td><%=content.getId()%></td>
				</tr>
				
				<tr>
					<th>친구 이름</th>
					<td><%=content.getName() %></td>
				</tr>
				
				<tr>
					<th>친구 직업</th>
					<td><%=content.getJob() %></td>
				</tr>
				
				<tr>
					<th>친구 나이</th>
					<td><%=content.getAge() %></td>
				</tr>
				
				<tr>
					<th>친구 주소</th>
					<td><%=content.getAddr() %></td>
				</tr>
				
				<tr>
					<th>친구 전화번호</th>
					<td><%=content.getPhone() %></td>
				</tr>
				
			<%	} else{ // 검색된 정보가 없는 경우
				%>	
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 정보가 없습니다.</h3>
					</td>
				</tr>
			<%}%>
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="친구 수정" 
					onclick="location.href='update.do?num=<%=content.getId()%>'">
					
					<input type="button" value="친구 삭제" 
					onclick="if(confirm('정말로 삭제하시겠습니까?')){
						location.href='delete.do?num=<%=content.getId()%>'
					}else{return;}">
					
					<input type="button" value="친구 목록" 
					onclick="location.href='select'">
				</td>			
			</tr>
			
		
		</table>
	</div>
</body>
</html>