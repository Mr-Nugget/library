package fr.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import fr.library.wsdl.connect.ConnectionImplService;
import fr.library.wsdl.connect.IConnection;
import fr.library.wsdl.manage.IManage;
import fr.library.wsdl.manage.ManageImplService;
import fr.library.wsdl.search.ISearch;
import fr.library.wsdl.search.SearchImplService;

/**
 * Spring java configuration instead of library-servlet.xml
 * @author Titouan
 *
 */
@EnableWebMvc // Enabled Spring MVC annotations
@Configuration
public class SpringWebConfig implements WebMvcConfigurer {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index");
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();

		bean.setViewClass(JstlView.class);
		// Where find the views
		bean.setPrefix("/WEB-INF/view/");
		// Wich file used
		bean.setSuffix(".jsp");

		return bean;
	}

	@Bean
	public IManage manageService() {
		ManageImplService manage = new ManageImplService();
		return manage.getManageImplPort();
		
	}

	@Bean
	public ISearch serachService() {
		SearchImplService search = new SearchImplService();
		return search.getSearchImplPort();
	}

	@Bean
	public IConnection connectionService() {
		ConnectionImplService connect = new ConnectionImplService();
		return connect.getConnectionImplPort();

	}


}