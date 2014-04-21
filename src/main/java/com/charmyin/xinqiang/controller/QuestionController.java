package com.charmyin.xinqiang.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.vo.Organization;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.web.utils.pagination.page.Pagination;
import com.charmyin.cmstudio.web.utils.pagination.page.PaginationResultVO;
import com.charmyin.xinqiang.persistence.QuestionMapper;
import com.charmyin.xinqiang.vo.Question;

/**
 * Manage Questions
 * @author Yincm
 *
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Resource
	private QuestionMapper questionMapper;
	
	@RequestMapping(value="/{subjectType}/manage", method=RequestMethod.GET)
	public String manage(@PathVariable String subjectType, Model model){
		model.addAttribute("subjectType", subjectType);
		return "/xinqiang/questionManage/questionManage";
	}

	@RequestMapping(value="/{subjectType}/all", method=RequestMethod.GET)
	@ResponseBody
	public PaginationResultVO findAllQuestionsByType(@PathVariable String subjectType, Pagination page){
		//model.addAttribute("subjectType", subjectType);
		Question question = new Question();
		question.setPageVO(page);
		question.setType(subjectType);
		List<Question> list = questionMapper.findAllQuestionsEqual(question);
		PaginationResultVO prv = new PaginationResultVO();
		prv.setTotal(String.valueOf(question.getPageVO().getTotalRows()));
		prv.setRows(list);
		return prv;
	}
	
	/**
	 * Insert the question committed from client
	 * @return 
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveQuestion( @Valid Question question, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			questionMapper.insertQuestion(question);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	 
	}
	
	
	/**
	 * Update the question committed from client
	 * @return 
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String updateQuestion( @Valid Question question, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			questionMapper.updateQuestion(question);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	 
	}
	
	
}
