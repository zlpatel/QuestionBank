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
    @NamedQuery(name = "Students.findAll", query = "SELECT s FROM Students s"),
    @NamedQuery(name = "Students.findByName", query = "SELECT s FROM Students s WHERE s.name = :name"),
    @NamedQuery(name = "Students.findByAsuid", query = "SELECT s FROM Students s WHERE s.asuid = :asuid"),
    @NamedQuery(name = "Students.findByPassword", query = "SELECT s FROM Students s WHERE s.password = :password")})
public class UserDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(nullable = false, length = 40)
    private String name;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 10)
    private String asuid;
    @Basic(optional = false)
    @Column(nullable = false, length = 32)
    private String password;
    @Column(nullable = false, length = 40)
    private String userName;
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * The password as an MD5 value
	 */
    @Column(nullable = false, length = 10)
    private String access;
    /**
	 * Access level of the user. 
	 * 1 = Admin user
	 * 2 = Regular user
	 */

    public UserDTO() {
    }

    public UserDTO(String asuid) {
        this.asuid = asuid;
    }

    public UserDTO(String asuid, String name, String password) {
        this.asuid = asuid;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsuid() {
        return asuid;
    }

    public void setAsuid(String asuid) {
        this.asuid = asuid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (asuid != null ? asuid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof UserDTO)) {
            return false;
        }
        UserDTO other = (UserDTO) object;
        if ((this.asuid == null && other.asuid != null) || (this.asuid != null && !this.asuid.equals(other.asuid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Test_QB_ORM.Students[ asuid=" + asuid + " ]";
    }

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
    
}
