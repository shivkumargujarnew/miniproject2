package com.example.demo.service;

import java.util.List;

import com.example.demo.binding.DashBoardResponce;
import com.example.demo.binding.EnquiryForm;
import com.example.demo.entity.Enquries;

public interface EnquriesService {

	
	  List<String> getCourseNames();
	  
	  List<String> getEnquiryStatus();
	  
	  DashBoardResponce getDashBoardData(Integer id);
	  
	  public String upsertEnquiry(EnquiryForm enqform);
	  
	  //List<EnquiryForm>  getEnquiries(Integer userId,EnquiriesSerchCriteria criteria);
	  
	  EnquiryForm getEnquiry(Integer id);
	  
	  
}
