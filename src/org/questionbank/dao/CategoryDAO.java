package org.questionbank.dao;

import java.util.List;

import org.questionbank.dto.CategoryDTO;

public interface CategoryDAO {

	List<CategoryDTO> getAllCategories();

	long getRightAttemptCount(String userName, Integer categoryId);

	long getWrongAttemptCount(String userName, Integer categoryId);

}
