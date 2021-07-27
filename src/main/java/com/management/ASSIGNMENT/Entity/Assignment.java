package com.management.ASSIGNMENT.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter; 

import java.util.Date;

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
	
	@Column(name = "SDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String sdate;
	

	@Column(name = "Date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	//File to be added
	//private File file;
	public Assignment(){}

	public Assignment( String title, String course, String instructions, Integer marks, String sdate) {
		super();
		this.title = title;
		this.course = course;
		this.instructions = instructions;
		this.marks = marks;
		this.sdate = sdate;
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


	public String getSdate() {
		return sdate;
	}
	
	public void setSdate(String sdate) throws ParseException {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		System.out.println(date);
		Date dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
		this.sdate = sdate;
		this.date = dateTime;
	}

	public Date getDate() {
		return date;
	}
	
	// public void setDate(String date) throws ParseException {
	// 	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// 	System.out.println(date);
	// 	Date dateTime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
	// 	this.sdate = date;
	// 	this.date = dateTime;
	// }
	

	
	

}
