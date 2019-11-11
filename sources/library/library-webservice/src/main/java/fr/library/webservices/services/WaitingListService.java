package fr.library.webservices.services;

import java.util.ArrayList;
import java.util.List;

import org.library.model.Document;
import org.library.model.Loan;
import org.library.model.Status;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import fr.library.exceptions.DocumentNotAvailableException;
import fr.library.exceptions.LoanStatusException;
import fr.library.exceptions.UserNotInTheListException;
import fr.library.exceptions.WaitingListFullException;
import fr.library.sql.DaoFactory;
import fr.library.sql.ILoanDao;
import fr.library.sql.IUserDao;
import fr.library.sql.IWaitingListDao;


@Service("WLService")
public class WaitingListService {

	private IUserDao userDao = DaoFactory.getInstance().getUserDao();
	
	@Autowired
	private IWaitingListDao waitingListDao;
	
	@Autowired
	private ILoanDao loanDao;
	
	@Autowired
	SimpleMailMessage simpleMail;
	
	@Autowired
	JavaMailSender javaMailSender;
		
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
	 * @throws DocumentNotAvailableException 
	 */
	public List<Loan> removeUserAfterTowDays(){
		List<Loan> listOfExpiredLoans = loanDao.cloturedAfterTwoDays();
		
		List<Loan> newLoanList = new ArrayList<>();
		User userTemp;
		
		for(Loan loan : listOfExpiredLoans) {
			WaitingList wlTemp = waitingListDao.getByDocument(loan.getDoc());
			if(wlTemp == null) {
				System.out.println("WL null");
			}else if(wlTemp.getUsersPositions().length == 0) {
				waitingListDao.deleteItem(wlTemp);
			}else {
				userTemp = wlTemp.removeTheFirstUser();
				waitingListDao.updateItem(wlTemp);
				Long newLoanId = null;
				try {
					newLoanId = loanDao.createLoan(loan.getDoc(), userTemp, Status.AWAITING);
					newLoanList.add(loanDao.getById(newLoanId));
				} catch (DocumentNotAvailableException e) {
					System.out.println("Document Not Available");
				}
			}
		}
		return newLoanList;
	}
	
	public User returnADocument(Long loanId) throws LoanStatusException, DocumentNotAvailableException {
		if(loanId == null) {
			return null;
		}
		
		Loan loanReturning = loanDao.getById(loanId);
		
		if(loanReturning == null) {
			return null;
		}
		
		WaitingList wl = waitingListDao.getByDocument(loanReturning.getDoc());
		loanDao.returnDocument(loanReturning);
		User userRes = null;
		if(wl != null) {
			if(wl.getLastPosition() > 0) {
				userRes = wl.removeTheFirstUser();
				userRes = userDao.getById(userRes.getId());
				waitingListDao.updateItem(wl);
				loanDao.createLoan(loanReturning.getDoc(), userRes, Status.AWAITING);
				StringBuilder message = new StringBuilder();
				
				message.append("Bonjour ");
				message.append(userRes.getFirstName());
				message.append(" ");
				message.append(userRes.getLastName());
				message.append(",\n\n");
				message.append("Vous vous êtes inscrit sur la liste d'attente pour l'ouvrage suivant : ");
				message.append(loanReturning.getDoc().getTitle());
				message.append(". L'exemplaire vous attend a la bibliothèque, merci de venir le récupérer sous 48h. Le délais expiré, la réservation ne sera plus effective.\n\nCordialement,\n\nL'équipe de la bibliothèque.");
				
				simpleMail.setText(message.toString());
				simpleMail.setTo(userRes.getMail());
				javaMailSender.send(simpleMail);
				
			}
		}

		return userRes;
	}
	
}
