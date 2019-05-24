package fr.library.webservices.services;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.library.model.User;

import fr.library.helpers.LoadProperties;
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
	
	public static void resetPassword(String password, Long id) {
		User userUpdate = dao.getById(id);
		userUpdate.setPassword(password);
		dao.updateItem(userUpdate);
	}

	public static void sendMail(String subject, String message, String mailTo) {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", true);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", LoadProperties.HOST_PROPERTY);
		prop.put("mail.smtp.port", LoadProperties.PORT_PROPERTY);
		prop.put("mail.smtp.ssl.trust", LoadProperties.HOST_PROPERTY);
		
		Session session = Session.getInstance(prop, new Authenticator() {
		    @Override
		    protected PasswordAuthentication getPasswordAuthentication() {
		        return new PasswordAuthentication(LoadProperties.ADRESS_PROPERTY, LoadProperties.MAIL_PASSWORD_PROPERTY);
		    }
		});
		
		MimeMessage mail = new MimeMessage(session);
		try {
			mail.setFrom(new InternetAddress(LoadProperties.ADRESS_PROPERTY));
			mail.addRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo));
			mail.setSubject(subject);
			mail.setText(message);
			
			Transport.send(mail);
			
			System.out.println("Mail envoyé !");
			
		} catch (MessagingException e) {
			System.out.println(e);
		}
		
	}
}
