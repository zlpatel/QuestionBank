package org.questionbank.dao;

import org.questionbank.dto.IQuestion;
import org.questionbank.dto.RegularQuestionDTO;
import org.questionbank.dto.UserDTO;


public interface QuestionDAO 
{
	IQuestion getAnUnansweredQuestion(String userName);
	Integer getNumberOfQuestions();
	RegularQuestionDTO getThisQuestion(String questionId);
	void markAsRightAttempted(String questionId,UserDTO user);
	void markAsWrongAttempted(String questionId,UserDTO user);
}
