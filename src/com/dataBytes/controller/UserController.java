package com.dataBytes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.core.userdetails.User;

@RestController
public class UserController {
/*
	@Autowired
    UserProfileService userProfileService;
     
    @Autowired
    UserService userService;
    */
	/*
	@RequestMapping(value = "/login")
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView("home");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("logout", "You've been logged out successfully.");
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth == null || auth instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = auth.getName();
		    model.addObject("user", currentUserName);
		}
		
		return model;

	}
	*/
	
	@RequestMapping(value = "/loginPage")
	public ModelAndView loginPage(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView("login");
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("logout", "You've been logged out successfully.");
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (!(auth == null || auth instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = auth.getName();
		    model.addObject("user", currentUserName);
		}
		
		return model;

	}

}
