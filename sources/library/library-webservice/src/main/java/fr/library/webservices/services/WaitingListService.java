package fr.library.webservices.services;

import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.library.exceptions.WaitingListFullException;
import fr.library.sql.ILoanDao;
import fr.library.sql.IWaitingListDao;

@Service("WaitingListService")
public class WaitingListService {

	@Autowired
	private IWaitingListDao wlDao;
	
	@Autowired
	private ILoanDao loanDao;
	
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
		}else if(wlDao.alreadyInTheList(doc, user)) {
			return null;
		}
		
		
		WaitingList wl = wlDao.getByDocument(doc);
				
		if(wl == null) {
			wlDao.createWaitingList(doc, user);
		}else {
			if(wl.getLastPosition() >= doc.getTotalStock()*2) {
				throw new WaitingListFullException();
			}else {
				wlDao.addUserToList(wl, user);
			}
		}
		
		return wl.getId();
	}
	
	public Long popTheFirstUserOfTheList(WaitingList wl, User user) {
		User userRemove = wl.removeTheFirstUser();
		if(wl.getUsersPositions().isEmpty()) {
			return null;
		}
		else if(userRemove.getId() != user.getId()) {
			return null;
		}else {
			if(wl.getUsersPositions().isEmpty()) {
				wlDao.deleteItem(wl);
			}else {
				wlDao.updateItem(wl);
			}
		}
		return user.getId();
	}
}
