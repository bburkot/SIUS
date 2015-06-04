package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.OperationStatus;
import pl.edu.agh.sius.server.pojo.User;

@XmlRootElement(name="ResponseUser")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseUser implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private User user;
	
	// constructors
	public ResponseUser(){
		this.operationStatus = OperationStatus.ERROR;
	}
	
	public ResponseUser(User user) {
		this.operationStatus = OperationStatus.ERROR;
		this.user = user;
	}
	
	public ResponseUser(OperationStatus operationStatus, String msg, User user) {
		this.operationStatus = operationStatus;
		this.msg = msg;
		this.user = user;
	}

	public void succeed() {
		operationStatus = OperationStatus.SUCCEED;		
	}
	public void failed(){
		operationStatus = OperationStatus.ERROR;
	}
	
	// getters and setters
	public OperationStatus getOperationStatus() {
		return operationStatus;
	}

	public void setOperationStatus(OperationStatus operationStatus) {
		this.operationStatus = operationStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	
}
