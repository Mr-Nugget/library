package org.library.model;

/*
 * Describe a document in the library. 
 * Getters and setters available for all properties
 */

public class Document {
	private Long id; //document id
	private String ref; //a reference to the document (example : RF456)
	private String title; //document name
	private String author; //document author or director
	private Category category; //category of the document ref the Java object Category
	private Type type; // Type of the document ref Type -> Java object 
	private int nbStock;
	
	//Constructor with all fields
	public Document(Long id, String ref, String title, String author, Category category, Type type, int nb_stock) {
		this.id = id;
		this.ref = ref;
		this.title = title;
		this.author = author;
		this.category = category;
		this.type = type;
		this.nbStock = nb_stock;
	}
	
	//empty constructor
	public Document() {
		
	}

	public int getNbstock() {
		return nbStock;
	}

	public void setNbstock(int nb_stock) {
		this.nbStock = nb_stock;
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
				+ category + ", type=" + type +" nb_stock="+nbStock+"}";
	}
	
}
