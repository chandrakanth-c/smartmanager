package com.webtools.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.webtools.exception.UserException;
import com.webtools.pojo.User;

public class UserDAO extends DAO{
	
	public User getUser(int uid) {
		
		return (User) getSession().get("com.webtools.pojo.User",1);
		
	}
	
	//Retrive user
	public boolean getUser(String username, String userpassword) {
		
		Query query = getSession().createQuery("FROM User WHERE uname=:uname OR upassword=:password");
		query.setString("uname",username);
		query.setString("password", userpassword);
		
		List<User> user = query.list();
		
		String passwordValidate="";
		
		for (User i : user) {
			passwordValidate = i.getUpassword();
		}
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		
		if(user.size() > 0 && !bcrypt.matches(userpassword, passwordValidate)) return false;
		else return true;
		
	}
	
	//Register user
	public User register(User user) throws UserException {
		
        try {
        	
            begin();
            
            if (!getUser(user.getUname(),user.getUpassword())) {
                throw new HibernateException("User Already Exists");
            }
            
            BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
            String bcryptPassword = bcrypt.encode(user.getUpassword());
            user.setUpassword(bcryptPassword);

            getSession().save(user);
            
            commit();
            close();
            
            return user;
            
        } catch (HibernateException e) {
        	
            rollback();
            throw new UserException(e.getMessage());
            
        }
        
    }
	
	
	
	

}
