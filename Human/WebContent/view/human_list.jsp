<%@page import="human_model.HumanDTO"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 자바를 작성할 수 있는 스클립트 태그
	
	// 오브젝트 타입의 List<HumanDTO> 태그를 가져온다.
	// 리스트의 전체는 오브젝트타입이기에 HumanDTO 오브젝트 형변환을 요청에 형변환하여 넣어줘야한다.
	List<HumanDTO> humanList = (List<HumanDTO>)request.getAttribute("human");


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
			<h3>HUMAN 테이블 친구 전체 목록</h3>
		<hr width="30%" color="red">
		
		
		<br><br>
		
		
		<table border="1" cellspacing="0">
		 	<tr>
		 		<th>친구 아이디</th>
		 		<th>친구 이름</th>
				<th>친구 직업</th>		 		
		 		<th>친구 삭제</th>
		 	</tr>
		 	
		 		<% 
					if(humanList.size() != 0){
						//데이터가 있는 경우 데이터 수만큼 출력
						for(int i=0; i<humanList.size(); i++){
							HumanDTO dto = humanList.get(i);
				%>
					<tr>
						<td><%=dto.getId()%></td>
						
						<td>
						<a href="<%=request.getContextPath()%>/content.do?num=<%=dto.getId()%>">					
						<%=dto.getName() %></a>
						</td>
						
						<td><%=dto.getJob() %></td>
						
						<td>
							<input type="button" value="친구삭제" 
							onclick="location.href='delete?no=<%=dto.getId()%>'">
						</td>
					</tr>
				<%		
						}
					}else{
				%>
					<tr>
						<td colspan="4" align="center">
							<h2>검색된 친구가 없어요</h2>						
						</td>
					</tr>
				<% }%>
				
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="친구 추가" 
					onclick="location.href='insert.jsp'">
				</td>
			</tr>
				
		</table>
	</div>

</body>
</html>