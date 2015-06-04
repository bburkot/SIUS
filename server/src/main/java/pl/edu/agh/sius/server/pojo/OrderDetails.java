package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="OrderDetails")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="\"order\"")
public class OrderDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@XmlID
	@XmlElement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
	
	@XmlElement
	@Column(columnDefinition="nvarchar(200)", nullable=false)
	private String msg;
	
	@XmlElement 
	@Column(columnDefinition="nvarchar(50)", nullable=false)
	private String title;
	
	@XmlElement		
	@Column(nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private OrderState state;
	
	@XmlElement
	@Column(columnDefinition="smalldatetime", nullable=false)
	private Date date;
	
	@XmlElement
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="realized_by_user_id", nullable=false)
	private User realizedBy;
	
	@XmlElement
	@XmlElementWrapper
	@OneToMany(fetch=FetchType.LAZY, mappedBy="order", cascade=CascadeType.ALL)
	private List<Product> products;

	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="group_id", nullable=false)
	private GroupDetails groupDetails;
	
	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public List<Product> getProducts() {
		if (products == null)
			return new ArrayList<Product>();
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public GroupDetails getGroupDetails() {
		return groupDetails;
	}

	public void setGroupDetails(GroupDetails groupDetails) {
		this.groupDetails = groupDetails;
	}
}
