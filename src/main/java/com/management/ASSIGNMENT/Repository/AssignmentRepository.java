package com.management.ASSIGNMENT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Entity.Submission;


public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
	
}
