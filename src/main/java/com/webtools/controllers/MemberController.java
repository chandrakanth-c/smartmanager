package com.webtools.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webtools.converters.StringToDateConverter;
import com.webtools.dao.MemberDAO;
import com.webtools.dao.ProjectDAO;
import com.webtools.pojo.Member;
import com.webtools.pojo.Project;

@Controller
public class MemberController {
	
	@RequestMapping("/members")
	public String reloadPage(HttpServletRequest request,ProjectDAO projectdao,MemberDAO memberdao) {
		
		List<Project> projects=projectdao.getProjects();
		request.setAttribute("projects", projects);
		
		List<Member> members=memberdao.getMembers();
		request.setAttribute("members", members);
		
		return "members-page";
	}
	
	@RequestMapping(value="/members.htm",method=RequestMethod.POST)
	public String registerMember(HttpServletRequest request, MemberDAO memberdao,ProjectDAO projectdao) {
			
			String memberName = request.getParameter("mname");
			String memberProject = request.getParameter("mproject");
			String memberCareer = request.getParameter("mcareer");
			
			List<Project> projects=projectdao.getProjects();
			request.setAttribute("projects", projects);
			
			Member member=new Member();
			member.setMemberName(memberName);
			member.setProjectName(memberProject);
			member.setCareerLevel(memberCareer);
			
			for (Project project : projects) {
				
				if(project.getProjectName().equals(memberProject)) {
					
					member.setProject(project);
					
				}
				
			}

			
			if(!member.getMemberName().equals("")) {
				try {
					memberdao.addMember(member);
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
			List<Member> members=memberdao.getMembers();	
			request.setAttribute("members", members);
			
			
			
			return "members-page";
			
		}
	
	@RequestMapping(value="/memberDeleted.htm",method=RequestMethod.POST)
	public String deleteMember(HttpServletRequest request, MemberDAO memberdao,ProjectDAO projectdao) {
		
		String memberName = request.getParameter("membername");
		MemberController mc=new MemberController();
		
		for (Member m : memberdao.getMembers()) {
			
			if(m.getMemberName().equals(memberName)) {
				try {
					memberdao.deleteMember(m);;
				}catch(Exception ex) {
					System.out.println("Exception occured: "+ex);
				}
			}
			
		}
		
		List<Project> projects=projectdao.getProjects();
		request.setAttribute("projects", projects);
		
		List<Member> members=memberdao.getMembers();	
		request.setAttribute("members", members);
		
		return "members-page";
		
	}

}
