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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAOImpl implements QuestionDAO
{
	@Autowired
	private SessionFactory sessionFactory;
	protected static Logger logger = Logger.getLogger("dao");

	@Override
	public QuestionDTO getAQuestion() 
	{
		logger.debug("Request to find a random question in QuestionDAO");
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(QuestionDTO.class)
                .setProjection(Projections.property("questionId"));
		List<?> list = criteria.list();
		QuestionDTO questionDTO=new QuestionDTO(); 
		Random random=new Random();
		int size=list.size();
		int next=random.nextInt(size);
		String questionIdToGet = list.get(next).toString();
		Query query = sessionFactory.getCurrentSession().createQuery("FROM QuestionDTO q WHERE q.questionId = :questionId");
		query.setString("questionId", questionIdToGet);
		questionDTO = (QuestionDTO) query.uniqueResult();
		if(questionDTO!=null)
			return questionDTO;
		logger.error("Question does not exist!");
		throw new RuntimeException("Question does not exist!");
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
	public QuestionDTO getAQuestion(String questionId) 
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
}
