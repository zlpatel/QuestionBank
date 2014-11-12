package org.questionbank.dao;

import org.questionbank.dto.UserDTO;

public interface UserDAO {

	/**
	 * Simulates retrieval of data from a database.
	 */
	public UserDTO searchDatabase(String username);

}