package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;


//import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class LoginController{
	
	@Autowired
	private UserService userService;
	
	
// --------------------------------------this request is for login page-----------------------------------------
	
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
//-----------------------------------	this request is for registration page---------------------------------------
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.addObject("allRoles",getRoles());
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	
	private List<String> getRoles() {
	    List<String> list = new ArrayList<>();
	    list.add("ADMIN");
	    list.add("USER");
	    list.add("LAWYER");
	    return list;
	}

	// ----------------------------------this request is to send data from registration page---------------------------------------------------
	
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allRoles",getRoles());
		User userExists = userService.findUserByEmail(user.getEmail());
		if (userExists != null) {
			bindingResult
					.rejectValue("email", "error.user",
							"There is already a user registered with the email provided");
		}
		if (bindingResult.hasErrors()) {
			//modelAndView.addObject("allRoles",getRoles());
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("allRoles",getRoles());
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
	

	
	
	//---------------for default url based on the role of user----------------------
	 @RequestMapping(value="/default",method=RequestMethod.GET)
	 public String defaultAfterLogin() {
		 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			if(user.getRole().contains("ADMIN"))
				return "redirect:/admin/AdminHome";
			else if(user.getRole().contains("USER"))
				return "redirect:/client/ClientHome";
			else if(user.getRole().contains("LAWYER"))
				return "redirect:/lawyer/LawyerHome";
			else
				return "/login";
	 }
				
	     


}
