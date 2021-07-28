package com.management.ASSIGNMENT.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ASSIGNMENT.Entity.Course;

public interface CourseRepository extends JpaRepository<Course, String>{
	Course findById(long id);
}