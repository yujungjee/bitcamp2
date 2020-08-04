<%@ page language="java" contentType="text/html; charset=UTF-8" 
	import=" java.util.*,join.*" 
	pageEncoding="UTF-8"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  

<%
	request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
   <meta  charset="UTF-8">
   <title>회원 정보 출력창</title>
</head>
<body>
<body>
<body>
	<h2><c:out value="${param.id}" />님, 회원 가입에 성공했습니다. <br /></h2>
	<a href="${contextPath}/join_jsp/login.jsp">메인으로 가기</a> <!--  연결되야 하는 부분이라 아무거나 적어뒀음 -->
</body>
</body>
</html>