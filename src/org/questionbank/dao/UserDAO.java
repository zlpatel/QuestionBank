package org.questionbank.dao;

import org.questionbank.dto.UserDTO;

public interface UserDAO {

	public UserDTO searchDatabase(String username);

}