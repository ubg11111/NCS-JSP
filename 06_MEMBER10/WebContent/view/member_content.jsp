<%@	page import="com.member.model.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	MemberDTO cont = (MemberDTO)request.getAttribute("content");

%>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="tomato">
			<h3>MEMBER10 테이블 회원 상세 정보</h3>
		<hr width="30%" color="tomato">
	
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<%
				if(cont != null){
					// 데이터가 있다면
			%>
				<tr>
					<th>회원No.</th>
					<td><%=cont.getNum()%></td>
				</tr>
			
				<tr>
					<th>회원 아이디</th>
					<td><%=cont.getMemid() %></td>
				</tr>			
				
				
				<tr>
					<th>회원 이름</th>
					<td><%=cont.getMemname() %></td>
				</tr>
				
				<tr>
					<th>회원 비밀번호</th>
					<td>
						<%
						if(cont.getPwd().length() != 0){
							for(int i=1; i<=cont.getPwd().length(); i++){
						%>		
								*	
						<%  		}
							} %>
					</td>
				</tr>
				
				<tr>
					<th>회원 나이</th>
					<td><%=cont.getAge() %></td>
				</tr>
				
				<tr>
					<th>회원 마일리지</th>
					<td><%=cont.getMileage() %></td>
				</tr>
				
				<tr>
					<th>회원 직업</th>
					<td><%=cont.getJob() %></td>
				</tr>
				
				<tr>
					<th>회원 주소</th>
					<td><%=cont.getAddr() %></td>
				</tr>
				
				<tr>
					<th>회원 가입일</th>
					<td><%=cont.getRegdate() %></td>
				</tr>
				
			<%	} else{ // 검색된 정보가 없는 경우
			%>	
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 정보가 없습니다.</h3>
					</td>
				</tr>
			<% } %> 
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="회원수정" 
					onclick="location.href='update.do?num=<%=cont.getNum()%>'">
					
					<input type="button" value="회원삭제" 
					onclick="if(confirm('정말로 삭제하시겠습니까?')){
						location.href='delete.do?num=<%=cont.getNum()%>'
					}else{return;}">
					
					<input type="button" value="회원목록" 
					onclick="location.href='select.do'">
				</td>			
			</tr>
		</table>
	</div>
	
</body>
</html>