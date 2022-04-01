<%@page import="com.member.model.MemberDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
<%
	
	// 오브젝트 타입(List<MemberDTO>)으로 형변환하여 가져온다. 
	List<MemberDTO> member = 	
				(List<MemberDTO>)request.getAttribute("member");
	

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="red">
			<h3>MEMEBER10 테이블 회원 전체 목록</h3>
		<hr width="30%" color="red">
		
		<br>
		
		<table border="1" cellspacing="0" width="900">
			<tr>
				<th>회원NO.</th>
				<th>회원아이디</th>
				<th>회원명</th>
				<th>회원비밀번호</th>
				<th>회원 나이</th>
				<th>회원 마일리지</th>
				<th>회원 직업</th>
				<th>회원 주소</th>
				<th>가입일</th>
			</tr>
			
			<%
				if(member.size() != 0){ // 데이터가 있는 경우
					// 데이터 수 만큼 반복하여 웹 브라우저에 출력
					for(int i=0; i<member.size(); i++){
						MemberDTO dto = member.get(i);						
			%>		
				<tr>
					<td><%=dto.getNum() %></td>
					<td><%=dto.getMemid() %></td>
					<td><%=dto.getMemname() %></td>
					<td><%=dto.getPwd() %></td>
					<td><%=dto.getAge() %></td>
					<td><%=dto.getMileage() %></td>
					<td><%=dto.getJob() %></td>
					<td><%=dto.getAddr() %></td>
					<td><%=dto.getRegdate() %></td>
				</tr>	
			<% 		
					}// for문의 end부분
				}else{ // 데이터가 없는 경우 - 검색된 회원 목록이 없는 경우
			%>
				<tr>
					<td colspan="9" align="center">
						<h3>검색된 회원이 없습니다.......</h3>
					</td>
				</tr>	
			<% 	} %>
		
		
		
		</table>
	</div>
	
	
</body>
</html>