package fr.library.controllers;


import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.library.wsdl.manage.IManage;
import fr.library.wsdl.manage.JWTCheckingException_Exception;
import fr.library.wsdl.manage.Loan;

/***
 * Controllers for Loans actions
 * @author Titouan
 *
 */

@Controller
public class LoanController {
	@Autowired
	private IManage service;
	
	Logger logger = Logger.getLogger(LoanController.class);
	
	/**
	 * 
	 * @param model
	 * @param jwtCookie
	 * @return JSP page
	 */
	@GetMapping("/currentloans")
	public String currentLoans(ModelMap model,
			@CookieValue(value="jwtCookie") String jwtCookie
			) {
		
		List<Loan> listResLoans = new ArrayList<>();
		try {
			// Convert the array into an ArrayList
			listResLoans = service.getCurrentLoans(jwtCookie);
		} catch (JWTCheckingException_Exception e) {
			logger.error("current Loans", e);
			return "login";
		}
		model.addAttribute("listResLoans", listResLoans);

		return "current";
	}
	
	/**
	 * 
	 * @param model
	 * @param jwtCookie
	 * @return JSP page
	 */
	@GetMapping("/archivedloans")
	public String archivedLoans(ModelMap model,
			@CookieValue(value="jwtCookie") String jwtCookie
			) {
		
		List<Loan> listResLoans = new ArrayList<>();
		try {
			listResLoans = service.getArchivedLoans(jwtCookie);
		} catch (JWTCheckingException_Exception e) {
			logger.error("archived Loans", e);
			return "login";
		}
		model.addAttribute("listResLoans", listResLoans);

		return "archived";
	}
	
	/**
	 * 
	 * @param jwtCookie
	 * @param loanId
	 * @return JSP page
	 */
	@GetMapping("/extend")
	public ModelAndView extendLoan(
			@CookieValue(value="jwtCookie") String jwtCookie,
			@RequestParam(value="id") Long loanId	
			) {

		// Return a model and view to redirect to another controller
		ModelAndView model = new ModelAndView();
		try {
			service.extendLoan(jwtCookie, loanId);
		} catch (JWTCheckingException_Exception e) {
			logger.error("extend Loans", e);
			model.setViewName("redirect:/connection");
			return model;
		}
		// Refresh the list
		List<Loan> listResLoans = new ArrayList<>();
		try {
			listResLoans = service.getCurrentLoans(jwtCookie);
		} catch (JWTCheckingException_Exception e) {
			logger.error("extend Loans", e);
			model.setViewName("redirect:/connection");
			return model;
		}
		model.addObject("listResLoans", listResLoans);
		model.setViewName("redirect:/currentloans");
		return model;
	}
	
}
