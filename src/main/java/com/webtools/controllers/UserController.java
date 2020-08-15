package com.webtools.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.webtools.dao.UserDAO;
import com.webtools.pojo.Message;
import com.webtools.pojo.User;

@Controller
public class UserController {
	
	@RequestMapping(value="/user.htm",method=RequestMethod.GET)
	public String showLogin() {
	   return "user-login";
	}
	
	@RequestMapping(value="/user.htm",method=RequestMethod.POST)
	public String registerUser(HttpServletRequest request, UserDAO userdao) {
		
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		User user=new User();
		user.setUname(username);
		user.setUpassword(password);
		
		//inserting the user to the table
		try {
			userdao.register(user);
			return "Login";
		}catch(Exception ex) {
			System.out.println("Exception occured: "+ex);
			request.setAttribute("errormsg", "User already exists!");
			return "error-page";
		}
		
	}
	
	@RequestMapping(value="/home.htm",method=RequestMethod.POST)
	public String verifyUser(HttpServletRequest request, UserDAO userdao) {
		
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		
		if(userdao.getUser(username, password)) return "home-page";
		else {
			
			request.setAttribute("errormsg", "Please enter the valid Username/password");
			return "error-page";
		}
		
	}
	
}
