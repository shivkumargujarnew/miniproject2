package com.example.demo.runners;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Courses;
import com.example.demo.entity.EnquriesStatus;
import com.example.demo.repository.CoursesRepo;
import com.example.demo.repository.EnquriesRepo;
import com.example.demo.repository.EnquriesStatusRepo;
@Component
public class DataRunners implements ApplicationRunner{
        @Autowired
	    private CoursesRepo crepo;
        @Autowired
	    private EnquriesStatusRepo erepo;
	  
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
	
		
		Courses c=new Courses();
		c.setCourseName("java");
		crepo.save(c);
	    
	    
		Courses c1=new Courses();
		c1.setCourseName("python");
		crepo.save(c1);
	    
		
		Courses c2=new Courses();
		c2.setCourseName("ui");
		crepo.save(c2);
		
		
		EnquriesStatus st=new EnquriesStatus();
		st.setStatusName("new");
		erepo.save(st);
		
		EnquriesStatus st1=new EnquriesStatus();
		st1.setStatusName("enrolled");
		erepo.save(st1);
		
		EnquriesStatus st2=new EnquriesStatus();
		st2.setStatusName("lost");
		erepo.save(st2);
		
		
	}

}
