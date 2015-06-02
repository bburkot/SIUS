package agh.sius.server.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="\"user\"", uniqueConstraints = { @UniqueConstraint(columnNames = "login", name="UK_login" ), @UniqueConstraint(columnNames = "email", name="UK_mail" )} )
public class User implements Serializable { 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private long id;
	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String login;
	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String name;
	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String surname;
	
	@Column(columnDefinition="nvarchar(100)", nullable=false)
	private String email;
	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String password;
	
	@Column(columnDefinition="char(50)")
	private String token;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="users")
	private Set<Group> groups;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable (name = "user_product",
		joinColumns = { @JoinColumn(name="user_id") },
		inverseJoinColumns = { @JoinColumn(name="product_id") }	)
	private List<Product> products;

	
	// constructors
	public User(String login, String name, String surname, String email, String password) {
		this.login = login;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.groups = new HashSet<>();
		this.products = new ArrayList<Product>();
	}
	
	public User(){
		this.groups = new HashSet<>();
		this.products = new ArrayList<Product>();
	}	
	
	// getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
