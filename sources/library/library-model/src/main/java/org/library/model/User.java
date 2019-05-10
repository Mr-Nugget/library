package org.library.model;


/*
 * Describe a user registered into the library
 * Getters and setters available
 */

public class User {
	
	private Long id = null; //user id
	private String firstName = null; //user first name
	private String lastName = null; //user last name
	private String mail = null; // mail, also the login
	private String password = null;	//user password
	private boolean isConnected = false;

	//Empty constructor
	public User() {}
	
	//constructor using fields
	public User(Long id, String firstName, String lastName, String mail, String password, boolean isConnected) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.isConnected = isConnected;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	@Override
	public String toString() {
		return "{id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail
				+ ", password=" + password + " isConnected="+isConnected+"}";
	}
	
}
