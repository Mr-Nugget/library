package org.library.model;

import java.util.HashMap;
import java.util.Map;

/**
 * describe a WaitingList object for 
 * @author Titouan
 *
 */
public class WaitingList {
	
	public Long id;
	public Document doc;
	public Map<Integer, User> usersPositions;
	public Integer lastPosition;
	
	public WaitingList() {
		usersPositions = new HashMap<>();
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
		this.doc = doc;
	}

	public Map<Integer, User> getUsersPositions() {
		return usersPositions;
	}

	public void setUsersPositions(Map<Integer, User> usersPositions) {
		this.usersPositions = usersPositions;
	}
	
	/**
	 * Add a user into the waiting list and increment lastPosition
	 * @param user
	 */
	public void addUserInWaitingList(User user) {
		lastPosition ++;
		usersPositions.put(lastPosition, user);
	}
	/**
	 * remove the first user and update the list
	 * @return the first user of the waiting list
	 */
	public User removeTheFirstUser() {
		if(usersPositions.isEmpty()) {
			return null;
		}
		// Get and remove the first user
		User user = usersPositions.get(1);
		usersPositions.remove(1);
		// Create a temporary list to stock the new data
		Map<Integer, User> positionsTemp = new HashMap<>();
		// create the same map but with key - 1
		usersPositions.forEach((position, value) -> positionsTemp.put(position -1, value));
		
		setUsersPositions(positionsTemp);
		
		lastPosition --;
		
		return user;
	}
}
