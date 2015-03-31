package org.questionbank.daoImpl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dto.AdditionalQuestionDTO;
import org.questionbank.dto.AdditionalQuestionLookupDTO;
import org.questionbank.dto.IQuestion;
import org.questionbank.dto.RegularQuestionDTO;
import org.questionbank.dto.RightAttemptsDTO;
import org.questionbank.dto.UserDTO;
import org.questionbank.dto.WrongAttemptsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAOImpl implements QuestionDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	protected static Logger logger = Logger.getLogger("dao");

	@Override
	public IQuestion getAnUnansweredQuestion(String userName) 
	{
		logger.debug("Request to find a random question in QuestionDAO");
		
		boolean isAlreadyAnsweredCorrectly=true;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dateFormat.format(date));
		
		RegularQuestionDTO questionDTO; 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM QuestionDTO q WHERE q.assignedDate = :assignedDate");
		query.setString("assignedDate", dateFormat.format(date));
		questionDTO = (RegularQuestionDTO) query.uniqueResult();
		
		if(questionDTO!=null)
			isAlreadyAnsweredCorrectly=checkInRightAttempted(userName,questionDTO.getQuestionId());
		else
			throw new RuntimeException("No assigned question found!");
		if(isAlreadyAnsweredCorrectly)
		{
			Integer nextQuestionId;
			query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionLookupDTO aql WHERE aql.user.userName = :userName");
			query.setString("userName", userName);
			AdditionalQuestionLookupDTO AQlookUpDTO= (AdditionalQuestionLookupDTO) query.uniqueResult();
			if(AQlookUpDTO!=null)
			{
				nextQuestionId=AQlookUpDTO.getQuestion().getQuestionId() + 1;
				query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionDTO aq WHERE aq.questionId = :questionId");
				query.setString("questionId", nextQuestionId.toString());
				AdditionalQuestionDTO nextAditionalQuestion= (AdditionalQuestionDTO) query.uniqueResult();
				if(nextAditionalQuestion!=null)
					return nextAditionalQuestion;
				else
					throw new RuntimeException("All additional questions already answered!");
			}
			else
			{
				query = sessionFactory.getCurrentSession().createQuery("FROM AdditionalQuestionDTO aq WHERE aq.questionId = :questionId");
				query.setString("questionId", new Integer(1).toString());
				AdditionalQuestionDTO nextAditionalQuestion= (AdditionalQuestionDTO) query.uniqueResult();
				if(nextAditionalQuestion!=null)
					return nextAditionalQuestion;
				else
					throw new RuntimeException("No additional questions added!");
			}
		}
		return questionDTO;
	}
	
	public boolean checkInRightAttempted(String userName,Integer questionId) 
	{
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM RightAttemptsDTO r WHERE r.question.questionId = :questionId and r.user.userName = :userName");
		query.setString("questionId", questionId.toString());
		query.setString("userName", userName);
		long count = (Long) query.uniqueResult();
		if(count!=0)
			return true;
		return false;
	}

	@Override
	public Integer getNumberOfQuestions() 
	{
		logger.debug("Request to find number of questions");
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(q) FROM QuestionDTO q");
		Long qouestionCount = (Long)query.uniqueResult();
		Integer i = (int) (long) qouestionCount;
		return i;
	}
    @Override
	public RegularQuestionDTO getThisQuestion(String questionId) 
	{
		logger.debug("Request to find a given question in QuestionDAO");
		RegularQuestionDTO questionDTO=new RegularQuestionDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM QuestionDTO q WHERE q.questionId = :questionId");
		query.setString("questionId", questionId);
		questionDTO = (RegularQuestionDTO) query.uniqueResult();
		if(questionDTO!=null)
			return questionDTO;
		logger.error("Question does not exist!");
		throw new RuntimeException("Question does not exist!");
	}

	@Override
	public void markAsRightAttempted(String questionId, UserDTO user) {
		logger.debug("Marking the question as Right Attempt");
		RightAttemptsDTO rightAttempt=new RightAttemptsDTO();
		RegularQuestionDTO question=getThisQuestion(questionId);
		rightAttempt.setQuestion(question);
		rightAttempt.setUser(user);
		sessionFactory.getCurrentSession().save(rightAttempt);
	}

	@Override
	public void markAsWrongAttempted(String questionId, UserDTO user) {
		Query query = sessionFactory.getCurrentSession().createQuery("FROM WrongAttemptsDTO w WHERE w.question.questionId = :questionId and w.user.userName=:userName");
		query.setString("questionId", questionId);
		query.setString("userName", user.getUserName());
		WrongAttemptsDTO wrongAttempt = (WrongAttemptsDTO)query.uniqueResult();
		if(wrongAttempt==null)
		{
			logger.debug("Marking the question as Wrong Attempt");
			wrongAttempt=new WrongAttemptsDTO();
			RegularQuestionDTO question=getThisQuestion(questionId);
			wrongAttempt.setQuestion(question);
			wrongAttempt.setUser(user);
			wrongAttempt.setAttemptCount(1);
			sessionFactory.getCurrentSession().save(wrongAttempt);
		}	
		else
		{
			logger.debug("Updating the question as Wrong Attempt");
			Integer attemptCount=wrongAttempt.getAttemptCount();
			attemptCount++;
			wrongAttempt.setAttemptCount(attemptCount);
			Query updateQuery = sessionFactory.getCurrentSession().createQuery("UPDATE WrongAttemptsDTO w set w.attemptCount = :attemptCount WHERE w.question.questionId = :questionId and w.user.userName=:userName");
			updateQuery.setString("questionId", questionId);
			updateQuery.setString("userName", user.getUserName());
			updateQuery.setString("attemptCount", attemptCount.toString());
			Integer result = updateQuery.executeUpdate();
			System.out.println(result.toString()+" records Updated Wrong Attempt");
		}
	} 
}
