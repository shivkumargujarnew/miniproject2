package com.example.demo.utis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
    @Autowired
	private JavaMailSender mailsender;
    
    
    public boolean sendEmail(String to,String subject,String body) {
    	boolean isSend=false;
    	
    	try {
    		MimeMessage mimeMessage=mailsender.createMimeMessage();
    		MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
    		helper.setTo(to);
    		helper.setSubject(subject);
    		helper.setText(body,true);
    		mailsender.send(mimeMessage);
    		isSend=true;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return isSend;
    }
    
    
    
    
    
}
