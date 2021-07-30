package com.management.ASSIGNMENT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.management.ASSIGNMENT.Repository.AssignmentRepository;

@SpringBootApplication
public class ManagementApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
	
    @Autowired
    private AssignmentRepository assignmentRepository;

	@Override
	public void run(String... args) throws Exception {
		
	}
    

}
