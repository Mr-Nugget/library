package fr.library.sql;

import org.library.model.User;

/**
 * Methods to get access to the table users of library
 * @author Titouan
 *
 */
public interface IUserDao extends IScrudDao<User>{
	//create a new user into the table users
	Long createUser(User user); 
	//check if the user is already in the table user and return the user associated
	User userExists(String login);
	//check is the user is connected
	boolean isConnected(Long id);
	// Set isConnected value to true and return the user
	User login(String mail, String password);
	//set isConnected value to false
	void logout(Long id);
}
