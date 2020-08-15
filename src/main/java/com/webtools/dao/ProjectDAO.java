package com.webtools.dao;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webtools.converters.StringToDateConverter;
import com.webtools.dao.UserDAO;
import com.webtools.exception.ProjectException;
import com.webtools.exception.UserException;
import com.webtools.pojo.Project;
import com.webtools.pojo.User;

@Controller
public class ProjectDAO extends DAO{
	
		public List<Project> getProjects() {
			
			Query query = getSession().createQuery("FROM Project");
			
			List<Project> projects = query.list();
			
			return projects;
			
		}
		
		public Project addProject(Project project) throws ProjectException {
				
		        try {
		        	
		            begin();
		            
		            if(!project.getProjectName().equals("")) {
		            	getSession().save(project);
		            }
		            
		            commit();
		            close();
		            
		            return project;
		            
		        } catch (HibernateException e) {
		        	
		            rollback();
		            throw new ProjectException(e.getMessage());
		            
		        }
		        
		    }
		
		public void deleteProject(Project project) throws ProjectException {
	        try {
	            begin();
	            String hql = "DELETE FROM Project WHERE projectID=:projectID";
	            Query query = getSession().createQuery(hql);
	            query.setParameter("projectID", project.getProjectID());
	            int result = query.executeUpdate();
	            commit();
	            close();
	        } catch(HibernateException e) {
	            rollback();
	            throw new ProjectException(e.getMessage());
	        } 
	    }
		
		public void updateProject(Project project) throws ProjectException {
			
	        try {
	        	
	            begin();
	            
	            String hql = "UPDATE Project SET startdate=:startdate, enddate=:enddate WHERE projectname=:projectname";
	            
	            Query query=getSession().createQuery(hql);
	            
	            query.setParameter("startdate", project.getStartDate());
	            query.setParameter("enddate",project.getEndDate());
	            query.setParameter("projectname", project.getProjectName());
	            
	            if(query.executeUpdate() == 0)
	                throw new ProjectException("Not updated or project not found");
	            commit();
	            close();
	            
	        } catch (HibernateException e) {
	        	
	            rollback();
	            throw new ProjectException(e.getMessage());
	            
	        }
	        
	    }

}
