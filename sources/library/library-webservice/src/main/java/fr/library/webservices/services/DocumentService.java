package fr.library.webservices.services;

import java.util.List;

import org.library.model.Document;

import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;

public class DocumentService {
	private static IDocumentDao dao = DaoFactory.getInstance().getDocumentDao();
	
	public static List<Document> search(String word, String criteria){
		return dao.searchByCriteria(criteria, word);
	}
}
