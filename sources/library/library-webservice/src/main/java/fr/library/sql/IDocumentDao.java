package fr.library.sql;

import java.util.List;

import org.library.model.Document;


/**
 * Methods to get access to the table documents of library
 * @author Titouan
 *
 */
public interface IDocumentDao extends ICrudDao<Document> {

	//constant to use the parameter criteria in searchByCriteria
	public final String AUTHOR = "author"; 
	public final String TITLE = "title";
	public final String REFERENCE = "reference";
	
	//return a list of document by a criteria (author, title, ref) and a search
	public List<Document> searchByCriteria(String crit, String search); 
	//insert a new document in to the table documents
	public Long createDocument(Document doc); 
	//check if the document is in the table, doesn't check by id
	public Boolean documentExists(Document document); 
}
