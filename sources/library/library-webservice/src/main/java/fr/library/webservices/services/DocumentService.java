package fr.library.webservices.services;

import java.util.Date;
import java.util.List;

import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;
import fr.library.sql.ILoanDao;
import fr.library.sql.IWaitingListDao;

@Service("DocumentService")
public class DocumentService {
	
	private static IDocumentDao dao = DaoFactory.getInstance().getDocumentDao();
	
	@Autowired
	private ILoanDao loanDao;
	
	@Autowired
	private IWaitingListDao wlDao;
	
	/**
	 * Return a list of document corresponding to the criteria and the keyword.
	 * If a document has no stock, set the date when there will be a return.
	 * @param word
	 * @param criteria
	 * @return
	 */
	public List<Document> search(String word, String criteria){
		List<Document> ld =  dao.searchByCriteria(criteria, word);
		for(Document doc : ld) {
			if(doc.getCurrentstock() == 0) {
				doc.setAvailableDate(getAvailableDate(doc));
				WaitingList wl = wlDao.getByDocument(doc);
				if(wl != null) {
					doc.setResa(wl.getLastPosition());
				}
			}
		}
		
		return ld;
	}
	
	/**
	 * return the date when the document will be available.
	 * Return null if there is no loans associated to this document.
	 * @param document
	 * @return Date
	 */
	public Date getAvailableDate(Document doc) {
		List<Loan> ls = loanDao.getLoansByDocument(doc);
		// Check if the list is empty
		if(ls.isEmpty()) {
			return null;
		}
		
		Date availableDate = ls.get(0).getEndDate();
		for(Loan l : ls) {
			if(l.getEndDate().before(availableDate)) {
				availableDate = l.getEndDate();
			}
		}
		
		return availableDate;
	}
}
