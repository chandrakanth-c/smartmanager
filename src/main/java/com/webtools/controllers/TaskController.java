package com.webtools.controllers;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webtools.converters.StringToDateConverter;
import com.webtools.dao.MemberDAO;
import com.webtools.dao.ProjectDAO;
import com.webtools.dao.TaskDAO;
import com.webtools.pojo.Member;
import com.webtools.pojo.Project;
import com.webtools.pojo.Task;
import com.webtools.utils.MailUtil;

import java.time.temporal.ChronoUnit;

@Controller
public class TaskController {
	
	@RequestMapping("/tasks")
	public String reloadPage(HttpServletRequest request,TaskDAO taskdao,ProjectDAO projectdao,MemberDAO memberdao) {
		
			List<Task> tasks=taskdao.getTasks();
			request.setAttribute("tasks", tasks);
			
			List<Project> projects=projectdao.getProjects();
			request.setAttribute("projects", projects);
			
			List<Member> members=memberdao.getMembers();
			request.setAttribute("members", members);
			
			return "tasks-page";
	}
	
	@RequestMapping(value="/tasks.htm",method=RequestMethod.POST)
	public String registerTask(HttpServletRequest request, ProjectDAO projectdao,MemberDAO memberdao,TaskDAO taskdao) {
			
			String taskName=request.getParameter("tname");
			String projectName=request.getParameter("tproject");
			String startDate=request.getParameter("startdate");
			String endDate=request.getParameter("enddate");
			String assignedTo=request.getParameter("tmember");
			String assEmail=request.getParameter("assemail");
			String status=request.getParameter("status");
			String comments=request.getParameter("comments");
			
			LocalDate beforeDate=LocalDate.parse(startDate);
			LocalDate afterDate=LocalDate.parse(endDate);
			
			long daysRemaining=ChronoUnit.DAYS.between(beforeDate, afterDate);
			
			Task task=new Task();
			task.setTaskName(taskName);
			task.setProjectName(projectName);
			task.setStartDate(startDate);
			task.setEndDate(endDate);
			task.setAssignedTo(assignedTo);
			task.setAssignedToEmail(assEmail);
			task.setStatus(status);
			task.setComments(comments);
			task.setDaysRemaining((int)daysRemaining);
			
			String message = "Hi,"
					+ "A task with name "+taskName+" of "+projectName+" project name has been assigned to you. "
							+ "Regards,"
							+ "Smart Manager";
					
			
			MailUtil.sendEmail(assEmail,message, "A new task has been assigned to you");
			
			
			for (Project p : projectdao.getProjects()) {
				
				if(p.getProjectName().equals(projectName)) {
					
					task.setProject(p);
					
				}
				
			}
			
			
			if(!task.getTaskName().equals("")) {
				try {
					taskdao.addTask(task);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
			List<Project> projects=projectdao.getProjects();	
			request.setAttribute("projects", projects);
			
			List<Member> members=memberdao.getMembers();	
			request.setAttribute("members", members);
			
			List<Task> tasks=taskdao.getTasks();
			request.setAttribute("tasks", tasks);
			
			return "tasks-page";
			
		}
	
	@RequestMapping(value="/taskDeleted.htm",method=RequestMethod.POST)
	public String deleteTask(HttpServletRequest request, ProjectDAO projectdao,TaskDAO taskdao,MemberDAO memberdao) {
		
		String taskName = request.getParameter("taskname");
		TaskController tc=new TaskController();
		
		for (Task t : taskdao.getTasks()) {
			
			if(t.getTaskName().equals(taskName)) {
				try {
					taskdao.deleteTask(t);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
		}
		
		List<Project> projects=projectdao.getProjects();
		request.setAttribute("projects", projects);
		
		List<Member> members=memberdao.getMembers();	
		request.setAttribute("members", members);
		
		List<Task> tasks=taskdao.getTasks();
		request.setAttribute("tasks", tasks);
		
		return "tasks-page";
		
	}
	
	@RequestMapping(value="/taskUpdated.htm",method=RequestMethod.POST)
	public String updateTask(HttpServletRequest request, ProjectDAO projectdao,MemberDAO memberdao,TaskDAO taskdao) {
			
			String taskName=request.getParameter("tname");
			String projectName=request.getParameter("tproject");
			String startDate=request.getParameter("startdate");
			String endDate=request.getParameter("enddate");
			String assignedTo=request.getParameter("tmember");
			String assEmail="chittappa.c@husky.neeu.edu";
			String status=request.getParameter("status");
			String comments=request.getParameter("comments");
			
			LocalDate beforeDate=LocalDate.parse(startDate);
			LocalDate afterDate=LocalDate.parse(endDate);
			
			long daysRemaining=ChronoUnit.DAYS.between(beforeDate, afterDate);
			

			Task task=new Task();
			task.setTaskName(taskName);
			task.setProjectName(projectName);
			task.setStartDate(startDate);
			task.setEndDate(endDate);
			task.setAssignedTo(assignedTo);
			task.setAssignedToEmail(assEmail);
			task.setStatus(status);
			task.setComments(comments);
			task.setDaysRemaining((int)daysRemaining);
			
			
			for (Project p : projectdao.getProjects()) {
				
				if(p.getProjectName().equals(projectName)) {
					
					task.setProject(p);
					
				}
				
			}
			
			for (Member m : memberdao.getMembers()) {
				
				if(m.getMemberName().equals(assignedTo)) {
					
					task.setMember(m);
					
				}
				
			}
			
			
			if(!task.getTaskName().equals("")) {
				try {
					taskdao.updateTask(task);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
			List<Project> projects=projectdao.getProjects();	
			request.setAttribute("projects", projects);
			
			List<Member> members=memberdao.getMembers();	
			request.setAttribute("members", members);
			
			List<Task> tasks=taskdao.getTasks();
			request.setAttribute("tasks", tasks);
			
			return "tasks-page";
			
		}
	
	
}
