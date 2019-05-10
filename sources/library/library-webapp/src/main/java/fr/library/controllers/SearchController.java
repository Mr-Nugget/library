package fr.library.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.library.wsdl.search.Document;
import fr.library.wsdl.search.ISearch;
import fr.library.wsdl.search.JWTCheckingException_Exception;


@Controller
public class SearchController {
	
	public static final String AUTHOR = "author";
	public static final String TITLE = "title";
	public static final String REFERENCE = "reference";
	
	@Autowired
	ISearch service;
	
	/**
	 * 
	 * @param model
	 * @param criteria
	 * @param value
	 * @param jwtCookie
	 * @return JSP page
	 */
	@GetMapping("/search")
	public String search(ModelMap model,
			@RequestParam(value="criteria") String criteria,
			@RequestParam(value="value") String value,
			@CookieValue(value="jwtCookie") String jwtCookie
			) {

		List<Document> listeDocument = new ArrayList<>();
		// Shows if the list is empty
		model.addAttribute("error", "Aucun résultat n'a été trouvé...");
		try {
			switch(criteria) {
			case "Auteur":
				listeDocument = service.search(jwtCookie, value, AUTHOR);
				break;
			case "Titre":
				listeDocument = service.search(jwtCookie, value, TITLE);
				break;
			case "Reference":
				listeDocument = service.search(jwtCookie, value, REFERENCE);
				break;
			}
		}catch(JWTCheckingException_Exception e) {
			return "login";
		}


		model.addAttribute("listResSearch", listeDocument);
		return "search";
	}

	/**
	 * 
	 * @return JSP page
	 */
	@GetMapping("/accessSearch")
	public String searchList() {
		return "search";
	}
}
