package com.charmyin.cmstudio.basic.authorize.persistence;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.charmyin.cmstudio.basic.authorize.domain.Identity;
import com.charmyin.cmstudio.basic.authorize.form.RegistrationForm;

/**
 * Mybatis Mapper Interface used for User Identity
 * @author charmyin
 *
 */
@Component
public interface IdentityMapper {
	
	public Identity getIdentityById(@Param("id") int id);
	
	public Identity getIdentityByUserId(@Param("userId") String userId);
	
	public Integer registerIdentity(RegistrationForm registration);
}
