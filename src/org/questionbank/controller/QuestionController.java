package org.questionbank.controller;

import org.apache.log4j.Logger;
import org.questionbank.form.QuestionFormBean;
import org.questionbank.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class QuestionController 
{
	protected static Logger logger = Logger.getLogger("controller");
	
	@Autowired
	QuestionService questionService;

	@RequestMapping(value = "/question", method = RequestMethod.GET)
	public ModelAndView initForm() 
	{
		logger.debug("Received request to show question page");
		
		ModelAndView model=new ModelAndView("questionpage");
		QuestionFormBean question=questionService.getAQuestion();
		model.addObject("optionList", question.getOptionList());
		model.addObject("wholeQuestion",question.getWholeQuestion());
		model.addObject("command", question);
		return model;
	}
	@RequestMapping(value = "/question", method = RequestMethod.POST)
	public ModelAndView  questionFormSubmit(@ModelAttribute("command")QuestionFormBean question)
	{
		logger.debug("Question page subimtted");
		ModelAndView mav=new ModelAndView();
		System.out.println(question.getSelectedOption());
		boolean result=questionService.checkAnswer(question.getQuestionId(),question.getSelectedOption());
		
		if(result)
			question.setMessage("Your answer is correct :D !!");
		else
			question.setMessage("Your answer is incorrect :( !!");
		mav.setViewName("choicepage");
		mav.addObject("message",question.getMessage());
		return mav;
	}
	@RequestMapping(value = "/choice/{choice}", method=RequestMethod.GET)
	public String moreQuestionChoice(@PathVariable boolean choice) 
	{
		logger.debug("More question choice get");
		if(choice)
			return "redirect:../question";
		else
			return "userpage";
	}
}
