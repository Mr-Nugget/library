package org.library.model;


/**
 * Describe a user registered into the library
 * Getters and setters available
 * @author Titouan
 **/

public class User {

	private Long id = null; //user id
	private String firstName = null; //user first name
	private String lastName = null; //user last name
	private String mail = null; // mail, also the login
	private String password = null;	//user password
	private boolean isConnected = false;
	private boolean mailRecall = true;



	//Empty constructor
	public User() {}
	
	
	// Constructor with mailRecall set to true
		public User(Long id, String firstName, String lastName, String mail, String password, boolean isConnected) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.mail = mail;
			this.password = password;
			this.isConnected = isConnected;
			this.mailRecall = true;
		}
	
	//constructor using fields
	public User(Long id, String firstName, String lastName, String mail, String password, boolean isConnected, boolean mailRecall) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mail = mail;
		this.password = password;
		this.isConnected = isConnected;
		this.mailRecall = mailRecall;
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

	public boolean isMailRecall() {
		return mailRecall;
	}

	public void setMailRecall(boolean mailRecall) {
		this.mailRecall = mailRecall;
	}
	
	@Override
	public String toString() {
		return "{id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", mail=" + mail
				+ ", password=" + password + " isConnected="+isConnected+"}";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}
	
}
