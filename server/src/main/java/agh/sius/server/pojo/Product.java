package agh.sius.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private long id;
	
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String name;
	
	@Column(name="cost", columnDefinition="smallmoney", nullable=false)
	private BigDecimal cost;
	
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	private Order order;
	
	@ManyToMany(mappedBy="products", fetch=FetchType.LAZY)
	private List<User> users;
	
	@Column(name="max_user_per_product", columnDefinition="smallint")
	private int maxUserPerProduct;
}
