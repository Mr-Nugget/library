package fr.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controllers for home pages
 * @author Titouan
 *
 */
@Controller
public class HomeController {
	
	@GetMapping("/infos")
	public String infos() {
		return "infos";
	}
}
