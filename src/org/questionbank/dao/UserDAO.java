package org.questionbank.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.questionbank.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO 
{
	protected static Logger logger = Logger.getLogger("dao");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDTO searchDatabase(String username) 
	{
	    logger.debug("Seraching for user in DB");
		UserDTO user=new UserDTO();
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO u WHERE u.userName = :userName");
		query.setParameter("userName", username);
		user = (UserDTO) query.uniqueResult();
		if(user!=null)
			return user;
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");
	}
	private List<UserDTO> internalDatabase() 
	{
		// Dummy database
		
		// Create a dummy array list
		List<UserDTO> users = new ArrayList<UserDTO>();
		UserDTO user = null;
		
		// Create a new dummy user
		user = new UserDTO();
		user.setUserName("john");
		// Actual password: admin
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");
		// Admin user
		user.setAccess("1");
		
		// Add to array list
		users.add(user);
		
		// Create a new dummy user
		user = new UserDTO();
		user.setUserName("jane");
		// Actual password: user
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		// Regular user
		user.setAccess("2");
		
		// Add to array list
		users.add(user);
		
		// Create a new dummy user
		user = new UserDTO();
		user.setUserName("janis");
		// Actual password: user
		user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
		// Regular user
		user.setAccess("2");
		
		// Add to array list
		users.add(user);
				
		return users;
	}
	
}
