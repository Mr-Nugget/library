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
}
