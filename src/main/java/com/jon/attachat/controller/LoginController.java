package com.jon.attachat.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage(HttpServletRequest request) {
		
		// Set referrer to prior page so MyCustomLoginSuccessHandler 
		// can redirect user back to previous page.
	    String referrer = request.getHeader("Referer");
	    request.getSession().setAttribute("url_prior_login", referrer);
		
		//return "plain-login";
		return "login-form";

	}
	
	// Add request mapping for /access-denied
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
	}
	
}
