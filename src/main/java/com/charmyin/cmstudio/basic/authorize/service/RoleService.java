package com.charmyin.cmstudio.basic.authorize.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.vo.Role;

/**
 * Role operation service
 * @author YinCM
 * @since  2013-9-3 10:12:00
 */
@Service
public interface RoleService {
	
	/**
	 * Get all the role items.
	 * Only the developer has this operation. 
	 * @return Role List
	 */
	public List<Role> getAllRole();
	
	/**
	 * Update one role by id
	 * @param role
	 */
	public void updateRole(Role role);
	
	/**
	 * Insert Role 
	 * @param role
	 */
	public void insertRole(Role role);
	
	/**
	 * Delete role by id array (int)
	 * @param int array Role Ids
	 */
	public void deleteRole(int[] ids);
	
	/**
	 * SearchRole by role fields
	 * @param role
	 * @return Role List
	 */
	public List<Role> searchRole(Role role);
	
	/**
	 * Get roles by organization Id
	 * @param orgId
	 * @return
	 */
	public List<Role> getRoleByOrganizationId(Integer orgId);
	
	/**
	 * Get role item by its id
	 * @param id
	 * @return Role Object
	 */
	public Role getRoleById(int id);
	
 
}
