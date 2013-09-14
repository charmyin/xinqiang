package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * 用户服务层接口
 * @author YinCM
 * @since 2013-9-14 10:59:52
 */
@Service
public interface UserService {
	
	/**
	 * Get all users from user role 
	 * @return
	 */
	public List<User> getAllUser();
 
	/**
	 * Get user by user id
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
	
	/**
	 * Get user by orgnization id
	 * @return
	 */
	public List<User> getUserByOrgnizationId(int organizationId);
	
	/**
	 * Insert a piece of user to table
	 * @param user
	 * @return
	 */
	public void insertUser(User user);
	
	/**
	 * Update user by user object which must contain id
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * Delete users by id string array
	 * @param ids
	 */
	public void deleteUser(int[] ids);
}
