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
		<h2>회원 가입 화면</h2>
		
		<form method="post" action="join">
			<table	border="1" cellspacing="0">
				<tr>
					<th>아이디</th>
					<td><input type='text' name='id'>
				</tr>
				
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name='pwd'>
				</tr>
				
				<tr>
					<th>이름</th>
					<td><input type='text' name='name'>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td><input type='text' name='phone'>
				</tr>
				
				<tr>
					<th>주소</th>
					<td><input type='text' name='adder'>
				</tr>
				
				
				<tr>
					<th>취미</th>
					<td>
						<input type='checkbox' name='hobby' value="여행">여행&nbsp;&nbsp;
						<input type='checkbox' name='hobby' value="독서">독서&nbsp;&nbsp;
						<input type='checkbox' name='hobby' value="운동">운동&nbsp;&nbsp;
						<input type='checkbox' name='hobby' value="게임">게임&nbsp;&nbsp;
				</tr>
				
				
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="가입">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="취소">
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	
</body>
</html>