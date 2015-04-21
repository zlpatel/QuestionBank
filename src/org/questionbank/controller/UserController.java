package org.questionbank.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.questionbank.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	CustomUserDetailsService customUserDetailsService; // wired in spring-security.xml

	protected static Logger logger = Logger.getLogger("controller");
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getUserPage(HttpSession session, ModelMap model) {
		logger.debug("Received request to show user home page");
		try 
		{
			String fullName=customUserDetailsService.getUserFullName((String)session.getAttribute("USERNAME"));
			session.setAttribute("name", fullName);
			//model.addObject("name",fullName);
			return "userpage";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "Something went wrong, please try again later!");
			session.invalidate();
			return "err";
		}
		
		
		
	}
}
