package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="GroupDetails")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="\"group\"")
public class GroupDetails implements Serializable{
	private static final long serialVersionUID = 1L;
		
	@Id
	@XmlID
	@XmlElement	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(100)", nullable=false)
	private String name;
	
	@XmlElement
	@XmlElementWrapper
	@OneToMany(mappedBy="groupDetails" ,fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private List<OrderDetails> orders;
	
	@XmlElement
	@XmlElementWrapper
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable (name = "member_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private List<User> memberUsers;
	
	@XmlElement
	@XmlElementWrapper
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable (name = "applicant_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private List<User> applicantUsers;

	
	// constructors
	public GroupDetails(){}
	public GroupDetails(Group newGroup, User user) {
		this.name = newGroup.getName();
		this.memberUsers = new ArrayList<User>();
		this.memberUsers.add(user);
	}

	
	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OrderDetails> getOrders() {
		if (orders == null)
			return new ArrayList<OrderDetails>();
		return orders;
	}

	public List<User> getMemberUsers() {
		if (memberUsers == null)
			return new ArrayList<User>();
		return memberUsers;
	}

	public List<User> getApplicantUsers() {
		if (applicantUsers == null)
			return new ArrayList<User>();
		return applicantUsers;
	}
	public void setOrders(List<OrderDetails> orders) {
		this.orders = orders;
	}
	public void setMemberUsers(List<User> memberUsers) {
		this.memberUsers = memberUsers;
	}
	public void setApplicantUsers(List<User> applicantUsers) {
		this.applicantUsers = applicantUsers;
	}
}
