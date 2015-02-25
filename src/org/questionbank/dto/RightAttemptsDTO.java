package org.questionbank.dto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "test", schema = "", name="RightAttempts")
@XmlRootElement
public class RightAttemptsDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name="question_id", referencedColumnName="question_id", nullable = false)
	private QuestionDTO question;
	
	@Id
	@ManyToOne
	@JoinColumn(name="userName", referencedColumnName="userName", nullable = false)
	private UserDTO user;
	
	
	
	public QuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(QuestionDTO question) {
		this.question = question;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
}
