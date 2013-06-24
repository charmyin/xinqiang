package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.domain.Identity;
import com.charmyin.cmstudio.basic.authorize.form.LoginForm;
import com.charmyin.cmstudio.basic.authorize.form.RegistrationForm;
import com.charmyin.cmstudio.basic.authorize.service.IdentityService;

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

	  /**
	   * Show the Login form
	   * 
	   * @param locale
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET, value = { "/login", "/identity" })
	  public String login(Locale locale, Model model) {
	    logger.trace("Entering login");
	    return "basic/authorize/login";
	  }

	  /**
	   * Shows the registration form.
	   * 
	   * @param locale
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET, value = { "/registration" })
	  public String registration(Locale locale, Model model) {
	    logger.trace("Entering Registration");
	    model.addAttribute("registration", new RegistrationForm());
	    return "identity/registration";
	    //return "basic/main/index";
	  }

	  /**
	   * Handles the submission from the registration form.
	   * 
	   * @param registration
	   * @param result
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.POST, value = { "/register" })
	  public String register(@ModelAttribute(value = "registration") @Valid RegistrationForm registration, BindingResult result, Model model) {
	    logger.trace("Entering Register");

	    if (result.hasErrors()) {

	      //return "identity/registration";
	    	return "basic/main/index";
	    }

	    Identity identity = this.identityService.registerIdentity(registration);

	    model.addAttribute("registration", registration);
	    model.addAttribute("identity", identity);
	    //return "identity/register";
	    return "basic/main/index";
	  }

	  /**
	   * Logs the user in, handles submission from the login form.
	   * 
	   * @param loginForm
	   * @param result
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.POST, value = { "/authenticate" })
	  public @ResponseBody Map register(@ModelAttribute(value = "loginForm") @Valid LoginForm loginForm, BindingResult result, Model model) {
	    logger.trace("Entering Authenticate");
	    
	    Map<String,Object> map = new HashMap();
	    
	    if (result.hasErrors()) {
	    //	List<ObjectError> errorlist = result.getAllErrors();
	    	List<FieldError> errors = result.getFieldErrors();
	    	String errorInfo = "";
	        for (FieldError error : errors ) {
	        	errorInfo += error.getObjectName() + " - " + error.getDefaultMessage();
	        }
	       
	       
	        map.put("status", "error");
	        map.put("data", errorInfo);
	    	return map ;
	      //return "identity/login";
	    	//return "basic/main/index";
	    }

	    UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassphrase());

	    // �Remember Me�?built-in, just do this
	    // TODO: Make this a user option instead of hard coded in.
	    token.setRememberMe(true);

	    Subject currentUser = SecurityUtils.getSubject();

	    try {
	      currentUser.login(token);
	      logger.info("AUTH SUCCESS");
	    } catch (AuthenticationException ae) {
	      logger.info("AUTH MSSG: " + ae.getMessage());
	    }

	    if (currentUser.isAuthenticated()) {
	      map.put("status", "ok");
	      return map;
	    }

	    //return "identity/login";
	     
        map.put("status", "error");
        map.put("msg", "用户名或密码错误!");
        
        return map;
	  }
	  
	  
	  @RequestMapping(method = RequestMethod.GET, value = { "/logout" })
	  public String logout(Locale locale, Model model){
		  Subject currentUser = SecurityUtils.getSubject();
		  try{
			  currentUser.logout();
		  }catch(Exception e){
			  e.printStackTrace();
		  }
		  model.addAttribute("loginForm", new LoginForm());
		  //return "identity/login";
		  return "basic/main/index";
		  		  
	  }
	
}
