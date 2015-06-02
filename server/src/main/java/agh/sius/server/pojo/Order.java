package agh.sius.server.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.directory.AttributeInUseException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import agh.sius.server.pojo.xsd.OrderState;

@Entity
@Table(name="\"order\"")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private long id;
	
	@Column(columnDefinition="nvarchar(200)", nullable=false)
	private String msg;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(nullable=false)
	private OrderState state;
	
	@Column(columnDefinition="smalldatetime", nullable=false)
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="realized_by_user_id", nullable=false)
	private User realizedBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="group_id", nullable=false)
	private Group group;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="order", cascade=CascadeType.ALL)
	private List<Product> products;

	// util methods
	public void addProduct(Product product){
		products.add(product);
	}
	
	// contructors
	public Order(){
		this.products = new ArrayList<Product>();
	}
	public Order(String msg, Date date, User realizedBy) {
		this.msg = msg;
		this.state = OrderState.OPEN;
		this.date = date;
		this.realizedBy = realizedBy;
		this.products = new ArrayList<Product>();
	}

	// getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public OrderState getState() {
		return state;
	}
	public void setState(OrderState state) {
		this.state = state;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getRealizedBy() {
		return realizedBy;
	}
	public void setRealizedBy(User realizedBy) {
		this.realizedBy = realizedBy;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) throws AttributeInUseException {
		if (this.group != null)
			throw new AttributeInUseException("This order belong to another group");
		this.group = group;
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
