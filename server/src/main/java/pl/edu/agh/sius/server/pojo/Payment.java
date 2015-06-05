package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Payment")
@XmlAccessorType(XmlAccessType.NONE)
@Entity
@Table(name="payment")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@XmlID
	@XmlElement
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
	
	@XmlElement
	@Transient
	private User from;
	
	@XmlElement
	@Transient
	private User to;
	
	@XmlElement					// set date when second user confirm it
	@Column(columnDefinition="smalldatetime")
	private Date date;
	
	@XmlElement
	@Column(columnDefinition="smallmoney", nullable=false)		// amount < 0 => second user have paid to first user
	private BigDecimal amount;									// amount > 0 => first user have paid to second user

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="billing_id", nullable=false)
	private Billings billing;

	public Payment(){}
	
	public Payment(User first, User second, BigDecimal amount, Payment p) {
		this.from = first;
		this.to = second;
		this.amount = amount;
		this.date = p.date;
		this.id = p.id;
	}

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Billings getBilling() {
		return billing;
	}

	public void setBilling(Billings billing) {
		this.billing = billing;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
