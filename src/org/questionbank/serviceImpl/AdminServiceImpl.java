package org.questionbank.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.questionbank.dao.QuestionDAO;
import org.questionbank.dao.UserDAO;
import org.questionbank.dto.UserDTO;
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

	@Override
	public List<StudentsRecordFormBean> getStudentsRecord() {
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

}
