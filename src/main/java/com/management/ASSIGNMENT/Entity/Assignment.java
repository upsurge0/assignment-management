package com.management.ASSIGNMENT.Entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.management.ASSIGNMENT.Entity.Submission;

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
	
	@Column(name = "STime", nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private String stime;

	@Column(name = "Date", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date date;

	@JsonIgnore
	@OneToMany(mappedBy = "assignment")
	private Set<Submission> submissions = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Doc doc;

	//File to be added
	//private File file;
	public Assignment(){}

	public Assignment( String title, String course, String instructions, Integer marks, String sdate, String stime,
	Date date, Doc doc) {
		super();
		this.title = title;
		this.course = course;
		this.instructions = instructions;
		this.marks = marks;
		this.stime = stime;
		this.sdate = sdate;
		this.date = date;
		this.doc = doc;
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

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.stime = stime;
	}
	
	public void setSdate(String sdate) throws ParseException {
		this.sdate=sdate;	
	}

	public Date getDate(){
		return date;
	}

	public Set<Submission> getSubmissions() {
		return submissions;
	}

	public Doc getDoc() {
		return doc;
	}

	public void setDoc(Doc doc) {
		this.doc = doc;
	}
	
	public void convertTime() throws ParseException{
		String combinedDate = this.sdate +" " +  this.stime;
		System.out.println(combinedDate);
		Date dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(combinedDate);
		System.out.println(dateTime);
		this.date = dateTime;
	}

}
