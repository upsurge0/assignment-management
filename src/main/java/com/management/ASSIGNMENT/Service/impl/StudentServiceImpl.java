package com.management.ASSIGNMENT.Service.impl;

import java.util.List;

import com.management.ASSIGNMENT.Entity.Student;
import com.management.ASSIGNMENT.Repository.StudentRepository;
import com.management.ASSIGNMENT.Service.StudentService;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;

	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

    @Override
    public Student findById(long id){
        return studentRepository.findById(id);
    }
}
