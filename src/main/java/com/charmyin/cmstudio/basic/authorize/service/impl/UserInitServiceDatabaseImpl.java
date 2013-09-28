package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.MenuMapper;
import com.charmyin.cmstudio.basic.authorize.persistence.RoleMapper;
import com.charmyin.cmstudio.basic.authorize.persistence.UserMapper;
import com.charmyin.cmstudio.basic.authorize.service.UserInitService;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;
import com.charmyin.cmstudio.basic.authorize.vo.Role;
import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * User initial service by using database~
 * @author YinCM
 * @since
 */
@Service
public class UserInitServiceDatabaseImpl implements UserInitService {
	
	@Resource
	private UserMapper userMapper; 
	
	@Resource
	private RoleMapper roleMapper;
	
	@Resource
	private MenuMapper menuMapper;

	@Override
	public Set<String> getRoleNamesByUserName(String userName) {
		//Get user id
		User user = userMapper.getUserByName(userName);
		//Get role name list
		List<String> list = userMapper.getRoleNamesByUserId(user.getId());
		//Change the roleName arrayList to hashSet
		Set<String> set = new HashSet<String>(list);
		return set;
	}

	@Override
	public Set<String> getMenuIdsByUserRoleNames(Set<String> roleNames) {
		//Convert set to list, convenient for iterating
		List<String> list = new ArrayList<String>(roleNames);
		//Role menuId in strings
		StringBuilder roleMenus = new StringBuilder();
		for(String roleName : list){
			Role role = roleMapper.getRoleByName(roleName);
			if(role.getMenu()!=null && !role.getMenu().trim().equals("")){
				roleMenus.append(role.getMenu()).append(",");
			}
		}
		String[] roleMenuIdArray = roleMenus.toString().split(",");
		
		//Menu id String set
		Set<String> menuIdSet = new HashSet<String>(); 
		for(String menuId : roleMenuIdArray){
			if(!menuId.trim().equals("")){
				menuIdSet.add(menuId);
			}
		}
		//TODO 2013-9-28 22:39:47
		return menuIdSet;
	}
	
	

	@Override
	public List<Menu> getMenusByMenuIds(Set<String> menuIdSet) {
		Integer[] menuIds = menuIdSet.toArray(new Integer[0]);
		List<Menu> menuList = new ArrayList<Menu>();
		for(Integer menuId : menuIds){
			Menu menu = menuMapper.getMenuById(menuId);
			if(menu!=null){
				menuList.add(menu);
			}
		}
		return menuList;
	}
	
	
	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	
}
