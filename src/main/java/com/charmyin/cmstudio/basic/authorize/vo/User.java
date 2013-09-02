package com.charmyin.cmstudio.basic.authorize.vo;

import java.util.Date;

import javax.validation.constraints.Size;

/**
 * Role VO
 * @author YinCM
 * @since 2013-9-2 16:57:30
 */
public class User {	
	
	private Integer id;
	
	private String loginId;
	
	private String 	name;
 
	private String organizationId;
	 
	private String email;
 
	private String passphrase;
 
	private String salt;
 
	private Boolean state;
 
	private Date dateCreated;
 
 
	@Size(max=100, message="密码长度应小于100")
	private String remark;
	
	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
}
