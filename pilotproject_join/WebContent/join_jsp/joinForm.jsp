<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>


<% response.setContentType("text/html"); %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jstl/sql"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />
    
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>회원 가입창</title>
   <script>
   function chkVali(){
	   var id = document.getElementById("id").value;
	   var name = document.getElementById("name").value;
	   var pwd1 = document.getElementById("pwd1").value;
	   var pwd2 = document.getElementById("pwd2").value;
	   
	   if(!id){
		   alert("아이디를 입력해주세요.");
		   document.getElementById("id").focus();
		   return false;
	   }
	   
	   if(!name){
		   alert("이름을 입력해주세요.");
		   document.getElementById("name").focus();
		   return false;
	   }
	   
	   if(!pwd1){
		   alert("암호를 입력해주세요.");
		   document.getElementById("pwd1").focus();
		   return false;
	   }
	   
	   if(!pwd2){
		   alert("확인 암호를 입력해주세요.");
		   document.getElementById("pwd2").focus();
		   return false;
	   }
	   
	   if(pwd1 != pwd2){
		   var obj = document.getElementById("chkTxt2");
		   obj.style.visibility = "";
		   
	   }else{
		   obj.style.visibility = "hidden";
		   return true;
	   }

   }
   </script>
</head>
<body>
<form method="post"   action="${contextPath}/member/addMember.do" onSubmit="chkVali();return false">
		<input type="hidden" name="command" value="joinForm" />
		아이디: <input type="text" name="id" id="id"/>
		<span id="chkTxt" <c:if test="${param.chk ne 'N'}">style="visibility: hidden;"</c:if>>이미 사용중인 아이디 입니다.</span><br><br>
		이름 : <input type="text" name="name" id="name" /><br><br>
		암호: <input type="password" name="pwd1" id="pwd1" /><br><br>
		확인: <input type="password" name="pwd2" id="pwd2"/>
		<span id="chkTxt2" style="visibility: hidden;">암호와 확인이 일치하지 않습니다.</span><br><br>
		<br>
		<input type="submit" value="가입"/>	
</form>
</body>
</html>