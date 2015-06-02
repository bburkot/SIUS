package agh.sius.server.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.directory.AttributeInUseException;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="\"group\"")
public class Group implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private long id;
	
	@Column(columnDefinition="nvarchar(100)", nullable=false)
	private String name;
		
	@OneToMany(mappedBy="group" ,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<Order> orders;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable (name = "user_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private Set<User> users;

	// util methods
	public Group addUser(User user) throws AttributeInUseException{	
		if (user == null)
			throw new AttributeInUseException("User is equal null");
		users.add(user);
		return this;
	}
	public Group addOrder(Order order) throws AttributeInUseException{		
		if (users.contains(order.getRealizedBy())){
			orders.add(order);
			order.setGroup(this);
			return this;
		}
		throw new AttributeInUseException("User realized this order do not belong to group");
	}
	
	// constructors
	public Group(){
		orders = new HashSet<Order>();
		users = new HashSet<User>();
	}
	public Group(String name) {
		this.name = name;
		orders = new HashSet<Order>();
		users = new HashSet<User>();
	}
	
	// getters and setters 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

