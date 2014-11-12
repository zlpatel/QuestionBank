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

@Controller
@RequestMapping("/user")
public class UserController 
{

	protected static Logger logger = Logger.getLogger("controller");
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getCommonPage() {
		logger.debug("Received request to show common page");

		return "userpage";
	}

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView initForm() 
	{
		logger.debug("Received request to show question page");
		
		List<String> optionList = new ArrayList<String>();
		ModelAndView model=new ModelAndView("question");
		
		QuestionBean question=new QuestionBean();
		question.setQuestionId("12");
		question.setStatement("This is the question");
		optionList.add("option1");
		optionList.add("option2");
		optionList.add("option3");
		optionList.add("option4");
		model.addObject("optionList", optionList);
		model.addObject("command", question);
		return model;
	}
	
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public String questionFormSubmit(@ModelAttribute("command")QuestionBean question)
	{
		logger.debug("Question page subimtted");
		System.out.println(question.getQuestionId());
		System.out.println(question.getSelectedOption());
		return "userpage";
	}
}
