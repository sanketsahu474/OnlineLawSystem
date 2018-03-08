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
	

	//--------------------------------request for admin/home page after correct credential has been given by user-----------------------------------------
	
	
	@RequestMapping(value="/admin/AdminHome", method = RequestMethod.GET)
	public ModelAndView home(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
		modelAndView.setViewName("admin/AdminHome");
		return modelAndView;

}
//------------------------------request for user/home page after correct credential has been given by user--------------------------------------------------	
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
	//------------------------------request for lawyer/home page after correct credential has been given by user--------------------------------------------------	
	@RequestMapping(value="/lawyer/LawyerHome", method = RequestMethod.GET)
	public ModelAndView lawyerhome(){
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		modelAndView.addObject("adminMessage","Content Available Only for Users with Lawyer Role");
		modelAndView.setViewName("lawyer/LawyerHome");
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
				
	       /* if (request.isUserInRole("ROLE_admin")) {
	            return "redirect:/admin/AdminHome";
	        }
	        else if(request.isUserInRole("ROLE_user"))
	        return "redirect:/client/ClientHome";
	        else 
	        	//(request.isUserInRole("lawyer"))
	        	return "redirect:/lawyer/LawyerHome";*/
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$Request for ADMIN$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$	      
	
	//------------------------------request for update Ipc Codes by admin--------------------------------------------------	
	 @RequestMapping("/UpdateIpcCode")
	 public String IpcCode() {
		 return "admin/UpdateIpcCode";
	 }
	//------------------------------request for admin Home page by admin--------------------------------------------------	
	 @RequestMapping("/AdminHome")
	 public String AdminHome() {
		 return "admin/AdminHome";
	 }
	
		//------------------------------request for user Info page by admin--------------------------------------------------	

	 @RequestMapping("/UserInfo")
	 public String UserInfo() {
		 return "admin/UserInfo";
	 }
	 
		//------------------------------request for contact page by any user--------------------------------------------------	

	 @RequestMapping("/Contact")
	 public String Contact() {
		 return "admin/Contact";
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

//----------------------------------------------Request for Lawyers----------------------------------------------------------
@RequestMapping("/LawyerHome")
public String LawyerHome()
{
	return "lawyer/LawyerHome";
}


@RequestMapping("/Details")
public String Details()
{
	return "lawyer/Details";
}

@RequestMapping("/SearchIpc")
public String SearchIpc()
{
	return "lawyer/SearchIpc";
}

@RequestMapping("/ContactLawyer")
public String ContactLawyer()
{
	return "lawyer/ContactLawyer";
}
	
//----------------------------------------------------request for submitting form details-----------------------------------------------------------------------	
}
