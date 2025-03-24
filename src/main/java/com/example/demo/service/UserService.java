package com.example.demo.service;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.SignUpForm;
import com.example.demo.binding.UnlockForm;

public interface UserService {

	String logIn(LoginForm loginForm);
	    boolean signUp(SignUpForm signUpForm);
	    boolean unlockAccount(UnlockForm unlockForm);
	    String forgotPass(String email);
	    
}
