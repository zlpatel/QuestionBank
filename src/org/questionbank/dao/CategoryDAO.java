package org.questionbank.dao;

import java.util.List;

import org.questionbank.dto.CategoryDTO;

public interface CategoryDAO {

	List<CategoryDTO> getAllCategories() throws Exception;

	long getRightAttemptCount(String userName, Integer categoryId) throws Exception;

	long getWrongAttemptCount(String userName, Integer categoryId) throws Exception;

}
