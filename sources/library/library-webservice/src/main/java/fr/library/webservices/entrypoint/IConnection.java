package fr.library.webservices.entrypoint;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.library.model.User;

import fr.library.exceptions.JWTCheckingException;
/**
 * Interface of Connection Web service, methods to register, login and logout
 * @author Titouan
 *
 */
@WebService
public interface IConnection {
	// Log a user, set TRUE to the raw connected, check if the user exist and if the password is correct
	@WebMethod
	public String login(String mail, String password);
	// Logout a user, set FALSE to the raw connected use the jwt token 
	@WebMethod
	public void logout(String jwt) throws JWTCheckingException;
	// Get user thanks to jwt.get("idUser")
	@WebMethod
	public User getUser(String jwt) throws JWTCheckingException;
}
