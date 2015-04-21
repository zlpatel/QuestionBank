package org.questionbank.serviceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.questionbank.dao.UserDAO;
import org.questionbank.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A custom service for retrieving users from a custom datasource, such as a database.
 * <p>
 * This custom service must implement Spring's {@link UserDetailsService}
 */
@Transactional
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService,CustomUserDetailsService
{
protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private UserDAO userDao;
 
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
 		org.questionbank.dto.UserDTO user;
		try {
			user = userDao.fetchUserByUserName(username);
		} catch (Exception e) {
			throw new RuntimeException();
		}
		List<GrantedAuthority> authorities = buildUserAuthority(user.getAccess());
 		return buildUserForAuthentication(user, authorities);
 	}
	@Transactional
	@Override
	public String getUserFullName(String username) throws Exception
	{
		return userDao.fetchUserByUserName(username).getName();
	}
	private User buildUserForAuthentication(org.questionbank.dto.UserDTO user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUserName(), 
			user.getPassword(), true, 
                        true, true, true , authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority(String access) {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		String userRole="";
		if(access.compareTo("1")==0){
			userRole="ROLE_ADMIN";
		}
		else if(access.compareTo("2")==0){
			userRole="ROLE_USER";
		}
		// Build user's authorities
			setAuths.add(new SimpleGrantedAuthority(userRole));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
	@Transactional
	@Override
	public UserDAO getUserDao() throws Exception{
		return userDao;
	}
 
	@Transactional
	@Override
	public void setUserDao(UserDAO userDao) throws Exception{
		this.userDao = userDao;
	}
 
}




