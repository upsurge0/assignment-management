package com.management.ASSIGNMENT.Entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Course {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

    private String name;
    
    private static List<Long> students = new ArrayList<Long>();
    
    public Course(long id, String name) {
        this.id = id;
        this.name = name;
        // this.students = students;
    }
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
	public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public List<Long> getStudents() {
        return students;
    }
    public void setStudents(List<Long> students) {
        this.students = students;
    }
	
	
}
