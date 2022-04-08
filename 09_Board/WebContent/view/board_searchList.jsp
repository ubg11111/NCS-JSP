<%@page import="com.board.model.BoardDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	List<BoardDTO> search = (List<BoardDTO>)request.getAttribute("Search");

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
			<h3>BOARD 테이블 검색 게시물 목록</h3>
		<hr width="30%" color="skyblue">
		
		<table border="1" cellspacing="0" width="500">
			<tr>
				<th>글 번호</th> <th>글 제목</th> <th>작성자</th>
				 <th>글 조회수</th><th>글 작성일자</th> 
			</tr>
			
			<%
				if(search.size() != 0){
					// 검색된 글만큼 출력
					for(int i=0; i<search.size(); i++){
			
					// DTO 주소값에 해당 반복문을 넣기
					BoardDTO dto = search.get(i);
			%>		
				<tr>
					<td><%=dto.getBoard_no() %></td>
					
					<td>
					
					<a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getBoard_no()%>">
					<%=dto.getBoard_title() %></a>
					
					
					</td>
					
					<td><%=dto.getBoard_writer() %></td>
					<td><%=dto.getBoard_hit() %></td>
					<td><%=dto.getBoard_date().substring(0,10) %></td>
				</tr>
				
			<% } // for 반복문의 end
			
			
			}else{ // 검색된 게물이 없는 경우
			%>	
				<tr>
					<td colspan="5" align="center">
						<h3>검색된 게시물이 없습니다.</h3>
					</td>
				</tr>
			
			<%}%>
			
		</table>
		
	</div>

</body>
</html>