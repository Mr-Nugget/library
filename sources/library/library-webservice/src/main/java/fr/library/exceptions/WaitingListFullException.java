package fr.library.exceptions;

public class WaitingListFullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WaitingListFullException() {
		// TODO Auto-generated constructor stub
		super();
	}
	public WaitingListFullException(String message) {
		super(message);
	}

}
