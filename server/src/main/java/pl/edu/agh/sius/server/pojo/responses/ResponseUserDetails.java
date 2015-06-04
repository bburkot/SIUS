package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.OperationStatus;
import pl.edu.agh.sius.server.pojo.UserDetails;

@XmlRootElement(name="ResponseUserDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponseUserDetails implements Serializable {
	private static final long serialVersionUID = 1L;
	
		
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private UserDetails userDetails;
	
	// constructors
	public ResponseUserDetails(){
		this.operationStatus = OperationStatus.ERROR;
	}
	
	public ResponseUserDetails(UserDetails userDetails) {
		this.operationStatus = OperationStatus.ERROR;
		this.userDetails = userDetails;
	}
	
	public ResponseUserDetails(OperationStatus operationStatus, String msg, UserDetails userDetails) {
		this.operationStatus = operationStatus;
		this.msg = msg;
		this.userDetails = userDetails;
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

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
}
