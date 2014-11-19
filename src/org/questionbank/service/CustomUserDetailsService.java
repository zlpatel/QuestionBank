package org.questionbank.service;

import org.questionbank.dao.UserDAO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public interface CustomUserDetailsService {

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException;

	UserDAO getUserDao();

	void setUserDao(UserDAO userDao);

}