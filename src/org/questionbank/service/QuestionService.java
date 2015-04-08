package org.questionbank.service;

import org.questionbank.form.QuestionFormBean;

public interface QuestionService 
{
	QuestionFormBean getAQuestion(String userName) throws Exception;
	public boolean checkAnswer(QuestionFormBean question,String userName) throws Exception;
	String getVideoLink(QuestionFormBean question) throws Exception;
}
