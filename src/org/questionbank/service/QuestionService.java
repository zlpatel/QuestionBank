package org.questionbank.service;

import org.questionbank.form.QuestionFormBean;

public interface QuestionService 
{
	QuestionFormBean getAQuestion();
	boolean checkAnswer(String questionId, String selectedOption);
}
