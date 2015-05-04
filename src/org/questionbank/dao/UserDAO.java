package org.questionbank.dao;

import java.util.List;

import org.questionbank.dto.UserDTO;

public interface UserDAO {

	public UserDTO fetchUserByUserName(String username) throws Exception;
	public List<UserDTO> getAllStudents() throws Exception;
	public UserDTO getThisStudent(String userName) throws Exception;
	public boolean addStudent(UserDTO user) throws Exception;
}