package com.charmyin.cmstudio.basic.authorize.vo;

/**
 * PO class used for users' menus
 * @author charmyin
 *
 */
public class Menu {
	
	//Menu id
	private int id;
	
	//Menu name used to show on the tree
	private String name;
	
	//Parent id
	private int parentId;
	
	//The url which is linked to the page
	private String linkUrl;
	
	//The order of the menu in the tree list.Maybe it's better to be "int"
	private int orderNumber;
	
	//The remark to add description of this menu 
	private String remark;
	
	//Full permission to get this menu can read and write
	private String fullPermission;
	
	//Read permission to get this menu just can read
	private String readPermission;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFullPermission() {
		return fullPermission;
	}
	public void setFullPermission(String fullPermission) {
		this.fullPermission = fullPermission;
	}
	public String getReadPermission() {
		return readPermission;
	}
	public void setReadPermission(String readPermission) {
		this.readPermission = readPermission;
	}
	
	
}
