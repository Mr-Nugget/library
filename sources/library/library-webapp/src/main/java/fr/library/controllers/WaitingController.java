package fr.library.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.library.wsdl.connect.IConnection;
import fr.library.wsdl.connect.JWTCheckingException_Exception;
import fr.library.wsdl.waiting.IWaitingList;
import fr.library.wsdl.connect.User;

@Controller
public class WaitingController {
	
	@Autowired
	private IWaitingList waitingService;
	
	@Autowired
	private IConnection userService;
	
	Logger logger = Logger.getLogger(WaitingController.class);
	
	@GetMapping("/addToList")
	public ModelAndView addToList(
			@CookieValue(value="jwtCookie") String jwtCookie,
			@RequestParam(value="docId") Long docId) {
	
		// Return a model and view to redirect to another controller
		ModelAndView model = new ModelAndView();
		User userJWT;
		try {
			// Check if the token is still OK
			userJWT = userService.getUser(jwtCookie);
			
		} catch (JWTCheckingException_Exception e) {
			logger.error("Erreur de connection", e);
			model.setViewName("redirect:/connection");
			return model;
		}
		
		
		Long WLId = waitingService.addUserToList(docId, userJWT.getId());
		
		if(WLId == null) {
			logger.info("Erreur lors de l'inscription en file d'attente");
			model.setViewName("waitingError");
			
		}else {
			model.setViewName("waitingConfirm");
		}
		
		return model;
	}
}
