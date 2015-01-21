package org.questionbank.serviceImpl;

import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.QuestionDTO;
import org.questionbank.dto.UserDTO;
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
	@Autowired
	private UserDAO userDAO; 
	
	@Override
	public QuestionFormBean getAQuestion(String userName) 
	{
		logger.debug("Request to find a random question in QuestionService");
		TreeMap<String,String> optionList = new TreeMap<String,String>();
		StringBuffer wholeQuestion=new StringBuffer();
		wholeQuestion.append("$");
		QuestionFormBean questionFormBean=new QuestionFormBean();
		QuestionDTO questionDTO=questionDAO.getAnUnansweredQuestion(userName);
		questionFormBean.setQuestionId(questionDTO.getQuestionId().toString());
		questionFormBean.setStatement(questionDTO.getStatement());
		wholeQuestion.append(questionDTO.getStatement());
		wholeQuestion.append("$ \\\\ $");
		optionList.put("1",questionDTO.getOption1());
		wholeQuestion.append("1. "+questionDTO.getOption1()+" \\newline ");
		optionList.put("2",questionDTO.getOption2());
		wholeQuestion.append("2. "+questionDTO.getOption2()+" \\newline ");
		optionList.put("3",questionDTO.getOption3());
		wholeQuestion.append("3. "+questionDTO.getOption3()+" \\newline ");
		optionList.put("4",questionDTO.getOption4());
		wholeQuestion.append("4. "+questionDTO.getOption4()+" \\newline ");
		optionList.put("5",questionDTO.getOption5());
		wholeQuestion.append("5. "+questionDTO.getOption5()+" \\newline ");
		wholeQuestion.append("$");
		questionFormBean.setOptionList(optionList);
		questionFormBean.setWholeQuestion(wholeQuestion.toString());
		questionFormBean.setSelectedOption(optionList.get("1"));
		return questionFormBean;
		
	}
	@Override
	public boolean checkAnswer(String questionId, String selectedOption,String userName) 
	{		
		QuestionDTO questionDTO= questionDAO.getThisQuestion(questionId);
		UserDTO user=userDAO.fetchUserByUserName(userName);
		if(selectedOption.equals(questionDTO.getAnswer()))
		{
			questionDAO.markAsRightAttempted(questionId, user);
			return true;
		}
		else
		{
			questionDAO.markAsWrongAttempted(questionId, user);
			return false;
		}
	}
	
}
