<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<c:url value="/resources/stylesheet/page.css"/>"></link>
<title>Sign Up</title>
</head>
<body>

<nav>
   	<label class="logo">Smart Manager</label>
   	<ul>
   		<li><a href="home-page.jsp">Home</a>
   		<li><a href="/smartmanager/tasks">Tasks</a>
   		<li><a href="/smartmanager/members">Members</a>
   		<li><a href="/smartmanager/projects">Projects</a>
   	</ul>
   </nav>

<form action="/smartmanager/user.htm" method="post">
	
	<h2>Sign Up</h2>
	<hr/>
	Username &nbsp;<input type="text" name="uname"/> <br><br>
	Password   &nbsp; <input type="password" name="password"/> <br><br>
	
	<input type="submit" value="Sign Up" id="button-1"/><br><br>
	
</form>

</body>
</html>