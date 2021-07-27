package com.management.ASSIGNMENT.Service;

import java.util.List;

import com.management.ASSIGNMENT.Entity.Assignment;

public interface AssignmentService {
	List<Assignment> getAllAssignments();
	
	Assignment saveAssignment(Assignment assignment);
	
	Assignment getAssignmentById(long id);
//	Assignment getDate(long id);
	Assignment updateAssignment(Assignment assignment);
	
	void deleteAssignmentById(Long id);

}
