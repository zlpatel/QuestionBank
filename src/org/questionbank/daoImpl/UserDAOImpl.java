package org.questionbank.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.UserDTO;
import org.springframework.stereotype.Repository;

/**
 * A custom DAO for accessing data from the database.
 *
 */
@Repository
public class UserDAOImpl implements UserDAO{

	protected static Logger logger = Logger.getLogger("dao");
	
	/* (non-Javadoc)
	 * @see org.questionbank.daoImpl.UserDAO#searchDatabase(java.lang.String)
	 */
	@Override
	public UserDTO searchDatabase(String username) {
		// Retrieve all users from the database
		List<UserDTO> users = internalDatabase();
		
		// Search user based on the parameters
		for(UserDTO dbUser:users) {
			if ( dbUser.getUserName().equals(username)  == true ) {
				logger.debug("User found");
				// return matching user
				return dbUser;
			}
		}
		
		logger.error("User does not exist!");
		throw new RuntimeException("User does not exist!");
	}

	/**
	 * Our fake database. Here we populate an ArrayList with a dummy list of users.
	 */
	private List<UserDTO> internalDatabase() {
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
