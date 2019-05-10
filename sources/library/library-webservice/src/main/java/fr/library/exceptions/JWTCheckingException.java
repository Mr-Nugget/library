package fr.library.exceptions;

/**
 * Exception if there is an JWT token error like expiration date or idUser incorrect
 * @author Titouan
 *
 */
public class JWTCheckingException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JWTCheckingException() {
		super();
	}
	public JWTCheckingException(String message) {
		super(message);
	}

}
