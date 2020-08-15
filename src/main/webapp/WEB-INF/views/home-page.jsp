<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<script src="<c:url value="/resources/javascript/home.js"/>" defer></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<link rel="stylesheet" href="<c:url value="/resources/stylesheet/page.css"/>"></link>
<title>Smart Manager</title>
</head>

<body>
   	<nav>
   	<label class="logo">Smart Manager</label>
   	<ul>
   		<li><a href="/smartmanager/home">Home</a>
   		<li><a href="/smartmanager/tasks">Tasks</a>
   		<li><a href="/smartmanager/members">Members</a>
   		<li><a href="/smartmanager/projects">Projects</a>
   	</ul>
   </nav>
   
   <div class="home">
   
   		<h1>Welcome to Smart Manager</h1>
   		<p>With smart manager you can manage your projects in an efficient manner</p>
   </div>
   <div class="statistics">
   		
   		<h3>Current statistics</h3>
   		
   		<p>Projects: <p>
   		<c:forEach var="project" items="${requestScope.projects}">
   			 <label for="male"><c:out value="${project.projectName}"/></label><br>
		</c:forEach>
   		<p>No of members: <p>
   		<c:forEach var="member" items="${requestScope.members}">
   			 <label for="male"><c:out value="${member.memberName}"/></label><br>
		</c:forEach>
   		<p>No of tasks:<p>
   		<c:forEach var="task" items="${requestScope.tasks}">
   			 <label for="male"><c:out value="${task.taskName}"/></label><br>
		</c:forEach>
   		
   </div>
   
</body>

</html>