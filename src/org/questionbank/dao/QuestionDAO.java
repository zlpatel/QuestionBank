package org.questionbank.dao;

import org.questionbank.dto.QuestionDTO;


public interface QuestionDAO 
{
	QuestionDTO getAQuestion();
	Integer getNumberOfQuestions();
	QuestionDTO getAQuestion(String questionId);
}
