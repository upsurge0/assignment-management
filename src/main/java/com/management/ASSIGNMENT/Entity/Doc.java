package com.management.ASSIGNMENT.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "docs")
public class Doc {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;

	private String docName;
	private String docType;
	
	@Lob
	private byte[] data;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doc")
	private Submission submission;
	
	public Doc(){}
	public Doc(String docName, String docType, byte[] data) {
		super();
		this.docName = docName;
		this.docType = docType;
		this.data = data;
	}

	public Integer getId() {
		return Id;
	}


	public void setId(Integer id) {
		Id = id;
	}


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getDocType() {
		return docType;
	}


	public void setDocType(String docType) {
		this.docType = docType;
	}


	public byte[] getData() {
		return data;
	}


	public void setData(byte[] data) {
		this.data = data;
	}	
}
