package fr.library.exceptions;

public class UserNotInTheListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UserNotInTheListException() {
		super();
	}
	public UserNotInTheListException(String message) {
		super(message);
	}

}
