<%@page import="com.products.model.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	ProductDTO cont = (ProductDTO)request.getAttribute("content");

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
			<h3>물품 상세 정보</h3>
		<hr width="30%" color="skyblue">
		
		<table border="1" cellspacing="0" width="400">
			<%
				if(cont != null){
			%>		
				<tr>
					<th>제품 번호</th>
					<td><%=cont.getPnum() %></td>
				</tr>
				
				<tr>
					<th>제품 코드</th>
					<td><%=cont.getCategory_fk() %></td>
				</tr>
				
				<tr>
					<th>제품명</th>
					<td><%=cont.getCategory_name() %></td>
				</tr>
				
				<tr>
					<th>제품 상세코드</th>
					<td><%=cont.getEp_code_fk() %></td>
				</tr>
				
				<tr>
					<th>입고가</th>
					<td><%=cont.getInput_pricte() %>원</td>
				</tr>
				
				<tr>
					<th>출고가</th>
					<td><%=cont.getOutput_price() %>원</td>
				</tr>
				
				<tr>
					<th>제품 배송비</th>
					<td><%=cont.getTrans_cost()%>원</td>
				</tr>
				
				<tr>
					<th>마일리지</th>
					<td><%=cont.getMailge() %>포인트</td>
				</tr>
				
				<tr>
					<th>제조사</th>
					<%if(cont.getCompany() == null){
						%>
					<td>
					
					</td>
					<% } else{ %>
						<td><%=cont.getCompany() %></td>
					<% } %>
				</tr>
				
			<%} else{
			%>
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 제품이 없습니다...</h3>
					</td>				
				</tr>
			<% } %>
			
			<tr>
				<td colspan="2" align="center" width="100">
					<input type="button" value="제품 수정" 
					onclick="location.href='update.do?no=<%=cont.getPnum() %>'">
					
					<input type="button" value="제품 삭제" 
					onclick="if(confirm('정말로 삭제하시겠습니까?')){
						location.href='delete.do?no=<%=cont.getPnum()%>'
					}else{return;}">
					
					<input type="button" value="제품 목록" 
					onclick="location.href='select.do'">
				</td>
			</tr>
					
		
		</table>
	</div>
</body>
</html>