package org.questionbank.dao;

import org.questionbank.dto.QuestionDTO;
import org.questionbank.dto.UserDTO;


public interface QuestionDAO 
{
	QuestionDTO getAnUnansweredQuestion(String userName);
	Integer getNumberOfQuestions();
	QuestionDTO getThisQuestion(String questionId);
	boolean isAnsweredCorrectly(QuestionDTO questionDTO, String userName);
	void markAsRightAttempted(String questionId,UserDTO user);
	void markAsWrongAttempted(String questionId,UserDTO user);
}
