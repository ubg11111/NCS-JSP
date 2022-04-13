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
	
		<c:set var="dto" value="${Cont }"/>
	
		<hr width="30%" color="tomato">
			<h3>${dto.getBoard_wirter()} 님 BOARD 테이블 게시물 상세 내역 페이지</h3>
		<hr width="30%" color="tomato">
		
		
		<br>
		
		
		<table border="1" cellspacing="0" width="400">
			<tr>
				<th>작성자</th>
				<td>${dto.getBoard_wirter() }</td>
			</tr>
		
			<tr>
				<th>글제목</th>
				<td>${dto.getBoard_title() }</td>
			</tr>
			
			<tr>
				<th>글 내용</th>
				<td>
				<textarea rows="7" cols="25" readonly>
					${dto.getBoard_cont() }
				</textarea>
				</td>
			</tr>
			
			<tr>
				<th>비밀번호</th>
				<td>
				<c:if test="${dto.getBoard_pwd().length() !=0}">
					<c:forEach begin="1" end="${dto.getBoard_pwd().length()}">
							*
					</c:forEach>
				</c:if>
				</td>
			</tr>
			
			<tr>
				<th>조회수</th>
				<td>${dto.getBoard_hit() }</td>
			</tr>
			
			<tr>
				<c:if test="${empty dto.getBoard_update()}">
					<th>작성일자</th>
					<td>${dto.getBoard_date() }</td>
				</c:if>
				
				<c:if test="${!empty dto.getBoard_update()}">
					<th>수정일자</th>
					<td>${dto.getBoard_update() }</td>
				</c:if>
				
				
			</tr>
			
			
			<c:if test="${empty dto }">
				<tr>
					<td colspan="2" align="center">
						<h3>검색된 게시물이 없습니다.....</h3>
					</td>				
				</tr>
			</c:if>
		
			
			<tr>
				<td colspan="2" align="center">
					<input type="button" value="글수정" 
						onclick="location.href='board_update.do?no=${dto.getBoard_no()}&page=${page}'">
				
					<input type="button" value="글삭제"
						onclick="if(confirm('게시글을 삭제하시겠습니까?')){
							location.href='board_delete.do?no=${dto.getBoard_no()}&page=${page}'
						}else{return;}">
					
					<input type="button" value="글목록"
						onclick="location.href='board_list.do?page=${page}'">
					
				</td>
			</tr>
			
		
		</table>
	</div>
	

</body>
</html>