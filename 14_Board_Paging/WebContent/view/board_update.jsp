<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="skyblue">	
			<h3>${dto.getBoard_wirter()}님 게시물 수정 폼 페이지</h3>
		<hr width="30%" color="skyblue">	
	
		<br>
		
		<c:set var="fix" value="${modify }"/>
		
		<form method="post" action="<%=request.getContextPath()%>/board_update_ok.do">
		
						
			<input type="hidden" name="board_no" value="${fix.getBoard_no()}">
			<input type="hidden" name="db_pwd" value="${fix.getBoard_pwd()}">
			<input type="hidden" name="page" value="${page}">
			
			
			
			<table border="1" cellspacing="0" width="400">
				
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
							<textarea rows="7" cols="25" name="content">
								${fix.getBoard_cont() }
							</textarea>
						</td>
					</tr>
					
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="pwd"></td>
					</tr>
					
					
				</c:if>
				
				
				<c:if test="${empty fix }">
					<tr>
						<td colspan="4" align="center">
							<h3>검색된 게시물이 없습니다.</h3>
						</td>
					</tr>
				</c:if>
				
				<tr>
					<td colspan="2" align="center">
						<input type=submit value="수정">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
				
							
			</table>
		</form>
	
	
	</div>
	
	
</body>
</html>