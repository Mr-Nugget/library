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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginDate == null) ? 0 : beginDate.hashCode());
		result = prime * result + ((doc == null) ? 0 : doc.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Loan))
			return false;
		Loan other = (Loan) obj;
		if (beginDate == null) {
			if (other.beginDate != null)
				return false;
		} else if (!beginDate.equals(other.beginDate))
			return false;
		if (doc == null) {
			if (other.doc != null)
				return false;
		} else if (!doc.getId().equals(other.doc.getId()))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.getId().equals(other.user.getId()))
			return false;
		return true;
	}
	
	
}
