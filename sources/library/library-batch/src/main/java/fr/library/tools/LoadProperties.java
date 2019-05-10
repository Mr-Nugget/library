package fr.library.tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
/**
 * Class to load properties from application.properties
 * @author Titouan
 *
 */
public class LoadProperties {
	// Mail properties
	public static String HOST_PROPERTY;
	public static Integer PORT_PROPERTY;
	public static String ADRESS_PROPERTY;
	public static String MAIL_PASSWORD_PROPERTY;
	
	private final static Logger logger =  Logger.getLogger(LoadProperties.class);

	static {
		// If one of the properties is missing, refresh the properties
		if(HOST_PROPERTY == null || MAIL_PASSWORD_PROPERTY==null || ADRESS_PROPERTY==null || PORT_PROPERTY == null) {
			
			Properties prop = new Properties();
			try (InputStream input = LoadProperties.class.getClassLoader().getResourceAsStream("application.properties")){
				prop.load(input);
				// Mail properties
				HOST_PROPERTY = prop.getProperty("spring.mail.host");
				ADRESS_PROPERTY = prop.getProperty("spring.mail.username");
				PORT_PROPERTY =  Integer.parseInt(prop.getProperty("spring.mail.port"));
				MAIL_PASSWORD_PROPERTY = prop.getProperty("spring.mail.password");
			} catch (FileNotFoundException e) {
				logger.error("Aucun fichier trouvé", e);
			} catch (IOException e) {
				logger.error("Erreur d'entrée/sortie", e);
			}
		}
	}
}
