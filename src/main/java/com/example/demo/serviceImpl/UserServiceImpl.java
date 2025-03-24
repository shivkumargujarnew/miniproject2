package com.example.demo.serviceImpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.SignUpForm;
import com.example.demo.binding.UnlockForm;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UserService;
import com.example.demo.utis.EmailUtils;
import com.example.demo.utis.PwdUtils;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private PwdUtils utils;
	@Autowired
	private UserRepo repo;
	@Autowired
	private EmailUtils emailUtils;
	@Autowired
	private HttpSession session;
	
	@Override
	public boolean signUp(SignUpForm signUpForm) {
		// Todo:copy data binding obj entity obj

		User user = repo.findByEmail(signUpForm.getEmail());
		if (user != null) {
			return false;
		}

		User entity = new User();

		BeanUtils.copyProperties(signUpForm, entity);

		// generate random pwd and set

		String temppwd = utils.generatePassword(12);
		entity.setPwd(temppwd);

		// set account status locked

		entity.setAccStatus("Locked");

		// Insert records
		repo.save(entity);

		// send email to unlock the account
		String to = signUpForm.getEmail();
		String subject = "unlock your account";
		StringBuffer body = new StringBuffer("");
		body.append("<h1>use below temp pwd to unlock account</h1>");
		body.append("temp pwds" + temppwd);
		body.append("<br/>");
		body.append("<a href=\"unlock?email=to\">click here to enable your account</a>");
		emailUtils.sendEmail(to, subject, body.toString());

		return true;
	}

	@Override
	public String logIn(LoginForm login) {
		System.out.println(login.getEmail()+" ");
		System.out.println(login.getPwd());
		  User userentity=this.repo.findByEmailAndPwd(login.getEmail(), login.getPwd());
		if(userentity==null) {
			return "invalid credentials";
		}
		if(userentity.getAccStatus().equals("Locked")) {
			return "your account is locked";
		}
		
		//crete http session stored user data in that one
		session.setAttribute("userId",userentity.getUserId());
		
		return "succes";
	}

	@Override
	public boolean unlockAccount(UnlockForm unlockForm) {
		
		    User user=this.repo.findByEmail(unlockForm.getEmail());
		
		         if(user.getPwd().equals(unlockForm.getTemppass())) {
		        	 user.setPwd(unlockForm.getPass());
		        	 user.setAccStatus("UNLOCKED");
		           repo.save(user);
		           return true;
		         }else {
		        	 return false;
		         }
		    
		    
		   
	}

	@Override
	public String forgotPass(String email) {
		System.out.println(email);
		  //find account using email
		//if avalable send pwd to emails
		User euser=repo.findByEmail(email);
		
		  if(euser==null) {
			  return "invalid user email please chceck";
		  }
		if(euser!=null) {
			String pwd=euser.getPwd();
			String to = email;;
			String subject = "Recover password";
			String body = pwd;
			this.emailUtils.sendEmail(to, subject, body);
		}
		
		return "password send to your email";
	}

}
