package org.library.model;

/*
 * Type of a document
 * Book, CD, Movie, Article, Journal, news paper, archive...
 */

public class Type {
	private Long id; //type id
	private String label; //type name (example : Novel)
	private String description; //little description of the type
	
	public Type(Long id, String label, String description) {
		this.id = id;
		this.label = label;
		this.description = description;
	}
	
	public Type() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return this.label;
	}
	
	
	
}
