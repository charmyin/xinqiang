package com.charmyin.xinqiang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Manage Questions
 * @author Yincm
 *
 */
@Controller
@RequestMapping("/question")
public class QuestionManage {
	@RequestMapping(value="/1/manage", method=RequestMethod.GET)
	public String manage(){
		return "/basic/user/userManage";
	}
	
	
}
