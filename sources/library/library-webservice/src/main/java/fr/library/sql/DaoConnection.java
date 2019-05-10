package fr.library.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import fr.library.helpers.LoadProperties;

/**
 * Get the connection of library using Postgre driver and properties 
 * @author Titouan
 *
 */
public class DaoConnection {

	private String url;
	private String username;
	private String password;
	private static DaoConnection instance = null;
	private final static Logger logger =  Logger.getLogger(DaoConnection.class);

	// Constructor using fields
	DaoConnection(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoConnection getInstance() {

		if(instance!=null) {
			return instance;
		}else {
			try {
				Class.forName("org.postgresql.Driver");
			}catch (ClassNotFoundException e) {
				logger.error("Le driver postgre n'a pas été trouvé", e);
			}
			instance = new DaoConnection(LoadProperties.URL_PROPERTY, LoadProperties.LOGIN_PROPERTY, LoadProperties.PASSWORD_PROPERTY);
			return instance;
		}
	}

	// Get a unique connection to access the database
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
