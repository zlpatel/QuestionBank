package org.questionbank.dto;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="users")
@XmlRootElement
public class UserDTO implements Serializable 
{
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false, length = 40,name="userName")
    private String userName;
    @Basic(optional = false)
    @Column(nullable = false, length = 40,name="fullName")
    private String fullName;
    @Basic(optional = false)
    @Column(nullable = false, length = 10,name="asuid")
    private String asuid;
    @Basic(optional = false)
    @Column(nullable = false, length = 32,name="passkey")
    private String passkey;  //  The password as an MD5 value
	@Column(nullable = false, length = 10,name="access")
    private String access;
    /**
	 * Access level of the user. 
	 * 1 = Admin user
	 * 2 = Regular user
	 */
    public UserDTO() {
    }

    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    public UserDTO(String asuid) {
        this.asuid = asuid;
    }

    public UserDTO(String asuid, String name, String password) {
        this.asuid = asuid;
        this.fullName = name;
        this.passkey = password;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String name) {
        this.fullName = name;
    }

    public String getAsuid() {
        return asuid;
    }

    public void setAsuid(String asuid) {
        this.asuid = asuid;
    }

    public String getPassword() {
        return passkey;
    }

    public void setPassword(String password) {
        this.passkey = password;
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

    public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}
    
}
