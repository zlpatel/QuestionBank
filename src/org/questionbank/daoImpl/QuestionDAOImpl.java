package org.questionbank.daoImpl;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dto.QuestionDTO;
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
	public QuestionDTO getAnUnansweredQuestion(String userName) 
	{
		logger.debug("Request to find a random question in QuestionDAO");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionDTO.class)
				.setProjection(Projections.property("questionId"));
		List<?> list = criteria.list();
		QuestionDTO questionDTO=new QuestionDTO(); 
		Random random=new Random();
		int size=list.size();
		boolean isAlreadyAnsweredCorrectly=true;
		int findTries=0;
		while(isAlreadyAnsweredCorrectly)
		{
			int next=random.nextInt(size);
			String questionIdToGet = list.get(next).toString();
			Query query = sessionFactory.getCurrentSession().createQuery("FROM QuestionDTO q WHERE q.questionId = :questionId");
			query.setString("questionId", questionIdToGet);
			questionDTO = (QuestionDTO) query.uniqueResult();
			if(questionDTO!=null)
				isAlreadyAnsweredCorrectly=isAnsweredCorrectly(questionDTO,userName);
			if(findTries==size)
			{
				logger.error("All Questions already answered, No new question Found!");
				throw new RuntimeException("All Questions answered, No new question Found!");
			}
			findTries++;
		}
		if(!isAlreadyAnsweredCorrectly)
			return questionDTO;
		logger.error("Question does not exist!");
		throw new RuntimeException("Question does not exist!");
	}
	
	@Override
	public boolean isAnsweredCorrectly(QuestionDTO questionDTO,String userName) {
		logger.debug("Request to find if a random question is already answered");
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM RightAttemptsDTO r WHERE r.question.questionId = :questionId and r.user.userName = :userName");
		query.setString("questionId", questionDTO.getQuestionId().toString());
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
	public QuestionDTO getThisQuestion(String questionId) 
	{
		logger.debug("Request to find a given question in QuestionDAO");
		QuestionDTO questionDTO=new QuestionDTO(); 
		Query query = sessionFactory.getCurrentSession().createQuery("FROM QuestionDTO q WHERE q.questionId = :questionId");
		query.setString("questionId", questionId);
		questionDTO = (QuestionDTO) query.uniqueResult();
		if(questionDTO!=null)
			return questionDTO;
		logger.error("Question does not exist!");
		throw new RuntimeException("Question does not exist!");
	}

	@Override
	public void markAsRightAttempted(String questionId, UserDTO user) {
		logger.debug("Marking the question as Right Attempt");
		RightAttemptsDTO rightAttempt=new RightAttemptsDTO();
		QuestionDTO question=getThisQuestion(questionId);
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
			QuestionDTO question=getThisQuestion(questionId);
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
