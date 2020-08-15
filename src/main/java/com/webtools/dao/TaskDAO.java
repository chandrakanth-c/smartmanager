package com.webtools.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.webtools.exception.ProjectException;
import com.webtools.exception.TaskException;
import com.webtools.exception.UserException;
import com.webtools.pojo.Project;
import com.webtools.pojo.Task;

public class TaskDAO extends DAO{
	
	public List<Task> getTasks() {
		
		Query query = getSession().createQuery("FROM Task");
		
		List<Task> tasks = query.list();
		
		return tasks;
		
	}
	
	public Task addTask(Task task) throws TaskException {
		
        try {
        	
            begin();
            
            if(!task.getTaskName().equals("")) {
            	getSession().saveOrUpdate(task);
            }
            
            commit();
            close();
            
            return task;
            
        } catch (HibernateException e) {
        	
            rollback();
            throw new TaskException(e.getMessage());
            
        }
        
    }

	public void deleteTask(Task task) throws TaskException {
	    try {
	        begin();
	        String hql = "DELETE FROM Task WHERE taskID=:taskID";
	        Query query = getSession().createQuery(hql);
	        query.setParameter("taskID", task.getTaskID());
	        int result = query.executeUpdate();
	        commit();
	        close();
	    } catch(HibernateException e) {
	        rollback();
	        throw new TaskException(e.getMessage());
	    } 
	}
	
	public void updateTask(Task task) throws TaskException {
		
        try {
        	
            begin();
            
            String hql = "UPDATE Task SET startdate=:startdate, projectname=:projectname, assignedto=:assignedto, enddate=:enddate, status=:status WHERE taskname=:taskname";
            Query query = getSession().createQuery(hql);
            query.setParameter("startdate", task.getStartDate());
            query.setParameter("projectname", task.getProjectName());
            query.setParameter("assignedto", task.getAssignedTo());
            query.setParameter("enddate", task.getEndDate());
            query.setParameter("status", task.getStatus());
            query.setParameter("taskname", task.getTaskName());
            
            commit();
            close();
            
        } catch (HibernateException e) {
        	
            rollback();
            throw new TaskException(e.getMessage());
            
        }
        
    }
	
}
