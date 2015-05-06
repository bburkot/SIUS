package agh.sius.server.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition="int")
	private long id;
	
	@Column(columnDefinition="smalldatetime", nullable=false)
	private Date date;
	
	@Column(columnDefinition="smallmoney", nullable=false)		// amount < 0 => second user have paid to first user
	private BigDecimal amount;									// amount > 0 => first user have paid to second user
	
	@ManyToOne
	@JoinColumn(name="billing_id", nullable=false)
	private Billing billing;
}
