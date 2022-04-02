<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<div align="center">
		<hr width="30%" color="orange">
			<h2>HUMAN 추가 화면</h2>	
		<hr width="30%" color="orange">
	</div>
	
	<div align="center">
		<form method="post" action="insert_ok">
			<table border="1" cellspacing="0" width="270">
				<tr>		
					<th>아이디</th>
					<td><input type="text" name="id"></td>				
				</tr>
				
				<tr>		
					<th>이름</th>
					<td><input type="text" name="name"></td>				
				</tr>
				
				<tr>		
					<th>직업</th>
					<td><input type="text" name="job"></td>				
				</tr>
				
				<tr>		
					<th>나이</th>
					<td><input type="text" name="age"></td>				
				</tr>
				
				<tr>		
					<th>주소</th>
					<td><input type="text" name="addr"></td>				
				</tr>
				
				<tr>		
					<th>전화번호</th>
					<td><input type="text" name="phone"></td>				
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="친구추가">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="다시작성">
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
</body>
</html>