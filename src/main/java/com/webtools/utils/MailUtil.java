package com.webtools.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.mail.Message;

public class MailUtil {
	
public static String sendEmail(String to,String msg, String subject){
        
        String toEmail = to;
        String fromEmail="smartmanager222@gmail.com";
        
        Properties properties=new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        Session session=Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            
            protected PasswordAuthentication getPasswordAuthentication(){
                
                return new PasswordAuthentication("smartmanager222@gmail.com", "Smart@2019!@");  
            }
        });
        
        try{
            
            MimeMessage message=new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setText(msg);
            Transport.send(message);
            return "successfull";
            
        }catch(Exception ex){
            //JOptionPane.showMessageDialog(null, "Invalid email!");
            return "unsuccessful";
            
        }
        
    }

}
