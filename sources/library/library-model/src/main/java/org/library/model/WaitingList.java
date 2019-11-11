package org.library.model;


/**
 * describe a WaitingList object for 
 * @author Titouan
 *
 */


public class WaitingList {
	
	private Long id;
	
	private Document doc;
	
	private User[] usersPositions;
	
	private Integer lastPosition;
	
	public WaitingList(Document doc) {
		// Initialize the array with a size of twice the total stock of a document
		usersPositions = new User[doc.getTotalstock()*2];
		this.doc = doc;
		lastPosition = 0;
	}
	
	public Integer getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Integer lastPosition) {
		this.lastPosition = lastPosition;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		usersPositions = new User[doc.getTotalstock()];
		this.doc = doc;
	}

	public User[] getUsersPositions() {
		return usersPositions;
	}

	public void setUsersPositions(User[] usersPositions) {
		this.usersPositions = usersPositions;
	}
	
	/**
	 * Add a user into the waiting list and increment lastPosition
	 * @param user
	 */
	public void addUserInWaitingList(User user) {
		usersPositions[lastPosition] = user;
		lastPosition ++;
	}
	
	/**
	 * Add user into the waitingList at a specifif position and increment lastPosition
	 * @param user
	 * @param position
	 */
	public void addUserWithPosition(User user, Integer position) {
		usersPositions[position] = user;
		lastPosition ++;
	}
	
	/**
	 * remove the first user and update the list
	 * @return the first user of the waiting list
	 */
	public User removeTheFirstUser() {
		if(lastPosition == 0) {
			return null;
		}
		// Get the first user
		User user = usersPositions[0];
		for(int i=0; i < lastPosition - 1; i++) {
			usersPositions[i] = usersPositions[i+1];
		}
		
		lastPosition --;
		
		usersPositions[lastPosition] = null;
		
		return user;
	}
	
	/**
	 * Return the postion of one user
	 * @param user
	 * @return user postion
	 */
	
	public Integer userPosition(User user) {
		
		for(int i = 0; i < lastPosition; i++) {
			if(usersPositions[i].getId().equals(user.getId())) {
				return new Integer(i);
			}
		}
		return null;
	}

	public void ejectUserByPosition(Integer userPosition) {
		for(int i = userPosition; i < lastPosition - 1; i++) {
			usersPositions[i] = usersPositions[i+1];
		}
		lastPosition --;
		usersPositions[lastPosition] = null;
	}
}
