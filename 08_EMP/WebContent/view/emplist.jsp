<%@page import="com.emp.model.EmpDTO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="skyblue">
			<h3>EMP사원 전체 목록</h3>
		<hr width="30%" color="skyblue">
		
		<br>
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>사원No.</th>
				<th>사원이름</th>
				<th>매니저번호</th>
				<th>부서번호</th>
			</tr>
			
			<!-- EL언어 사용하기  list변수에 List(키)값을 저장한다-->
			<c:set var="list" value="${List }"/>
			
			<!-- list 값이 비어있지 않은경우 -->
			<c:if test="${!empty list}">
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.getEmpno() }</td>					
						
						<td>
						<a href="<%=request.getContextPath()%>/content.do?no=${getEmpno()}">
							${dto.getEname() }</a>
						</td>
						
						<td>${dto.getMgr() }</td>
						
						<td>${dto.getDeptno() }</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<!-- 목록값이 없는경우 -->
			<c:if test="${empty list }">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 사원의 목록이 없습니다....</h3>					
					</td>				
				</tr>
			</c:if>
			
			<tr>
				<td colspan="4" align="center">
					<input type="button" value="사원 등록"
						onclick="location.href='insert.do'">
				</td>
			</tr>
			
			
		</table>
	</div>
	
</body>
</html>