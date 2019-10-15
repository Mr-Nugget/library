package fr.library.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import fr.library.helpers.LoadProperties;




/**
 * Spring DataSource configuration
 * @author Titouan
 *
 */

@Configuration
@ComponentScan("fr.library")
public class AppConfig {
	
	@Bean(name="dataSource")
	public DataSource PSQLDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(LoadProperties.URL_PROPERTY);
		dataSource.setUsername(LoadProperties.LOGIN_PROPERTY);
		dataSource.setPassword(LoadProperties.PASSWORD_PROPERTY);
		
		return dataSource;
	}
	
}
