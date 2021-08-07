package com.management.ASSIGNMENT.Service;

import java.util.List;

import com.management.ASSIGNMENT.Entity.Course;

public interface CourseService {
    List<Course> getAllCourses();
    void defaultCourses();
}
