package test;


import fr.library.webservices.services.LoanService;
import fr.library.webservices.services.WaitingListService;

import java.util.Date;


import org.library.model.Document;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import fr.library.config.AppConfig;
import fr.library.exceptions.DocumentNotAvailableException;
import fr.library.exceptions.LoanStatusException;


public class Test {

	public static void main(String[] args) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        WaitingListService service = (WaitingListService) context.getBean("WLService");
        
        try {
			service.returnADocument(new Long(989898));
		} catch (LoanStatusException | DocumentNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        context.close();

	}
}
