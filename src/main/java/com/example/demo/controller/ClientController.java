package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Case;
import com.example.demo.model.IpcCode;
import com.example.demo.model.LawyerInfo;
import com.example.demo.model.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;

@Controller
public class ClientController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailService emailService;
	private int users_id;
	private String emailId;
	private int case_id;
	ConstantList list = new ConstantList();

	// Request for client home page
	@RequestMapping(value = "/client/ClientHome", method = RequestMethod.GET)
	public ModelAndView getClientHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		users_id = user.getId();
		modelAndView.setViewName("client/ClientHome");
		return modelAndView;

	}

	// Edit user info
	@RequestMapping(value = { "/client/UpdateInfo" }, method = RequestMethod.GET)
	public ModelAndView getUpdateUser() {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(users_id);
		emailId = user.getEmail();
		modelAndView.addObject("user", user);
		modelAndView.addObject("allRoles", list.getRoles());
		modelAndView.setViewName("/client/UpdateInfo");
		return modelAndView;
	}

	@RequestMapping(value = "/client/UpdateInfo", method = RequestMethod.POST)
	public ModelAndView setUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allRoles", list.getRoles());
		if (user.getEmail() == emailId)
			;
		User userExists = userService.findUserByEmail(user.getEmail());
		if (!emailId.equalsIgnoreCase(user.getEmail())) {
			if (userExists != null) {
				bindingResult.rejectValue("email", "error.user",
						"There is already a user registered with the email provided");
			}
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("client/UpdateInfo");
		} else {
			userService.updateUser(user, users_id);
			modelAndView.addObject("successMessage", "user has been updated.");
			modelAndView.addObject("user", user);
			modelAndView.addObject("allRoles", list.getRoles());
			modelAndView.setViewName("client/UpdateInfo");
		}
		return modelAndView;
	}

	// Request by user for searching IPC codes

	@RequestMapping(value = "/client/SearchIpcCode", method = RequestMethod.GET)
	public ModelAndView getSearchIpcCode() {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());
		modelAndView.addObject("ipccode", userService.findAllIpc());
		modelAndView.setViewName("/client/SearchIpcCode");
		return modelAndView;
	}

	// Search on the basis of section no.
	@RequestMapping(value = "/SectionSearch", method = RequestMethod.POST)
	public ModelAndView sectionSearchIpcCode(@RequestParam("section") String section) {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());

		modelAndView.addObject("ipccode", userService.findBySection(section));
		modelAndView.setViewName("/client/SearchIpcCode");
		return modelAndView;
	}

	// Search on the basis of keywords
	@RequestMapping(value = "/KeywordSearch", method = RequestMethod.POST)
	public ModelAndView keywordSearchIpcCode(@RequestParam("keyword") String keyword) {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());

		modelAndView.addObject("ipccode", userService.findByKeyword(keyword));
		modelAndView.setViewName("/client/SearchIpcCode");
		return modelAndView;
	}

	// Request by user for searching lawyers
	@RequestMapping(value = "/client/SearchLawyer", method = RequestMethod.GET)
	public ModelAndView getLawyerSearch() {
		ModelAndView modelAndView = new ModelAndView();
		LawyerInfo law = new LawyerInfo();
		modelAndView.addObject("law", law);
		modelAndView.addObject("allCourts", list.getCourt());
		modelAndView.addObject("allTypes", list.getType());
		modelAndView.setViewName("/client/SearchLawyer");

		return modelAndView;
	}

	@RequestMapping(value = "/client/SearchLawyers", method = RequestMethod.POST)
	public ModelAndView setLawyerSearch(@RequestParam("court") String court, @RequestParam("type") String type) {
		ModelAndView modelAndView = new ModelAndView();
		LawyerInfo law = new LawyerInfo();
		modelAndView.addObject("law", law);
		modelAndView.addObject("allCourts", list.getCourt());
		modelAndView.addObject("allTypes", list.getType());
		modelAndView.addObject("lawyer", userService.findByCourtAndType(court, type));
		modelAndView.setViewName("/client/SearchLawyers");
		return modelAndView;
	}

	// Request by user for cases

	@RequestMapping(value = "/client/Cases", method = RequestMethod.GET)
	public ModelAndView getCases() {
		ModelAndView modelAndView = new ModelAndView();
		User userid = userService.findByIds(users_id);
		modelAndView.addObject("case", userService.findByUsers(userid));
		modelAndView.setViewName("/client/Cases");
		return modelAndView;
	}

	@RequestMapping(value = "/client/Cases", method = RequestMethod.POST)
	public ModelAndView setCases(@Valid @ModelAttribute Case cases, BindingResult bindingResult,
			@RequestParam("email") String email) {
		ModelAndView modelAndView = new ModelAndView();
		User userid = userService.findByIds(users_id);
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("/client/Case");
		} else {
			if (email != null)
				emailService.sendMail(email, "This case has been offered to you ", cases.getDescription());

			userService.saveCase(cases, users_id);
			modelAndView.addObject("cases", new Case());
			modelAndView.addObject("case", userService.findByUsers(userid));
			modelAndView.setViewName("/client/Cases");
		}
		return modelAndView;
	}

	// Request for deleting the case

	@RequestMapping(value = { "/delete_case" }, method = RequestMethod.GET)
	public ModelAndView deleteCase(@RequestParam(name = "caseId") int caseid) {
		ModelAndView modelAndView = new ModelAndView();
		User userid = userService.findByIds(users_id);
		userService.deletecaseByCaseId(caseid);
		Case cases = new Case();
		modelAndView.addObject("cases", cases);

		modelAndView.addObject("case", userService.findByUsers(userid));
		modelAndView.setViewName("/client/Cases");
		return modelAndView;
	}

	// Request for editing the case

	@RequestMapping(value = { "/edit_case" }, method = RequestMethod.GET)
	public ModelAndView getUpdateCase(@RequestParam(name = "caseId") int caseid) {
		ModelAndView modelAndView = new ModelAndView();
		case_id = caseid;
		Case cases = userService.findcaseByCaseid(caseid);
		modelAndView.addObject("cases", cases);
		modelAndView.addObject("allcasetypes", list.getCaseType());
		modelAndView.setViewName("/client/UpdateCase");
		return modelAndView;
	}

	@RequestMapping(value = "/client/UpdateCase", method = RequestMethod.POST)
	public ModelAndView setUpdateCase(@Valid @ModelAttribute("cases") Case cases, BindingResult bindingResult,
			@RequestParam("email") String email) {
		ModelAndView modelAndView = new ModelAndView();
		Case caseExists = userService.findcaseByCaseid(cases.getCaseId());
		if (caseExists != null) {
			bindingResult.rejectValue("cases", "error", "");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("allcasetypes", list.getCaseType());
			modelAndView.setViewName("/client/UpdateCase");
		} else {

			if (email != null)
				emailService.sendMail(email, "The case has little updates ", cases.getDescription());

			userService.UpdateCase(cases, case_id, users_id);
			modelAndView.addObject("cases", cases);
			modelAndView.addObject("successMessage", "case has been updated.");
			modelAndView.addObject("allcasetypes", list.getCaseType());
			modelAndView.setViewName("/client/UpdateCase");
		}
		return modelAndView;
	}

	// Request for adding new case

	@RequestMapping(value = "/client/AddCase", method = RequestMethod.GET)
	public ModelAndView getAddCases() {
		ModelAndView modelAndView = new ModelAndView();
		Case cases = new Case();
		modelAndView.addObject("cases", cases);
		modelAndView.addObject("allcasetypes", list.getCaseType());
		modelAndView.setViewName("/client/AddCase");
		return modelAndView;
	}

	@RequestMapping(value = "/client/AddCase", method = RequestMethod.POST)
	public ModelAndView setAddCases(@Valid @ModelAttribute("cases") Case cases, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (cases == null) {
			bindingResult.rejectValue("cases", "error.cases", "There is something wrong");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("allcasetypes", list.getCaseType());
			modelAndView.setViewName("/client/AddCase");
		} else {
			userService.saveCase(cases, users_id);
			if (cases.getEmail().length() >= 5) {
				emailService.sendMail(cases.getEmail(), "This case has been offered to you ", cases.getDescription());
			}

			userService.saveCase(cases, users_id);
			modelAndView.addObject("cases", new Case());
			modelAndView.addObject("allcasetypes", list.getCaseType());
			modelAndView.addObject("successMessage", "case has been registered succesfully.");
			modelAndView.setViewName("/client/AddCase");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/client/ClientChat", method = RequestMethod.GET)
	public ModelAndView ClientChat() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/client/ClientChat");
		return modelAndView;
	}

	// Request for sending mail to all in particular field

	/*
	 * @RequestMapping(value = "/sendToAll", method = RequestMethod.POST) public
	 * ModelAndView sendToAll(@Valid @ModelAttribute("cases") Case cases,
	 * BindingResult bindingResult) { ModelAndView modelAndView = new
	 * ModelAndView(); User userid = userService.findByIds(users_id);
	 * 
	 * List<LawyerInfo> emails = userService.findByTypes(cases.getCaseType());
	 * String[] array = new String[emails.size()]; int index = 0; for (Object value
	 * : emails) { array[index] = (String) value; index++; } /* String[] email =
	 * emails.toArray(new String[emails.size()]); String[] email = new
	 * String[emails.size() + 1]; email[0] = "sanketsahu474@gmail.com"; for (int i =
	 * 1; i < emails.size(); i++) { email[i] = emails.get(i - 1); }
	 * 
	 * emailService.sendMails(array, "This case has been offered to you ",
	 * "something"); //userService.saveCase(cases, users_id);
	 * modelAndView.addObject("cases", new Case());
	 * modelAndView.addObject("allcasetypes", list.getCaseType());
	 * modelAndView.addObject("case", userService.findByUsers(userid));
	 * modelAndView.setViewName("/client/Cases"); // } return modelAndView; }
	 * 
	 * // Request for Contacting client by user
	 * 
	 * @RequestMapping(value = "/client/ClientChat", method = RequestMethod.GET)
	 * public ModelAndView ClientChat() { ModelAndView modelAndView = new
	 * ModelAndView(); modelAndView.setViewName("/client/ClientChat"); return
	 * modelAndView; }
	 */

}
