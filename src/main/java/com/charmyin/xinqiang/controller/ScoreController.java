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
 * Manage Score
 * @author Yincm
 *
 */
@Controller
@RequestMapping("/score")
public class ScoreController {
	
	@Resource
	private ScoreMapper scoreMapper;
	
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String manage(Model model){
		return "xinqiang/scoreManage/scoreView";
	}
	
	/**
	 * Insert the score committed from client
	 * @return 
	 */
	@RequestMapping(value="/saveScore", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveQuestion( @Valid Score score, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		Subject currentUser = SecurityUtils.getSubject();
		User user = (User)currentUser.getSession().getAttribute("userInfo");
		score.setUserId(user.getId());
		
		Date date = new Date();
		score.setTestTime(date);
		
		try{
			scoreMapper.insertScore(score);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	@RequestMapping(value="/{userId}/{type}/scorelist", method=RequestMethod.GET)
	@ResponseBody
	//public List<Score> findScoreByUserIdAndType(@PathVariable String subjectType){
	public List<Score> findScoreByUserIdAndType(Score score){
		//model.addAttribute("subjectType", subjectType);
		List<Score> list = scoreMapper.findScoreByUserIdAndType(score);
		return list;
	}
	//findScoreByUserIdAndType
	       
	
}
