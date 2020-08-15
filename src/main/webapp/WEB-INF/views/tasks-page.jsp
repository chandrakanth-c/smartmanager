<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="<c:url value="/resources/javascript/task.js"/>" defer></script>
<link href="<c:url value="/resources/css/homestyle.css"/>" rel="stylesheet"></link>
<title>Tasks</title>
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
	 
	 
		<form action="/smartmanager/tasks.htm" method="post" id="usrform" name="usrform">
			<div class="taskinsert">
				<label id="lblPname">Task Name:</label>
				<input type="text" name="tname"/>
				<label id="lblPname">Project Name</label>
				<select id="projectsdrp" name="tproject">
					<c:forEach var="project" items="${requestScope.projects}">
						<option value="<c:out value="${project.projectName}"/>"><c:out value="${project.projectName}"/></option>
					</c:forEach>
				</select>
				<label id="lblPname">Start Date</label>
				<input type="date" name="startdate"/>
				<label id="lblPname">End Date</label>
				<input type="date" name="enddate"/>
				<label id="lblPname">Assigned to</label>
				<select id="projectsdrp" name="tmember">
					<c:forEach var="member" items="${requestScope.members}">
						<option value="<c:out value="${member.memberName}"/>"><c:out value="${member.memberName}"/></option>
					</c:forEach>
				</select>
				<label id="lblPname">Assigned to email</label>
				<input type="email" name="assemail"/>
				<label id="lblPname">Status</label>
				<select id="statusDrp" name="status">
					<option value="Yet to Start">Yet to Start</option>
					<option value="Working on it">Working on it</option>
					<option value="Waiting for the review">Waiting for the review</option>
					<option value="Review completed">Review completed</option>
					<option value="Done">Done</option>
				</select><br>
				<div class="commentssection">
				<label id="lblPname">Comments:</label>
				<textarea rows="3" cols="20" name="comments" form="usrform"></textarea>
				<input type="submit" value="Add task" id="addtask"/></div>
			</div>
		</form>
		<div class="projects-table">
		<table class="task-table">
			<thead>
				<tr>
					<th>Task Name</th>
					<th>Project Name</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Assigned To</th>
					<th>Status</th>
					<th>Days Remaining</th>
					<th>Comments</th>
					<th>Modify task</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="task" items="${requestScope.tasks}">
			
				<tr>
					<td><input type="text" class="tablebtn" onblur="aTaskName(event)" id="taskname" value="<c:out value="${task.taskName}"/>" disabled/>   </td>
					
					<td><select id="projectsdrp" class="tablebtn" name="tproject" onblur="aProjectName()" disabled>
							<option value="<c:out value="${task.projectName}"/>" selected><c:out value="${task.projectName}"/></option> 
							<c:forEach var="project" items="${requestScope.projects}">
								<option value="<c:out value="${project.projectName}"/>"><c:out value="${project.projectName}"/></option>
							</c:forEach></select>
					</td>
					
					<td><input type="text" class="tablebtn" onblur="aTaskStart(event)" id="taskstart" value="<c:out value="${task.startDate}"/>" disabled/></td>
					<td><input type="text" class="tablebtn" onblur="aTaskEnd(event)" id="taskend" value="<c:out value="${task.endDate}"/>" disabled/></td>
					
					<td><select id="memberdrp" class="tablebtn" name="tmember" onblur="aTaskAssigned()" disabled>
							<option value="<c:out value="${task.assignedTo}"/>" selected><c:out value="${task.assignedTo}"/></option> 
							<c:forEach var="member" items="${requestScope.members}">
								<option value="<c:out value="${member.memberName}"/>"><c:out value="${member.memberName}"/></option>
							</c:forEach></select>
					</td>
					
					<td><input type="text" class="tablebtn" onblur="aTaskStatus(event)" id="taskstatus" value="<c:out value="${task.status}"/>" disabled/></td>
					<td><input type="text" class="tablebtn" onblur="aTaskRemaining(event)" id="taskremaining" value="<c:out value="${task.daysRemaining}"/>" disabled/></td>
					<td><teaxtarea class="tablebtn" onblur="assignEndDate(event)" id="taskcomments" value="<c:out value="${task.comments}"/>" disabled/></td>
					<td>
					<button class="minus" id="edit" onclick="toggleDisable()">Edit</button>
					<form action="/smartmanager/taskDeleted.htm" method="post">
						<input type="submit" class="minus" value="-"></button>
						<input type="hidden" name="taskname" value="<c:out value="${task.taskName}"/>"/>
					</form>
					<form action="/smartmanager/taskUpdated.htm" method="post">
						<input type="submit" class="minus"  value="✔"></button>
						<input type="hidden" id="desttaskname" name="tname"/>
						<input type="hidden" id="desttaskproject" name="tproject" />
						<input type="hidden" id="desttaskstart" name="startdate" />
						<input type="hidden" id="desttaskend" name="enddate" />
						<input type="hidden" id="desttaskassigned" name="tmember" />
						<input type="hidden" id="desttaskstatus" name="assemai" /> 
						<input type="hidden" id="desttaskremaining" name="status" /> 
						<input type="hidden" id="desttaskcomments" name="comments" /> 
					</form></td>
				</tr>
			
			</c:forEach>
			
			</tbody>
		</table>
	</div>

</body>
</html>