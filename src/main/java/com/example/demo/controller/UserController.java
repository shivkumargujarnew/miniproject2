package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.LoginForm;
import com.example.demo.binding.SignUpForm;
import com.example.demo.binding.UnlockForm;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService service;

	@PostMapping("/save")
	public String handleSignUp(@RequestBody SignUpForm form) {
		boolean status = service.signUp(form);
		if (status) {
			return "record added succesfully";
		} else {
			return "no record not added";
		}
	}

	@GetMapping("/get")
	public String unlockUserAccount(@RequestBody UnlockForm form) {

		if (form.getPass().equals(form.getConfirmpass())) {
			boolean b = service.unlockAccount(form);

			if (b) {
				return "your account unlocked";
			} else {
				return "wrong given temp pass ";
			}

		} else {
			return "actual pass and cofirm pass not same";
		}

	}
      @PostMapping("/log")
	public String logIn(@RequestBody LoginForm lform) {
		String b = this.service.logIn(lform);

		if (b.equals("succes")) {
			// redirect to dashboard

			return "redirect to dashboard";

		}
		return b;

	}

	@PostMapping("/fgp")
	public String forgotPass(@RequestParam("email") String email) {

		String msg = this.service.forgotPass(email);
		return msg;

		// TODOP:LOgic
	}

}
