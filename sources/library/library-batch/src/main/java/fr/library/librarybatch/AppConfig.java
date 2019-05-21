package fr.library.librarybatch;

import java.util.Properties;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import fr.library.tools.LoadProperties;
import fr.library.wsdl.manage.IManage;
import fr.library.wsdl.manage.ManageImplService;




/**
 * Spring batch configuration beans, the javaMailSender, the DataSource, the JDBCTemplate
 * @author Titouan
 *
 */

@Configuration
public class AppConfig {
	

	
	@Bean
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(LoadProperties.HOST_PROPERTY);
	    mailSender.setPort(LoadProperties.PORT_PROPERTY);
	    // A noreply mail for the batch 
	    mailSender.setUsername(LoadProperties.ADRESS_PROPERTY);
	    mailSender.setPassword(LoadProperties.MAIL_PASSWORD_PROPERTY);
	     
	    Properties props = mailSender.getJavaMailProperties();
	    // Gmail SMTP configuration
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
	
	@Bean
	public SimpleMailMessage templateSimpleMessage() {
		SimpleMailMessage mail = new SimpleMailMessage();
		// Set the 'from' and the subject
		mail.setFrom(LoadProperties.ADRESS_PROPERTY); 
		mail.setSubject("[Rappel] Bibliothèque Municipale");
		return mail;
	}
	
	@Bean
	public IManage manageLoanservice() {
		ManageImplService manage = new ManageImplService();
		return manage.getManageImplPort();
		
	}
}
