package agh.sius.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="billing", uniqueConstraints=@UniqueConstraint(columnNames = {"user_first_id" , "user_second_id" } ))
public class Billing implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private long id;
	
	@Column(columnDefinition="smallmoney", nullable=false)		// balance > 0 second user is in debt to first person
	private BigDecimal balance;									// balance < 0 first user is in debt to secong person
	
	@Column(name="max_debt_to_first_user", columnDefinition="smallmoney")
	private BigDecimal maxSecondUserDebtToFirstUser;
	
	@Column(name="max_debt_to_second_user", columnDefinition="smallmoney")
	private BigDecimal maxFirstUserDebtToSecondUser;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_first_id", nullable=false)
	private User first;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_second_id", nullable=false)
	private User second;
	
	@OneToMany(mappedBy="billing", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Payment> payments;

	// util methods
	public void addPayment(Payment payment){
		payments.add(payment);
	}
	
	// contructors
	public Billing(){
		this.payments = new ArrayList<Payment>();
	}
	public Billing(BigDecimal balance, BigDecimal maxSecondUserDebtToFirstUser,
			BigDecimal maxFirstUserDebtToSecondUser, User first, User second) {
		this.balance = balance;
		this.maxSecondUserDebtToFirstUser = maxSecondUserDebtToFirstUser;
		this.maxFirstUserDebtToSecondUser = maxFirstUserDebtToSecondUser;
		this.first = first;
		this.second = second;
		this.payments = new ArrayList<Payment>();
	}
	
	// getters and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getMaxSecondUserDebtToFirstUser() {
		return maxSecondUserDebtToFirstUser;
	}
	public void setMaxSecondUserDebtToFirstUser(
			BigDecimal maxSecondUserDebtToFirstUser) {
		this.maxSecondUserDebtToFirstUser = maxSecondUserDebtToFirstUser;
	}
	public BigDecimal getMaxFirstUserDebtToSecondUser() {
		return maxFirstUserDebtToSecondUser;
	}
	public void setMaxFirstUserDebtToSecondUser(
			BigDecimal maxFirstUserDebtToSecondUser) {
		this.maxFirstUserDebtToSecondUser = maxFirstUserDebtToSecondUser;
	}
	public User getFirst() {
		return first;
	}
	public void setFirst(User first) {
		this.first = first;
	}
	public User getSecond() {
		return second;
	}
	public void setSecond(User second) {
		this.second = second;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payment) {
		this.payments = payment;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
