package com.management.ASSIGNMENT.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.management.ASSIGNMENT.Entity.Course;
import com.management.ASSIGNMENT.Repository.CourseRepository;
import com.management.ASSIGNMENT.Service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {
	
	private CourseRepository courseRepository;

	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public List<Course> getAllCourses() {
		defaultCourses();
		return courseRepository.findAll();
	}

	@Override
	public void defaultCourses(){
		if(courseRepository.findAll().isEmpty()){
			courseRepository.save(new Course("maths"));
			courseRepository.save(new Course("science"));
		}
	}
	
}
