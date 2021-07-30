package com.management.ASSIGNMENT.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import com.management.ASSIGNMENT.Entity.Doc;

@Entity
@Table(name= "Submission")
public class Submission {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "studentId", nullable = true)
	private Long studentId;

    // @Column(name = "assignment_id", nullable = true)
	// private String assignmentId; //Change to false when added
	
	@Column(name = "Marks", nullable = true)
	private Integer marks;

	@Column(name = "subTime", nullable = true)
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date subTime;  

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    private Assignment assignment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "doc_id")
    private Doc doc;

    public Submission(){}

    public Submission(Long studentId, Integer marks, Date subTime, Doc doc) {
        super();
        this.studentId = studentId;
        // this.assignmentId = assignmentId;
        this.marks = marks;
        this.subTime = subTime;
        this.doc = doc;
    }

    public Long getId(){
        return this.id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    // public String getAssignmentId() {
    //     return assignmentId;
    // }

    // public void setAssignmentId(String assignmentId) {
    //     this.assignmentId = assignmentId;
    // }

    public Integer getMarks() {
        return marks;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Date getSubTime() {
        return subTime;
    }

    public void setSubTime(Date subTime) {
        this.subTime = subTime;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void submit(Assignment existingAssignment) {
        this.assignment = existingAssignment;
    }
    

}
