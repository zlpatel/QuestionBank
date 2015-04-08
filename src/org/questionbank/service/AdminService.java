package org.questionbank.service;

import java.util.List;

import org.questionbank.form.AdditionalQuestionsRecordFormBean;
import org.questionbank.form.RegularQuestionsRecordFormBean;
import org.questionbank.form.StudentsRecordFormBean;
import org.questionbank.form.CategoricalRecordFormBean;
public interface AdminService {

	List<StudentsRecordFormBean> getStudentsRecord() throws Exception;
	String getStudentName(String userName) throws Exception;
	List<CategoricalRecordFormBean> getCategoricalRecord(String userName) throws Exception;
	List<RegularQuestionsRecordFormBean> getRegularQuestionsRecord(String userName) throws Exception;
	List<AdditionalQuestionsRecordFormBean> getAdditionalQuestionsRecord(String userName) throws Exception;
}
