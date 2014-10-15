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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author zeel
 */
@Entity
@Table(catalog = "qb_test", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Questions.findAll", query = "SELECT q FROM Questions q"),
    @NamedQuery(name = "Questions.findByQuestionId", query = "SELECT q FROM Questions q WHERE q.questionId = :questionId"),
    @NamedQuery(name = "Questions.findByStatement", query = "SELECT q FROM Questions q WHERE q.statement = :statement"),
    @NamedQuery(name = "Questions.findByOptions", query = "SELECT q FROM Questions q WHERE q.options = :options")})
public class QuestionsDTO implements Serializable {
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
    private String options;

    public QuestionsDTO() {
    }

    public QuestionsDTO(Integer questionId) {
        this.questionId = questionId;
    }

    public QuestionsDTO(Integer questionId, String options) {
        this.questionId = questionId;
        this.options = options;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof QuestionsDTO)) {
            return false;
        }
        QuestionsDTO other = (QuestionsDTO) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Test_QB_ORM.Questions[ questionId=" + questionId + " ]";
    }
    
}
