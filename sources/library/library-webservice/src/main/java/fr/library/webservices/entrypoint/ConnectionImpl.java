package fr.library.webservices.entrypoint;

import javax.jws.WebService;

import org.library.model.User;

import fr.library.exceptions.JWTCheckingException;
import fr.library.helpers.JWTHelper;
import fr.library.webservices.services.UserService;


@WebService(endpointInterface = "fr.library.webservices.entrypoint.IConnection")
public class ConnectionImpl implements IConnection {
	
	@Override
	public String login(String mail, String password){
		if(!mail.isEmpty() && !password.isEmpty()) {
			User user = UserService.connection(mail, password);
			String jwt = JWTHelper.createJWT(user.getId(), user.getMail());
			return jwt;
		}else {
			return null;
		}
	}
	/**
	 * Web method, use logout Dao method to disconnect the user
	 */
	@Override
	public void logout(String jwt) throws JWTCheckingException {
		Long id = JWTHelper.checkJwt(jwt);
		if(id != null) {
			UserService.disconnnection(id);
		}
	}
	/**
	 * Get a user thanks to its id
	 */
	@Override
	public User getUser(String jwt) throws JWTCheckingException {
		Long id = JWTHelper.checkJwt(jwt);
		if(id!=null) {
			return UserService.getUser(id);
		}else {
			return null;
		}
	}
	/**
	 * Check if user exist
	 */
	@Override
	public User userExist(String mail) {
		if(mail.isEmpty()) {
			return null;
		}
		return UserService.userExist(mail);
		
	}
	/**
	 * reset a user password, check if the token is OK before
	 */
	@Override
	public void resetPassword(String password, String token) throws JWTCheckingException{
		Long id = JWTHelper.checkJwt(token);
		if(id!=null) {
			UserService.resetPassword(password, id);
		}
	}
	@Override
	public void sendMail(String subject, String message, String mailTo) {
		if(subject!= null && message !=null && mailTo!=null) {
			UserService.sendMail(subject, message, mailTo);
		}
	}
}
