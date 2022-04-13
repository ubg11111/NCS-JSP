<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="gray">
			<h3>글 상세 내역</h3>
		<hr width="30%" color="gray">
		
		<br>
		
		
	<form method="post" action="<%=request.getContextPath()%>/border_update_ok.do">
		<c:set var="fix" value="${modify}" />
		
		
		<input type="hidden" name="border_no" value="${fix.getBoard_no()}">
		
		
		<table border="1" cellspacing="0" width="350">
			<c:if test="${!empty fix}">
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer" value="${fix.getBoard_wirter()}" readonly></td>
				</tr>
			
				<tr>
					<th>글제목</th>
					<td><input type="text" name="title" value="${fix.getBoard_title()}"></td>
				</tr>
				
				
				<tr>
					<th>글내용</th>
					<td>
					<textarea rows="7" cols="20" name="content">
						${fix.getBoard_cont() }</textarea>
					</td>
				</tr>
			
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="pwd"></td>
				</tr>
			</c:if>
			
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="글 수정">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시 작성">
				</td>
			</tr>
		</table>
	</form>
	
	</div>
	
</body>
</html>