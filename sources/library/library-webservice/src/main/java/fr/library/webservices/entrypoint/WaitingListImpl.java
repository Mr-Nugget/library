package fr.library.webservices.entrypoint;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;

import fr.library.exceptions.WaitingListFullException;
import fr.library.webservices.services.WaitingListService;

/**
 * IWaitingList service implementation
 * @author Titouan
 *
 */
@WebService(endpointInterface = "fr.library.webservices.entrypoint.IWaitingList")
public class WaitingListImpl implements IWaitingList {

	Logger logger = Logger.getLogger(WaitingListImpl.class);
	
	@Override
	public Long addUserToList(Document doc, User user) {
		try {
			if(doc == null || user == null) {
				return null;
			}else {
				return WaitingListService.addUserToList(doc, user);	
			}
		} catch (WaitingListFullException e) {
			logger.error("The list is full");
			return null;
		}
	}

	@Override
	public List<WaitingList> getAllWaiting(User user) {
		if(user == null) {
			return null;
		}
		return WaitingListService.getAll(user);
	}

}
