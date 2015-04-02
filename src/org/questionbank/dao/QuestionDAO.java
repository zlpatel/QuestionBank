package org.questionbank.dao;

import org.questionbank.dto.AdditionalQuestionDTO;
import org.questionbank.dto.AdditionalQuestionLookupDTO;
import org.questionbank.dto.RegularQuestionDTO;
import org.questionbank.dto.UserDTO;


public interface QuestionDAO 
{
	boolean checkInRightAttempted(String userName,Integer questionId);
	Integer getNumberOfQuestions();
	RegularQuestionDTO getThisRegularQuestion(String questionId);
	AdditionalQuestionDTO getThisAdditionalQuestion(String questionId);
	void markAsRightAttemptedAdditional(String questionId, UserDTO user);
	void markAsRightAttemptedRegular(String questionId, UserDTO user);
	void markAsWrongAttemptedRegular(String questionId, UserDTO user);
	void markAsWrongAttemptedAdditional(String questionId, UserDTO user);
	RegularQuestionDTO getTodaysQuestion();
	AdditionalQuestionLookupDTO checkIfLookUpTableIsEmpty(String userName);
	AdditionalQuestionDTO getNextAdditionalQuestion(AdditionalQuestionLookupDTO aQlookUpDTO, String userName);
	AdditionalQuestionDTO getFirstAdditionalQuestion();
	String getVideoLink(String questionId);
	long getRightAttemptCount(String userName);
	long getWrongAttemptCount(String userName);
}
