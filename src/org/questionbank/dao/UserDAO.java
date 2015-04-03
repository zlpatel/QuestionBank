package org.questionbank.dao;

import java.util.List;

import org.questionbank.dto.UserDTO;

public interface UserDAO {

	public UserDTO fetchUserByUserName(String username);
	public List<UserDTO> getAllStudents();
	public UserDTO getThisStudent(String userName);

}