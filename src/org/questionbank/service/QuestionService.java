package org.questionbank.service;

import org.questionbank.form.QuestionFormBean;

public interface QuestionService 
{
	QuestionFormBean getAQuestion(String userName);
	boolean checkAnswer(String questionId, String selectedOption, String userName);
}
