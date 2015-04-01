package org.questionbank.serviceImpl;

import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.AdditionalQuestionDTO;
import org.questionbank.dto.AdditionalQuestionLookupDTO;
import org.questionbank.dto.RegularQuestionDTO;
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
		boolean isAlreadyAnsweredCorrectly=true;
		logger.debug("Request to find a question in QuestionService");
		TreeMap<String,String> optionList = new TreeMap<String,String>();
		StringBuffer wholeQuestion=new StringBuffer();
		wholeQuestion.append("$");
		QuestionFormBean questionFormBean=new QuestionFormBean();
		RegularQuestionDTO questionDTO=questionDAO.getTodaysQuestion();
		if(questionDTO!=null)
			isAlreadyAnsweredCorrectly=questionDAO.checkInRightAttempted(userName,questionDTO.getQuestionId());
		else
			throw new RuntimeException("No assigned question found!");
		if(isAlreadyAnsweredCorrectly)
		{
			AdditionalQuestionLookupDTO AQlookUpDTO=questionDAO.checkIfLookUpTableIsEmpty(userName);
			AdditionalQuestionDTO additionalQuestionDTO;
			if(AQlookUpDTO!=null){
				additionalQuestionDTO=questionDAO.getNextAdditionalQuestion(AQlookUpDTO,userName);
			}
			else {
				additionalQuestionDTO=questionDAO.getFirstAdditionalQuestion();
			}
			questionFormBean.setQuestionId(additionalQuestionDTO.getQuestionId().toString());
			questionFormBean.setTypeId(additionalQuestionDTO.getType().getTypeId());
			questionFormBean.setStatement(additionalQuestionDTO.getStatement());
			wholeQuestion.append(additionalQuestionDTO.getStatement());
			wholeQuestion.append("$ \\\\ $");
			optionList.put("1",additionalQuestionDTO.getOption1());
			wholeQuestion.append("1. "+additionalQuestionDTO.getOption1()+" \\newline ");
			optionList.put("2",additionalQuestionDTO.getOption2());
			wholeQuestion.append("2. "+additionalQuestionDTO.getOption2()+" \\newline ");
			optionList.put("3",additionalQuestionDTO.getOption3());
			wholeQuestion.append("3. "+additionalQuestionDTO.getOption3()+" \\newline ");
			optionList.put("4",additionalQuestionDTO.getOption4());
			wholeQuestion.append("4. "+additionalQuestionDTO.getOption4()+" \\newline ");
			optionList.put("5",additionalQuestionDTO.getOption5());
			wholeQuestion.append("5. "+additionalQuestionDTO.getOption5()+" \\newline ");
			wholeQuestion.append("$");
			questionFormBean.setOptionList(optionList);
			questionFormBean.setWholeQuestion(wholeQuestion.toString());
			questionFormBean.setSelectedOption(optionList.get("1"));
			return questionFormBean;
		}
		questionFormBean.setQuestionId(questionDTO.getQuestionId().toString());
		questionFormBean.setTypeId(questionDTO.getType().getTypeId());
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
	public boolean checkAnswer(QuestionFormBean question,String userName) 
	{	
		RegularQuestionDTO questionDTO;
		AdditionalQuestionDTO additionalQuestionDTO;
		UserDTO user=userDAO.fetchUserByUserName(userName);
		if(question.getTypeId()==1)
		{
			questionDTO= (RegularQuestionDTO)questionDAO.getThisRegularQuestion(question.getQuestionId());
			if(question.getSelectedOption().equals(questionDTO.getAnswer())) {
				questionDAO.markAsRightAttemptedRegular(question.getQuestionId(), user);
				return true;
			}
			else{
				questionDAO.markAsWrongAttemptedRegular(question.getQuestionId(), user);
				return false;
			}
		}
		else
		{
			additionalQuestionDTO= (AdditionalQuestionDTO)questionDAO.getThisAdditionalQuestion(question.getQuestionId());
			if(question.getSelectedOption().equals(additionalQuestionDTO.getAnswer())) {
				questionDAO.markAsRightAttemptedAdditional(question.getQuestionId(), user);
				return true;
			}
			else{
				questionDAO.markAsWrongAttemptedAdditional(question.getQuestionId(), user);
				return false;
			}
		}
		
		
		
	}
	
}
