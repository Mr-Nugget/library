package fr.library.exceptions;

/**
 * Exception for a loan already extended or clotured
 * @author Titouan
 *
 */
public class LoanStatusException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LoanStatusException() {
		super();
	}
	public LoanStatusException(String message) {
		super(message);
	}
}
