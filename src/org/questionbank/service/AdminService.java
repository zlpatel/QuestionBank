package org.questionbank.service;

import java.util.List;

import org.questionbank.form.CategoricalRecordFormBean;
import org.questionbank.form.StudentsRecordFormBean;

public interface AdminService {

	List<StudentsRecordFormBean> getStudentsRecord();
	String getStudentName(String userName);
	List<CategoricalRecordFormBean> getCategoricalRecord(String userName);
	
}
