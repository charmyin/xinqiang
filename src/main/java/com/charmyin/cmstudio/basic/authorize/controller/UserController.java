package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.common.utils.ArrayUtil;
import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.web.utils.ResponseUtil;

/**
 * 用户web控制层
 * @author YinCM
 * @since 2013-9-14 11:36:00
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	UserService userService;
	
	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manage(){
		return "/basic/user/userManage";
	}
	
	@RequestMapping(value="/organizationId/{organizationId}/allUser", method=RequestMethod.POST)
	@ResponseBody
	public List<User> getUserByOrganization(@PathVariable("organizationId") Integer organizationId){
		List<User> list = userService.getUserByOrgnizationId(organizationId);
		return list;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	@ResponseBody
	public String saveUser(@Valid User user, BindingResult result){
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			userService.insertUser(user);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public String updateUser(@Valid User user, BindingResult result){
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			userService.updateUser(user);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	
	/**
	 * Delete by ids string split by ',' ; Example:"1,2,3,4,5" 
	 * @param ids eg."1,2,3,4,5"
	 * @return
	 */
	@RequestMapping(value="/deleteByIds", method=RequestMethod.POST)
	@ResponseBody
	@Transactional
	//TODO 长度异常，这里是否要去考虑,测试的时候考虑
	public Map<String, Object> deleteRoleByNames(@RequestParam("ids") String ids){
		//ids can not be null
				if(ids==null || ids.isEmpty()){
					Map<String, Object> map = ResponseUtil.getFailResultMap();
					map.put("errorMsg", "删除数据，id不允许为空！");
					return map;
				}
				
				String[] idsArrayNotEmpty = ArrayUtil.removeEmptyString(ids.split(","));
				
				int[] idsIntArray = new int[idsArrayNotEmpty.length];
				try{
					for(int i=0; i<idsArrayNotEmpty.length;i++){
						int idInt = Integer.parseInt(idsArrayNotEmpty[i]);
						idsIntArray[i] = idInt;
					}
					userService.deleteUser(idsIntArray);
				}catch(NumberFormatException ne){
					logger.error("提交id值错误!"+ne.getMessage());
					Map<String, Object> map = ResponseUtil.getFailResultMap();
					map.put("errorMsg", "提交id值错误!");
					return map;
				}catch(Exception e){
					logger.error(e.getMessage());
					Map<String, Object> map = ResponseUtil.getFailResultMap();
					map.put("errorMsg", "删除过程中出错！");
					return map;
				}
				
				return ResponseUtil.getSuccessResultMap();
	}
}
