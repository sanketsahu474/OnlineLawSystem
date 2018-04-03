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
import com.example.demo.service.UserService;

@Controller
public class LawyerController {

	@Autowired
	private UserService userService;
	private int User_id;
	private String EmailId;
	ConstantList list = new ConstantList();

	// request for lawyer home page
	@RequestMapping(value = "/lawyer/LawyerHome", method = RequestMethod.GET)
	public ModelAndView getLawyerHome() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		User_id = user.getId();
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Lawyer Role");
		modelAndView.setViewName("lawyer/LawyerHome");
		return modelAndView;

	}

	// Edit user info
	@RequestMapping(value = { "/lawyer/UpdateInfo" }, method = RequestMethod.GET)
	public ModelAndView getUpdateUser() {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(User_id);
		EmailId = user.getEmail();
		modelAndView.addObject("user", user);
		modelAndView.addObject("allRoles", list.getRoles());
		modelAndView.setViewName("/lawyer/UpdateInfo");
		return modelAndView;
	}

	@RequestMapping(value = "/lawyer/UpdateInfo", method = RequestMethod.POST)
	public ModelAndView setUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allRoles", list.getRoles());
		if (user.getEmail() == EmailId)
			;
		User userExists = userService.findUserByEmail(user.getEmail());
		if (!EmailId.equalsIgnoreCase(user.getEmail())) {
			if (userExists != null) {
				bindingResult.rejectValue("email", "error.user",
						"There is already a user registered with the email provided");
			}
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("client/UpdateInfo");
		} else {
			userService.updateUser(user, User_id);
			modelAndView.addObject("successMessage", "user has been updated.");
			modelAndView.addObject("user", user);
			modelAndView.addObject("allRoles", list.getRoles());
			modelAndView.setViewName("lawyer/UpdateInfo");
		}
		return modelAndView;
	}
	// Request for searching IPC Codes

	@RequestMapping(value = "/lawyer/SearchIpc", method = RequestMethod.GET)
	public ModelAndView getSearchIpcCode() {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());
		modelAndView.addObject("ipccode", userService.findAllIpc());
		modelAndView.setViewName("/lawyer/SearchIpc");
		return modelAndView;
	}

	// Search on the basis of section number

	@RequestMapping(value = "/Section", method = RequestMethod.POST)
	public ModelAndView sectionSearchIpcCode(@RequestParam("section") String section) {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());

		modelAndView.addObject("ipccode", userService.findBySection(section));
		modelAndView.setViewName("/lawyer/SearchIpc");
		return modelAndView;
	}

	// search on the basis of keywords

	@RequestMapping(value = "/Keyword", method = RequestMethod.POST)
	public ModelAndView keywordSearchIpcCode(@RequestParam("keyword") String keyword) {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());

		modelAndView.addObject("ipccode", userService.findByKeyword(keyword));
		modelAndView.setViewName("/lawyer/SearchIpc");
		return modelAndView;
	}

	// request for checking cases

	@RequestMapping(value = "/lawyer/ShowCases", method = RequestMethod.GET)
	public ModelAndView getShowCases() {
		ModelAndView modelAndView = new ModelAndView();
		Case cases = new Case();
		modelAndView.addObject("cases", cases);
		modelAndView.addObject("allcasetypes", list.getCaseType());
		modelAndView.addObject("case", userService.findAllCases());
		modelAndView.setViewName("/lawyer/ShowCases");
		return modelAndView;
	}

	@RequestMapping(value = "/lawyer/ShowCases", method = RequestMethod.POST)
	public ModelAndView setShowCases(@RequestParam("caseType") String casetype) {
		ModelAndView modelAndView = new ModelAndView();
		Case cases = new Case();
		modelAndView.addObject("cases", cases);
		modelAndView.addObject("allcasetypes", list.getCaseType());

		modelAndView.addObject("case", userService.findByType(casetype));
		modelAndView.setViewName("/lawyer/ShowCases");
		return modelAndView;
	}
	// Request for filling lawyer details

	@RequestMapping(value = "/lawyer/Details", method = RequestMethod.GET)
	public ModelAndView getLawyerDetail() {
		ModelAndView modelAndView = new ModelAndView();
		User userid = userService.findByIds(User_id);
		LawyerInfo lawyer = userService.findUser(userid);
		if (lawyer == null) {
			lawyer = new LawyerInfo();
		}
		if (lawyer.getEmail() == null) {
			lawyer = new LawyerInfo();
		}

		modelAndView.addObject("lawyer", lawyer);
		modelAndView.addObject("allCourts", list.getCourt());
		modelAndView.addObject("allTypes", list.getType());
		modelAndView.setViewName("lawyer/Details");
		return modelAndView;
	}

	@RequestMapping(value = "/lawyer/Details", method = RequestMethod.POST)
	public ModelAndView setLawyerDetail(@Valid @ModelAttribute("lawyer") LawyerInfo lawyer,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allCourts", list.getCourt());
		modelAndView.addObject("allTypes", list.getType());
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("lawyer/Details");
		} else {
			userService.saveLawyer(lawyer, User_id);
			modelAndView.addObject("successMessage", "your details have been saved thankyou...");
			modelAndView.addObject("allCourts", list.getCourt());
			modelAndView.addObject("allTypes", list.getType());
			modelAndView.addObject("lawyer", lawyer);
			modelAndView.setViewName("lawyer/Details");
		}
		return modelAndView;
	}

	// Request for chat option
	@RequestMapping(value = "/lawyer/LawyerChat", method = RequestMethod.GET)
	public ModelAndView getClientChat() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/lawyer/LawyerChat");
		return modelAndView;
	}

}
