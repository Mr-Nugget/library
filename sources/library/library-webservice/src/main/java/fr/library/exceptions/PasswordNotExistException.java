package fr.library.exceptions;

public class PasswordNotExistException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public PasswordNotExistException() {
		super();
	}
	
	public PasswordNotExistException(String message) {
		super(message);
	}
	
}
