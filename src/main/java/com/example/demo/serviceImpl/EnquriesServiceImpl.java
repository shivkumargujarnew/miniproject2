package com.example.demo.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.binding.DashBoardResponce;
import com.example.demo.binding.EnquiryForm;
import com.example.demo.entity.Enquries;
import com.example.demo.entity.User;
import com.example.demo.repository.EnquriesRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.EnquriesService;

import jakarta.servlet.http.HttpSession;
@Service
public class EnquriesServiceImpl implements  EnquriesService{
      @Autowired
	  private  EnquriesRepo repo;
      @Autowired
      private UserRepo userRepo;
      @Autowired
      private HttpSession session;
      
	@Override
	public List<String> getCourseNames() {
	    
		List<String> list=repo.findAllDistinctCourseNames();
		
		return list;
	}

	@Override
	public List<String> getEnquiryStatus() {
		List<String> list=repo.findAllDistinctEnquriesStatus();
		return list;
	}

	@Override
	public DashBoardResponce getDashBoardData(Integer id) {
		Optional<User> userOptional=userRepo.findById(id);
		
		   if(userOptional.isPresent()) {
			   List<Enquries> list=userOptional.get().getEnquiries();
			   
			     Map<String,Long> counts=list.stream().collect(Collectors.groupingBy(Enquries::getEnquriesStatus,Collectors.counting()));
			   
			     DashBoardResponce responce=new DashBoardResponce();
			     responce.setTotalEnquiryCount(counts.size());
			     responce.setEnrolledCount(counts.getOrDefault("enrolled", 0L).intValue());
			     responce.setLostCount(counts.getOrDefault("lost", 0L).intValue());
			     
			     
			     //alternate code
			     
					/*
					 * List<Enquries>
					 * endrolled=list.stream().filter(e->e.getEnquriesStatus().equals("enrolled")).
					 * collect(Collectors.toList());
					 * 
					 * List<Enquries>
					 * lost=list.stream().filter(e->e.getEnquriesStatus().equals("lost")).collect(
					 * Collectors.toList());
					 * 
					 */
			     
			     
			     
			     
			     
			     return responce;
			  
			     
		   }
		
		
		return null;
	}

	@Override
	public String upsertEnquiry(EnquiryForm enqform) {
		
		 Enquries enquiry=new Enquries();
		 Integer userid=(Integer) session.getAttribute("userId");
		 enquiry.setStudName(enqform.getStudeName());
		    enquiry.setPhon(enqform.getStudPhon());
		    enquiry.setClassMode(enqform.getClassMode());
		    enquiry.setCourseName(enqform.getCourseName());
		    enquiry.setEnquriesStatus(enqform.getEnquiryStatus());
		    enquiry.setCreatedDate(LocalDate.now());
		    enquiry.setUpdatedDate(LocalDate.now());
		   
		    Optional<User> user=userRepo.findById(userid);
		    enquiry.setUser(user.get());
		    Enquries savedEnquiry=repo.save(enquiry);
		
		    return "Enquiry saved succesfuully";
	}

	@Override
	public EnquiryForm getEnquiry(Integer id) {
		
		return null;
	}

}
