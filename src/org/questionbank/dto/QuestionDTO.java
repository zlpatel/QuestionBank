/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.questionbank.dto;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(catalog = "test", schema = "", name="questions")
@XmlRootElement
public class QuestionDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id", nullable = false)
    private Integer questionId;
    
    @Column(length = 10)
    private String statement;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String option1;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String option2;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String option3;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String option4;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String option5;
    
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String answer;

    public QuestionDTO() {
    }

    public QuestionDTO(Integer questionId) {
        this.questionId = questionId;
    }

    public QuestionDTO(Integer questionId, String option1, String option2, String option3, String option4, String option5, String answer) 
    {
        this.questionId = questionId;
        this.option1=option1;
        this.option2=option2;
        this.option3=option3;
        this.option4=option4;
        this.option5=option5;
        this.answer=answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof QuestionDTO)) {
            return false;
        }
        QuestionDTO other = (QuestionDTO) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getOption5() {
		return option5;
	}

	public void setOption5(String option5) {
		this.option5 = option5;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
    public String toString() {
        return "Test_QB_ORM.Questions[ questionId=" + questionId + " ]";
    }
    
}
