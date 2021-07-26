package com.management.ASSIGNMENT.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "assignments")
public class Assignment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "Title", nullable = false)
	private String title;

	@Column(name = "Course", nullable = true)
	private String course; //Change to false when added
	
	@Column(name = "Instructions", nullable = false)
	private String instructions;
	
	@Column(name = "Marks", nullable = true)
	private Integer marks;
	
	@Column(name = "Date", nullable = false)
	private String date;
	
	//File to be added
	//private File file;
	public Assignment(){}

	public Assignment( String title, String course, String instructions, Integer marks, String date) {
		super();
		this.title = title;
		this.course = course;
		this.instructions = instructions;
		this.marks = marks;
		this.date = date;
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getInstructions() {
		return instructions;
	}


	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}


	public Integer getMarks() {
		return marks;
	}


	public void setMarks(Integer marks) {
		this.marks = marks;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	

	
	

}
