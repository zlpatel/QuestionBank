package org.questionbank.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.questionbank.dao.CategoryDAO;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.CategoryDTO;
import org.questionbank.dto.RightAttemptsDTO;
import org.questionbank.dto.UserDTO;
import org.questionbank.dto.WrongAttemptsDTO;
import org.questionbank.form.AdditionalQuestionsRecordFormBean;
import org.questionbank.form.CategoricalRecordFormBean;
import org.questionbank.form.RegularQuestionsRecordFormBean;
import org.questionbank.form.StudentsRecordFormBean;
import org.questionbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{

	protected static Logger logger = Logger.getLogger("admin service");
	@Autowired
	private QuestionDAO questionDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Override
	public List<StudentsRecordFormBean> getStudentsRecord() {
		logger.debug("Received request get all student records in admin service");
		long rightAttemptCount;
		long wrongAttemptCount;
		List<StudentsRecordFormBean> studentsRecord=new ArrayList<StudentsRecordFormBean>();
		List<UserDTO> studentList=userDAO.getAllStudents();
		for(UserDTO student: studentList)
		{
			rightAttemptCount=questionDAO.getRightAttemptCount(student.getUserName());
			wrongAttemptCount=questionDAO.getWrongAttemptCount(student.getUserName());
			StudentsRecordFormBean studentRecord=new StudentsRecordFormBean();
			studentRecord.setRightAttemptCount(rightAttemptCount);
			studentRecord.setStudentName(student.getName());
			studentRecord.setUserName(student.getUserName());
			studentRecord.setWrongAttemptCount(wrongAttemptCount);
			studentsRecord.add(studentRecord);
		}
		return studentsRecord;
	}

	@Override
	public String getStudentName(String userName) {
		logger.debug("Received request get a given student record in admin service");
		UserDTO user=userDAO.getThisStudent(userName);
		return user.getName();
	}

	@Override
	public List<CategoricalRecordFormBean> getCategoricalRecord(String userName) {
		logger.debug("Received request get categorical records in admin service");
		long rightAttemptCount;
		long wrongAttemptCount;
		List<CategoricalRecordFormBean> categoricalRecordList=new ArrayList<CategoricalRecordFormBean>();
		List<CategoryDTO> categoryList=categoryDAO.getAllCategories();
		for(CategoryDTO category : categoryList)
		{
			rightAttemptCount=categoryDAO.getRightAttemptCount(userName,category.getCategoryId());
			wrongAttemptCount=categoryDAO.getWrongAttemptCount(userName,category.getCategoryId());
			CategoricalRecordFormBean categoricalRecord=new CategoricalRecordFormBean();
			categoricalRecord.setRightAttemptCount(rightAttemptCount);
			categoricalRecord.setWrongAttemptCount(wrongAttemptCount);
			categoricalRecord.setCategoryName(category.getCategoryName());
			categoricalRecordList.add(categoricalRecord);
			
		}
		return categoricalRecordList;
	}

	@Override
	public List<RegularQuestionsRecordFormBean> getRegularQuestionsRecord(
			String userName) {
		logger.debug("Received request get regular questions records in admin service");
		List<RegularQuestionsRecordFormBean> regularQuestionsRecordList=new ArrayList<RegularQuestionsRecordFormBean>();
		List<RightAttemptsDTO> regularQuestionsListForRightAttempts=questionDAO.getQuestionsListForRightAttempts(userName,1);
		List<WrongAttemptsDTO> regularQuestionsListForWrongAttempts=questionDAO.getQuestionsListForWrongAttempts(userName,1);
		for(RightAttemptsDTO question : regularQuestionsListForRightAttempts)
		{
			RegularQuestionsRecordFormBean regularQuestionsRecord=new RegularQuestionsRecordFormBean();
			regularQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document}$"+question.getQuestionRegular().getStatement()+" $\\end{document} $");
			regularQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document}$"+question.getSelectedAnswer()+" $\\end{document} $");
			regularQuestionsRecord.setDateTime(question.getAttemptTime());
			regularQuestionsRecord.setResult(true);
			regularQuestionsRecordList.add(regularQuestionsRecord);
			
		}
		
		for(WrongAttemptsDTO question : regularQuestionsListForWrongAttempts)
		{
			RegularQuestionsRecordFormBean regularQuestionsRecord=new RegularQuestionsRecordFormBean();
			
			regularQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document} $"+question.getQuestionRegular().getStatement()+"  $\\end{document} $");
			regularQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document} $"+question.getSelectedAnswer()+"  $\\end{document} $");
			regularQuestionsRecord.setDateTime(question.getAttemptTime());
			regularQuestionsRecord.setResult(false);
			regularQuestionsRecordList.add(regularQuestionsRecord);
			
		}
		Collections.sort(regularQuestionsRecordList);
		return regularQuestionsRecordList;
	}

	@Override
	public List<AdditionalQuestionsRecordFormBean> getAdditionalQuestionsRecord(
			String userName) {
		logger.debug("Received request get additional questions records in admin service");
		List<AdditionalQuestionsRecordFormBean> additionalQuestionsRecordList=new ArrayList<AdditionalQuestionsRecordFormBean>();
		List<RightAttemptsDTO> additionalQuestionsListForRightAttempts=questionDAO.getQuestionsListForRightAttempts(userName,2);
		List<WrongAttemptsDTO> additionalQuestionsListForWrongAttempts=questionDAO.getQuestionsListForWrongAttempts(userName,2);
		for(RightAttemptsDTO question : additionalQuestionsListForRightAttempts)
		{
			AdditionalQuestionsRecordFormBean additionalQuestionsRecord=new AdditionalQuestionsRecordFormBean();
			additionalQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document} $"+question.getQuestionAdditional().getStatement()+" $\\end{document} $");
			additionalQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document} $"+question.getSelectedAnswer()+" $\\end{document} $");
			additionalQuestionsRecord.setDateTime(question.getAttemptTime());
			additionalQuestionsRecord.setResult(true);
			additionalQuestionsRecordList.add(additionalQuestionsRecord);
			
		}
		
		for(WrongAttemptsDTO question : additionalQuestionsListForWrongAttempts)
		{
			AdditionalQuestionsRecordFormBean additionalQuestionsRecord=new AdditionalQuestionsRecordFormBean();
			
			additionalQuestionsRecord.setQuestionName("$ \\documentclass[14pt]{report} $ $\\begin{document} $"+question.getQuestionAdditional().getStatement()+" $\\end{document} $");
			additionalQuestionsRecord.setMarkedAnswer("$ \\documentclass[14pt]{report} $ $\\begin{document} $"+question.getSelectedAnswer()+" $\\end{document} $");
			additionalQuestionsRecord.setDateTime(question.getAttemptTime());
			additionalQuestionsRecord.setResult(false);
			additionalQuestionsRecordList.add(additionalQuestionsRecord);
			
		}
		Collections.sort(additionalQuestionsRecordList);
		return additionalQuestionsRecordList;
	}

}
