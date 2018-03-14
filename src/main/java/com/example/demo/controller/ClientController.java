package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.IpcCodes;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.User;
import com.example.demo.repository.IpcCodesRepository;
import com.example.demo.repository.LawyerRepository;
import com.example.demo.service.UserService;

@Controller
public class ClientController {

	
	@Autowired
	private UserService userService;
	
	@Autowired
	   IpcCodesRepository ipcCodesRepository;
	@Autowired
	LawyerRepository lawyerRepository;
	
	
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
	//List for keywords
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

	//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Request for Client $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		
		//-------------------------------------Request for search IPC codes by user-----------------------------------------
		 
	 @RequestMapping(value = "/client/SearchIpcCode", method = RequestMethod.GET)
	    public ModelAndView getSearchIpcCode(){
	    	ModelAndView modelAndView = new ModelAndView();
	    	IpcCodes ipc = new IpcCodes();
			modelAndView.addObject("ipc", ipc);
	    	modelAndView.addObject("allkeywords",getKeywords());
	        modelAndView.addObject("ipccode",ipcCodesRepository .findAll());
	        modelAndView.setViewName("/client/SearchIpcCode");
	        return modelAndView;
	    }
	 
	@RequestMapping(value = "/SectionSearch", method = RequestMethod.POST)
	 public ModelAndView sectionSearchIpcCode(@RequestParam("section") String section) {
		 ModelAndView modelAndView = new ModelAndView();
		 IpcCodes ipc = new IpcCodes();
		 modelAndView.addObject("ipc", ipc);
		 modelAndView.addObject("allkeywords",getKeywords());
		
		 modelAndView.addObject("ipccode",ipcCodesRepository.findBySection(section));
		 modelAndView.setViewName("/client/SearchIpcCode");
		 return modelAndView;
	 }
	
	@RequestMapping(value = "/KeywordSearch", method = RequestMethod.POST)
	public ModelAndView keywordSearchIpcCode(@RequestParam("keyword") String keyword) {
		 ModelAndView modelAndView = new ModelAndView();
		 IpcCodes ipc = new IpcCodes();
		 modelAndView.addObject("ipc", ipc);
		 modelAndView.addObject("allkeywords",getKeywords());
		
		 modelAndView.addObject("ipccode",ipcCodesRepository.findByKeyword(keyword));
		 modelAndView.setViewName("/client/SearchIpcCode");
		 return modelAndView;
	 }
	
		// ------------------------------------Request for searching lawyers by user ----------------------------------------
	 @RequestMapping(value = "/client/SearchLawyer", method = RequestMethod.GET)
	    public ModelAndView getSearchLawyer(){
	    	ModelAndView modelAndView = new ModelAndView();
	    	LawyerInfo law = new LawyerInfo();
			modelAndView.addObject("law", law);
			modelAndView.addObject("allCourts",getCourt());
			modelAndView.addObject("allTypes",getType());
	        modelAndView.setViewName("/client/SearchLawyer");
	        
	        return modelAndView;
	    }
	
	 @RequestMapping(value = "/client/SearchLawyers", method = RequestMethod.POST)
		public ModelAndView LawyerSearch(@RequestParam("court") String court,@RequestParam("type") String type) {
			 ModelAndView modelAndView = new ModelAndView();
			 LawyerInfo law = new LawyerInfo();
			 modelAndView.addObject("law", law);
			 modelAndView.addObject("allCourts",getCourt());
				modelAndView.addObject("allTypes",getType());
			 modelAndView.addObject("lawyer",lawyerRepository.findByCourtAndType(court,type));
			 modelAndView.setViewName("/client/SearchLawyers");
			 return modelAndView;
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
