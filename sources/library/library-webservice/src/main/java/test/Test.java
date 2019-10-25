package test;


import fr.library.webservices.services.LoanService;

import java.util.Date;


import org.library.model.Document;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;


import fr.library.config.AppConfig;



import fr.library.webservices.services.DocumentService;


public class Test {

	public static void main(String[] args) {

		System.out.println(LoanService.mailRecall());

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        DocumentService service = (DocumentService) context.getBean("DocumentService");
        
      
        Document doc = new Document();
        doc.setId(new Long(502));
        Date ls = service.getAvailableDate(doc);
        System.out.println(ls);
        context.close();

	}
}
