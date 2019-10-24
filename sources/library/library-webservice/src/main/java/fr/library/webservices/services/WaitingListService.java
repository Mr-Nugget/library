package fr.library.webservices.services;

import java.util.List;

import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.library.exceptions.WaitingListFullException;
import fr.library.sql.DaoFactory;
import fr.library.sql.IDocumentDao;
import fr.library.sql.ILoanDao;
import fr.library.sql.IWaitingListDao;


@Service("WLService")
public class WaitingListService {

	@Autowired
	private IWaitingListDao waitingListDao;
	
	@Autowired
	private ILoanDao loanDao;
	
	private IDocumentDao documentDao = DaoFactory.getInstance().getDocumentDao();
	
	/**
	 * Add a user into the waitingList at the end of it.
	 * If there is no waitingList for the document, create a new one.
	 * 
	 * @param doc
	 * @param user
	 * @return the id of the waitingList
	 */
	public Long addUserToList(Document doc, User user) throws WaitingListFullException{
		if(user == null) {
			return null;
		}else if(user.getId() == null) {
			return null;
		}else if(loanDao.alreadyHaveTheDocument(user, doc)) {
			return null;
		}else if(waitingListDao.alreadyInTheList(doc, user)) {
			return null;
		}
		
		
		WaitingList wl = waitingListDao.getByDocument(doc);
				
		if(wl == null) {
			waitingListDao.createWaitingList(doc, user);
		}else {
			if(wl.getLastPosition() >= doc.getTotalstock()*2) {
				throw new WaitingListFullException();
			}else {
				waitingListDao.addUserToList(wl, user);
			}
		}
		
		return wl.getId();
	}
	
	public Long popTheFirstUserOfTheList(WaitingList wl, User user) {
		User userRemove = wl.removeTheFirstUser();
		if(wl.getLastPosition() == 0) {
			return null;
		}
		else if(userRemove.getId() != user.getId()) {
			return null;
		}else {
			if(wl.getLastPosition() == 0) {
				waitingListDao.deleteItem(wl);
			}else {
				waitingListDao.updateItem(wl);
			}
		}
		return user.getId();
	}
	
	public List<WaitingList> getAll(User user){
		
		List<WaitingList> wlL = waitingListDao.getUserReservations(user);
		
		return wlL;
	}
}
