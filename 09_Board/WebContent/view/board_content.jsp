<%@page import="com.board.model.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	BoardDTO board_content = (BoardDTO)request.getAttribute("board_cont");

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
			<h3>글 상세 내역</h3>
		<hr width="30%" color="skyblue">
		
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<%
				if(board_content != null){			
			%>
			
			<tr>
				<th colspan="2">
					<h3>
					<%=board_content.getBoard_writer() %>님 게시글 상세 내역
					</h3>
				</th>
			</tr>
			
			<tr>
				<th>작성자</th>
				<td><%=board_content.getBoard_writer() %></td>
			</tr>
				
			<tr>
				<th>글제목</th>
				<td><%=board_content.getBoard_title() %></td>
			</tr>
				
			<tr>
				<th>글내용</th>
				<td>
				<textarea rows="7" cols="20" readonly>
				<%=board_content.getBoard_cont()%></textarea>
				</td>
			</tr>	
			
			<tr>
				<th>비밀번호</th>
				<td>
					<%
						if(board_content.getBoard_pwd().length() != 0){
							for(int i=1; i<=board_content.getBoard_pwd().length(); i++){
					%>			
									*
					<% 		}
						} 
					%>				
				
				</td>
			</tr>
			
			<tr>
				<th>조회수</th>
				<td><%=board_content.getBoard_hit() %></td>			
			</tr>
			
			
			<tr>
				<%
					if(board_content.getBoard_update() == null){
				%>
				
					<th>작성일자</th>
					<td><%=board_content.getBoard_date() %></td>		
				<% 	} else {%>
					
					<th>수정일자</th>
					<td><%=board_content.getBoard_update() %></td>
					
				<% } %>
			</tr>
			
			
			<% } else{ %>
				
				<tr>
					<td colspan="2" align="center">
						<h3>조회된 게시글 상세내역이 없습니다.</h3>
					</td>
				</tr>
				
			<% }%>
			
			
			
			
			<tr>
				
				<td colspan="6" align="center">

					<input type="button" value="글 수정" 
					onclick="location.href='update.do?no=<%=board_content.getBoard_no()%>'">
					
					<input type="button" value="글 삭제" 
					onclick="if(confirm('정말로 삭제하시겠습니까?')){
						location.href='view/board_delet.jsp?no=<%=board_content.getBoard_no()%>'
					}else{return;}">
				

					<input type="button" value="글 목록"
						onclick="location.href='select.do'">
				
				</td>
			</tr>
				
		</table>
	</div>

</body>
</html>