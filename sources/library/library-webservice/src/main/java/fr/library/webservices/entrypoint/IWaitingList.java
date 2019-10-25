package fr.library.webservices.entrypoint;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.library.model.WaitingList;

import fr.library.exceptions.UserNotInTheListException;

/**
 * Webservice interface for waitingList objects
 * @author Titouan
 *
 */
@WebService
public interface IWaitingList {
	// Add a user in the waitinglist of a document and return its ID
	@WebMethod
	public Long addUserToList(Long docId, Long userId);
	
	// Get all the waitingList of a user
	@WebMethod
	public List<WaitingList> getAllWaiting(Long userId);
	
	// Cancel a reservation of a user
	@WebMethod
	public void cancelAReservation(Long docId, Long userId) throws UserNotInTheListException;
}
