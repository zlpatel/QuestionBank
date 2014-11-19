package org.questionbank.form;

import java.util.List;

public class QuestionFormBean 
{
	private String questionId;
	private String statement;
	private List<String> optionList;
	private String selectedOption;
	private String message;
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public List<String> getOptionList() {
		return optionList;
	}
	public void setOptionList(List<String> optionList) {
		this.optionList = optionList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
