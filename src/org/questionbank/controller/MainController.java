package org.questionbank.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.questionbank.form.QuestionBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves the common or admin page depending on the URI template.
 * A user must be log-in first he can access these pages.  Only the admin can see
 * the adminpage, however.
 */
@Controller
@RequestMapping("/main")
public class MainController 
{

	protected static Logger logger = Logger.getLogger("controller");
	
	QuestionBean question=new QuestionBean();
	/**
	 * Handles and retrieves the common JSP page that everyone can see
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String getCommonPage() {
		logger.debug("Received request to show common page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/commonpage.jsp
		return "commonpage";
	}

	/**
	 * Handles and retrieves the admin JSP page that only admins can see
	 * 
	 * @return the name of the JSP page
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		logger.debug("Received request to show admin page");

		// Do your work here. Whatever you like
		// i.e call a custom service to do your business
		// Prepare a model to be used by the JSP page

		// This will resolve to /WEB-INF/jsp/adminpage.jsp
		return "adminpage";
	}
	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView initForm() 
	{
		logger.debug("Received request to show question page");
		
		List<String> optionList = new ArrayList<String>();
		ModelAndView model=new ModelAndView("question");
		System.out.println("new request came in");
		System.out.println("statement should be null"+this.question.getStatement());
		System.out.println("selectedOption should be null"+this.question.getSelectedOption());
		this.question.setStatement("This is the question");
		optionList.add("option1");
		optionList.add("option2");
		optionList.add("option3");
		optionList.add("option4");
		model.addObject("optionList", optionList);
		model.addObject("command", this.question);
		return model;
	}
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String questionFormSubmit(@ModelAttribute("command")QuestionBean ques)
	{
		logger.debug("Question page subimtted");
		question.setSelectedOption(ques.getSelectedOption());
		System.out.println(question.getStatement());
		System.out.println(question.getSelectedOption());
		return "commonpage";
	}
}
