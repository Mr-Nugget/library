package fr.library.exceptions;

public class UserAlreadyConnectedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyConnectedException() {
		super();
	}
	public UserAlreadyConnectedException(String message) {
		super(message);
	}
}
