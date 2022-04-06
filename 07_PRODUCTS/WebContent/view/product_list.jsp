<%@page import="com.products.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 서블릿을 통해서 해당 정보를 받아와야한다.
	List<ProductDTO> product = (List<ProductDTO>)request.getAttribute("pList");

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
			<h3>PRODUCT 테이블 제품 전체 리스트</h3>
		<hr width="30%" color="skyblue">
		
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>제품 NO.</th> <th>카테고리코드</th>
				<th>제품명</th> <th>제조사</th>	
			</tr>		
			
			
			<%	// 검색된 제품목록이 있는경우
				if(product.size() != 0){
					// 검색된 제품목록 크기만큼 반복하여 웹 브라우저에 출력
					for(int i=0; i<product.size(); i++){
						
						// DTO 주소값에 해당 반복문을 넣기
						ProductDTO dto = product.get(i);
			%>		
					<tr>
						<td><%=dto.getPnum() %></td>
						<td><%=dto.getCategory_fk() %></td>
						<td>
					
						<a href="<%=request.getContextPath()%>/content.do?no=<%=dto.getPnum()%>">
						<%=dto.getCategory_name() %></a>
						
						</td>
						<%
						if(dto.getCompany() == null){
						%>
							<td></td>								
						<% }else{ %>
							
							<td><%=dto.getCompany() %></td>						
							
						<% } %>
					</tr>
					
			<%		} // for반복문의 end
					
				}else{ // 검색된 제품 목록이 없다면
			%>
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 제품 목록이 없습니다.....</h3>
					</td>
				</tr>
			
			<%}%>
			
			<tr>
				<td colspan="4" align="right">
					<input type="button" value="제품등록"
						onclick="location.href='insert.do'">
				</td>
			</tr>
			
		</table>
	</div>
	
</body>
</html>