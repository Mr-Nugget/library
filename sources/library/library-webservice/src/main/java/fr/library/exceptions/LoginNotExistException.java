package fr.library.exceptions;

public class LoginNotExistException extends Exception {
	private static final long serialVersionUID = 1L;

	public LoginNotExistException() {
		super();
	}
	public LoginNotExistException(String message) {
		super(message);
	}
}
