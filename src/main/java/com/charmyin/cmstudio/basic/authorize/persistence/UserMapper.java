package com.charmyin.cmstudio.basic.authorize.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.basic.initial.SQLMapper;

/**
 * Mybatis Mapper Interface used for user operation
 * @author charmyin
 *
 */
@SQLMapper
public interface UserMapper {
	
	/**
	 * Get all users from user role 
	 * @return
	 */
	public List<User> getAllUser();
	
	/**
	 * Get user by the conditions contained by params "user", it use "=" in where condition
	 * @param user User instance which contains the question conditions.
	 * @return
	 */
	public List<User> getUserEqual(User user);
	
	/**
	 * Get user by the conditions contained by params "user", it use "like" in where condition
	 * @param user User instance which contains the conditions.
	 * @return
	 */
	public List<User> getUserLike(User user);
	
	/**
	 * Get user by user id
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM SHIRO_USER WHERE id=#{id, jdbcType=BIGINT}")
	public User getUserById(int id);
	
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
	public void deleteUser(int id);
}
