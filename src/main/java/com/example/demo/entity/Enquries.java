package com.example.demo.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Enquries {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Integer eId;
	  private String studName;
	  private Integer phon;
	  private String classMode;
	  private String courseName;
	  private String enquriesStatus;
	  @CreationTimestamp
	  private LocalDate createdDate;
	  @UpdateTimestamp
	  private LocalDate updatedDate;
	 
	  @ManyToOne
	  @JoinColumn(name = "user_id")
	  private User user;
	  
	  
}
