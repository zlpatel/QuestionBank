package org.questionbank.serviceImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.AdditionalQuestionDTO;
import org.questionbank.dto.AdditionalQuestionLookupDTO;
import org.questionbank.dto.RegularQuestionDTO;
import org.questionbank.dto.UserDTO;
import org.questionbank.exception.AllAdditionalQuestionAnsweredException;
import org.questionbank.exception.NoAdditionalQuestionAvailableException;
import org.questionbank.exception.NoAssignedQuestionException;
import org.questionbank.exception.QuestinExpiredException;
import org.questionbank.formbean.QuestionFormBean;
import org.questionbank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService 
{
	protected static Logger logger = Logger.getLogger("question service");
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private UserDAO userDAO; 
	
	@Transactional
	@Override
	public QuestionFormBean getAQuestion(String userName) throws NoAdditionalQuestionAvailableException,NoAssignedQuestionException,AllAdditionalQuestionAnsweredException,Exception
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
			throw new Exception("No assigned question found!");
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
			questionFormBean.setImageName(additionalQuestionDTO.getImageName());
			questionFormBean.setTypeId(additionalQuestionDTO.getType().getTypeId());
			questionFormBean.setStatement(additionalQuestionDTO.getStatement());
			wholeQuestion.append("$ \\documentclass[20pt]{report} $ $\\begin{document} $ "+additionalQuestionDTO.getStatement());
			wholeQuestion.append(" $ \\\\ $");
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
			wholeQuestion.append(" $\\end{document} $");
			questionFormBean.setOptionList(optionList);
			questionFormBean.setWholeQuestion(wholeQuestion.toString());
			questionFormBean.setSelectedOption(optionList.get("1"));
			return questionFormBean;
		}
		questionFormBean.setQuestionId(questionDTO.getQuestionId().toString());
		questionFormBean.setImageName(questionDTO.getImageName());
		questionFormBean.setTypeId(questionDTO.getType().getTypeId());
		questionFormBean.setStatement(questionDTO.getStatement());
		wholeQuestion.append("$ \\documentclass[20pt]{report} $ $\\begin{document} $ "+questionDTO.getStatement());
		wholeQuestion.append(" $ \\\\ $");
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
		wholeQuestion.append(" $\\end{document} $");
		questionFormBean.setOptionList(optionList);
		questionFormBean.setWholeQuestion(wholeQuestion.toString());
		questionFormBean.setSelectedOption(optionList.get("1"));
		return questionFormBean;
			
	}
	@Transactional
	@Override
	public boolean checkAnswer(QuestionFormBean question,String userName) throws QuestinExpiredException, Exception
	{	
		RegularQuestionDTO questionDTO;
		AdditionalQuestionDTO additionalQuestionDTO;
		UserDTO user=userDAO.fetchUserByUserName(userName);
		if(question.getTypeId()==1)
		{
			questionDTO= (RegularQuestionDTO)questionDAO.getThisRegularQuestion(question.getQuestionId());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			String todayDate=dateFormat.format(new Date());
			String questionDate=dateFormat.format(questionDTO.getAssignedDate());
			if(dateFormat.parse(questionDate).before(dateFormat.parse(todayDate)))
				throw new QuestinExpiredException("The question has Expired!");
			if(question.getSelectedOption().equals(questionDTO.getAnswer())) {
				questionDAO.markAsRightAttemptedRegular(question.getQuestionId(),question.getSelectedOption(), user);
				return true;
			}
			else{
				questionDAO.markAsWrongAttemptedRegular(question.getQuestionId(),question.getSelectedOption(), user);
				return false;
			}
		}
		else
		{
			additionalQuestionDTO= (AdditionalQuestionDTO)questionDAO.getThisAdditionalQuestion(question.getQuestionId());
			if(question.getSelectedOption().equals(additionalQuestionDTO.getAnswer())) {
				questionDAO.markAsRightAttemptedAdditional(question.getQuestionId(),question.getSelectedOption(), user);
				return true;
			}
			else{
				questionDAO.markAsWrongAttemptedAdditional(question.getQuestionId(),question.getSelectedOption(), user);
				return false;
			}
		}
	}
	@Transactional
	@Override
	public String getVideoLink(QuestionFormBean question) throws Exception{
		if(question.getTypeId()==2)
			return null;
		String videoLink=questionDAO.getVideoLink(question.getQuestionId());
		return videoLink;
	}
}