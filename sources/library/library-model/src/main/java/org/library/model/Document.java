package org.library.model;

import java.util.Date;

/*
 * Describe a document in the library. 
 * Getters and setters available for all properties
 */

public class Document {
	//document id
	private Long id; 
	//a reference to the document (example : RF456)
	private String ref; 
	//document name
	private String title;
	//document author or director
	private String author; 
	//category of the document ref the Java object Category
	private Category category; 
	// Type of the document ref Type -> Java object 
	private Type type;
	// Total Stock
	private int totalStock;
	// Current Stock
	private int currentStock;
	// Available date
	private Date availableDate;

	
	//Constructor with all fields
	public Document(Long id, String ref, String title, String author, Category category, Type type, int totalStock, int currentStock) {
		this.id = id;
		this.ref = ref;
		this.title = title;
		this.author = author;
		this.category = category;
		this.type = type;
		this.totalStock = totalStock;
		this.currentStock = currentStock;
		this.availableDate = null;
	}
	
	public Date getAvailableDate() {
		return availableDate;
	}

	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	//empty constructor
	public Document() {
		
	}

	public int getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(int nb_stock) {
		this.totalStock = nb_stock;
	}

	public int getCurrentStock() {
		return currentStock;
	}

	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "{id=" + id + ", ref=" + ref + ", title=" + title + ", author=" + author + ", category="
				+ category + ", type=" + type +" nb_stock="+totalStock+"}";
	}
	
}
