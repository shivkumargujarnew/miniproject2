package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	 

	User findByEmail(String email);
	
	@Query(value="SELECT * FROM user WHERE email = ?1 AND pwd = ?2",nativeQuery = true)
	User findByEmailAndPwd(String email,String pwd);
	
	
}
