package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.binding.DashBoardResponce;
import com.example.demo.binding.EnquiryForm;
import com.example.demo.service.EnquriesService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/enquries")
public class EnquriesController {
        @Autowired
	   private HttpSession session;
        @Autowired
        private EnquriesService service;
        
	@GetMapping("/dashboard")
	  public String dashboardPage() {
		  return "sdashboard";
	  }
	
	@GetMapping("/logout")
	public String logOut() {
		session.invalidate();
		return "log out succesfully";
	}
	@GetMapping("/getcounts/{id}")
	public DashBoardResponce getCountsOfEnquiries(@PathVariable("id") Integer id) {	
		
		Integer userId=(Integer) session.getAttribute("userId");
		
		DashBoardResponce responce=service.getDashBoardData(userId);
		return responce;
	}
	
	@GetMapping("/getallcoursename")
	public List<String> getCourseNames(){
		List<String> list=service.getCourseNames();
		return list;
	 }
	  @GetMapping("/getenquirysttaus")
	 public  List<String> getEnquiryStatus(){
		    List<String> list=service.getEnquiryStatus();
		    return list;
	  }
	  
	@PostMapping("/saveenqurires")
	  public String upsertEnquiry(@RequestBody EnquiryForm enqform) {
		  
		  String responce=service.upsertEnquiry(enqform);
		  
		  return responce;
	  }
			
	
	
	
	
	
	
	
	
	
	
	
	
	
}
