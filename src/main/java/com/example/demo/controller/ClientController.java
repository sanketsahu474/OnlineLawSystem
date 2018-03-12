package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class ClientController {

	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/client/ClientHome", method = RequestMethod.GET)
	public ModelAndView clienthome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with User Role");
		modelAndView.setViewName("client/ClientHome");
		return modelAndView;

}	

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Request for Client $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		
		//-------------------------------------Request for search IPC codes by user-----------------------------------------
		 
			 @RequestMapping("/SearchIpcCode")
			 public String SearchIpcCode() {
				 return "/SearchIpcCode";
			 }
		// ------------------------------------Request for searching lawyers by user ----------------------------------------
		 @RequestMapping("/SearchLawyer")
		 public String SearchLawyer() {
			 return "client/SearchLawyer";
		 }
		 
		// ------------------------------------Request for clientHome by user ----------------------------------------
		 @RequestMapping("/ClientHome")
		 public String ClientHome() {
			 return "client/ClientHome";
		 }
		// ------------------------------------------------------Request for cases by user ----------------------------------------
		 @RequestMapping("/Cases")
		 public String Cases() {
			 return "client/Cases";
		 }
		 
		// ------------------------------------------------------Request for fill details by user ----------------------------------------
		 @RequestMapping("/FillDetail")
		 public String FillDetail() {
			 return "client/FillDetail";
		 }
		 
			// ------------------------------------------------------Request for Contacting client by user ----------------------------------------
		 @RequestMapping("/ContactClient")
		 public String ContactClient() {
			 return "client/ContactClient";
		 }

}
