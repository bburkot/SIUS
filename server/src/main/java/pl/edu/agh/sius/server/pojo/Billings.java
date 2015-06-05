package pl.edu.agh.sius.server.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import pl.edu.agh.sius.server.pojo.Payment;
import pl.edu.agh.sius.server.pojo.User;


@Entity
@Table(name="billing",  uniqueConstraints=@UniqueConstraint(
		columnNames = {"user_first_id" , "user_second_id" }, 
		name="UK__user_first_id__and__user_second_id" ))
public class Billings implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private String id;
	

	@Column(columnDefinition="smallmoney", nullable=false)		// balance > 0 second user is in debt to first person
	private BigDecimal balance;									// balance < 0 first user is in debt to secong person
	

	@Column(name="max_debt_to_first_user", columnDefinition="smallmoney")
	private BigDecimal maxSecondUserDebtToFirstUser;
	
	@Column(name="max_debt_to_second_user", columnDefinition="smallmoney")
	private BigDecimal maxFirstUserDebtToSecondUser;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name="user_first_id", nullable=false)
	private User first;
	
	@ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.DETACH)
	@JoinColumn(name="user_second_id", nullable=false)
	private User second;
	
	@OneToMany(mappedBy="billing", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Payment> payments;

	public Billings(){}
	public Billings(User user1, User user2) {
		balance = BigDecimal.ZERO;
		first = user1;
		second = user2;
		payments = new HashSet<Payment>();
	}
	

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Set<Payment> getPayments() {
		if (payments == null)
			return new HashSet<>();
		return payments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	public void addPayment(BigDecimal amount, User whoPaid) {
		Payment payment = new Payment();
		payment.setBilling(this);
		
		if (first.getId().equals(whoPaid.getId()))
			payment.setAmount(amount);
		else 
			payment.setAmount(amount.negate());
		getPayments().add(payment);
	}
}
