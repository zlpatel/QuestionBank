package org.questionbank.dao;

import java.util.List;

import org.questionbank.dto.AdditionalQuestionDTO;
import org.questionbank.dto.AdditionalQuestionLookupDTO;
import org.questionbank.dto.RegularQuestionDTO;
import org.questionbank.dto.RightAttemptsDTO;
import org.questionbank.dto.UserDTO;
import org.questionbank.dto.WrongAttemptsDTO;


public interface QuestionDAO 
{
	boolean checkInRightAttempted(String userName,Integer questionId);
	Integer getNumberOfQuestions();
	RegularQuestionDTO getThisRegularQuestion(String questionId);
	AdditionalQuestionDTO getThisAdditionalQuestion(String questionId);
	void markAsRightAttemptedAdditional(String questionId, String selectedOption, UserDTO user);
	void markAsRightAttemptedRegular(String questionId, String selectedOption, UserDTO user);
	void markAsWrongAttemptedRegular(String questionId, String selectedOption, UserDTO user);
	void markAsWrongAttemptedAdditional(String questionId, String selectedOption, UserDTO user);
	RegularQuestionDTO getTodaysQuestion();
	AdditionalQuestionLookupDTO checkIfLookUpTableIsEmpty(String userName);
	AdditionalQuestionDTO getNextAdditionalQuestion(AdditionalQuestionLookupDTO aQlookUpDTO, String userName);
	AdditionalQuestionDTO getFirstAdditionalQuestion();
	String getVideoLink(String questionId);
	long getRightAttemptCount(String userName);
	long getWrongAttemptCount(String userName);
	List<RightAttemptsDTO> getQuestionsListForRightAttempts(String userName, int questionType);
	List<WrongAttemptsDTO> getQuestionsListForWrongAttempts(String userName, int questionType);
}
