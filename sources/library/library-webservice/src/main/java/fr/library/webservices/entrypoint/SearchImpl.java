package fr.library.webservices.entrypoint;
import java.util.List;

import javax.jws.WebService;

import org.library.model.Document;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import fr.library.config.AppConfig;
import fr.library.exceptions.JWTCheckingException;
import fr.library.helpers.JWTHelper;
import fr.library.webservices.services.DocumentService;

/**
 * Web service : all the methods for users to search a new document and get the stock
 * @author Titouan
 *
 */

@WebService(endpointInterface = "fr.library.webservices.entrypoint.ISearch")
public class SearchImpl implements ISearch{

	/**
	 * Search a document by its Title
	 * @param title
	 * @return
	 */
	@Override
	public List<Document> search(String jwt, String word, String criteria) throws JWTCheckingException{
		Long id = JWTHelper.checkJwt(jwt);
		@SuppressWarnings("resource")
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		DocumentService service = (DocumentService) context.getBean("DocumentService");
		
		if(id!=null) {
			return service.search(word, criteria);
		}else {
			return null;
		}
	}
}