<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 
		<fmt:formatDate> 태그의 여러 가지 속성.
		- value : 포맷팅될 날짜를 지정하는 속성.
				* date : 날짜를 지정.
				
		- type : 포맷팅할 타입을 지정하는 속성.
				*date : 날짜를 지정.
				*time : 시간을 지정.
				*both : 날짜 / 시간을 모두 지정.
		- dateStyle : 날짜의 출력 양식을 지정하는 속성.
					값(value)에는 full, long, medium, short 등을 지정할 수 있음.
					
		- timeStyle : 시간 출력 형식을 지정하는 속성.
		- pattern : 직접 출력 형식을 지정하는 속성.
		- timeZone : 특정 나라 시간대로 시간을 설정하는 속성
		
	
	 --%>
	
	<h2>formatDate 예제</h2>
	<c:set var="now" value="<%=new Date()%>"></c:set>
	
	<fmt:formatDate value="${now }" type="date" dateStyle="full"/> <br>
	
	<fmt:formatDate value="${now }" type="date" dateStyle="long"/> <br>
	
	<fmt:formatDate value="${now }" type="date" dateStyle="medium"/> <br>
	
	<fmt:formatDate value="${now }" type="date" dateStyle="short"/> <br>
	
	<hr>
	
	<fmt:formatDate value="${now }" type="time" timeStyle="full"/> <br>
	
	<fmt:formatDate value="${now }" type="time" timeStyle="long"/> <br>
	
	<fmt:formatDate value="${now }" type="time" timeStyle="medium"/> <br>
	
	<fmt:formatDate value="${now }" type="time" timeStyle="short"/> <br>
	
	<hr>
	
	<fmt:formatDate value="${now }" type="both" timeStyle="full"/> <br>
	
	<fmt:formatDate value="${now }" type="both" timeStyle="long"/> <br>
	
	<fmt:formatDate value="${now }" type="both" timeStyle="medium"/> <br>
	
	<fmt:formatDate value="${now }" type="both" timeStyle="short"/> <br>
	
	<hr>
	
	<fmt:formatDate value="${now }" pattern="YYYY-MM-dd hh:mm:ss"/> <br>
	
	<hr>
	
	한국 현재 시간 :
	<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/> <br>
	
	<fmt:timeZone value="America/LA">
		미국 LA 현재 시간:
		<fmt:formatDate value="${now }" type="both" dateStyle="full" timeStyle="full"/> <br>
	
	</fmt:timeZone>
	
</body>
</html>