package org.questionbank.service;

import org.questionbank.dao.UserDAO;


public interface CustomUserDetailsService {

	UserDAO getUserDao() throws Exception;
	void setUserDao(UserDAO userDao) throws Exception;
	String getUserFullName(String username) throws Exception;

}