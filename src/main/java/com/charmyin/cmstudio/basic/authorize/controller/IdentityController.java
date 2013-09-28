package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.domain.Identity;
import com.charmyin.cmstudio.basic.authorize.form.LoginForm;
import com.charmyin.cmstudio.basic.authorize.form.RegistrationForm;
import com.charmyin.cmstudio.basic.authorize.service.IdentityService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;

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
	   * If have loged in then go to the basic/index page
	   * 
	   * @param locale
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET, value = { "/login", "/identity" })
	  public String login(Locale locale, Model model) {
	    Subject currentUser = SecurityUtils.getSubject();
	    if (currentUser.isAuthenticated()) {
	    	return "basic/index";
	    	
	    }
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
	  public String register(@Valid RegistrationForm registration, BindingResult result) {
	    logger.trace("Entering Register");

	    if (result.hasErrors()) {
	    	return "basic/main/index";
	    }

	    Identity identity = this.identityService.registerIdentity(registration);
//
//	    model.addAttribute("registration", registration);
//	    model.addAttribute("identity", identity);
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
	  public @ResponseBody Map authenticateUser( @Valid LoginForm loginForm, BindingResult result) {
	    logger.trace("Entering Authenticate");
	    
	    Map<String,Object> map = new HashMap<String, Object>();
	    
	    if (result.hasErrors()) {
	    	String errorInfo = 	JSRErrorUtil.getErrorString(result);
	        map.put("status", "error");
	        map.put("msg", errorInfo);
	    	return map ;
	    }

	    UsernamePasswordToken token = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassphrase());

	    // <Remember Me>built-in, just do this
	    // TODO: Make this a user option instead of hard coded in.
	    token.setRememberMe(true);
	    
	    Subject currentUser = SecurityUtils.getSubject();
	    
	    //Authenticate the user
	    authenticateUserByToken(currentUser, token, map);

	    if (currentUser.isAuthenticated()) {
	      //Set user menus to session
	      map.put("status", "ok");
	      return map;
	    }
	    
        map.put("status", "error");
        
        return map;
	}
	  
	/**
	 * Authenticate the login user, catch the exception, log and return it~ 
	 * @param currentUser
	 * @param token
	 * @param resultMap
	 */
	private void authenticateUserByToken(Subject currentUser, UsernamePasswordToken token, Map<String, Object> resultMap) {
		try {
	        currentUser.login(token);
	        logger.info("AUTH SUCCESS");
	    } catch (UnknownAccountException ue) {
	    	logger.info("AUTH MSSG: " + ue.getMessage());
	    	resultMap.put("msg", "用户名不存在！");
	    } catch (IncorrectCredentialsException ice){
	    	logger.info("AUTH MSSG: " + ice.getMessage());
	    	resultMap.put("msg", "用户名或密码错误!");
	    } catch (LockedAccountException lae){
	    	logger.info("AUTH MSSG: " + lae.getMessage());
	    	resultMap.put("msg", "当前账号已被锁定!请稍后再试！");
	    } catch (ExpiredCredentialsException ece){
	    	logger.info("AUTH MSSG: " + ece.getMessage());
	    	resultMap.put("msg", "当前用户密码已经过期，请及时更改！");
	    } catch (ExcessiveAttemptsException eae){
	    	logger.info("AUTH MSSG: " + eae.getMessage());
	    	resultMap.put("msg", "当前账号登录尝试过于频繁，请稍后再试！");
	    } catch (ConcurrentAccessException cae){
	    	logger.info("AUTH MSSG: " + cae.getMessage());
	    	resultMap.put("msg", "当前系统不允许多点登录，请先退出之前登录的系统！");
	    } catch (AuthenticationException ae){
	    	logger.warn("AUTH MSSG: " + ae.getMessage());
	    	resultMap.put("msg", "未知原因，登录失败！"+ae.getMessage());
	    }
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
		  return "basic/authorize/login";
	  }
	  
	  /**
	   * Show the Login form
	   * 
	   * @param locale
	   * @param model
	   * @return
	   */
	  @RequestMapping(method = RequestMethod.GET, value = { "/unauthorized"})
	  public String unauthorized(Locale locale, Model model) {
	    logger.trace("Unauthorized user");
	    return "basic/errorpage/unauthorized";
	  }
	
}
