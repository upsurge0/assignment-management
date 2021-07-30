package com.management.ASSIGNMENT.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.management.ASSIGNMENT.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	Student findByEmail(String email);
}
