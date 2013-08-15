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

	@RequestMapping("/menu/all")
	@ResponseBody
	public List<Menu> getAllMenu(){
		List<Menu> menuList = menuService.getAllMenu();
		menuList.remove(0);
		return menuList;
	}
	
	//TODO The parentId parse exception has not been handled~
	@RequestMapping(value="/menuparent/{parentId}/menu", method=RequestMethod.GET)
	@ResponseBody
	public List<Menu> getMenuByParent(@PathVariable int parentId){
		List<Menu> menuList = menuService.getChildrenMenus(parentId);
		return menuList;
	}
	
	
	public MenuService getMenuService() {
		return menuService;
	}


	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
}
