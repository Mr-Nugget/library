package org.library.model;

import java.util.Date;
/*
 * Describe a Loan, reference a Document and an User. The date of the loan and the date of return.
 */

public class Loan {
	private Long id; //loan id
	private User user; //ref the user who borrow the document
	private Document doc; //ref the doc borrowed by the user
	private Date beginDate; //date of the borrow
	private Date endDate; //limit date to return the book, can be extended once
	private Status status; // status, if the loan is finished, in progress or extended
	
	//Constructor using fields
	public Loan(Long id, User user, Document doc, Date beginDate, Date endDate, Status status) {
		this.id = id;
		this.user = user;
		this.doc = doc;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.status = status;
	}

	//empty constructor
	public Loan() {
		// TODO Auto-generated constructor stub
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", user=" + user + ", doc=" + doc.toString() + ", beginDate=" + beginDate + ", endDate="
				+ endDate + ", status=" + status + "}";
	}
	
	
}
