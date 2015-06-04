package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="Product")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlID
	@XmlElement	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String name;
	
	@XmlElement
	@Column(name="cost", columnDefinition="smallmoney", nullable=false)
	private BigDecimal cost;
		
	@XmlElement
	@XmlElementWrapper
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable (name = "user_product",
		joinColumns = { @JoinColumn(name="product_id") },
		inverseJoinColumns = { @JoinColumn(name="user_id") }	)
	private List<User> users;
	
	@XmlElement
	@Column(name="max_user_per_product", columnDefinition="smallint")
	private int maxUserPerProduct;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private OrderDetails order;

	
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

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public List<User> getUsers() {
		if (users == null)
			return new ArrayList<User>();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getMaxUserPerProduct() {
		return maxUserPerProduct;
	}

	public void setMaxUserPerProduct(int maxUserPerProduct) {
		this.maxUserPerProduct = maxUserPerProduct;
	}

	public OrderDetails getOrder() {
		return order;
	}

	public void setOrder(OrderDetails order) {
		this.order = order;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
