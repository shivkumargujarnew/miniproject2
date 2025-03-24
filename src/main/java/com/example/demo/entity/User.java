package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Entity
@Data
public class User {

	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer userId;
	   private String name;
	   private String email;
	   private Long phon;
	   private String pwd;
	   private String accStatus;
	  
	   
	   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	   private List<Enquries> enquiries;
	    
	    
	    
	    
	    
	
}
