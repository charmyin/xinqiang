package com.charmyin.cmstudio.basic.authorize.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.charmyin.cmstudio.basic.authorize.service.MenuService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;

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
