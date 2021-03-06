package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="User")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="\"user\"", uniqueConstraints = { @UniqueConstraint(columnNames = "login", name="UK_login" ), @UniqueConstraint(columnNames = "email", name="UK_mail" )} )
public class User implements Serializable{
	private static final long serialVersionUID = 1L;	
		
	@Id
	@XmlID
	@XmlElement	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
		
	@XmlElement	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String login;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String firstname;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String lastname;
	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String password;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(100)", nullable=false)
	private String email;
	
	@XmlElement
	@Column(columnDefinition="nchar(40)")
	private String token;
	
	@XmlElement
	@Transient
	private BigDecimal maxDept;
	
	@XmlElement
	@XmlElementWrapper
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="memberUsers")
	private Set<Group> groups;

	public User(){}
	
	public User(String login, String firstname, String lastname,
			String email, String password) {
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
	}

	public void updateDataFrom(User user) {
		this.firstname = user.firstname;
		this.lastname = user.lastname;
		this.email = user.email;		
	}
	
	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BigDecimal getMaxDept() {
		return maxDept;
	}

	public void setMaxDept(BigDecimal maxDept) {
		this.maxDept = maxDept;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
