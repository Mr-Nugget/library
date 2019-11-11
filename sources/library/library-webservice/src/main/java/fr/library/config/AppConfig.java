package fr.library.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import fr.library.helpers.LoadProperties;




/**
 * Spring DataSource configuration
 * @author Titouan
 *
 */

@Configuration
@ComponentScan("fr.library")
public class AppConfig {
	
	@Bean
	public DataSource PSQLDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(LoadProperties.URL_PROPERTY);
		dataSource.setUsername(LoadProperties.LOGIN_PROPERTY);
		dataSource.setPassword(LoadProperties.PASSWORD_PROPERTY);
		
		return dataSource;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }
	
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
		mail.setSubject("[Bibliothèque Municipale] Votre réservation vous attend");
		return mail;
	}
	
	
}
