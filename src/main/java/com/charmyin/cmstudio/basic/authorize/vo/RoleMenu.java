package com.charmyin.cmstudio.basic.authorize.vo;

/**
 * VO for table role-menu 
 * @author YinCM
 * @since
 */
public class RoleMenu {
	//角色名称
	private String roleName;
	//菜单id
	private int menu_id;
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	
}
