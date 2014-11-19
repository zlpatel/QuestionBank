package org.questionbank.daoImpl;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

	protected static Logger logger = Logger.getLogger("dao");
	
	@Autowired
	private SessionFactory factory;
	
	//Method to retrieve user by a username
	@Override
	public UserDTO fetchUserByUserName(String username) {

		Session session = null;
		UserDTO user = null;
		try {
			session = factory.getCurrentSession();
			user = (UserDTO)session.get(UserDTO.class, username);
			return user;
		}
		catch(Exception e){
			e.printStackTrace();
			return user;
		}
		finally{
			
		}
	}
}
