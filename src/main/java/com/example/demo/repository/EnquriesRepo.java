package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.example.demo.entity.Enquries;

public interface EnquriesRepo extends JpaRepository<Enquries, Integer>{
	
	
	@Query(value="SELECT DISTINCT e.course_name FROM Enquries e",nativeQuery=true)
	public List<String> findAllDistinctCourseNames();


	  
	@Query(value="SELECT DISTINCT e.enquries_status FROM Enquries e",nativeQuery=true)
    public List<String> findAllDistinctEnquriesStatus();
}
