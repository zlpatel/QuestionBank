package org.questionbank.daoImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

	protected static Logger logger = Logger.getLogger("user dao");
	
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
		logger.debug("Request to get list of all students in UserDAO");
		List<UserDTO> students = null;
		students=sessionFactory.getCurrentSession().createCriteria(UserDTO.class).add(Restrictions.eq("access","2")).list();
		return students;
	}

	@Override
	public UserDTO getThisStudent(String userName) {
		logger.debug("Request to find a given student in UserDAO");
		UserDTO userDTO=new UserDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM UserDTO u WHERE u.userName = :userName");
		query.setString("userName", userName);
		userDTO = (UserDTO) query.uniqueResult();
		if(userDTO!=null)
			return userDTO;
		logger.error("Student does not exist!");
		throw new RuntimeException("Student does not exist!");
	}
}
