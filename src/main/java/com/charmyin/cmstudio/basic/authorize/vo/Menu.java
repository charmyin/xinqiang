package com.charmyin.cmstudio.basic.authorize.vo;

/**
 * PO class used for users' menus
 * @author charmyin
 *
 */
public class Menu {
	
	private int id;
	private String name;
	private int parentId;
	private String linkUrl;
	private String orderNumber;
	private String remark;
	private String fullPermission;
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
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
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
