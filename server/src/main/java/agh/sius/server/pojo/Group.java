package agh.sius.server.pojo;

import java.io.Serializable;
import java.util.ArrayList;
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
	private List<Order> orders;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable (name = "user_group",
		joinColumns = { @JoinColumn(name="group_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") })
	private Set<User> users;

	// util methods
	public void addUser(User user){	
		users.add(user);
	}
	public void addOrder(Order order){
		orders.add(order);
	}
	
	// constructors
	public Group(){
		orders = new ArrayList<Order>();
		users = new HashSet<User>();
	}
	public Group(String name) {
		this.name = name;
		orders = new ArrayList<Order>();
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
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
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

