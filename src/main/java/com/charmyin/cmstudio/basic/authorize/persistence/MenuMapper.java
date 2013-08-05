package com.charmyin.cmstudio.basic.authorize.persistence;

import java.util.List;

import com.charmyin.cmstudio.basic.authorize.vo.Menu;

/**
 * Mybatis Mapper Interface used for menu operation
 * @author charmyin
 *
 */
public interface MenuMapper {
	
	/**
	 * Get all menus from menu role 
	 * @return
	 */
	public List<Menu> getAllMenu();
	
	/**
	 * Get menu by the conditions contained by params "menu", it use "=" in where condition
	 * @param menu Menu instance which contains the question conditions.
	 * @return
	 */
	public List<Menu> getMenuEqual(Menu menu);
	
	/**
	 * Get menu by the conditions contained by params "menu", it use "like" in where condition
	 * @param menu Menu instance which contains the conditions.
	 * @return
	 */
	public List<Menu> getMenuLike(Menu menu);
}
