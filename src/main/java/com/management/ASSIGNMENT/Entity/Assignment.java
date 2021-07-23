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
	
	@Column(name = "Sub", nullable = false)
	private String Sub;
	
	@Column(name = "Que", nullable = false)
	private String Que;
	
	@Column(name = "Mar", nullable = false)
	private String Mar;
	
	@Column(name = "Dat", nullable = false)
	private String Dat;
	
	public Assignment()
	{
		
	}
	
	public Assignment(String sub, String que, String mar, String dat) {
		super();
		Sub = sub;
		Que = que;
		Mar = mar;
		Dat = dat;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSub() {
		return Sub;
	}
	public void setSub(String sub) {
		Sub = sub;
	}
	public String getQue() {
		return Que;
	}
	public void setQue(String que) {
		Que = que;
	}
	public String getMar() {
		return Mar;
	}
	public void setMar(String mar) {
		Mar = mar;
	}
	public String getDat() {
		return Dat;
	}
	public void setDat(String dat) {
		Dat = dat;
	}
	
	

}
