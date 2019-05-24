package fr.library.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.library.wsdl.connect.IConnection;
import fr.library.wsdl.connect.JWTCheckingException_Exception;
import fr.library.wsdl.connect.User;


/**
 * Controller for connection users action like login, logout
 * @author Titouan
 *
 */

@Controller
public class ConnectionController {

	@Autowired
	IConnection service;


	Logger logger = Logger.getLogger(ConnectionController.class);

	/**
	 * 
	 * @param response
	 * @param model
	 * @param mail
	 * @param password
	 * @param jwtCookie
	 * @return JSP page
	 */
	@PostMapping("/login")
	public ModelAndView login(HttpServletResponse response,
			@RequestParam(value="mail", required=false) String mail,
			@RequestParam(value="password", required=false) String password,
			@CookieValue(value="jwtCookie", required=false) String jwtCookie
			) {

		ModelAndView model = new ModelAndView();
		String jwt = null;

		// If there is no jwtCookie, use the login webservice
		if(jwtCookie == null) {
			if(mail.isEmpty() || password.isEmpty()) {
				// Add a message for the client
				model.addObject("error", "Identifiants incorrects");
				model.setViewName("redirect:/connection");
			}else {

				jwt = service.login(mail, password);

				if(jwt == null) {
					model.addObject("error", "Identifiants incorrects");
					model.setViewName("redirect:/connection");
				}else {
					// Creating a cookie to stock jwt token
					Cookie cookie = new Cookie("jwtCookie", jwt);

					// Set cookie Age to 30min
					cookie.setMaxAge(604800);
					response.addCookie(cookie);
					// Get users infos
					User user = new User();

					try {
						user = service.getUser(jwt);
						Cookie firstname = new Cookie("firstname", user.getFirstName());
						Cookie lastname = new Cookie("lastname", user.getLastName());

						response.addCookie(firstname);
						response.addCookie(lastname);
						model.setViewName("redirect:/home");
					} catch (JWTCheckingException_Exception e) {
						model.setViewName("redirect:/connection");
						logger.error("Login controller", e);
					}
				}
			}
		}else {
			// Get users infos
			User user = new User();
			try {
				user = service.getUser(jwtCookie);
				Cookie firstname = new Cookie("firstname", user.getFirstName());
				Cookie lastname = new Cookie("lastname", user.getLastName());
				response.addCookie(firstname);
				response.addCookie(lastname);
				model.setViewName("redirect:/home");
			} catch (JWTCheckingException_Exception e) {
				logger.error("Loggin controller", e);
				model.setViewName("redirect:/connection");
			}
		}
		return model;
	}
	/**
	 * 
	 * @param jwtCookie
	 * @param response
	 * @return JSP page
	 */
	@GetMapping("/connection")
	public String welcome(ModelMap model,
			@RequestParam(value="error", required=false) String error,
			@CookieValue(value="jwtCookie", required=false) String jwtCookie, HttpServletResponse response
			) {
		if(jwtCookie == null) {
			// Go to the connection page if there is no jwtCookie
			model.addAttribute("error", error);
			return "login";
		}else {
			// Else go to the homepage after checking the jwt token

			// Get users infos
			User user = new User();
			try {
				user = service.getUser(jwtCookie);
			} catch (JWTCheckingException_Exception e) {
				logger.error("Connection Controller",e);
				return "login";
			}
			Cookie firstname = new Cookie("firstname", user.getFirstName());
			Cookie lastname = new Cookie("lastname", user.getLastName());
			response.addCookie(firstname);
			response.addCookie(lastname);
			return "home";
		}
	}

	/**
	 * 
	 * @param response
	 * @param jwtCookie
	 * @return JSP page
	 */
	@GetMapping("/logout")
	public ModelAndView logout( HttpServletResponse response,
			@CookieValue(value="jwtCookie") String jwtCookie
			) {

		// Use ModelAndView object to redirect to another controller
		ModelAndView model = new ModelAndView();
		try {
			service.logout(jwtCookie);
		} catch (JWTCheckingException_Exception e) {
			logger.error("logout Controller", e);
			model.setViewName("redirect:/connection");
			return model;
		}

		// Delete jwt cookie
		Cookie cookie = new Cookie("jwtCookie", "");
		// Delete lastname and firstname Cookie
		Cookie lastnameCookie = new Cookie("lastname", "");
		Cookie firstnameCookie = new Cookie("firstname", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		response.addCookie(firstnameCookie);
		response.addCookie(lastnameCookie);
		model.setViewName("redirect:/connection");
		return model;
	}

	@GetMapping("/home")
	public String homePage(ModelMap model, 
			@CookieValue(value="firstname") String firstname, @CookieValue(value="lastname") String lastname) {

		model.addAttribute("firstname", firstname);
		model.addAttribute("lastname", lastname);
		return "home";
	}
	
	@GetMapping("/forget")
	public String forgetPasswordPage() {
		return "forget";
	}
	
	@PostMapping("/forgetresponse")
	public String sendMailPassword(ModelMap model, @RequestParam(value="mail", required=false) String mail) {
		User user = service.userExist(mail);
		String message = "";
		if(user == null) {
			message = "Aucun utilisateur connu pour le mail :"+mail+". Veuillez réessayer ou contacter le service d'administration de la bibliothèque";
		}else {
			message = "Un mail de réinitialisation vient d'être envoyé à l'adresse : "+mail;
			service.sendMail("bonjour", "bonjour", mail);
		}
		model.addAttribute("message", message);
		
		return "forgetresponse";
	}
	
	@PostMapping("/newpassword")
	public String resetPassword(ModelMap model,
			 @RequestParam(value="token", required=false) String token,
			 @RequestParam(value="password", required=false) String password,
			 @RequestParam(value="confirm", required=false) String confirm) {
		
		if(password.isEmpty() || confirm.isEmpty()) {
			return "home";
		}
		if(!password.equals(confirm)) {
			return "home";
		}
		
		//service.resetPassword(password, token);
		
		return "";
	}
}
