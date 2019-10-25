package fr.library.webservices.entrypoint;

import java.util.List;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.library.model.Document;
import org.library.model.User;
import org.library.model.WaitingList;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import fr.library.config.AppConfig;
import fr.library.exceptions.UserNotInTheListException;
import fr.library.exceptions.WaitingListFullException;
import fr.library.webservices.services.DocumentService;
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
	public Long addUserToList(Long docId, Long userId) {

			if(docId == null || userId == null) {
				return null;
			}else {
				
				Document doc = new Document();
				doc.setId(docId);
				User user = new User();
				user.setId(userId);
				AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
				WaitingListService service = (WaitingListService) context.getBean("WLService");
				Long res = null;
				try {
					res = service.addUserToList(doc, user);
					logger.info("WL id in webservice : " + res);
				} catch (WaitingListFullException e) {
					logger.error("The list if full",e);
				}
				context.close();
				return res;	
			}

	}

	@Override
	public List<WaitingList> getAllWaiting(Long userId) {
		if(userId == null) {
			return null;
		}
		
		User user = new User();
		user.setId(userId);
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		WaitingListService service = (WaitingListService) context.getBean("WLService");
		DocumentService docService = (DocumentService) context.getBean("DocumentService");
		List<WaitingList> res = service.getAll(user);
		
		// Get the available date for all the document of the WL
		for(WaitingList wl : res) {
			wl.getDoc().setAvailableDate(docService.getAvailableDate(wl.getDoc()));
		}
		context.close();
		return res;
	}

	@Override
	public void cancelAReservation(Long docId, Long userId) throws UserNotInTheListException {
		if(userId == null || docId == null) {
			return;
		}
		
		User user = new User();
		user.setId(userId);
		Document doc = new Document();
		doc.setId(docId);
		// Load spring context
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		WaitingListService service = (WaitingListService) context.getBean("WLService");
		
		service.cancelAReservation(doc, user);
		
		context.close();
		
	}

}
