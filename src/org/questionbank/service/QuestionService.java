package org.questionbank.service;

import org.questionbank.form.QuestionFormBean;

public interface QuestionService 
{
	QuestionFormBean getAQuestion(String userName);
	public boolean checkAnswer(QuestionFormBean question,String userName);
}
