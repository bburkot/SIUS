package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="GroupDetails")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="\"group\"", uniqueConstraints = { @UniqueConstraint(columnNames = "name", name="UK_Name" )})
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
	@OneToMany(mappedBy="groupDetails" ,fetch=FetchType.EAGER, cascade=CascadeType.DETACH)
	private Set<OrderDetails> orders;
	
	@XmlElement
	@XmlElementWrapper
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable (name = "member_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private Set<User> memberUsers;
	
	@XmlElement
	@XmlElementWrapper
	@ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable (name = "applicant_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private Set<User> applicantUsers;

	
	// constructors
	public GroupDetails(){}
	public GroupDetails(Group newGroup, User user) {
		name = newGroup.getName();
		memberUsers = new HashSet<User>();
		memberUsers.add(user);
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

	public Set<OrderDetails> getOrders() {
		if (orders == null)
			return new HashSet<OrderDetails>();
		return orders;
	}

	public Set<User> getMemberUsers() {
		if (memberUsers == null)
			return new HashSet<User>();
		return memberUsers;
	}

	public Set<User> getApplicantUsers() {
		if (applicantUsers == null)
			return new HashSet<User>();
		return applicantUsers;
	}
	public void setOrders(Set<OrderDetails> orders) {
		this.orders = orders;
	}
	public void setMemberUsers(Set<User> memberUsers) {
		this.memberUsers = memberUsers;
	}
	public void setApplicantUsers(Set<User> applicantUsers) {
		this.applicantUsers = applicantUsers;
	}
	@Override
	public String toString() {
		return "GroupDetails [id=" + id + ", name=" + name + ", orders="
				+ orders + ", memberUsers=" + memberUsers + ", applicantUsers="
				+ applicantUsers + "]";
	}
}
