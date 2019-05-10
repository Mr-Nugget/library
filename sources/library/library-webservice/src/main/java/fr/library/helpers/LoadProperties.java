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
	public static String URL_PROPERTY;
	public static String LOGIN_PROPERTY;
	public static String PASSWORD_PROPERTY;
	private final static Logger logger =  Logger.getLogger(LoadProperties.class);

	static {
		// If one of the following properties is missing, load all the file
		if(URL_PROPERTY == null || PASSWORD_PROPERTY == null || LOGIN_PROPERTY == null) {
			Properties prop = new Properties();
			try (InputStream input = LoadProperties.class.getClassLoader().getResourceAsStream("config.properties")){
				prop.load(input);
				URL_PROPERTY = prop.getProperty("db.url");
				LOGIN_PROPERTY = prop.getProperty("db.username");
				PASSWORD_PROPERTY = prop.getProperty("db.password");
			} catch (FileNotFoundException e) {
				logger.error("Aucun fichier trouvé", e);
			} catch (IOException e) {
				logger.error("Erreur d'entrée/sortie", e);
			}
		}
	}
}