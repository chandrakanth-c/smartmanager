package com.webtools.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webtools.dao.MemberDAO;
import com.webtools.dao.ProjectDAO;
import com.webtools.dao.TaskDAO;
import com.webtools.pojo.Member;
import com.webtools.pojo.Project;
import com.webtools.pojo.Task;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	//Registering the user
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpServletRequest request,ProjectDAO projectdao,MemberDAO memberdao,TaskDAO taskdao) {
		
		List<Project> projects=projectdao.getProjects();	
		request.setAttribute("projects", projects);
		
		List<Member> members=memberdao.getMembers();	
		request.setAttribute("members", members);
		
		List<Task> tasks=taskdao.getTasks();
		request.setAttribute("tasks", tasks);
		
		return "home-page";
		
	}
	
}
