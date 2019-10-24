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
}
