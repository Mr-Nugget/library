package fr.library.models;



import javax.xml.datatype.XMLGregorianCalendar;

public class SimplyWaiting {
	
	private Long id;
	private Long docId;
	private String title;
	private String author;
	private String ref;
	private XMLGregorianCalendar returnDate;
	private Integer position;
	
	
	public SimplyWaiting(Long id, Long docId, String title, String author, String ref, XMLGregorianCalendar returnDate, Integer position) {
		this.id = id;
		this.docId = docId;
		this.title = title;
		this.author = author;
		this.ref = ref;
		this.returnDate = returnDate;
		this.position = position;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
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
