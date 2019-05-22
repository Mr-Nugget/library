package fr.library.sql;

import org.library.model.User;

/**
 * Methods to get access to the table users of library
 * @author Titouan
 *
 */
public interface IUserDao extends IScrudDao<User>{
	/**
	 * create a new user into the table users
	 * @param user
	 * @return
	 */
	Long createUser(User user); 
	
	/**
	 * check if the user is already in the table user and return the user associated
	 * @param login
	 * @return
	 */
	User userExists(String login);
	
	/**
	 * check is the user is connected
	 * @param id
	 * @return
	 */
	boolean isConnected(Long id);
	
	/**
	 *  Set isConnected value to true and return the user
	 * @param mail
	 * @param password
	 * @return
	 */
	User login(String mail, String password);
	
	/**
	 * set isConnected value to false
	 * @param id
	 */
	void logout(Long id);
}
