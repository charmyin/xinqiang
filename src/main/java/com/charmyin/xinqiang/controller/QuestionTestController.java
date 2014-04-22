package com.charmyin.xinqiang.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.utils.NumberUtils;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.xinqiang.persistence.QuestionMapper;
import com.charmyin.xinqiang.persistence.ScoreMapper;
import com.charmyin.xinqiang.vo.Question;
import com.charmyin.xinqiang.vo.Score;

/**
 * Manage Questions
 * @author Yincm
 *
 */
@Controller
@RequestMapping("/questionTest")
public class QuestionTestController {
	
	@Resource
	private QuestionMapper questionMapper;
	
	@Resource
	private ScoreMapper scoreMapper;
	
	@RequestMapping(value="/{subjectType}/welcome", method=RequestMethod.GET)
	public String manage(@PathVariable String subjectType, Model model){
		model.addAttribute("subjectType", subjectType);
		return "/xinqiang/questionManage/questionWelcome";
	}
	
	@RequestMapping(value="/{subjectType}/test", method=RequestMethod.GET)
	public String manage(@PathVariable String subjectType, Model model, HttpServletRequest request){
		model.addAttribute("subjectType", subjectType);
		Subject currentUser = SecurityUtils.getSubject();
		//准备所有的试题数据至appcontext中
		Object subject4QuestionListObj;
		Object subject1QuestionListObj;
		int questionCount=0;
		if(subjectType.equals("1")){
			subject1QuestionListObj = request.getServletContext().getAttribute("subject1QuestionListObj");
			if(subject1QuestionListObj==null){
				subject1QuestionListObj = questionMapper.allSubject1Questions();
				request.getServletContext().setAttribute("subject1QuestionListObj", subject1QuestionListObj);
			}
			questionCount =( (List)subject1QuestionListObj).size();
			
			//存储1-1000的题目数索引，每考一题，将索引减去一个，再在剩余的数中选取随机数，作为下一题。
			List<Integer> questionIndexList = new ArrayList<Integer>();
			for(int i=0; i<questionCount; i++){
				questionIndexList.add(i);
			}
			currentUser.getSession().setAttribute("quesion1ContainerList", questionIndexList);
			
		}else if(subjectType.equals("4")){
			subject4QuestionListObj = request.getServletContext().getAttribute("subject4QuestionListObj");
			if(subject4QuestionListObj==null){
				subject4QuestionListObj = questionMapper.allSubject4Questions();
				request.getServletContext().setAttribute("subject4QuestionListObj", subject4QuestionListObj);
			}
			questionCount =( (List)subject4QuestionListObj).size();
			//存储1-1000的题目数索引，每考一题，将索引减去一个，再在剩余的数中选取随机数，作为下一题。
			List<Integer> questionIndexList = new ArrayList<Integer>();
			for(int i=0; i<questionCount; i++){
				questionIndexList.add(i);
			}
			currentUser.getSession().setAttribute("quesion4ContainerList", questionIndexList);
		}else{
			return "";
		}
	//	currentUser.getSession().setAttribute("questionIndexList", questionIndexList);
		return "/xinqiang/questionManage/questionTest";
	}
  
	@RequestMapping(value="/{subjectType}/randomQuestion", method=RequestMethod.GET)
	@ResponseBody
	public Question getQuestion(@PathVariable String subjectType, Model model, HttpServletRequest request){
		
		List<Integer> quesion1ContainerList;
		List<Integer> quesion4ContainerList;
		Question question=null;
		Subject currentUser = SecurityUtils.getSubject();
		//1,2,3,4,5,6.....1000
		//1,2,3,4,5,6...33,...100
		if(subjectType.equals("1")){
			List<Question> subject1QuestionListObj =(List<Question>) (request.getServletContext().getAttribute("subject1QuestionListObj"));
			quesion1ContainerList =  (List<Integer>)(currentUser.getSession().getAttribute("quesion1ContainerList"));
			int randomNum =  quesion1ContainerList.size();
			randomNum = NumberUtils.generateRandomInt(randomNum);
			question = subject1QuestionListObj.get(randomNum);
			//移出容器中索引数据
			quesion1ContainerList.remove(randomNum);
		}else if(subjectType.equals("4")){
			List<Question> subject4QuestionListObj =(List<Question>) (request.getServletContext().getAttribute("subject4QuestionListObj"));
			quesion4ContainerList =  (List<Integer>)(currentUser.getSession().getAttribute("quesion4ContainerList"));
			int randomNum =  quesion4ContainerList.size();
			randomNum = NumberUtils.generateRandomInt(randomNum);
			question = subject4QuestionListObj.get(randomNum);
			//移出容器中索引数据
			quesion4ContainerList.remove(randomNum);
		}
		return question;
	}
	
}
