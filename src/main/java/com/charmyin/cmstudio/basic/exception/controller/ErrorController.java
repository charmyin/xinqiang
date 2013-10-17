package com.charmyin.cmstudio.basic.exception.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handle the Exceptions redirect by the <error-page> in web.xml
 * 
 * @author YinCM
 * @since 2013-10-17 9:20:07
 */
@Controller
public class ErrorController {

	@RequestMapping(value = "/clientError")
	public ModelAndView  handle(HttpServletRequest request, Model model) {
		//Get status code
		String status_code = request.getAttribute("javax.servlet.error.status_code").toString();
		model.addAttribute("status_code", status_code);
		//request.getAttribute("javax.servlet.forward.request_uri");
	    String origialUri = (String) request.getAttribute(RequestDispatcher.FORWARD_CONTEXT_PATH);
		 
		return new ModelAndView("/basic/errorpage/4xx","origialUri", origialUri);
		
	}
	
	@RequestMapping(value = "/throwError", produces = "application/json")
	@ResponseBody
	public Map<String, Object> handleReturnJson(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		//Get status code
		map.put("status", request.getAttribute("javax.servlet.error.status_code"));
		
		Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
		if(throwable!=null){
			map.put("errorClassName", throwable.getClass().getName());
			map.put("errormsg", throwable.getMessage());
		}else{
			map.put("errormsg", "Unknown Server Error");
		}
		
		return map;
	}

}
