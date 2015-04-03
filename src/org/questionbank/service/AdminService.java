package org.questionbank.service;

import java.util.List;


import org.questionbank.form.StudentsRecordFormBean;
import org.questionbank.form.CategoricalRecordFormBean;
public interface AdminService {

	List<StudentsRecordFormBean> getStudentsRecord();
	String getStudentName(String userName);
	List<CategoricalRecordFormBean> getCategoricalRecord(String userName);
}
