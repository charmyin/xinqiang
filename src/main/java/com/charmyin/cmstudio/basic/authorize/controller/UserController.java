package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.form.UserForm;
import com.charmyin.cmstudio.basic.authorize.service.IdentityService;
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
	
	//TODO use property files
	//初始化的密码和密码盐分,密码默认为“111111”，six “1”
	private String salt = "qpOvViSVIY7XyYMpAsJHnQ==";
	private String passphrase = "9nr6bzUO+BwcJrk8/WQl2XSPb9M10Ra53TEf6TyA9XHqdBWp3AvzjKLPkqWZx6zmARLywD6Mw5lPMYTW/uGwkQ==";
	
	private Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	UserService userService;
	
	@Resource
	IdentityService identityService;
	
	@RequestMapping(value="/manage", method=RequestMethod.GET)
	public String manage(){
		return "/basic/user/userManage";
	}
	
	@RequestMapping(value="/organizationId/{organizationId}/allUser", method=RequestMethod.GET)
	@ResponseBody
	public List<User> getUserByOrganization(@PathVariable("organizationId") Integer organizationId){
		List<User> list = userService.getUserByOrgnizationId(organizationId);
		return list;
	}
	
	@RequestMapping(value="/{userId}/roleNames", method=RequestMethod.GET)
	@ResponseBody
	public List<String> getRoleNamesByUserId(@PathVariable("userId") Integer userId){
//		SecurityUtils.getSecurityManager().
		//logger.debug(SecurityUtils.getSubject().);
		List<String> list = userService.getRoleNamesByUserId(userId);
		return list;
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveUser(@Valid UserForm userForm, BindingResult result){
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		try{
			//初始化密码
			userForm.setPassphrase(passphrase);
			userForm.setSalt(salt);
			userForm.setState(true);
			userForm.setEmail(UUID.randomUUID().toString()+"@default.com");
			userService.insertUser(userForm);
			userService.updateRoles(userForm.getId(),userForm.getRoles());
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	@Transactional
	public String updateUser(@Valid UserForm userForm, BindingResult result){
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		//如果需要初始化密码，则初始化；否则赋值为null，忽略上传的密码；
		if(userForm.getInitPassphrase()!=null && userForm.getInitPassphrase()){
			userForm.setPassphrase(passphrase);
			userForm.setSalt(salt);
		}else{
			userForm.setPassphrase(null);
			userForm.setSalt(null);
		}
		try{
			userService.updateUser((User)userForm);
			userService.updateRoles(userForm.getId(),userForm.getRoles());
		}catch(Exception e){
			logger.error(e.getMessage());
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		return ResponseUtil.getSuccessResultString();
	}
	
	
	@RequestMapping(value="/modifyPassword", method=RequestMethod.POST)
	@ResponseBody
	public String modifyPassword(HttpServletRequest request, @RequestParam("oldPW") String oldPW, @RequestParam("newPW") String newPW, @RequestParam("newPW1") String newPW1){
		//验证是否为空
		if(oldPW==null || newPW==null || newPW1==null){
			return ResponseUtil.getFailResultString("密码不允许为空！");
		}
		//验证是否过长
		if(oldPW.length()>50 || newPW.length()>50 || newPW1.length()>50 ){
			return ResponseUtil.getFailResultString("密码字符过长！");
		}
		//获取session中的用户名称
		Subject currentUser = SecurityUtils.getSubject();
		Object userInfoObj = currentUser.getSession().getAttribute("userInfo");
		if(userInfoObj==null){
			return ResponseUtil.getFailResultString("修改密码失败，登录超时或者未登录！");
		}
		//结合原有密码，验证用户有效性
		User userInfo = (User)userInfoObj;
		UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getLoginId(), oldPW);
		//如果验证未通过，返回错误信息
		try{
			currentUser.login(token);
		}catch(Exception e){
			logger.warn("Dangerous user login:"+request.getRemoteHost());
			return ResponseUtil.getFailResultString("修改密码失败，登录超时或者未登录！");
		}
		String salt = IdentityService.getSalt();
		userInfo.setSalt(salt);
		//如果验证通过，进行更新
		String ps = identityService.encodePassphrase(newPW, salt);
		userInfo.setPassphrase(ps);
		
		userService.updateUser(userInfo);
		
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

	public IdentityService getIdentityService() {
		return identityService;
	}

	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
}
