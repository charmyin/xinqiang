package com.charmyin.cmstudio;

import java.util.Locale;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.charmyin.cmstudio.form.LoginForm;
import com.charmyin.cmstudio.form.RegistrationForm;
import com.charmyin.cmstudio.service.IdentityService;

/**
 * Handle the login authority requests of this program
 * @author charmyin
 *
 */
@Controller
@RequestMapping(value = "/identity")
public class IdentityController {
	@Autowired(required = true)
	private IdentityService identityService;
	
	private Validator validator;
	
	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	private static final Logger logger = LoggerFactory.getLogger(IdentityController.class);
	
	@RequestMapping(method = RequestMethod.GET, value = {"/login", "/identity"})
	public String login(Locale locale, Model model){
		logger.trace("Entering login");
		model.addAttribute("loginForm", new LoginForm());
		return "identity/registration";
	}
	
	
	public String registration(Locale locale, Model model){
		logger.trace("Entering Registration");
		model.addAttribute("registration", new RegistrationForm());
		return "identity/registration";
	}
	
}
