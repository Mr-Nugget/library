package fr.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.library.helpers.UserPosition;
import fr.library.models.SimplyWaiting;
import fr.library.wsdl.connect.IConnection;
import fr.library.wsdl.connect.JWTCheckingException_Exception;
import fr.library.wsdl.waiting.IWaitingList;
import fr.library.wsdl.waiting.UserNotInTheListException_Exception;
import fr.library.wsdl.waiting.WaitingList;
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
		logger.info("WL id value in webapp : " + WLId);
		
		if(WLId == null) {
			model.addObject("message", "Une erreur s'est produite. La liste d'attente est peut-etre pleine, vous y êtes déjà enregistré ou vous possédez déjà un exemplaire du document.");
			
		}else {
			model.addObject("message", "Votre demande réservation a bien été prise en compte. Vous pouvez suivre l'évolution des réservations dans l'onglet \"Mes Réservation\".");
		}
		
		model.setViewName("message");
		
		return model;
	}
	
	@GetMapping("/reservation")
	public ModelAndView myReservations(@CookieValue(value="jwtCookie") String jwtCookie) {
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
				
				List<WaitingList> wlRes =  waitingService.getAllWaiting(userJWT.getId());
				List<SimplyWaiting> swL = new ArrayList<SimplyWaiting>();
				
				
				for(WaitingList wl : wlRes) {
					Integer position = UserPosition.getUserPosition(wl.getUsersPositions(), userJWT) + 1;
					swL.add(new SimplyWaiting(wl.getId(), wl.getDoc().getId(), wl.getDoc().getTitle(), wl.getDoc().getAuthor(), wl.getDoc().getRef(), wl.getDoc().getAvailableDate(), position));
				}
				
				model.addObject("listRes", swL);
				
				model.setViewName("reservation");
							
				return model;
	}
	
	@GetMapping("/cancelReservation")
	public ModelAndView cancelReservation(@CookieValue(value="jwtCookie") String jwtCookie,
										  @RequestParam(value="docId") Long docId) {
		
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
		
		try {
			waitingService.cancelAReservation(docId, userJWT.getId());
		} catch (UserNotInTheListException_Exception e) {
			logger.error("UserNotInTheList", e);
			model.addObject("message", "Une erreur s'est produite lors de l'annulation de votre réservation. Veuillez ré-essayer plus tard.");
			model.setViewName("message");
			return model;
		}
		model.addObject("message", "L'annulation de votre réservation a bien été prise en compte !");
		model.setViewName("message");
		
		return model;
	}
	
	@GetMapping("/returnDocument")
	public String returnDocument(@RequestParam(value="loanId")Long loanId) {
		waitingService.returnDocument(loanId);
		return "returnDocument";
	}
}
