package fr.library.sql;

import java.util.List;

import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;

public interface IWaitingListDao extends ICrudDao<WaitingList> {
	
	/**
	 * Create a waiting list and put the user in param on the top of it
	 * @param doc
	 * @param user
	 * @return the id of the new waitingList
	 */
	public Long createWaitingList(Document doc, User user);
	
	/**
	 * Check if the user is already in the waitingList
	 * @param doc
	 * @param user
	 * @return
	 */
	public Boolean alreadyInTheList(Document doc, User user);
	
	/**
	 * Return a waitingList associated to a document
	 * @param doc
	 * @return
	 */
	public WaitingList getByDocument(Document doc);
	
	/**
	 * Add a user in the waitingList
	 * @param wl
	 * @param user
	 */
	public void addUserToList(WaitingList wl, User user);
	
	/**
	 * Get all the waitingList where the user is into
	 * @param user
	 * @return a list of waitingList
	 */
	public List<WaitingList> getUserReservations(User user);

	/**
	 * Remove an user from the waitingList and update positions
	 * @param wl
	 * @param user
	 */
	void removeAnUserFromList(WaitingList wl, User user);
}
