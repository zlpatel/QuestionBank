package org.questionbank.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

	protected static Logger logger = Logger.getLogger("dao");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	//Method to retrieve user by a username
	@Override
	public UserDTO fetchUserByUserName(String username) {

		Session session = null;
		UserDTO user = null;
		try {
			session = sessionFactory.getCurrentSession();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> getAllStudents() {
		List<UserDTO> students = null;
		logger.debug("Request to get all students");
		students=sessionFactory.getCurrentSession().createCriteria(UserDTO.class).add(Restrictions.eq("access","2")).list();
		return students;
		
	}
}
