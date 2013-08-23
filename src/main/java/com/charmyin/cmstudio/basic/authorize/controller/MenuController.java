package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.service.MenuService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;
import com.charmyin.cmstudio.web.utils.ResponseUtil;

/**
 * Users' menu operation.
 * @author charmyin
 * @since 2013-8-12
 */
@Controller
public class MenuController {
	
	@Resource
	MenuService menuService;

	/**
	 * Get all menu items in database
	 * @return all menu items JSON type data
	 */
	@RequestMapping("/menu/all")
	@ResponseBody
	public List<Menu> getAllMenu(){
		List<Menu> menuList = menuService.getAllMenu();
		return menuList;
	}
	
	//TODO The parentId parse exception has not been handled~
	/**
	 * Get child menu under one parent menu (Not include all the children and grand children, just the next level children)
	 * @param parentId 
	 * @return Menu items' JSON type data under the parent;  
	 */
	@RequestMapping(value="/menuparent/{parentId}/menu", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenuByParent(@PathVariable int parentId){
		List<Menu> menuList = menuService.getChildrenMenus(parentId);
		return menuList;
	}
	
	/**
	 * Insert the menu committed from client
	 * @return 
	 */
	@RequestMapping(value="/menu/save", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveMenu(HttpServletRequest request, Menu menu){
		try{
			menuService.insertMenu(menu);
		}catch(Exception e){
			e.printStackTrace();
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "保存过程中出错！");
			return map;
		}
		return ResponseUtil.getSuccessResultMap();
	}
	
	@RequestMapping(value="/menu/update", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateMenu(Menu menu){
		try{
			menuService.updateMenu(menu);
		}catch(Exception e){
			e.printStackTrace();
			Map<String, Object> map = ResponseUtil.getFailResultMap();
			map.put("errorMsg", "更新过程中出错！");
			return map;
		}
		return ResponseUtil.getSuccessResultMap();
	}
	
	/**
	 * Direct to the menuManage jsp file
	 * @return jsp file path
	 */
	@RequestMapping(value="/menu/manage")
	public String manageMenu(){
		return "basic/menu/menuManage";
	}	
	
	
	
	public MenuService getMenuService() {
		return menuService;
	}


	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
