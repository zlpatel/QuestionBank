package org.questionbank.service;

import java.util.List;

import org.questionbank.formbean.AdditionalQuestionsRecordFormBean;
import org.questionbank.formbean.CategoricalRecordFormBean;
import org.questionbank.formbean.RegularQuestionsRecordFormBean;
import org.questionbank.formbean.StudentsRecordFormBean;
public interface AdminService {

	List<StudentsRecordFormBean> getStudentsRecord() throws Exception;
	String getStudentName(String userName) throws Exception;
	List<CategoricalRecordFormBean> getCategoricalRecord(String userName) throws Exception;
	List<RegularQuestionsRecordFormBean> getRegularQuestionsRecord(String userName) throws Exception;
	List<AdditionalQuestionsRecordFormBean> getAdditionalQuestionsRecord(String userName) throws Exception;
}
