package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import com.charmyin.cmstudio.basic.authorize.vo.Menu;

public interface HomeService {
	
	/**
	 * Get all the menu items.
	 * Only the developer has this operation. 
	 * @return Menu List
	 */
	public List<Menu> getAllMenu();
	
	/**
	 * Get the menu owned by a role
	 * @return Menu List
	 */
	public List<Menu> getMenuByRole();
	
	/**
	 * Get the menus owned by a user. (A user has several roles~)
	 * @return Menu List
	 */
	public List<Menu> getMenuByUser();
}
