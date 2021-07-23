package com.management.ASSIGNMENT.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.management.ASSIGNMENT.Entity.Assignment;
import com.management.ASSIGNMENT.Repository.StudentRepository;
import com.management.ASSIGNMENT.Service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Assignment> getAllAssignments() {
		return studentRepository.findAll();
	}

	@Override
	public Assignment saveAssignment(Assignment assignment) {
		return studentRepository.save(assignment);
	}

	@Override
	public Assignment getAssignmentById(long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Assignment updateAssignment(Assignment assignment) {
		
		return studentRepository.save(assignment);
	}

	@Override
	public void deleteAssignmentById(Long id) {
		studentRepository.deleteById(id);
		
	}
	
}
