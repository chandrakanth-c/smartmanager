package com.webtools.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;

import com.webtools.exception.MemberException;
import com.webtools.exception.ProjectException;
import com.webtools.exception.UserException;
import com.webtools.pojo.Member;
import com.webtools.pojo.Project;

public class MemberDAO extends DAO{
	
	public List<Member> getMembers() {
		
		Query query = getSession().createQuery("FROM Member");
		
		List<Member> members = query.list();
		
		return members;
		
	}
	
	public Member addMember(Member member) throws MemberException {
		
        try {
        	
            begin();
            
            if(!member.getMemberName().equals("")) {
            	getSession().save(member);
            }
            
            commit();
            close();
            
            return member;
            
        } catch (HibernateException e) {
        	
            rollback();
            throw new MemberException(e.getMessage());
            
        }
        
    }
	
	public void deleteMember(Member member) throws MemberException {
        try {
            begin();
            String hql = "DELETE FROM Member WHERE memberID=:memberID";
            Query query = getSession().createQuery(hql);
            query.setParameter("memberID", member.getMemberID());
            int result = query.executeUpdate();
            commit();
            close();
        } catch(HibernateException e) {
            rollback();
            throw new MemberException(e.getMessage());
        } 
    }

}
