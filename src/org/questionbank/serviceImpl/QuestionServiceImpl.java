package org.questionbank.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dto.QuestionDTO;
import org.questionbank.form.QuestionFormBean;
import org.questionbank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService 
{
	protected static Logger logger = Logger.getLogger("service");
	@Autowired
	private QuestionDAO questionDAO; 
	@Override
	public QuestionFormBean getAQuestion() 
	{
		logger.debug("Request to find a random question in QuestionService");
		List<String> optionList = new ArrayList<String>();
		QuestionFormBean questionFormBean=new QuestionFormBean();
		QuestionDTO questionDTO=questionDAO.getAQuestion();
		questionFormBean.setQuestionId(questionDTO.getQuestionId().toString());
		questionFormBean.setStatement(questionDTO.getStatement());
		optionList.add(questionDTO.getOption1());
		optionList.add(questionDTO.getOption2());
		optionList.add(questionDTO.getOption3());
		optionList.add(questionDTO.getOption4());
		optionList.add(questionDTO.getOption5());
		questionFormBean.setOptionList(optionList);
		questionFormBean.setSelectedOption(optionList.get(0));
		return questionFormBean;
		
	}
	@Override
	public boolean checkAnswer(String questionId, String selectedOption) 
	{		
		QuestionDTO questionDTO= questionDAO.getAQuestion(questionId);
		if(selectedOption.equals(questionDTO.getAnswer()))
			return true;
		else
			return false;
	}
	
}
