package fr.library.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
/**
 * Spring java configuration instead of web.xml
 * @author Titouan
 *
 */
public class MainWebAppInitializer implements WebApplicationInitializer {
	@Override
    public void onStartup(final ServletContext sc) throws ServletException {
 
        AnnotationConfigWebApplicationContext root = 
          new AnnotationConfigWebApplicationContext();
         
        root.scan("fr.library");
        sc.addListener(new ContextLoaderListener(root));
        // Using the Spring dispatcher sevlet, frontController
        ServletRegistration.Dynamic appServlet = 
          sc.addServlet("mvc", new DispatcherServlet(new GenericWebApplicationContext()));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }
}
