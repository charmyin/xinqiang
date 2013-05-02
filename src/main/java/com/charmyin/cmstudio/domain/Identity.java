package com.charmyin.cmstudio.domain;

/**
 * Mybatis Domain object to access Identiry information in database
 * @author charmyin
 *
 */
public class Identity {
	private static final long serialVersionUID = 1L;
	private int id;
	private String userid;
	private String salt;
	private String created;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
