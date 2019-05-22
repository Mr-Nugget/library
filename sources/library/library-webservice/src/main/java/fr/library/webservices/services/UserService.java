package fr.library.webservices.services;


import org.library.model.User;

import fr.library.sql.DaoFactory;
import fr.library.sql.IUserDao;

public class UserService {
	private static IUserDao dao = DaoFactory.getInstance().getUserDao();
	
	
	public static User connection(String mail, String password) {
		return dao.login(mail, password);
	}
	
	public static void disconnnection(Long id) {
		dao.logout(id);
	}
	
	public static User getUser(Long id) {
		return dao.getById(id);
	}
	
	public static User userExist(String mail) {
		return dao.userExists(mail);
	}
}
