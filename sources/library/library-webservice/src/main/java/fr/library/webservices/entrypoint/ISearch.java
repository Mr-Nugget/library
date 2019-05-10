package fr.library.webservices.entrypoint;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.library.model.Document;

import fr.library.exceptions.JWTCheckingException;

/**
 * Interface of Search Webservice, methods to search document into the table documents
 * @author Titouan
 *
 */
@WebService
public interface ISearch {
	@WebMethod
	public List<Document> search(String jwt, String title, String criteria) throws JWTCheckingException;

}
