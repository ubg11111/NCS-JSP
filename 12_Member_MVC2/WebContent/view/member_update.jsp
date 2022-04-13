<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		
	<div align="center">
		<hr width="30%" color="tomato">
			<h3>MEMBER10 테이블 회원 수정</h3>
		<hr width="30%" color="tomato">
		<br>
		
		
		<form method="post" action="<%=request.getContextPath()%>/update_ok.do">
			
			<c:set var="fix" value="${Update}"/>
			<input type="hidden" name="mem_num" value="${fix.getNum()}">
			<input type="hidden" name="db_pwd" value="${fix.getPwd() }">
			
			<table border="1" cellspacing="0" width="300">
				
					<c:if test="${!empty fix}">
					<tr>
						<th>회원아이디</th>
						<td><input type="text" name="member_id" value="${fix.getMemid()}" readonly></td>
					</tr>
					
					
					<tr>
						<th>회원 이름</th>
						<td><input type="text" name="member_name" value="${fix.getMemname()}" readonly></td>
					</tr>
					
					<tr>
						<th>회원 비밀번호</th>
						<td>
							<input type="password" name="member_pwd" value="${fix.getPwd()}">
						</td>
					</tr>
					
					<tr>
						<th>회원 나이</th>
						<td><input type="text" name="member_age" value="${fix.getAge() }"></td>
					
					</tr>			
					
					<tr>
						<th>회원 마일리지</th>
						<td><input type="text" name="member_mileage" value="${fix.getMileage() }"></td>
					</tr>
					
					
					<tr>
						<th>회원 직업</th>
						<td><input type="text" name="member_job" value="${fix.getJob() }"></td>
					
					</tr>
					
					
					<tr>
						<th>회원 주소</th>
						<td><input type="text" name="member_addr" value="${fix.getAddr() }"></td>
					</tr>
				</c:if>
				
				<c:if test="${empty fix}">
					<tr>
						<td colspan="2" align="center">						
							<h3>검색된 회원에 대한 정보가 없습니다....</h3>
						</td>
					</tr>
				</c:if>
				
				
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="정보수정">
						<input type="reset" value="다시작성">
					</td>	
				</tr>
			</table>
		</form>
	
	</div>

</body>
</html>