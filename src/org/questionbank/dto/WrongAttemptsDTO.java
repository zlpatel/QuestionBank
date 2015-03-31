package org.questionbank.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "test", schema = "", name="WrongAttempts")
@XmlRootElement
public class WrongAttemptsDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@ManyToOne
	@JoinColumn(name="question_id", referencedColumnName="question_id", nullable = false)
	private RegularQuestionDTO question;
	
	@Id
	@ManyToOne
	@JoinColumn(name="userName", referencedColumnName="userName", nullable = false)
	private UserDTO user;
	
	@Id
	@Column(name="attempt_time")
	@Temporal(TemporalType.TIMESTAMP) 
	public Date attemptTime;
	
	@Column(name = "attempt_count", nullable = false)
	private int attemptCount;
	
	@ManyToOne
    @JoinColumn(name="type_id", referencedColumnName="type_id")
    @Column(name="type_id")
    private QuestionTypeDTO type;
	
	public Date getAttemptTime() {
		return attemptTime;
	}
	public void setAttemptTime(Date attemptTime) {
		this.attemptTime = attemptTime;
	}
	
	public QuestionTypeDTO getType() {
		return type;
	}
	public void setType(QuestionTypeDTO type) {
		this.type = type;
	}
	public int getAttemptCount() {
		return attemptCount;
	}
	public void setAttemptCount(int attemptCount) {
		this.attemptCount = attemptCount;
	}
	public RegularQuestionDTO getQuestion() {
		return question;
	}
	public void setQuestion(RegularQuestionDTO question) {
		this.question = question;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
}
