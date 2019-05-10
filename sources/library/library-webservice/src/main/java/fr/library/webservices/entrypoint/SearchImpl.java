package fr.library.webservices.entrypoint;
import java.util.List;

import javax.jws.WebService;

import org.library.model.Document;

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
		if(id!=null) {
			return DocumentService.search(word, criteria);
		}else {
			return null;
		}
	}
}