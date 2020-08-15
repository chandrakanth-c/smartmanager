package com.webtools.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.webtools.converters.StringToDateConverter;
import com.webtools.dao.ProjectDAO;
import com.webtools.pojo.Project;

@Controller
public class ProjectController {

	@RequestMapping(value="/project.htm",method=RequestMethod.POST)
	public String registerProject(HttpServletRequest request, ProjectDAO projectdao, HttpServletResponse response) {
		
			StringToDateConverter std=new StringToDateConverter();
			
			String projectName = request.getParameter("pname");
			String projectStartDate = request.getParameter("pstartdate");
			String projectEndDate = request.getParameter("penddate");
				
			Project project=new Project();
			project.setProjectName(projectName);
			project.setStartDate(projectStartDate);
			project.setEndDate(projectEndDate);
			
		
			if(!project.getProjectName().equals("")) {
				try {
					projectdao.addProject(project);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
			List<Project> projects=projectdao.getProjects();	
			request.setAttribute("projects", projects);
			
			return "projects-page";
			
		}
	
	@RequestMapping("/projects")
	public String reloadPage(HttpServletRequest request,ProjectDAO projectdao) {
		
		List<Project> projects=projectdao.getProjects();
		request.setAttribute("projects", projects);
		
		return "projects-page";
	}
	
	@RequestMapping(value="/projectDeleted.htm",method=RequestMethod.POST)
	public String deleteUser(HttpServletRequest request, ProjectDAO projectdao) {
		
		String projectName = request.getParameter("projectname");
		ProjectController pc=new ProjectController();
		
		for (Project p : projectdao.getProjects()) {
			
			if(p.getProjectName().equals(projectName)) {
				try {
					projectdao.deleteProject(p);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
		}
		
		List<Project> projects=projectdao.getProjects();
		request.setAttribute("projects", projects);
		
		return "projects-page";
		
	}
	
	@RequestMapping(value="/projectUpdated.htm",method=RequestMethod.POST)
	public String updateProject(HttpServletRequest request, ProjectDAO projectdao, HttpServletResponse response) {
			
			String projectName = request.getParameter("pname");
			String projectStartDate = request.getParameter("pstartdate");
			String projectEndDate = request.getParameter("penddate");
				
			Project project=new Project();
			project.setProjectName(projectName);
			project.setStartDate(projectStartDate);
			project.setEndDate(projectEndDate);
			
			List<Project> projects=projectdao.getProjects();
			
			for (Project p : projects) {
				
				if(p.getProjectName().equals(projectName)) {
					
					project.setProjectID(p.getProjectID());
					
				}
				
			}
			
			if(!project.getProjectName().equals("")) {
				try {
					projectdao.updateProject(project);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
			projects=projectdao.getProjects();
			request.setAttribute("projects", projects);
			
			return "projects-page";
			
		}
}
