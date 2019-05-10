package fr.library.exceptions;

/**
 * Exception if a nb_stock document if at 0
 * @author Titouan
 *
 */
public class DocumentNotAvailableException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DocumentNotAvailableException() {
		super();
	}
	public DocumentNotAvailableException(String message) {
		super(message);
	}
}
