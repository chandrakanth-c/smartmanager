<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<c:url value="/resources/javascript/home.js"/>" defer></script>
<link href="<c:url value="/resources/css/home.css"/>" rel="stylesheet"></link>
<title>Projects</title>
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
	 
	<div class="projects-table">
		<form action="/smartmanager/project.htm" method="post">
			<div class="projectinsert">
				<label id="lblPname">Project Name:</label>
				<input type="text" name="pname"/>
				<label id="lblPname">Start Date:</label>
				<input type="date" name="pstartdate"/>
				<label id="lblPname">End Date:</label>
				<input type="date" name="penddate"/> &nbsp;
				<input type="submit" value="Add project" id="addproject"/>
			</div>
		</form>
		<table class="content-table">
			<thead>
				<tr>
					<th>Project Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Modify Project</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="project" items="${requestScope.projects}">
			
				<tr>
					<td><input type="text" class="tablebtn" onblur="assignName(event)" id="projname" value="<c:out value="${project.projectName}"/>" disabled/>   </td>
					<td><input type="date" class="tablebtn" onblur="assignStartDate(event)" id="projstart" value="<c:out value="${project.startDate}"/>" disabled/></td>
					<td><input type="date" class="tablebtn" onblur="assignEndDate(event)" id="projend" value="<c:out value="${project.endDate}"/>" disabled/></td>
					<td>
					<button class="minus" id="edit" onclick="toggleDisable()">Edit</button>
					<form action="/smartmanager/projectDeleted.htm" method="post">
						<input type="submit" class="minus" value="-" />
						<input type="hidden" name="projectname" value="<c:out value="${project.projectName}"/>"/>
					</form>
					<form action="/smartmanager/projectUpdated.htm" method="post">
						<input type="hidden" id="proName" name="pname" value=""/>
						<input type="hidden" id="proStartDate" name="pstartdate" value=""/>
						<input type="hidden" id="proEndName" name="penddate" value="" /> 
						<input type="submit" class="minus"  value="✔" />
					</form></td>
				</tr>
			
			</c:forEach>
			
			</tbody>
		</table>
	</div>
	
	

</body>
</html>