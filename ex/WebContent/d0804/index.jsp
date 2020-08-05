<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.11.1.min.js" type="text/javascript"></script>
	<style>
		table, td, th {
			border: 1px solid black;
			border-collapse: collapse;
			text-align: center;
			width: 600;
			height: 50;
		}
		th {
			background-color: #acc7f2;
		}
	</style>
</head>
<body>
	<input type="text" id="id">
	<button id="search">조회</button>
	<div id="output"></div>
	<script>
		$(function() {
			$("#search").click(function() {
				var _id=$("#id").val();
				$.ajax({
					type: "post",
					async: true,
					url: "${contextPath}/member",
					data: {id:_id},
					success: function(data, textStatus) {
						var jsonInfo = JSON.parse(data);
						console.log(jsonInfo);
						var memberTable = "<table><tr><th>아이디</th><th>비밀번호</th>"
											+ "<th>이름</th><th>이메일</th><th>가입일</th></tr>";
						for(var i in jsonInfo) {
							memberTable += "<tr><td>" + jsonInfo[i].id + "</td>";
							memberTable += "<td>" + jsonInfo[i].pwd + "</td>";
							memberTable += "<td>" + jsonInfo[i].name + "</td>";
							memberTable += "<td>" + jsonInfo[i].email + "</td>";
							memberTable += "<td>" + jsonInfo[i].joinDate + "</td></tr>";
						}
						$("#output").html(memberTable);
					}
				});
			});
		});
	</script>
</body>
</html>