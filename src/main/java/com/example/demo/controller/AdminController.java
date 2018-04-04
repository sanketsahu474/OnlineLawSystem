package com.example.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.IpcCode;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
public class AdminController {

	@Autowired
	private UserService userService;
	private int user_id;
	private String emailid;
	ConstantList list = new ConstantList();

	// Request for ADMIN home page

	@RequestMapping(value = "/admin/AdminHome", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName",
				"Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
		user_id = user.getId();
		modelAndView.setViewName("admin/AdminHome");
		return modelAndView;

	}

	// request for user Info page by ADMIN

	@RequestMapping(value = "/admin/UserInfo", method = RequestMethod.GET)
	public ModelAndView getuserInfo() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", userService.find());
		modelAndView.setViewName("admin/UserInfo");
		return modelAndView;
	}

	// Request for adding IPC Codes

	@RequestMapping(value = "/admin/AddIpcCode", method = RequestMethod.GET)
	public ModelAndView getAddIpcCodes() {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());
		modelAndView.setViewName("/admin/AddIpcCode");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/AddIpcCode", method = RequestMethod.POST)
	public ModelAndView setAddIpcCodes(@Valid @ModelAttribute("ipc") IpcCode ipc, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("allkeywords", list.getKeywords());
		IpcCode ipcExists = userService.findBySection(ipc.getSection());
		if (ipcExists != null) {
			bindingResult.rejectValue("section", "error",
					"*There is already a section written with the same as you are providing  if you really want to change go to update option!!");
		}
		if (bindingResult.hasErrors()) {

			modelAndView.setViewName("/admin/AddIpcCode");
		} else {
			userService.saveIpcCode(ipc);
			modelAndView.addObject("successMessage", "Ipc Code has Been enterd Succesfully!!!");
			modelAndView.addObject("ipc", new IpcCode());
			modelAndView.addObject("allkeywords", list.getKeywords());
			modelAndView.setViewName("/admin/AddIpcCode");

		}
		return modelAndView;
	}

	// Edit user info
	@RequestMapping(value = { "/edit_user" }, method = RequestMethod.GET)
	public ModelAndView getUpdateUser(@RequestParam(name = "userId") int id) {
		ModelAndView modelAndView = new ModelAndView();
		User user = userService.findUserById(id);
		user_id = id;
		emailid = user.getEmail();
		modelAndView.addObject("user", user);
		modelAndView.addObject("allRoles", list.getRoles());
		modelAndView.setViewName("/admin/UpdateUser");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/UpdateUser", method = RequestMethod.POST)
	public ModelAndView setUpdateUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allRoles", list.getRoles());
		if (user.getEmail() == emailid)
			;
		User userExists = userService.findUserByEmail(user.getEmail());
		if (!emailid.equalsIgnoreCase(user.getEmail())) {
			if (userExists != null) {
				bindingResult.rejectValue("email", "error.user",
						"There is already a user registered with the email provided");
			}
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/UpdateUser");
		} else {
			userService.updateUser(user, user_id);
			modelAndView.addObject("successMessage", "user has been updated.");
			modelAndView.addObject("user", user);
			modelAndView.addObject("allRoles", list.getRoles());
			modelAndView.setViewName("admin/UpdateUser");
		}
		return modelAndView;
	}

	// Request for Updating IPC Codes

	@RequestMapping(value = { "/edit_ipc" }, method = RequestMethod.GET)
	public ModelAndView getUpdateipc(@RequestParam(name = "sectionId") String section) {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = userService.findBySection(section);
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("allkeywords", list.getKeywords());
		modelAndView.setViewName("/admin/UpdateIpcCode");
		return modelAndView;
	}

	@RequestMapping(value = "/admin/UpdateIpcCode", method = RequestMethod.POST)
	public ModelAndView setUpdateCode(@Valid @ModelAttribute("ipc") IpcCode ipc, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("allkeywords", list.getKeywords());
		IpcCode ipcExists = userService.findBySection(ipc.getSection());
		if (ipcExists == null) {
			bindingResult.rejectValue("section", "error",
					"");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("admin/UpdateIpcCode");
		} else {
			userService.saveIpcCode(ipc);
			modelAndView.addObject("successMessage", "Ipc Code has Been updated Succesfully.");
			modelAndView.addObject("allkeywords", list.getKeywords());
			modelAndView.addObject("ipc", ipc);
			modelAndView.setViewName("/admin/UpdateIpcCode");
		}
		return modelAndView;
	}

	// Request for deleting IPC codes

	@RequestMapping(value = { "/delete_ipc" }, method = RequestMethod.GET)
	public ModelAndView getDeleteIpcCode(@RequestParam(name = "sectionId") String section) {
		ModelAndView modelAndView = new ModelAndView();
		userService.deleteipcBySection(section);
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("ipccode", userService.findAllIpc());
		modelAndView.setViewName("/admin/ShowIpcCode");
		return modelAndView;
	}

	// Request for deleting a user

	@RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
	public ModelAndView setDeleteUser(@RequestParam(name = "userId") int id) {
		ModelAndView modelAndView = new ModelAndView();
		userService.deleteUserById(id);
		modelAndView.addObject("user", userService.find());
		modelAndView.setViewName("admin/UserInfo");
		return modelAndView;

	}

	// Request for showing IPC codes

	@RequestMapping(value = "/admin/ShowIpcCodes", method = RequestMethod.GET)
	public ModelAndView getSearchIpcCode() {
		ModelAndView modelAndView = new ModelAndView();
		IpcCode ipc = new IpcCode();
		modelAndView.addObject("ipc", ipc);
		modelAndView.addObject("ipccode", userService.findAllIpc());
		modelAndView.setViewName("/admin/ShowIpcCode");
		return modelAndView;
	}
}
