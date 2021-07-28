package com.management.ASSIGNMENT.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ASSIGNMENT.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, String>{
	Teacher findByEmail(String email);
}
