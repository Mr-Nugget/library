package fr.library.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import org.apache.log4j.Logger;

/**
 * Load all the properties of the file sql.properties and set static variable 
 * @author Titouan
 *
 */
public class LoadProperties {
	//Database properties
	public static String URL_PROPERTY;
	public static String LOGIN_PROPERTY;
	public static String PASSWORD_PROPERTY;
	//Mail properties
	public static String HOST_PROPERTY;
	public static Integer PORT_PROPERTY;
	public static String ADRESS_PROPERTY;
	public static String MAIL_PASSWORD_PROPERTY;
	//Project properties
	public static String URL_PROJECT_PROPERTY;
	
	private final static Logger logger =  Logger.getLogger(LoadProperties.class);

	static {
		// If one of the following properties is missing, load all the file
		if(URL_PROPERTY == null || PASSWORD_PROPERTY == null || LOGIN_PROPERTY == null || HOST_PROPERTY == null || PORT_PROPERTY == null ||
				ADRESS_PROPERTY == null || MAIL_PASSWORD_PROPERTY == null || URL_PROJECT_PROPERTY == null) {
			Properties prop = new Properties();
			try (InputStream input = LoadProperties.class.getClassLoader().getResourceAsStream("config.properties")){
				prop.load(input);
				URL_PROPERTY = prop.getProperty("db.url");
				LOGIN_PROPERTY = prop.getProperty("db.username");
				PASSWORD_PROPERTY = prop.getProperty("db.password");
				
				HOST_PROPERTY = prop.getProperty("spring.mail.host");
				ADRESS_PROPERTY = prop.getProperty("spring.mail.username");
				PORT_PROPERTY =  Integer.parseInt(prop.getProperty("spring.mail.port"));
				MAIL_PASSWORD_PROPERTY = prop.getProperty("spring.mail.password");
				
				URL_PROJECT_PROPERTY = prop.getProperty("project.url");
				
			} catch (FileNotFoundException e) {
				logger.error("Aucun fichier trouvé", e);
			} catch (IOException e) {
				logger.error("Erreur d'entrée/sortie", e);
			}
		}
	}
}