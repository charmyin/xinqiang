package com.charmyin.xinqiang.util;

import javax.servlet.http.HttpServletRequest;

import com.charmyin.xinqiang.persistence.QuestionMapper;

/**
 * 
 * @author Yincm
 *2014-4-24 08:50:56
 */
public class UpdateContextQuestion {
	public static void updateContextQuestion(QuestionMapper questionMapper, HttpServletRequest request){
		Object subject1QuestionListObj = questionMapper.allSubject1Questions();
		request.getServletContext().setAttribute("subject1QuestionListObj", subject1QuestionListObj);
		Object subject4QuestionListObj = questionMapper.allSubject4Questions();
		request.getServletContext().setAttribute("subject4QuestionListObj", subject4QuestionListObj);
	}
	
}
