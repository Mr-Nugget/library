package org.library.model;

/*
 * Category of a document
 * Example : drama, biography, history, comedy......
 */

public class Category {
	private Long id; //category id
	private String label; //name of the category
	private String description; //description of the category
	
	//constructor with all fields
	public Category(Long id, String label, String description) {
		this.id = id;
		this.label = label;
		this.description = description;
	}

	//empty constructor
	public Category() {}

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
		return this.label; //print only the category name (example : book)
	}
	
	
		
}
