package com.charmyin.cmstudio.basic.service;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.charmyin.cmstudio.basic.authorize.persistence.MenuMapper;
import com.charmyin.cmstudio.basic.authorize.vo.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml"})
public class MenuT {
	
	@Resource
	private MenuMapper menuMapper;
	
	
	@Test
	public void testInsert(){
		Menu menu = new Menu();
		menu.setName("root");
		menu.setOrderNumber(13);
		menu.setRemark("It's a link for google");
		menuMapper.insertMenu(menu);
	}

	public MenuMapper getMenuMapper() {
		return menuMapper;
	}

	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
	}
}
