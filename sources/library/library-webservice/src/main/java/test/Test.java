package test;

import java.util.Date;
import java.util.List;

import org.library.model.Document;
import org.library.model.Loan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import fr.library.config.AppConfig;
import fr.library.sql.ILoanDao;
import fr.library.sql.LoanDaoImpl;
import fr.library.webservices.entrypoint.ConnectionImpl;
import fr.library.webservices.entrypoint.IConnection;
import fr.library.webservices.services.DocumentService;

public class Test {

	public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        
        DocumentService service = (DocumentService) context.getBean("DocumentService");
        
        LoanDaoImpl dao2 = new LoanDaoImpl();
        Document doc = new Document();
        doc.setId(new Long(502));
        Date ls = service.getAvailableDate(doc);
        System.out.println(ls);
	}
}	
