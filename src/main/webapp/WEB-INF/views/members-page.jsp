<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<c:url value="/resources/css/home.css"/>" rel="stylesheet"></link>
<title>Insert title here</title>
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
		<form action="/smartmanager/members.htm" method="post">
			<div class="projectinsert">
				<label id="lblPname">Member Name:</label>
				<input type="text" name="mname"/>
				<label id="lblPname">Project Name</label>
				<select id="projectsdrp" name="mproject">
					<c:forEach var="project" items="${requestScope.projects}">
						<option value="<c:out value="${project.projectName}"/>"><c:out value="${project.projectName}"/></option>
					</c:forEach>
				</select>
				<label id="lblPname">Career Level</label>
				<select if="careerDrp" name="mcareer">
					<option value="manager">Manager</option>
					<option value="developer">Developer</option>
					<option value="qa">QA</option>
					<option value="analyst">Analyst</option>
				</select>
				<input type="submit" value="Add member" id="addproject"/>
			</div>
		</form>
		<table class="content-table">
			<thead>
				<tr>
					<th>Member Name</th>
					<th>Project</th>
					<th>Career level</th>
					<th>Modify Member</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="member" items="${requestScope.members}">
			
				<tr>
					<td><input type="text" class="tablebtn" onblur="assignName(event)" id="projname" value="<c:out value="${member.memberName}"/>" disabled/>   </td>
					<td><input type="text" class="tablebtn" onblur="assignStartDate(event)" id="projstart" value="<c:out value="${member.projectName}"/>" disabled/></td>
					<td><input type="text" class="tablebtn" onblur="assignEndDate(event)" id="projend" value="<c:out value="${member.careerLevel}"/>" disabled/></td>
					<td>
					<button class="minus" id="edit" onclick="toggleDisable()">Edit</button>
					<form action="/smartmanager/memberDeleted.htm" method="post">
						<input type="submit" class="minus" value="-"></button>
						<input type="hidden" name="membername" value="<c:out value="${member.memberName}"/>"/>
					</form>
					<form action="/smartmanager/member.htm" method="post">
						<input type="submit" class="minus"  value="✔"></button>
						<input type="hidden" id="destprojname" name="pname"/>
						<input type="hidden" id="destprojstart" name="pstartdate" />
						<input type="hidden" id="destprojend" name="penddate" /> &nbsp;
					</form></td>
				</tr>
			
			</c:forEach>
			
			</tbody>
		</table>
	</div>

</body>
</html>