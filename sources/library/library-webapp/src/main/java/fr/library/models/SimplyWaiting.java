package fr.library.models;



import javax.xml.datatype.XMLGregorianCalendar;

public class SimplyWaiting {
	
	private String title;
	private String author;
	private String ref;
	private XMLGregorianCalendar returnDate;
	private Integer position;
	
	
	public SimplyWaiting(String title, String author, String ref, XMLGregorianCalendar returnDate, Integer position) {
		this.title = title;
		this.author = author;
		this.ref = ref;
		this.returnDate = returnDate;
		this.position = position;
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
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public XMLGregorianCalendar getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(XMLGregorianCalendar returnDate) {
		this.returnDate = returnDate;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
}
