package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.LawyerInfo;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class LawyerController {

	
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping("/LawyerHome")
	public String LawyerHome()
	{
		return "lawyer/LawyerHome";
	}
	private List<String> getCourt() {
	    List<String> lists = new ArrayList<>();
	    lists.add("Supreme");
	    lists.add("High");
	    lists.add("District");
	    return lists;
	}

	private List<String> getType() {
	    List<String> list = new ArrayList<>();
	    list.add("Criminal");
	    list.add("Corporat");
	    list.add("Tax");
	    list.add("RealEstate");
	    list.add("Immigration");
	    list.add("PersonalInjury");
	    return list;
	}

	@RequestMapping(value="/lawyer/Details", method = RequestMethod.GET)
	public ModelAndView details(){
		ModelAndView modelAndView = new ModelAndView();
		LawyerInfo lawyer = new LawyerInfo();
		modelAndView.addObject("lawyer", lawyer);
		modelAndView.addObject("allCourts",getCourt());
		modelAndView.addObject("allTypes",getType());
		modelAndView.setViewName("lawyer/Details");
		return modelAndView;
	}

	@RequestMapping(value = "/lawyer/Details", method = RequestMethod.POST)
	public ModelAndView LawyerDetails(@Valid LawyerInfo lawyer,BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allCourts",getCourt());
		modelAndView.addObject("allTypes",getType());
		LawyerInfo lawyerExists =userService.findLawyerByEmail(lawyer.getEmail());
		if(lawyerExists != null) {
			bindingResult
			.rejectValue("email", "error.use",
					"There is already a user using this emailId");
	}
		if (bindingResult.hasErrors()) {
			//modelAndView.addObject("lawyer", lawyer);
			modelAndView.setViewName("lawyer/Details");
		} else {
			userService.saveLawyer(lawyer);
			modelAndView.addObject("successMessage", "your details have been saved thankyou...");
		modelAndView.addObject("allCourts",getCourt());
			modelAndView.addObject("allTypes",getType());
			modelAndView.addObject("lawyer", new LawyerInfo());
			modelAndView.setViewName("lawyer/Details");
		}
			return modelAndView;
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
}
