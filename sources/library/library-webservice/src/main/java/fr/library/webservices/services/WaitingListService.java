package fr.library.webservices.services;

import java.util.ArrayList;
import java.util.List;

import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.library.exceptions.UserNotInTheListException;
import fr.library.exceptions.WaitingListFullException;
import fr.library.sql.ILoanDao;
import fr.library.sql.IWaitingListDao;


@Service("WLService")
public class WaitingListService {

	@Autowired
	private IWaitingListDao waitingListDao;
	
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
		}else if(waitingListDao.alreadyInTheList(doc, user)) {
			return null;
		}
		
		WaitingList wl = waitingListDao.getByDocument(doc);
				
		if(wl == null) {
			return waitingListDao.createWaitingList(doc, user);
		}else {
			if(wl.getLastPosition() >= wl.getDoc().getTotalstock()*2) {
				throw new WaitingListFullException();
			}else {
				waitingListDao.addUserToList(wl, user);
				return wl.getId();
			}
		}
	}
	/**
	 * Eject the user at position 1 in the waitingList
	 * @param wl
	 * @param user
	 * @return
	 */
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
	/**
	 * Get all the reservation for a user
	 * @param user
	 * @return
	 */
	public List<WaitingList> getAll(User user){
		
		List<WaitingList> wlL = waitingListDao.getUserReservations(user);
		
		return wlL;
	}
	
	/**
	 * Cancel a reservation, can throw Exception if the user is not in the list
	 * @param document
	 * @param user
	 * @throws UserNotInTheListException
	 */
	public void cancelAReservation(Document document, User user) throws UserNotInTheListException {
		WaitingList wl = waitingListDao.getByDocument(document);
		
		Integer userPosition = wl.userPosition(user);
		
		if(userPosition == null) {
			throw new UserNotInTheListException();
		}else {
			wl.ejectUserByPosition(userPosition);
			// Delete the WL if the there is no reservation anymore
			if(wl.getLastPosition() == 0) {
				waitingListDao.deleteItem(wl);
			// Update the positions otherwise and remove
			}else {
				waitingListDao.removeAnUserFromList(wl, user);
			}
		}
	}
	
	/**
	 * Set clotured to user's loans who haven't get the document after 48h of reservation.
	 * Create a new loans with status "awaiting" and update the waiting list.
	 * @return list of users with new loan
	 */
	public List<User> removeUserAfterTowDays(){
		List<Loan> listOfExpiredLoans = loanDao.cloturedAfterTwoDays();
		List<WaitingList> wlList = new ArrayList<>();
		
		for(Loan loan : listOfExpiredLoans) {
			WaitingList wlTemp = waitingListDao.getByDocument(loan.getDoc());
			if(wlTemp == null) {
				
			}else if(wlTemp.getUsersPositions().length == 0) {
				waitingListDao.deleteItem(wlTemp);
			}else {
				wlList.add(wlTemp);
			}
		}
		
		
		
		return null;
	}
}
