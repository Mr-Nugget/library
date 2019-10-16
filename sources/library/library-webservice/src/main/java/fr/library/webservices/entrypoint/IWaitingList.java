package fr.library.webservices.entrypoint;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;

/**
 * Webservice interface for waitingList objects
 * @author Titouan
 *
 */
@WebService
public interface IWaitingList {
	// Add a user in the waitinglist of a document and return its ID
	@WebMethod
	public Long addUserToList(Document doc, User user);
	
	// Get all the waitingList of a user
	@WebMethod
	public List<WaitingList> getAllWaiting(User user);
}
