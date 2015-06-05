package pl.edu.agh.sius.server.pojo.responses;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.User;


@XmlRootElement(name="Balance")
@XmlAccessorType(XmlAccessType.NONE)
public class Balance {
	
	@XmlElement
	private User user;
	
	@XmlElement
	private BigDecimal balance;

	
	public Balance(){}
		
	public Balance(User user, BigDecimal balance) {
		this.user = user;
		this.balance = balance;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
}
