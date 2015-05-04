package org.questionbank.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.questionbank.formbean.AddStudentFormBean;
import org.questionbank.formbean.AdditionalQuestionsRecordFormBean;
import org.questionbank.formbean.CategoricalRecordFormBean;
import org.questionbank.formbean.RegularQuestionsRecordFormBean;
import org.questionbank.formbean.StudentsRecordFormBean;
import org.questionbank.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	protected static Logger logger = Logger.getLogger("controller");
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView getAdminPage(HttpSession session) {
		logger.debug("Received request to show admin page");
		
		ModelAndView model=new ModelAndView("adminpage");
		try {
			String fullName=adminService.getAdminName((String)session.getAttribute("USERNAME"));
			session.setAttribute("name", fullName);
			//model.addObject("name",fullName);
			return model;
		} catch (Exception e) {
			model=new ModelAndView("err");
			model.addObject("message", "Something went wrong, please try again later!");
			session.invalidate();
			return model;
		}
		
	}
	@RequestMapping(value = "/studentsRecord", method = RequestMethod.GET)
	public ModelAndView getStudentsRecord() {
		logger.debug("Received request to all student record");
		List<StudentsRecordFormBean> recordList =null;
		try {
			recordList = adminService.getStudentsRecord();
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		} 
		ModelAndView mav=new ModelAndView();
		mav.setViewName("studentsrecord");
		mav.addObject("recordList",recordList);
		return mav;
	}
	@RequestMapping(value = "/categoricalRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentCategoricalDetails(@PathVariable String userName) {
		logger.debug("Received request to show single student record based on category");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<CategoricalRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getCategoricalRecord(userName);
		}catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		mav.addObject("recordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("categoricalrecord");
		return mav;
	}
	@RequestMapping(value = "/regularQuestionsRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentRegularQuestionsDetails(@PathVariable String userName) {
		logger.debug("Received request to show single student record for Regular Questions");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<RegularQuestionsRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getRegularQuestionsRecord(userName);
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		mav.addObject("regularQuestionsRecordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("regularquestionsrecord");
		return mav;
	}
	@RequestMapping(value = "/additionalQuestionsRecords/{userName}", method = RequestMethod.POST)
	public ModelAndView getSingleStudentAdditionalQuestionsDetails(@PathVariable String userName) {
		logger.debug("Received request to show single student record for Additional Questions");
		ModelAndView mav=new ModelAndView();
		String studentName=null;
		List<AdditionalQuestionsRecordFormBean> recordList=null;
		try {
			studentName = adminService.getStudentName(userName);
			recordList=adminService.getAdditionalQuestionsRecord(userName);
		} catch (Exception e) {
			ModelAndView model=new ModelAndView("adminerr");
			model.addObject("message", "Something went wrong, please try again later!");
			return model;
		}
		mav.addObject("additionalQuestionsRecordList", recordList);
		mav.addObject("studentName", studentName);
		mav.setViewName("additionalquestionsrecord");
		return mav;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView addStudentAccount() {
		logger.debug("Received request to show add account page");
		ModelAndView model=new ModelAndView("adduserpage");
		return model;
		
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addStudentAccount(@ModelAttribute("command")AddStudentFormBean student,
			HttpSession session,HttpServletRequest request) {
		logger.debug("Received request to add account");
		ModelAndView model=null;
		try {
			model=new ModelAndView("adduserpage");
			student.setSelectedAccess(request.getParameter("radios"));
			adminService.addStudent(student);
			model.addObject("message", "User "+ student.getFullName() +" successfuly added");
			student.setFullName("");
			student.setPassWord("");
			student.setUserName("");
			return model;
		}catch (Exception e) {
			logger.debug("DB exception during add student.");
			model=new ModelAndView("adduserpage");
			model.addObject("error", "User already exist or Error with Database.");
			student.setFullName("");
			student.setPassWord("");
			student.setUserName("");
			return model;
		}
	}
}
