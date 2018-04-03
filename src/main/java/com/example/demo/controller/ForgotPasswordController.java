package com.example.demo.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.demo.model.User;
import com.example.demo.service.EmailServices;
import com.example.demo.service.UserService;

@RestController
public class ForgotPasswordController {

	@Autowired
	private UserService userService;
	@Autowired
	private EmailServices emailServices;

	// Display forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public ModelAndView displayForgotPasswordPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forgot");
		return modelAndView;

	}

	// Process form submission from forgotPassword page
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ModelAndView processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request) {

		// Lookup user in database by e-mail
		User users = userService.findUserByEmail(userEmail);
		ModelAndView modelAndView = new ModelAndView();
		if (users == null) {
			modelAndView.addObject("successMessage", "We didn't find an account for that e-mail address.");

		} else {

			// Generate random 36-character string token for reset password
			// User user = new User();
			users.setResetToken(UUID.randomUUID().toString());

			// Save token to database
			userService.saveUser(users);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			// Email message
			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("sanketsahu474@gmail.com");
			passwordResetEmail.setTo(users.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + ":8097/reset?token="
					+ users.getResetToken());

			emailServices.sendEmail(passwordResetEmail);

			// Add success message to view
			modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
		}

		modelAndView.setViewName("forgot");
		return modelAndView;
	}

	private String tokens;

	// Display form to reset password
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public ModelAndView displayResetPasswordPage(@RequestParam("token") String token) {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		tokens = token;
		modelAndView.addObject("user", user);
		user = userService.findUserByResetToken(token);
		if (user != null) { // Token found in DB
			modelAndView.addObject("resetToken", token);
		} else { // Token not found in DB
			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
		}

		modelAndView.setViewName("reset");
		return modelAndView;
	}

	// Process reset password form
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ModelAndView setNewPassword(@RequestParam Map<String, String> requestParams, RedirectAttributes redir) {
		ModelAndView modelAndView = new ModelAndView();
		// Find the user associated with the reset token
		User users = userService.findUserByResetToken(tokens);
		modelAndView.addObject("user", users);
		if (users != null) {
			User resetUser = userService.findUserByResetToken(tokens);
			modelAndView.addObject("user", resetUser);
			// Set new password
			resetUser.setPassword(requestParams.get("password"));

			// Set the reset token to null so it cannot be used again
			resetUser.setResetToken(null);

			// Save user
			userService.saveUser(users);

			// In order to set a model attribute on a redirect, we must use
			// RedirectAttributes
			redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");
			// modelAndView.setViewName("reset");
			modelAndView.setViewName("redirect:login");
			return modelAndView;
		} else {
			users = new User();
			modelAndView.addObject("user", users);
			modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
			modelAndView.setViewName("reset");
		}

		return modelAndView;
	}

	// Going to reset page without a token redirects to login page
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:login");
	}
}
