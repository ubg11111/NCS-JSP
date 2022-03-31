<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	body{
		margin: 0;
		padding: 0;
		width: 100%;
		height: 100%;
	}
	
	#box{
		position:relative;
		text-align: center;
		margin: 0 auto;
		margin-top: 50px;
		width: 300px;
		height: 300px;
		border: 3px solid gray;
	}

	form{
		margin-top: 1px;
		padding: 10px 5px;
	}
	.button_box{
		display: flex;
		justify-content: center;
		align-items: center;
		margin-top: 60px;
	}
	.button_box input{
		margin: 20px;
	}

</style>
</head>
<body>
	
	<div id="box" align="center">
		<form method="post" action="login_test">
			<div class="login_box">
				<p>아이디 <input type="text" name="id"></p>
				<p>비밀번호 <input type="password" name="pwd"></p>
			</div>
			
			<div class="button_box">
				<input type="submit" value="전송">
				<input type="reset" value="취소">
			</div>
		</form>
	</div>
	
</body>
</html>