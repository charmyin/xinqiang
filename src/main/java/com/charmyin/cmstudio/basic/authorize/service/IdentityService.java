package com.charmyin.cmstudio.basic.authorize.service;


import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.charmyin.cmstudio.basic.authorize.domain.Identity;
import com.charmyin.cmstudio.basic.authorize.form.RegistrationForm;
import com.charmyin.cmstudio.basic.authorize.persistence.IdentityMapper;

/**
 * Implement from IdentityService
 * @author charmyin
 *
 */
@Service
//@PropertySource used on unit test to load properties
@PropertySource("classpath:application-spring.properties")
public class IdentityService {
	
	@Autowired
	private Environment env;
	
	public void setEnv(Environment env){
		this.env = env;
	}
	
	@Autowired(required = true)
	private IdentityMapper identityMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(Identity.class);
	
	public IdentityMapper getIdentityMapper(){
		return identityMapper;
	}
	
	
	public Identity registerIdentity(RegistrationForm registration){
		
		registration.setSalt(getSalt());
		registration.setPassphrase(encodePassphrase(registration.getPassphrase(), registration.getSalt()));
		
		identityMapper.registerIdentity(registration);
		
		logger.trace("INSERTED ID = (" + registration.getId() + ")");
		return this.getIdentity(registration.getId());
	}
	
	
	public void registerIdentity(){
		RegistrationForm registration = new RegistrationForm();
		registration.setSalt(getSalt());
		registration.setPassphrase(encodePassphrase("111111", registration.getSalt()));
		System.out.println("salt="+registration.getSalt()+"  pasd:"+registration.getPassphrase());
		
		identityMapper.registerIdentity(registration);
		
		logger.trace("INSERTED ID = (" + registration.getId() + ")");
		//return this.getIdentity(registration.getId());
	}
	
	public static String getSalt(){
		return new SecureRandomNumberGenerator().nextBytes().toBase64();
	}
	
	public String getApplicationSalt(){
		return env.getProperty("shiro.applicationSalt");
	}
	
	public String getCombinedSalt(String salt){
		return env.getProperty("shiro.applicationSalt") + ":" + salt;
	}
	
	public String encodePassphrase(String rawPassphrase, String salt){
		return new Sha512Hash(rawPassphrase, getCombinedSalt(salt), getIterations()).toBase64();
	}
	
	public Integer getIterations(){
		return Integer.parseInt(env.getProperty("shiro.hashIterations"));
	}
	
	public Identity getIndentity(int id){
		logger.trace("Enter getIdentiry(" + id + ")");
		if(identityMapper == null){
			logger.debug("identityMapper didn't get autowired!");
		}
		return identityMapper.getIdentityById(id);
	}
	
	public Identity getIdentity(String userId){
		logger.trace("Enter getIdentiry(" + userId + ")");
		if(identityMapper == null){
			logger.debug("identityMapper didn't get autowired!");
		}
		return identityMapper.getIdentityByUserId(userId);
	}
	
	public Identity getIdentity(int id){
		logger.trace("Enter getIdentiry(" + id + ")");
		if(identityMapper == null){
			logger.debug("identityMapper didn't get autowired!");
		}
		return identityMapper.getIdentityById(id);
	}
	
	public void setIdentityMapper(IdentityMapper identityMapper){
		this.identityMapper = identityMapper;
	}
	
}
