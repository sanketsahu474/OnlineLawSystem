package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.IpcCodes;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class AdminController {

	
	@Autowired
	private UserService userService;
	
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
	 /*
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.addObject("allRoles",getRoles());
		modelAndView.setViewName("registration");
		return modelAndView;
	}*/
		 
		 @RequestMapping(value="/AddIpcCode", method = RequestMethod.GET)
			public ModelAndView AddIpcCodes(){
				ModelAndView modelAndView = new ModelAndView();
				IpcCodes ipc = new IpcCodes();
				modelAndView.addObject("ipc", ipc);
				modelAndView.addObject("allkeywords",getKeywords());
				modelAndView.setViewName("admin/AddIpcCode");
				return modelAndView;
			}
			
			
			private List<String> getKeywords() {
			    List<String> list = new ArrayList<>();
			    list.add("Introduction");
			    list.add("General");
			    list.add("Punishment");
			    list.add("Exception");
			    list.add("Private Defence");
			    list.add("Abetment");
			    list.add("Conspiracy");
			    list.add("Offence Against the State");
			    list.add("Offence Realated to Army");
			    list.add("Offence Against Public Transquillity");
			    list.add("Public Servant");
			    list.add("Election");
			    list.add("Contempt of Lawful Athority");
			    list.add("Offence Against Public Justice");
			    list.add("Coins");
			    list.add("Stamp");
			    list.add("Weights and Measures");
			    list.add("Offence Affecting Public Safety");
			    list.add("Religion");
			    list.add("Affecting Life");
			    list.add("Miscarriage");
			    list.add("Hurt");
			    list.add("Wrongful Confinement");
			    list.add("Criminal Force and Assault");
			    list.add("Kidnapping");
			    list.add("Slaves");
			    list.add("Extortion");
			    list.add("Robbery and Dacoity");
			    list.add("Of Criminal Misappropriation of Property");
			    list.add("Of Criminal Breach of Trust");
			    list.add("Of the Receiving of Stolen Property");
			    list.add("Of Cheating");
			    list.add("Of Fraudulent Deeds and Dispositions of Property");
			    list.add("Of Mischief");
			    list.add("Of Criminal Trespass");
			    list.add("OF OFFENCES RELATING TO DOCUMENTS AND TO PROPERTY MARKS");
			    list.add("Of Property and Other Marks");
			    list.add("Of Currency-Notes and Bank-Notes");
			    list.add("OF THE CRIMINAL BREACH OF CONTRACTS OF SERVICE");
			    list.add("OF OFFENCES RELATING TO MARRIAGE");
			    list.add("OF CRUELTY BY HUSBAND OR RELATIVES OF HUSBAND");
			    list.add("OF DEFAMATION");
			    list.add("OR CRIMINAL INTIMIDATION , INSULTAND ANNOYANCE");
			    list.add("OF ATTEMPTS OF COMMIT OFFENCES");
			    return list;
			}
			
			/*@RequestMapping(value = "/registration", method = RequestMethod.POST)
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
			
		*/
			
			@RequestMapping(value = "/AddIpcCode", method = RequestMethod.POST)
			public ModelAndView AddIpc(@Valid @ModelAttribute IpcCodes ipc, BindingResult bindingResult) {
				ModelAndView modelAndView = new ModelAndView();
		
				modelAndView.addObject("allkeywords",getKeywords());
				IpcCodes ipcExists = userService.findipcBySection(ipc.getSection());
				if (ipcExists != null) {
					bindingResult
							.rejectValue("section", "error.ipc",
									"There is already a section wrriten from that if you really want to change go to update option!!");
				}
				if (bindingResult.hasErrors()) {
					
					modelAndView.setViewName("admin/AddIpcCode");
				} else {
					userService.saveIpcCode(ipc);
					modelAndView.addObject("successMessage", "Ipc Code has Been enterd Succesfully!!!");
					modelAndView.addObject("allkeywords",getKeywords());
					modelAndView.addObject("ipc", new IpcCodes());
					modelAndView.setViewName("admin/AddIpcCode");
					
				}
				return modelAndView;
			}

}
