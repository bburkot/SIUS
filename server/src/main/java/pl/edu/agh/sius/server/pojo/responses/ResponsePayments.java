package pl.edu.agh.sius.server.pojo.responses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.agh.sius.server.pojo.OperationStatus;
import pl.edu.agh.sius.server.pojo.Payment;


@XmlRootElement(name="ResponsePayments")
@XmlAccessorType(XmlAccessType.NONE)
public class ResponsePayments implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@XmlElement
	private OperationStatus operationStatus;
	
	@XmlElement
	private String msg;
	
	@XmlElement
	private List<Payment> payments;	
	
	public ResponsePayments(){
		operationStatus = OperationStatus.ERROR;
	}
	
	public void succeed() {
		operationStatus = OperationStatus.SUCCEED;		
	}
	public void failed(){
		operationStatus = OperationStatus.ERROR;
	}

	// setters and getters
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

	public List<Payment> getGroupDetails() {
		if (payments == null)
			return new ArrayList<Payment>();
		return payments;
	}

	public void setGroupDetails(List<Payment> payments) {
		this.payments = payments;
	}
}
