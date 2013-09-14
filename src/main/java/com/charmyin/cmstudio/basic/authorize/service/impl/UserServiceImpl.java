package com.charmyin.cmstudio.basic.authorize.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.persistence.UserMapper;
import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.User;

/**
 * 用户信息数据服务实现
 * @author YinCM
 * @since 2013-9-14 11:05:19
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Resource
	UserMapper userMapper;

	@Override
	public List<User> getAllUser() {
		List<User> list = userMapper.getAllUser();
		return list;
	}

	@Override
	public User getUserById(int id) {
		User user = userMapper.getUserById(id);
		return user;
	}

	@Override
	public List<User> getUserByOrgnizationId(int organizationId) {
		User user = new User();
		user.setOrganizationId(organizationId);
		List<User> list = userMapper.getUserEqual(user);
		return list;
	}

	@Override
	public void insertUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	@Override
	public void deleteUser(int[] ids) {
		for(int id : ids){
			userMapper.deleteUser(id);
		}
	}

	
	public UserMapper getUserMapper() {
		return userMapper;
	}

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
}
